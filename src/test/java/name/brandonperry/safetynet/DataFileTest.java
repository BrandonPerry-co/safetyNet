package name.brandonperry.safetynet;

import lombok.extern.slf4j.Slf4j;
import name.brandonperry.safetynet.models.Firestation;
import name.brandonperry.safetynet.models.MedicalRecord;
import name.brandonperry.safetynet.models.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DataFile.class})
@Slf4j
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
    public void testGetStations() throws IOException {
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
    @DirtiesContext
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

    @Test
    public void testCommunityEmail() throws IOException {
        List<String> getAllEmails = dataFile.getCommunityEmails("Culver");
        assertThat(getAllEmails).isNotNull();
        System.out.println(getAllEmails);

    }

    @Test
    public void testGetPhoneAlerts() throws IOException {
        List<String> getAllPhoneAlerts = dataFile.getPhoneAlerts("2");
        assertThat(getAllPhoneAlerts).isNotNull();
        log.debug(getAllPhoneAlerts.toString());
    }

    @Test
    public void testGetChildAlerts() throws IOException {
        List<String> getAllChildAlerts = dataFile.getChildAlerts("1509 Culver St");
        assertThat(getAllChildAlerts).isNotNull();
        System.out.println(getAllChildAlerts);
        log.debug(getAllChildAlerts.toString());
    }

    @Test
    public void testGetPerson() throws IOException {
        Person getPerson = dataFile.getPerson("John", "Boyd");
        assertThat(getPerson).isNotNull();
        log.debug(getPerson.toString());
    }

    @Test
    public void testGetServicingStation() throws IOException {
        List<String> getServicingStation = dataFile.getServicingStation("1509 Culver St");
        assertThat(getServicingStation).isNotNull();
        System.out.println(getServicingStation);
        log.debug(getServicingStation.toString());
    }

    @Test
    public void testGetServicedArea() throws IOException {
        List<Person> getServicingArea = dataFile.getServicedArea("1");
        assertThat(getServicingArea).isNotNull();
        System.out.println(getServicingArea);
        log.debug(getServicingArea.toString());
    }

    @Test
    public void testGetEveryoneServicedByStation() throws IOException {
        List<String> getListOfAllStations = dataFile.getEveryoneServicedByStation("1");
        assertThat(getListOfAllStations).isNotNull();
        System.out.println(getListOfAllStations);
        log.debug(getListOfAllStations.toString());
    }

    @Test
    public void testGetMeds() throws IOException {
        MedicalRecord getMedical = dataFile.getMedicalRecords("John", "Boyd");
        assertThat(getMedical).isNotNull();
        log.debug(getMedical.toString());
    }


    @Test
    public void testGetAddress() throws IOException {
        Person getPerson = dataFile.getAddress("1509 Culver St");
        assertThat(getPerson).isNotNull();
        log.debug(getPerson.toString());
    }

    @Test
    public void testGetFireAddress() throws IOException {
        List<Person> allPeopleList = dataFile.getFireAddress("1509 Culver St");
        assertThat(allPeopleList).isNotNull();
        log.debug(allPeopleList.toString());
    }

    @Test
    public void testGetFireStationNumber1() throws IOException {
        Firestation station = new Firestation();
        station.setAddress("1509 Culver St");
        station.setStation("3");
        dataFile.getStation().add(station);

        String fireStationNumber = dataFile.getFireStationNumber("1509 Culver St");
        assertThat(fireStationNumber).isEqualTo("3");

        fireStationNumber = dataFile.getFireStationNumber("4422 N Step St");
        assertThat(fireStationNumber).isNull();
    }


}
