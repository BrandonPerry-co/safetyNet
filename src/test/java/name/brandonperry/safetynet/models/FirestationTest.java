package name.brandonperry.safetynet.models;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class FirestationTest {
    @Test
    public void testAddresses() {
        Firestation newAddress = new Firestation();
        newAddress.setAddress("1222 N forest hills Dr.");
        assertThat(newAddress.getAddresses()).isEqualTo("1222 N forest hills Dr.");
        System.out.println(newAddress);
    }

    @Test
    public void testFirstName() {
        Person person = new Person();
        person.setFirstName("Brandon");
        assertThat(person.getFirstName()).isEqualTo("Brandon");
    }
//    @Disabled("Fix me ")
    @Test
    public void testStationNum() {
        Firestation station = new Firestation();
        station.setStation("2");
        assertThat(station.getStation()).isEqualTo("2");
        System.out.println(station);
    }


}