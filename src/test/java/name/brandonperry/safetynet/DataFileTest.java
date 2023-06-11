package name.brandonperry.safetynet;

import name.brandonperry.safetynet.models.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DataFile.class})
public class DataFileTest {
    @Autowired
    private DataFile dataFile;

    @Test
    public void testLoadMedicalRecords() throws IOException {
        // TODO Find out how many people are in the DATA FILE
        assertThat(dataFile.getRecords().size()).isEqualTo(23);
        System.out.println(dataFile.getRecords());
    }

    @Test
    public void testLoadPeople() throws IOException {
        assertThat(dataFile.getPeople().size()).isEqualTo(23);
        System.out.println(dataFile.getPeople());
    }

    @Test
    public void testLoadStations() throws IOException {
        assertThat(dataFile.getStation().size()).isEqualTo(13);
        System.out.println(dataFile.getStation());
    }

    @Test
    public void testUpdatePerson() throws IOException {
        List<Person> testPeople = dataFile.getPeople();
        Person expected = testPeople.stream().filter(p -> "John"
                        .equals(p.getFirstName()) && "Boyd"
                        .equals(p.getLastName()))
                .findFirst()
                .orElse(null);
        expected.setCity("Dallas");
        Person actual = dataFile.updatePerson("John+Boyd", expected);
        assertThat(actual).isEqualTo(expected);
        System.out.println(dataFile.getPeople());
    }

    @Test
    public void testDeletePerson() throws IOException {
        Person deletedPerson = dataFile.deletePerson("John+Boyd");
        assertThat(deletedPerson.getFirstName()).isEqualTo("John");
        assertThat(deletedPerson.getLastName()).isEqualTo("Boyd");
        List<Person> testPeople = dataFile.getPeople();
        Person deleteThisPerson = testPeople.stream()
                .filter(p -> "John".equals(p.getFirstName()) && "Boyd".equals(p.getLastName()))
                .findFirst()
                .orElse(null);
        assertThat(deleteThisPerson).isNull();
    }

}
