package name.brandonperry.safetynet.models;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonTest {
    @Test
    public void testFirstName() {
        Person person = new Person();
        person.setFirstName("Brandon");
        assertThat(person.getFirstName()).isEqualTo("Brandon");
    }

    @Test
    public void testLastName() {
        Person person = new Person();
        person.setLastName("Williams");
        assertThat(person.getLastName()).isEqualTo("Williams");
    }

    @Test
    public void testAddress() {
        Person person = new Person();
        person.setAddress("1800 W Colonial Dr");
        assertThat(person.getAddress()).isEqualTo("1800 W Colonial Dr");
    }

    @Test
    public void testCity() {
        Person person = new Person();
        person.setCity("Houston");
        assertThat(person.getCity()).isEqualTo("Houston");
    }

    @Test
    public void testZip() {
        Person person = new Person();
        person.setZip("88615");
        assertThat(person.getZip()).isEqualTo("88615");
    }

    @Test
    public void testPhone() {
        Person person = new Person();
        person.setPhone("281-330-8004");
        assertThat(person.getPhone()).isEqualTo("281-330-8004");
    }

    @Test
    public void testEmail() {
        Person person = new Person();
        person.setEmail("pie@gmail.net");
        assertThat(person.getEmail()).isEqualTo("pie@gmail.net");
    }

    @Test
    public void testBuilder() {
        Person person = Person.builder().firstName("Brandon").build();
        assertThat(person.getFirstName()).isEqualTo("Brandon");
    }

    @Test
    public void testAddPerson() {
        Person person = Person.builder().firstName("Brandon").build();
        assertThat(person.getFirstName()).isEqualTo("Brandon");
    }
}
