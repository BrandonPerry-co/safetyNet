package name.brandonperry.safetynet.models;

import name.brandonperry.safetynet.contoller.views.FloodStationsPersonInfo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FloodStationTest {

    @Test
    void getName() {
        FloodStationsPersonInfo floodstations = new FloodStationsPersonInfo();
        floodstations.setName("John Boyd");
        assertThat(floodstations.getName()).isEqualTo("John Boyd");
    }

    @Test
    void testGetAddress() {
        FloodStationsPersonInfo floodstations = new FloodStationsPersonInfo();
        floodstations.setPhone("180-800-2656");
        assertThat(floodstations.getPhone()).isEqualTo("180-800-2656");
    }

    @Test
    void testGetAge() {
        FloodStationsPersonInfo floodstations = new FloodStationsPersonInfo();
        floodstations.setAge("38");
        assertThat(floodstations.getAge()).isEqualTo("38");
    }

    @Test
    void testGetEmail() {
        FloodStationsPersonInfo floodstations = new FloodStationsPersonInfo();
        floodstations.setBirthdate("12/15/2003");
        assertThat(floodstations.getBirthdate()).isEqualTo("12/15/2003");
    }

    @Test
    void testGetMedications() {
        FloodStationsPersonInfo floodstations = new FloodStationsPersonInfo();
        floodstations.setMedications(Arrays.asList("Motrin", "Benadryl"));
        assertThat(floodstations.getMedications()).contains("Motrin", "Benadryl");
    }

    @Test
    void testGetAllergies() {
        FloodStationsPersonInfo floodstations = new FloodStationsPersonInfo();
        floodstations.setAllergies(Arrays.asList("nillacilan"));
        assertThat(floodstations.getAllergies()).contains("nillacilan");
    }

    @Test
    public void testFloodStationsPersonInfoGetters() {
        String name = "John Boyd";
        String phone = "111-222-3333";
        String birthdate = "1990-01-01";
        String age = "30";
        List<String> medications = new ArrayList<>();
        medications.add("Motrin");
        medications.add("Fish oil");
        List<String> allergies = new ArrayList<>();
        allergies.add("Soda");
        allergies.add("Wifi");

        FloodStationsPersonInfo personInfo = new FloodStationsPersonInfo(name, phone, birthdate, age, medications, allergies);

        assertEquals(name, personInfo.getName());
        assertEquals(phone, personInfo.getPhone());
        assertEquals(birthdate, personInfo.getBirthdate());
        assertEquals(age, personInfo.getAge());
        assertEquals(medications, personInfo.getMedications());
        assertEquals(allergies, personInfo.getAllergies());
    }

    @Test
    public void testFloodStationsPersonInfo() {
        String name = "Jane Boyd";
        String phone = "444-555-6666";
        String birthdate = "1995-05-05";
        String age = "26";
        List<String> medications = new ArrayList<>();
        medications.add("vodka");
        medications.add("Sleep");
        List<String> allergies = new ArrayList<>();
        allergies.add("Dogs");
        allergies.add("Sunlight");

        FloodStationsPersonInfo personInfo = FloodStationsPersonInfo.builder()
                .name(name)
                .phone(phone)
                .birthdate(birthdate)
                .age(age)
                .medications(medications)
                .allergies(allergies)
                .build();

        assertEquals(name, personInfo.getName());
        assertEquals(phone, personInfo.getPhone());
        assertEquals(birthdate, personInfo.getBirthdate());
        assertEquals(age, personInfo.getAge());
        assertEquals(medications, personInfo.getMedications());
        assertEquals(allergies, personInfo.getAllergies());
    }
}
