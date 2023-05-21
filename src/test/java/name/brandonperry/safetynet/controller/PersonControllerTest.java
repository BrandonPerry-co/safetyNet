package name.brandonperry.safetynet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import name.brandonperry.safetynet.DataFile;
import name.brandonperry.safetynet.contoller.PersonContoller;
import name.brandonperry.safetynet.models.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonContoller.class)
public class PersonControllerTest {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: 4/29/2023 Add autoWired Mock MVC
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetAllPeople() throws Exception {
        // TODO: 5/7/2023 Code it here
        this.mockMvc.perform(get("/person")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(23)));
    }

    @Test
    public void testAddPerson() throws Exception {
        Person testPerson = Person.builder() //
                .firstName("John").lastName("Doe").address("123 main St").city("Dallas").zip("12345").phone("1234567").email("test@aol.com").build();
        String testPersonJson = new ObjectMapper().writeValueAsString(testPerson);
        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(testPersonJson)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").exists())
                .andExpect(content().string(testPersonJson));
    }

    @Test
    public void testUpdatePerson() throws Exception {
        List<Person> testPeople = DataFile.getPeople();
        Person testPerson = testPeople.stream().filter(p -> "John".equals(p.getFirstName()) && "Boyd".equals(p.getLastName())).findFirst().orElse(null);
        testPerson.setCity("Pizza");
        testPerson.setPhone("281-330-8004");
        String testingUpdatePerson = new ObjectMapper().writeValueAsString(testPerson);
        mockMvc.perform(put("/person/{id}", "John+Boyd")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(testingUpdatePerson)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Boyd"))
                .andExpect(jsonPath("$.city").value("Pizza"))
                .andExpect(jsonPath("$.phone").value("281-330-8004"));
        System.out.println(testPerson);
    }

//    @Test
//    public void testDeletePerson() throws Exception {
//        List<Person> testPerson = DataFile.getPeople();
//        Person deleteThisPerson = testPerson.stream().filter(p -> "John".equals(p.getFirstName()) && "Boyd".equals(p.getLastName())).findFirst().orElse(null);
//        String testingDeletePerson = new ObjectMapper().writeValueAsString(deleteThisPerson);
//        mockMvc.perform(MockMvcRequestBuilders.delete("/person/{id}", "John+Boyd"))
//        .andExpect(MockMvcResultMatchers.status().isOk());
//        System.out.println(deleteThisPerson + " was removed!");
//
//    }
//    @Test
//    public void testDeletePerson() throws Exception {
//        List<Person> testPeople = DataFile.getPeople();
//        Person testPerson = testPeople.stream()
//                .filter(p -> "John".equals(p.getFirstName()) && "Boyd".equals(p.getLastName()))
//                .findFirst()
//                .orElse(null);
//
//        mockMvc.perform(delete("/person/{id}", "John+Boyd")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk());
//
//        // Assertions to verify the deletion
//        assertThat(DataFile.getPeople()).doesNotContain(testPerson);
//        assertNull(testPerson);
//
//        System.out.println(DataFile.getPeople());
//    }

    @Test
    public void testDeletePerson() throws Exception {
        List<Person> testPeople = DataFile.getPeople();
        Person testPerson = testPeople.stream()
                .filter(p -> "John".equals(p.getFirstName()) && "Boyd".equals(p.getLastName()))
                .findFirst()
                .orElse(null);

        mockMvc.perform(delete("/person/{id}", "John+Boyd")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        // Get the updated list of people
        List<Person> updatedPeople = DataFile.getPeople();

        // Assertions to verify the deletion
        assertThat(updatedPeople).doesNotContain(testPerson);
        assertNull(testPerson);

        System.out.println(updatedPeople);
    }
}
