package name.brandonperry.safetynet.models;

import name.brandonperry.safetynet.contoller.views.PersonInfo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    public void testPersonInfoGetters() {
        String name1 = "John Boyd";
        String address1 = "123 Main St";
        String age1 = "30";
        String email1 = "john@gmail.com";
        List<String> medicationsList = new ArrayList<>();
        medicationsList.add("Food");
        medicationsList.add("Sleep");
        List<String> allergiesList = new ArrayList<>();
        allergiesList.add("Work");
        allergiesList.add("Cats");

        PersonInfo personInfo1 = new PersonInfo(name1, address1, age1, email1, medicationsList, allergiesList, "John", "Boyd");

        assertEquals(name1, personInfo1.getName());
        assertEquals(address1, personInfo1.getAddress());
        assertEquals(age1, personInfo1.getAge());
        assertEquals(email1, personInfo1.getEmail());
        assertEquals(medicationsList, personInfo1.getMedications());
        assertEquals(allergiesList, personInfo1.getAllergies());
        assertEquals("John", personInfo1.getFirstName());
        assertEquals("Boyd", personInfo1.getLastName());

        String name2 = "Jane Boyd";
        String address2 = "456 Park Ave";
        String age2 = "25";
        String email2 = "jane@gmail.com";
        List<String> medications2 = new ArrayList<>();
        List<String> allergies2 = new ArrayList<>();

        PersonInfo personInfo2 = new PersonInfo(name2, address2, age2, email2, medications2, allergies2, "Jane", "Boyd");

        assertEquals(name2, personInfo2.getName());
        assertEquals(address2, personInfo2.getAddress());
        assertEquals(age2, personInfo2.getAge());
        assertEquals(email2, personInfo2.getEmail());
        assertEquals(medications2, personInfo2.getMedications());
        assertEquals(allergies2, personInfo2.getAllergies());
        assertEquals("Jane", personInfo2.getFirstName());
        assertEquals("Boyd", personInfo2.getLastName());
    }

    @Test
    public void testPersonInfoBuilder() {
        String name1 = "John Culver";
        String address1 = "123 Main St";
        String age1 = "30";
        String email1 = "john@gmail.com";
        List<String> medications1 = new ArrayList<>();
        medications1.add("Work");
        medications1.add("Gym");
        List<String> allergies1 = new ArrayList<>();
        allergies1.add("Tv");
        allergies1.add("Books");

        PersonInfo personInfo1 = PersonInfo.builder()
                .name(name1)
                .address(address1)
                .age(age1)
                .email(email1)
                .medications(medications1)
                .allergies(allergies1)
                .firstName("John")
                .lastName("Culver")
                .build();

        assertEquals(name1, personInfo1.getName());
        assertEquals(address1, personInfo1.getAddress());
        assertEquals(age1, personInfo1.getAge());
        assertEquals(email1, personInfo1.getEmail());
        assertEquals(medications1, personInfo1.getMedications());
        assertEquals(allergies1, personInfo1.getAllergies());
        assertEquals("John", personInfo1.getFirstName());
        assertEquals("Culver", personInfo1.getLastName());


        String name2 = "Jane Boyd";
        String address2 = "456 Park Ave";
        String age2 = "25";
        String email2 = "jane@gmail.com";
        List<String> medications2 = new ArrayList<>();
        List<String> allergies2 = new ArrayList<>();

        PersonInfo personInfo2 = PersonInfo.builder()
                .name(name2)
                .address(address2)
                .age(age2)
                .email(email2)
                .medications(medications2)
                .allergies(allergies2)
                .firstName("Jane")
                .lastName("Boyd")
                .build();

        assertEquals(name2, personInfo2.getName());
        assertEquals(address2, personInfo2.getAddress());
        assertEquals(age2, personInfo2.getAge());
        assertEquals(email2, personInfo2.getEmail());
        assertEquals(medications2, personInfo2.getMedications());
        assertEquals(allergies2, personInfo2.getAllergies());
        assertEquals("Jane", personInfo2.getFirstName());
        assertEquals("Boyd", personInfo2.getLastName());
    }
}
