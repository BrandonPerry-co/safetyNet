package name.brandonperry.safetynet.models;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static name.brandonperry.safetynet.DataFile.getPeople;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.ARRAY;

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
    public void testBuilder() {
        Person person = Person.builder().firstName("Brandon").build();
        assertThat(person.getFirstName()).isEqualTo("Brandon");
    }
}
