package name.brandonperry.safetynet.contoller.views;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FireTest {

    @Test
    void getStation() {
        Fire fire = new Fire();
        fire.setStation("1");
        assertThat(fire.getStation()).isEqualTo("1");
    }

    @Test
    void testGetName() {
        Fire fire = new Fire();
        fire.setName("John Boyd");
        assertThat(fire.getName()).isEqualTo("John Boyd");
    }

    @Test
    void testGetPhone() {
        Fire fire = new Fire();
        fire.setPhone("2223334444");
        assertThat(fire.getPhone()).isEqualTo("2223334444");
    }

    @Test
    void testGetAge() {
        Fire fire = new Fire();
        fire.setAge("33");
        assertThat(fire.getAge()).isEqualTo("33");
    }

    @Test
    void testGetMedications() {
        Fire fire = new Fire();
        fire.setMedications(Arrays.asList("Motrin", "Benadryl"));
        assertThat(fire.getMedications()).contains("Motrin", "Benadryl");
    }

    @Test
    void testGetAllergies() {
        Fire fire = new Fire();
        fire.setMedications(Arrays.asList("Motrin", "Benadryl"));
        assertThat(fire.getMedications()).contains("Motrin", "Benadryl");
    }

    @Test
    public void testFire() {
        String station = "Station 1";
        String name = "John Boyd";
        String phone = "111-222-3333";
        String age = "30";
        List<String> medications = new ArrayList<>();
        medications.add("Motrin");
        medications.add("Benadryl");
        List<String> allergies = new ArrayList<>();
        allergies.add("Bees");
        allergies.add("Fish");

        Fire fire = new Fire(station, name, phone, age, medications, allergies);

        assertEquals(station, fire.getStation());
        assertEquals(name, fire.getName());
        assertEquals(phone, fire.getPhone());
        assertEquals(age, fire.getAge());
        assertEquals(medications, fire.getMedications());
        assertEquals(allergies, fire.getAllergies());
    }

    @Test
    public void testFireBuilder() {
        String station = "Station 2";
        String name = "Jane Boyd";
        String phone = "444-555-6666";
        String age = "25";
        List<String> medications = new ArrayList<>();
        medications.add("Motrin");
        medications.add("Benadryl");
        List<String> allergies = new ArrayList<>();
        allergies.add("Soda");
        allergies.add("Fish");

        Fire fire = Fire.builder()
                .station(station)
                .name(name)
                .phone(phone)
                .age(age)
                .medications(medications)
                .allergies(allergies)
                .build();

        assertEquals(station, fire.getStation());
        assertEquals(name, fire.getName());
        assertEquals(phone, fire.getPhone());
        assertEquals(age, fire.getAge());
        assertEquals(medications, fire.getMedications());
        assertEquals(allergies, fire.getAllergies());
    }
}