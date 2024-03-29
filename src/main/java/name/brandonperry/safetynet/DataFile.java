package name.brandonperry.safetynet;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import name.brandonperry.safetynet.models.Firestation;
import name.brandonperry.safetynet.models.MedicalRecord;
import name.brandonperry.safetynet.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DataFile {

    private static Logger logger = LoggerFactory.getLogger(DataFile.class);
    private List<Person> people = new ArrayList<>();
    private List<Firestation> stations = new ArrayList<>();
    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    @Autowired
    public DataFile() {
        try {
            load();
        } catch (IOException e) {
            logger.error("Error occurred parsing datafile: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void load() throws IOException {
        var filePath = "src/main/resources/data.json";
        var bytesFile = Files.readAllBytes(new File(filePath).toPath());
        var iter = JsonIterator.parse(bytesFile);
        var any = iter.readAny();
        var personAny = any.get("persons");
        var stationAny = any.get("firestations");
        var medicalRecordsAny = any.get("medicalrecords");

        personAny.forEach(p -> people.add( //
                Person.builder() //
                        .firstName(p.get("firstName").toString()) //
                        .lastName(p.get("lastName").toString()) //
                        .address(p.get("address").toString()) //
                        .city(p.get("city").toString()) //
                        .zip(p.get("zip").toString()) //
                        .phone(p.get("phone").toString()) //
                        .email(p.get("email").toString()) //
                        .build() //
        ));

        stationAny.forEach(p -> stations.add( //
                Firestation.builder() //
                        .address(p.get("address").toString()) //
                        .station(p.get("station").toString()) //
                        .build() //
        ));

        medicalRecordsAny.forEach(p -> medicalRecords.add( //
                MedicalRecord.builder() //
                        .firstName(p.get("firstName").toString()) //
                        .lastName(p.get("lastName").toString()) //
                        .birthdate(p.get("birthdate").toString()) //
                        .medications(getMedicalRecordItems(p, "medications")) //
                        .allergies(getAllergyItems(p, "allergies")) //
                        .build() //
        ));
    }

    private List<String> getMedicalRecordItems(Any medicalRecordAny, String key) {
        var items = new ArrayList<String>();

        medicalRecordAny.get(key).forEach(itemAny -> items.add(itemAny.toString()));
        return items;
    }

    private List<String> getAllergyItems(Any allergyAny, String key) {
        var items = new ArrayList<String>();

        allergyAny.get(key).forEach(itemAny -> items.add(itemAny.toString()));
        return items;
    }

    public List<Person> getPeople() {
        return people;
    }

    public List<Firestation> getStation() {
        return stations;
    }

    public List<MedicalRecord> getRecords() {
        return medicalRecords;
    }

    public Person updatePerson(String id, Person person) {
        String[] names = id.split("\\+");
        Person foundPerson = people.stream()
                .filter(p -> names[0].equals(p.getFirstName()) && names[1].equals(p.getLastName()))
                .findAny()
                .orElse(null);
        foundPerson.setAddress(person.getAddress());
        foundPerson.setCity(person.getCity());
        foundPerson.setPhone(person.getPhone());
        foundPerson.setZip(person.getZip());
        foundPerson.setEmail(person.getEmail());
        // TODO: 5/14/2023 set other all other fields except firstName and LastName -Done
        return foundPerson;
    }

    public Person deletePerson(String id) {
        logger.debug("Deleting person!");
        String[] names = id.split("\\+");
        Person removedPerson = people.stream()
                .filter(p -> names[0].equals(p.getFirstName()) && names[1].equals(p.getLastName()))
                .findAny()
                .orElse(null);
        people.remove(removedPerson);

        return removedPerson;
    }

    public Firestation updateStation(String id, Firestation firestation) {
        logger.debug("Updating station!");
        String[] station = id.split("\\+");
        Firestation foundStation = stations.stream()
                .filter(p -> station[0].equals(p.getAddress()) && station[1].equals(p.getStation()))
                .findFirst()
                .orElse(null);

        foundStation.setAddress(firestation.getAddress());
        foundStation.setStation(firestation.getStation());

        return foundStation;
    }

    public Firestation deleteStation(String id) {
        logger.debug("Deleting station!");
        String[] names = id.split("\\+");
        Firestation removedStation = stations.stream()
                .filter(p -> names[0].equals(p.getAddress()) && names[1].equals(p.getStation()))
                .findFirst()
                .orElse(null);

        stations.remove(removedStation);

        return removedStation;
    }

    public MedicalRecord updateMedicalRecord(String id, MedicalRecord medicalRecord) {
        logger.debug("Updating medical record!");
        String[] names = id.split("\\+");
        MedicalRecord foundMedicalRecord = medicalRecords.stream()
                .filter(p -> names[0].equals(p.getFirstName()) && names[1].equals(p.getLastName()))
                .findFirst()
                .orElse(null);

        foundMedicalRecord.setBirthdate(medicalRecord.getBirthdate());
        foundMedicalRecord.setMedications(medicalRecord.getMedications());
        foundMedicalRecord.setAllergies(medicalRecord.getAllergies());

        return foundMedicalRecord;
    }

    public MedicalRecord deleteMedicalRecord(String id) {
        logger.debug("Deleting medical record!!");
        String[] names = id.split("\\+");
        MedicalRecord removedMedicalRecord = medicalRecords.stream()
                .filter(p -> names[0].equals(p.getFirstName()) && names[1].equals(p.getLastName()))
                .findFirst()
                .orElse(null);

        medicalRecords.remove(removedMedicalRecord);

        return removedMedicalRecord;
    }

    //http://localhost:8080/communityEmail?city=<city>
    public List<String> getCommunityEmails(String city) {
        List<String> list = people.stream()
                .filter(p -> city.equals(p.getCity()))
                .map(p -> p.getEmail())
                .collect(Collectors.toList());
        return list;
    }

    public List<String> getPhoneAlerts(String fireStation_number) {
        List<String> alerts = stations.stream()
                .filter(p -> fireStation_number.equals(p.getStation()))
                .map(p -> p.getAddress())
                .collect(Collectors.toList());

        List<String> phoneAlertList = people.stream()
                .filter(p -> alerts.stream().anyMatch(a -> a.equals(p.getAddress())))
                .map(p -> p.getPhone())
                .collect(Collectors.toList());

        return phoneAlertList;
    }


    public List<String> getServicingStation(String address) {
        List<String> peopleByAddress = people.stream()
                .filter(p -> address.equals(p.getAddress()))
                .map(p -> p.getFirstName() + " " + p.getLastName())
                .collect(Collectors.toList());

        List<String> getMedicalInfo = medicalRecords.stream()
                .filter(p -> peopleByAddress.stream().anyMatch(a -> a.equals(p.getFirstName() + " " + p.getLastName())))
                .map(p -> "Name: " + p.getFirstName() + " " + p.getLastName() + " " + "DOB: " + p.getBirthdate() + " " + "Medications: " + p.getMedications() + " " + "Allergies: " + p.getAllergies())
                .collect(Collectors.toList());
        return getMedicalInfo;
    }

    //    //http://localhost:8080/firestation?stationNumber=<station_number>
    public List<Person> getServicedArea(String stationNumber) {
        List<String> area = stations.stream()
                .filter(p -> stationNumber.equals(p.getStation()))
                .map(p -> p.getAddress())
                .collect(Collectors.toList());

        List<Person> residents = people.stream()
                .filter(p -> area.stream().anyMatch(a -> a.equals(p.getAddress())))
                .collect(Collectors.toList());

        return residents;
    }


    //http://localhost:8080/flood/stations?stations=<a list of station_numbers>
    public List<String> getEveryoneServicedByStation(String stationNumber) {
        List<String> addresses = stations.stream()
                .filter(p -> stationNumber.equals(p.getStation()))
                .map(p -> p.getAddress())
                .collect(Collectors.toList());

        List<String> residents = people.stream()
                .filter(p -> addresses.stream().anyMatch(a -> a.equals(p.getAddress())))
                .map(p -> "Address: " + p.getAddress() + " " + "Name: " + p.getFirstName() + " " + p.getLastName() + " " + "Phone: " + p.getPhone())
                .collect(Collectors.toList());

        List<String> medicalInfo = medicalRecords.stream()
                .filter(p -> residents.stream().anyMatch(a -> a.equals(p.getFirstName() + p.getLastName())))
                .map(p -> "Medications: " + p.getMedications() + " " + "Allergy: " + p.getAllergies())
                .collect(Collectors.toList());
/**
 * Add Medical and allergy for each person
 */

        ArrayList<String> mergedList3 = new ArrayList<>();
        mergedList3.addAll(residents);
        mergedList3.addAll(medicalInfo);
        return medicalInfo;
    }

    public Person getPerson(String firstName, String lastName) {
        return people.stream()
                .filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
    }

    public MedicalRecord getMedicalRecords(String firstName, String lastName) {
        return medicalRecords.stream()
                .filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
    }

    public Person getAddress(String address) {
        return people.stream()
                .filter(p -> p.getAddress().equals(address))
                .findAny()
                .orElse(null);
    }

    public MedicalRecord getPersonRecords(String firstName, String lastName) {
        return medicalRecords.stream()
                .filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
    }

    public MedicalRecord getChildAge(String firstName, String lastName) {
        return medicalRecords.stream()
                .filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
    }

    public MedicalRecord getAge(String firstName, String lastName) {
        return medicalRecords.stream()
                .filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
    }

    public List<Person> getFireAddress(String address) {
        List<String> fireAddress = people.stream()
                .filter(p -> address.equals(p.getAddress()))
                .map(p -> p.getAddress())
                .collect(Collectors.toList());

        List<Person> res = people.stream()
                .filter(p -> fireAddress.stream().anyMatch(a -> a.equals(p.getAddress())))
                .collect(Collectors.toList());

        return res;
    }

    public String getFireStationNumber(String address) {
        List<String> stationNumbers = stations.stream()
                .filter(firestation -> address.equals(firestation.getAddress()))
                .map(p -> p.getStation())
                .collect(Collectors.toList());

        // Assuming each address is served by only one fire station
        if (!stationNumbers.isEmpty()) {
            return stationNumbers.get(0);
        }

        // Return null or a default value if address is not found in the firestations data
        return null;
    }


    public List<String> getChildAlerts(String address) {
        List<String> getPeopleByAddress = people.stream()
                .filter(p -> address.equals(p.getAddress()))
                .map(p -> p.getFirstName() + " " + p.getLastName())
                .collect(Collectors.toList());

        List<String> childAlertList = medicalRecords.stream()
                .filter(p -> getPeopleByAddress.stream().anyMatch(a -> a.equals(p.getFirstName() + " " + p.getLastName())))
                .map(p -> {
                    LocalDate birthdate = LocalDate.parse(p.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                    int age = calculateAge(birthdate);
                    return age < 18 ? age + " years - " + p.getFirstName() + " " + p.getLastName() : "Adult - " + p.getFirstName() + " " + p.getLastName();
                })
                .collect(Collectors.toList());

        return childAlertList;
    }

    private int calculateAge(LocalDate birthdate) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(birthdate, now);
        return period.getYears();
    }

}
