package name.brandonperry.safetynet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SafetynetControllerIT {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetFirestation() {
//        String expected = "This URL should return a list of people serviced by the corresponding fire station. So if station number = 1,it should return the people serviced by station number 1. The list of people should include these specificpieces of information: first name, last name, address, phone number. As well, it should provide asummary of the number of adults in the service area and the number of children (anyone aged 18 oryounger).";
//        assertThat(restTemplate.getForObject("http://localhost:" + port + "/firestation?stationNumber=1", String.class)).contains(expected);
    }
}

