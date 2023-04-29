package name.brandonperry.safetynet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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
}
