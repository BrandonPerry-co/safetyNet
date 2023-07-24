package name.brandonperry.safetynet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import name.brandonperry.safetynet.DataFile;
import name.brandonperry.safetynet.contoller.FireStationController;
import name.brandonperry.safetynet.models.Firestation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FireStationController.class)
@Import(DataFile.class)
public class FireStationControllerTest {
    @Autowired
    private DataFile dataFile;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DirtiesContext
    public void testAddStation() throws Exception {
        Firestation testStations = Firestation.builder() //
                .address("123 main St").station("4").build();
        String testStationJson = new ObjectMapper().writeValueAsString(testStations);
        mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON_VALUE).content(testStationJson).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andExpect(jsonPath("$.address").exists()).andExpect(content().string(testStationJson));
    }

    @Test
    @DirtiesContext
    public void testUpdateStation() throws Exception {
        List<Firestation> testUpdatingStation = dataFile.getStation();
        Firestation testStation = testUpdatingStation.stream()
                .filter(p -> "1509 Culver St".equals(p.getAddress()) && "3".equals(p.getStation()))
                .findFirst()
                .orElse(null);
        testStation.setAddress("1004 Grover lane");
        testStation.setStation("4");
        String testingUpdateStation = new ObjectMapper().writeValueAsString(testStation);
        mockMvc.perform(put("/firestation/{id}", "1004 Grover lane+4")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(testingUpdateStation).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("1004 Grover lane"))
                .andExpect(jsonPath("$.station").value("4"));
        System.out.println(testStation);
    }

    @Test
    @DirtiesContext
    public void testDeleteFireStationAddressMapping() throws Exception {
        List<Firestation> testStation = dataFile.getStation();
        Firestation testRemovedStation = testStation.stream()
                .filter(p -> "1509 Culver St".equals(p.getAddress()) && "3".equals(p.getStation()))
                .findFirst()
                .orElse(null);

        mockMvc.perform(delete("/firestation/{id}", "1509 Culver St+3")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        // Get the updated list of people
        List<Firestation> updatedStation = dataFile.getStation();

        // Assertions to verify the deletion
        assertThat(updatedStation).doesNotContain(testRemovedStation);

        System.out.println(updatedStation);
    }

    @Test
    public void testGetServicedArea() throws Exception {
        this.mockMvc.perform(get("/firestations?stationNumber={stationNumber}", "1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.stationPersonInfo").isArray())
                .andExpect(jsonPath("$.stationPersonInfo", hasSize(6)))
                .andExpect(jsonPath("$.numOfAdults").value(5))
                .andExpect(jsonPath("$.numOfChildren").value(1));
    }

}
