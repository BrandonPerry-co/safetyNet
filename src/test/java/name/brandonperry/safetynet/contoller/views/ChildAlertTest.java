package name.brandonperry.safetynet.contoller.views;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ChildAlertTest {

    @Test
    void getFirstName() {
        ChildAlert childAlert = new ChildAlert();
        childAlert.setFirstName("Brandon");
        assertThat(childAlert.getFirstName()).isEqualTo("Brandon");
    }

    @Test
    void getLastName() {
        ChildAlert childAlert = new ChildAlert();
        childAlert.setLastName("Perry");
        assertThat(childAlert.getLastName()).isEqualTo("Perry");
    }

    @Test
    void getAge() {
        ChildAlert childAlert = new ChildAlert();
        childAlert.setAge("38");
        assertThat(childAlert.getAge()).isEqualTo("38");
    }


    @Test
    public void testChildAlertConstructorAndGetters() {
        String firstName = "John";
        String lastName = "Boyd";
        String age = "10";

        ChildAlert childAlert = new ChildAlert(firstName, lastName, age);

        assertEquals(firstName, childAlert.getFirstName());
        assertEquals(lastName, childAlert.getLastName());
        assertEquals(age, childAlert.getAge());
    }

    @Test
    public void testChildAlertBuilder() {
        String firstName = "Jane";
        String lastName = "Boyd";
        String age = "8";

        ChildAlert childAlert = ChildAlert.builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .build();

        assertEquals(firstName, childAlert.getFirstName());
        assertEquals(lastName, childAlert.getLastName());
        assertEquals(age, childAlert.getAge());
    }
}