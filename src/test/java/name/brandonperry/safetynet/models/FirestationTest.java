package name.brandonperry.safetynet.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FirestationTest {
    @Test
    public void testAddresses() {
        Firestation newFireStation = new Firestation();
        newFireStation.setAddress("1222 N forest hills Dr.");
        assertThat(newFireStation.getAddress()).isEqualTo("1222 N forest hills Dr.");
        System.out.println(newFireStation);
    }

    @Test
    public void testStationNum() {
        Firestation station = new Firestation();
        station.setStation("2");
        assertThat(station.getStation()).isEqualTo("2");
        System.out.println(station);
    }


}