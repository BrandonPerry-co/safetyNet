package name.brandonperry.safetynet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import name.brandonperry.safetynet.DataFile;
import name.brandonperry.safetynet.contoller.FireStationController;
import name.brandonperry.safetynet.contoller.PersonContoller;
import name.brandonperry.safetynet.models.Firestation;
import name.brandonperry.safetynet.models.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FireStationController.class)
public class FireStationControllerTest {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllStations() throws Exception {
        // TODO: 5/7/2023 Code it here
        this.mockMvc.perform(get("/firestation")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(13)));
    }

    @Test
    public void testAddStation() throws Exception {
        Firestation testStations = Firestation.builder() //
                .address("123 main St").station("4").build();
        String testStationJson = new ObjectMapper().writeValueAsString(testStations);
        mockMvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(testStationJson)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").exists())
                .andExpect(content().string(testStationJson));
    }

    @Test
    public void testUpdateStation() throws Exception {
        List<Firestation> testStations = DataFile.getStation();
        Firestation testStation = testStations.stream().filter(p -> "1509 Culver St".equals(p.getAddress()) && "3".equals(p.getStation())).findFirst().orElse(null);
        testStation.setAddress("1004 Grover lane");
        testStation.setStation("4");
        String testingUpdateStation = new ObjectMapper().writeValueAsString(testStation);
        mockMvc.perform(put("/firetation/{id}", "3")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(testingUpdateStation)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("1004 Grover lane"))
                .andExpect(jsonPath("$.station").value("4"));
        System.out.println(testStation);
    }

    @Test
    public void testDeletePerson() throws Exception {
        List<Firestation> testStation = DataFile.getStation();
        Firestation testRemovedStation = testStation.stream()
                .filter(p -> "1509 Culver St".equals(p.getAddress()) && "3".equals(p.getStation()))
                .findFirst()
                .orElse(null);

        mockMvc.perform(delete("/firestation/{id}", "1509 Culver St+3")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        // Get the updated list of people
        List<Firestation> updatedStation = DataFile.getStation();

        // Assertions to verify the deletion
        assertThat(updatedStation).doesNotContain(testRemovedStation);
        assertNull(testRemovedStation);

        System.out.println(updatedStation);
    }

}
