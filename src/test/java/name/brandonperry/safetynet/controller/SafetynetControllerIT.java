package name.brandonperry.safetynet.controller;

import name.brandonperry.safetynet.DataFile;
import name.brandonperry.safetynet.contoller.SafetynetController;
import name.brandonperry.safetynet.models.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SafetynetController.class)
@Import(DataFile.class)
public class SafetynetControllerIT {

    @Autowired
    private DataFile dataFile;

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void testFireStationNumber() throws Exception {
//        List<Firestation> testGetFireStations = dataFile.getStation();
//        Firestation fireNum = testGetFireStations.stream()
//                .filter(p -> "3".equals(p.getStation()))
//                .findAny().orElse(null);
//        String testStationJson = new ObjectMapper().writeValueAsString(fireNum);
//        mockMvc.perform(get("/firestation?stationNumber={id}", "3")
//                        .content(testStationJson)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
//        System.out.println(fireNum);
//    }

    @Test
    public void testCommunityEmail() throws Exception {
        List<Person> testPeople = dataFile.getPeople();
        this.mockMvc.perform(get("/communityEmail?city={city}", "Culver"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasItem("jaboyd@email.com")));
        System.out.println(testPeople);

    }

//        @Test
//    public void testGetFirestationNum() throws Exception {
//        List<Person> testGetFireAddress = dataFile.getPeople();
//            this.mockMvc.perform(get("/fire?address={address}", "1509 Culver St"))
//                    .andDo(print())
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$").isArray())
//                    .andExpect(jsonPath("$lastName", hasItem("boyd")));
//            System.out.println(testGetFireAddress);
//    }

}