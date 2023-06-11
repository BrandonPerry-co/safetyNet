package name.brandonperry.safetynet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import name.brandonperry.safetynet.DataFile;
import name.brandonperry.safetynet.contoller.MedicalRecordController;
import name.brandonperry.safetynet.models.MedicalRecord;
import name.brandonperry.safetynet.models.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MedicalRecordController.class)
@Import(DataFile.class)
public class MedicalRecordControllerTest {
    @Autowired
    private DataFile dataFile;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllMedicalRecords() throws Exception {
        this.mockMvc.perform(get("/medicalRecord"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(23)));
    }

    @Test
    public void testAddMedicalRecord() throws Exception {
        MedicalRecord testMedicalRecords = MedicalRecord.builder() //
                .firstName("John").lastName("Doe").birthdate("11/30/1980").medications(Arrays.asList("Omega3")).allergies(Arrays.asList("veggies")).build();
        String testMedicalRecordJson = new ObjectMapper().writeValueAsString(testMedicalRecords);
        mockMvc.perform(post("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(testMedicalRecordJson)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").exists())
                .andExpect(content().string(testMedicalRecordJson));
    }

    @Test
    public void testUpdateMedicalRecord() throws Exception {
        List<MedicalRecord> testMedicalRecords = dataFile.getRecords();
        MedicalRecord test = testMedicalRecords.stream().filter(p -> "John".equals(p.getFirstName()) && "Boyd".equals(p.getLastName())).findFirst().orElse(null);
        test.setBirthdate("11/20/1998");
        test.setMedications(Arrays.asList("Motrin", "Benadryl"));
        test.setAllergies(Arrays.asList("Adderall"));
        String testingUpdateMedicalRecord = new ObjectMapper().writeValueAsString(test);
        mockMvc.perform(put("/medicalRecord/{id}", "John+Boyd")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(testingUpdateMedicalRecord)
                        .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Boyd"))
                .andExpect(jsonPath("$.birthdate").value("11/20/1998"))
                .andExpect(jsonPath("$.medications").value(hasItems("Motrin", "Benadryl")))
                .andExpect(jsonPath("$.allergies").value(hasItem("Adderall")));
        System.out.println(test);


    }

    @Test
    @DirtiesContext
    public void testDeleteMedicalRecord() throws Exception {
        List<MedicalRecord> testMedicalRecords = dataFile.getRecords();
        MedicalRecord test = testMedicalRecords.stream().filter(p -> "John".equals(p.getFirstName()) && "Boyd".equals(p.getLastName())).findFirst().orElse(null);
        mockMvc.perform(delete("/medicalRecord/{id}", "John+Boyd")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        // Get the updated list of records
        List<MedicalRecord> updatedMedicalRecords = dataFile.getRecords();
        // Assertions to verify the deletion
        assertThat(updatedMedicalRecords).doesNotContain(test);
        System.out.println(updatedMedicalRecords);

    }

}
