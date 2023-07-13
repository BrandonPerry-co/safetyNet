package name.brandonperry.safetynet.models;

import name.brandonperry.safetynet.contoller.views.PhoneAlert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}
