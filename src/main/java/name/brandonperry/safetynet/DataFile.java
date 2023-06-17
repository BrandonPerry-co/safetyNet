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
import java.util.ArrayList;
import java.util.List;

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
}
