package name.brandonperry.safetynet;

import name.brandonperry.safetynet.models.Person;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DataFileTest {
    //    @BeforeAll
//    static void setupDataFile() throws IOException {
//        DataFile.load();
//    }
    @Test
    public void testLoadMedicalRecords() throws IOException {
        // TODO Find out how many people are in the DATA FILE
        assertThat(DataFile.getRecords().size()).isEqualTo(23);
        System.out.println(DataFile.getRecords());
    }

    @Test
    public void testLoadPeople() throws IOException {
        assertThat(DataFile.getPeople().size()).isEqualTo(23);
        System.out.println(DataFile.getPeople());
    }

    @Test
    public void testLoadStations() throws IOException {
        assertThat(DataFile.getStation().size()).isEqualTo(13);
        System.out.println(DataFile.getStation());
    }

    @Test
    public void testUpdatePerson() throws IOException {
        List<Person> testPeople = DataFile.getPeople();
        Person expected = testPeople.stream().filter(p -> "John"
                        .equals(p.getFirstName()) && "Boyd"
                        .equals(p.getLastName()))
                .findFirst()
                .orElse(null);
        expected.setCity("Dallas");
        Person actual = DataFile.updatePerson("John+Boyd", expected);
        assertThat(actual).isEqualTo(expected);
        System.out.println(DataFile.getPeople());
    }
//
    @Test
    public void testDeletePerson() throws IOException {
//        List<Person> testPeople = DataFile.getPeople();
//        Person deleteThisPerson = testPeople.stream()
//                .filter(p -> "John".equals(p.getFirstName()) && "Boyd".equals(p.getLastName()))
//                .findFirst()
//                .orElse(null);
//
//        System.out.println(deleteThisPerson);
        Person deletedPerson = DataFile.deletePerson("John+Boyd");
        assertThat(deletedPerson.getFirstName()).isEqualTo("John");
        assertThat(deletedPerson.getLastName()).isEqualTo("Boyd");
        List<Person> testPeople = DataFile.getPeople();
        Person deleteThisPerson = testPeople.stream()
                .filter(p -> "John".equals(p.getFirstName()) && "Boyd".equals(p.getLastName()))
                .findFirst()
                .orElse(null);
        assertThat(deleteThisPerson).isNull();
    }

}
