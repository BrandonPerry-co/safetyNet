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

    @Test
    public void testPersonInfo() throws Exception {
        List<Person> testPeople = dataFile.getPeople();
        this.mockMvc.perform(get("/personInfo?firstName={firstName}&lastName={lastName}", "John", "Boyd"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("jaboyd@email.com"));
        System.out.println(testPeople);

    }
}