package name.brandonperry.safetynet.contoller.views;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}