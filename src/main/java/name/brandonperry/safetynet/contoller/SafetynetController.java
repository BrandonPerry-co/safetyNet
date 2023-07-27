package name.brandonperry.safetynet.contoller;

import name.brandonperry.safetynet.DataFile;
import name.brandonperry.safetynet.contoller.views.ChildAlert;
import name.brandonperry.safetynet.contoller.views.Fire;
import name.brandonperry.safetynet.contoller.views.FloodStationsPersonInfo;
import name.brandonperry.safetynet.contoller.views.PersonInfo;
import name.brandonperry.safetynet.models.MedicalRecord;
import name.brandonperry.safetynet.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Import(Person.class)
public class SafetynetController {
    Logger logger = LoggerFactory.getLogger(PersonController.class);
    @Autowired
    private DataFile dataFile;
    private Person person;

    @GetMapping("/communityEmail")
    public List<String> getCommunityEmails(@RequestParam("city") String city) {
        List<String> emails = dataFile.getCommunityEmails(city);
        logger.info("Here's all the emails for this area! ", emails);
        return emails;
    }

    @GetMapping("/phoneAlert")
    public List<String> getPhoneAlerts(@RequestParam("fireStation_number") String fireStation_number) {
        List<String> phoneAlertList = dataFile.getPhoneAlerts(fireStation_number);
        logger.info("Here's all the phone numbers for this area! ", phoneAlertList);
        return phoneAlertList;
    }

    @GetMapping("/personInfo")
    public PersonInfo getPersonInfo(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Person person = dataFile.getPerson(firstName, lastName);
        MedicalRecord medicalRecord = dataFile.getPersonRecords(firstName, lastName);
        LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        int age = calculateAge(birthDate);
        String ageString = String.valueOf(age);
        logger.info("The person found is ", person);
        PersonInfo personInfo = PersonInfo.builder() //
                .name(person.getFirstName() + " " + person.getLastName()) //
                .address(person.getAddress()) //
                .age(ageString) //
                .email(person.getEmail()) //
                .medications(medicalRecord.getMedications()) //
                .allergies(medicalRecord.getAllergies()) //
                .build();
        return personInfo;
    }

    private int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    @GetMapping("/childAlert")
    public List<ChildAlert> getChildAlert(@RequestParam("address") String address) {
        DataFile dataFile = new DataFile();
        List<Person> peopleAtAddress = dataFile.getPeople().stream()
                .filter(person -> person.getAddress().equalsIgnoreCase(address))
                .collect(Collectors.toList());

        List<ChildAlert> childAlertList = new ArrayList<>();

        boolean hasChildren = false;

        for (Person person : peopleAtAddress) {
            LocalDate birthdate = getBirthdateForPerson(person, dataFile);
            if (birthdate != null) {
                int age = getResidentAge(birthdate);
                if (age < 18) {
                    String ageString = String.valueOf(age);
                    ChildAlert childAlert = new ChildAlert(person.getFirstName(), person.getLastName(), ageString);
                    childAlertList.add(childAlert);
                    hasChildren = true;
                } else {
                    // Add other people (above 18) directly to the ChildAlert list
                    ChildAlert otherPerson = new ChildAlert(person.getFirstName(), person.getLastName(), "Adult");
                    childAlertList.add(otherPerson);
                }
            }
        }

        // Check if any children were found, if not, return an empty list
        if (!hasChildren) {
            return new ArrayList<>();
        }

        return childAlertList;
    }

    private LocalDate getBirthdateForPerson(Person person, DataFile dataFile) {
        MedicalRecord medicalRecord = dataFile.getRecords().stream()
                .filter(record -> record.getFirstName().equalsIgnoreCase(person.getFirstName())
                        && record.getLastName().equalsIgnoreCase(person.getLastName()))
                .findFirst().orElse(null);

        if (medicalRecord != null) {
            return LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        }
        return null;
    }

    private int getResidentAge(LocalDate birthdate) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(birthdate, now);
        return period.getYears();
    }


    @GetMapping("/fire")
    public List<Fire> getFireStationAndPeople(@RequestParam("address") String address) {
        List<Fire> fireList = new ArrayList<>();
        List<Person> personList = dataFile.getFireAddress(address);

        // Get the fire station number that services the provided address
        String stationNumber = getFireStationNumber(address);

        for (Person person : personList) {
            MedicalRecord medicalRecord = dataFile.getPersonRecords(person.getFirstName(), person.getLastName());
            LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            int age = fireAge(birthDate);
            String ageString = String.valueOf(age);

            Fire fire = Fire.builder()
                    .station(stationNumber)
                    .name(person.getFirstName() + " " + person.getLastName())
                    .phone(person.getPhone())
                    .age(ageString)
                    .medications(medicalRecord.getMedications())
                    .allergies(medicalRecord.getAllergies())
                    .build();

            fireList.add(fire);
        }

        return fireList;
    }

    private String getFireStationNumber(String address) {
        return dataFile.getFireStationNumber(address);
    }

    private int fireAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    @GetMapping("/flood/stations")
    public ResponseEntity<List<FloodStationsPersonInfo>> getFloodStationsInfo(@RequestParam("stations") List<String> stationNumbers) {
        List<FloodStationsPersonInfo> result = stationNumbers.stream()
                .flatMap(stationNumber -> dataFile.getServicedArea(stationNumber).stream())
                .collect(Collectors.groupingBy(Person::getAddress))
                .values()
                .stream()
                .map(this::createFloodStationsPersonInfo)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    private FloodStationsPersonInfo createFloodStationsPersonInfo(List<Person> people) {
        if (people.isEmpty()) {
            return null; // Or handle the case when no people are found in the serviced area
        }

        Person firstPerson = people.get(0);
        MedicalRecord medicalRecord = dataFile.getMedicalRecords(firstPerson.getFirstName(), firstPerson.getLastName());

        if (medicalRecord == null) {
            return null; // Or handle the case when no medical record is found for the person
        }

        LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        int age = getResAge(birthDate);
        String ageString = String.valueOf(age);

        return FloodStationsPersonInfo.builder()
                .name(firstPerson.getFirstName() + " " + firstPerson.getLastName())
                .phone(firstPerson.getPhone())
                .birthdate(medicalRecord.getBirthdate())
                .age(ageString)
                .medications(medicalRecord.getMedications())
                .allergies(medicalRecord.getAllergies())
                .build();
    }

    private int getResAge(LocalDate birthdate) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(birthdate, now);
        return period.getYears();
    }
}
