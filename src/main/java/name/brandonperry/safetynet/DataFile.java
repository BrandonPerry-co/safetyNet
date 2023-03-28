package name.brandonperry.safetynet;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import name.brandonperry.safetynet.models.Firestation;
import name.brandonperry.safetynet.models.MedicalRecord;
import name.brandonperry.safetynet.models.Person;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class DataFile {
    private static List<Person> people = new ArrayList<>();
    private static List<Firestation> stations = new ArrayList<>();
    static List<MedicalRecord> medicalRecords = new ArrayList<>();
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
}
