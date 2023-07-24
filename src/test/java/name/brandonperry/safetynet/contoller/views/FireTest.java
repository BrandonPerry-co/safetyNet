package name.brandonperry.safetynet.contoller.views;

import name.brandonperry.safetynet.models.MedicalRecord;
import name.brandonperry.safetynet.models.Person;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
}