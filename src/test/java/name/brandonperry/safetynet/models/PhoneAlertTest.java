package name.brandonperry.safetynet.models;

import name.brandonperry.safetynet.contoller.views.PhoneAlert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneAlertTest {

    @Test
    public void testPhoneAlertAddress() {
        PhoneAlert phoneAlert = new PhoneAlert();
        phoneAlert.setAddress("1222 N forest hills Dr.");
        assertThat(phoneAlert.getAddress()).isEqualTo("1222 N forest hills Dr.");
        System.out.println(phoneAlert);
    }

    @Test
    public void testPhoneAlertStation() {
        PhoneAlert phoneAlert = new PhoneAlert();
        phoneAlert.setStation("2");
        assertThat(phoneAlert.getStation()).isEqualTo("2");
        System.out.println(phoneAlert);
    }

    @Test
    public void testPhoneAlertPhoneNumber() {
        PhoneAlert phoneNumber = new PhoneAlert();
        phoneNumber.setPhone("281-330-8004");
        assertThat(phoneNumber.getPhone()).isEqualTo("281-330-8004");
        System.out.println(phoneNumber);
    }

    @Test
    public void testPhoneAlertConstructorAndGetters() {
        String address = "123 Main St";
        String station = "Station 1";
        String phone = "111-222-3333";

        PhoneAlert phoneAlert = new PhoneAlert(address, station, phone);

        assertEquals(address, phoneAlert.getAddress());
        assertEquals(station, phoneAlert.getStation());
        assertEquals(phone, phoneAlert.getPhone());
    }

    @Test
    public void testPhoneAlertBuilder() {
        String address = "456 Park Ave";
        String station = "Station 2";
        String phone = "444-555-6666";

        PhoneAlert phoneAlert = PhoneAlert.builder()
                .address(address)
                .station(station)
                .phone(phone)
                .build();

        assertEquals(address, phoneAlert.getAddress());
        assertEquals(station, phoneAlert.getStation());
        assertEquals(phone, phoneAlert.getPhone());
    }

    @Test
    public void testEqualsAndHashCode() {
        String address1 = "123 Main St";
        String station1 = "Station 1";
        String phone1 = "111-222-3333";

        String address2 = "123 Main St";
        String station2 = "Station 1";
        String phone2 = "111-222-3333";

        PhoneAlert phoneAlert1 = new PhoneAlert(address1, station1, phone1);
        PhoneAlert phoneAlert2 = new PhoneAlert(address2, station2, phone2);

        assertEquals(phoneAlert1, phoneAlert2);
        assertEquals(phoneAlert1.hashCode(), phoneAlert2.hashCode());
    }

    @Test
    public void testToString() {
        String address = "123 Main St";
        String station = "Station 1";
        String phone = "111-222-3333";

        PhoneAlert phoneAlert = new PhoneAlert(address, station, phone);

        String expectedString = "PhoneAlert(address=123 Main St, station=Station 1, phone=111-222-3333)";
        assertEquals(expectedString, phoneAlert.toString());
    }
}
