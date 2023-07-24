package name.brandonperry.safetynet.models;

import name.brandonperry.safetynet.contoller.views.FloodStationsPersonInfo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

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
}
