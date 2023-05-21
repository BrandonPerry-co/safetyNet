package name.brandonperry.safetynet;

import com.jsoniter.JsonIterator;
import name.brandonperry.safetynet.contoller.PersonContoller;
import name.brandonperry.safetynet.models.Firestation;
import name.brandonperry.safetynet.models.MedicalRecord;
import name.brandonperry.safetynet.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class DataFile {

    private static Logger logger = LoggerFactory.getLogger(DataFile.class);
    private static List<Person> people = new ArrayList<>();
    private static List<Firestation> stations = new ArrayList<>();
    private static List<MedicalRecord> medicalRecords = new ArrayList<>();

    static {
        try {
            load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void load() throws IOException {
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
                        .medications(p.get("medications").toString()) //
                        .allergies(p.get("allergies").toString()) //
                        .build() //
        ));
    }

    public static List<Person> getPeople() {
        return people;
    }

    public static List<Firestation> getStation() {
        return stations;
    }

    public static List<MedicalRecord> getRecords() {
        return medicalRecords;
    }

    public static Person updatePerson(String id, Person person) {
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

    public static Person deletePerson(String id) {
        logger.debug("Deleting person!");
        String[] names = id.split("\\+");
        Person removedPerson = people.stream()
                .filter(p -> names[0].equals(p.getFirstName()) && names[1].equals(p.getLastName()))
                .findAny()
                .orElse(null);
        people.remove(removedPerson);

        return removedPerson;
    }
}
