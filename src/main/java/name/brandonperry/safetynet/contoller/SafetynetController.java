package name.brandonperry.safetynet.contoller;

import name.brandonperry.safetynet.DataFile;
import name.brandonperry.safetynet.contoller.views.*;
import name.brandonperry.safetynet.models.Firestation;
import name.brandonperry.safetynet.models.MedicalRecord;
import name.brandonperry.safetynet.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    @GetMapping("/childAlert")
    public ChildAlert childAlertList(@RequestParam("address") String address) {
        Person person = dataFile.getAddress(address);
        MedicalRecord medicalRecord = dataFile.getPersonRecords(person.getFirstName(), person.getLastName());
        LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        int age = calculateChildAge(birthDate);
        String ageString = String.valueOf(age);
        logger.info("Here's a list of children and any adults at this location- ", person);
        ChildAlert childAlert = ChildAlert.builder() //
                .firstName(person.getFirstName()) //
                .lastName(person.getLastName()) //
                .age(ageString) //
                .build();
        return childAlert;
    }
    private int calculateChildAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
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

        @GetMapping("/flood/stations")
    public FloodStationsPersonInfo getFloodStations(@RequestParam("stations") String stations) {
        Firestation firestation = dataFile.getStation()
                .stream()
                .filter(p-> stations.equals(p.getStation()))
                .findFirst()
                .orElse(null);
        MedicalRecord medicalRecord = dataFile.getPersonRecords(person.getFirstName(), person.getLastName());
        LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        int age = getAge(birthDate);
        String ageString = String.valueOf(age);
        logger.info("The person found is ", person);
        FloodStationsPersonInfo floodStations = FloodStationsPersonInfo.builder() //
                .name(person.getFirstName() + " " + person.getLastName()) //
                .age(ageString) //
                .medications(medicalRecord.getMedications()) //
                .allergies(medicalRecord.getAllergies()) //
                .build(); //
        return floodStations;
    }
    private int getAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
}
// datafile
//    public Person updatePerson(String id, Person person) {
//        String[] names = id.split("\\+");
//        Person foundPerson = people.stream()
//                .filter(p -> names[0].equals(p.getFirstName()) && names[1].equals(p.getLastName()))
//                .findAny()
//                .orElse(null);
//        foundPerson.setAddress(person.getAddress());
//        foundPerson.setCity(person.getCity());
//        foundPerson.setPhone(person.getPhone());
//        foundPerson.setZip(person.getZip());
//        foundPerson.setEmail(person.getEmail());
//        // TODO: 5/14/2023 set other all other fields except firstName and LastName -Done
//        return foundPerson;
//    }
//Person conrtoller
//@PutMapping("/person/{id}")
//public Person updatePerson(@PathVariable("id") String id, @RequestBody Person person) {
//    Person foundPerson = dataFile.updatePerson(id, person);
//    logger.info("Successfully located ", foundPerson);
//    return foundPerson;
//}