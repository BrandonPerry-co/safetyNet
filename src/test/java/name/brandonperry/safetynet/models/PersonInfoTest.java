package name.brandonperry.safetynet.models;

import name.brandonperry.safetynet.contoller.views.PersonInfo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class PersonInfoTest {

    @Test
    void getName() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName("John Boyd");
        assertThat(personInfo.getName()).isEqualTo("John Boyd");
    }

    @Test
    void testGetAddress() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setAddress("1800 W Colonial Dr");
        assertThat(personInfo.getAddress()).isEqualTo("1800 W Colonial Dr");
    }

    @Test
    void testGetAge() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setAge("38");
        assertThat(personInfo.getAge()).isEqualTo("38");
    }

    @Test
    void testGetEmail() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setEmail("pie@gmail.net");
        assertThat(personInfo.getEmail()).isEqualTo("pie@gmail.net");
    }

    @Test
    void testGetMedications() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setMedications(Arrays.asList("Motrin", "Benadryl"));
        assertThat(personInfo.getMedications()).contains("Motrin", "Benadryl");
    }

    @Test
    void testGetAllergies() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setAllergies(Arrays.asList("nillacilan"));
        assertThat(personInfo.getAllergies()).contains("nillacilan");
    }
}