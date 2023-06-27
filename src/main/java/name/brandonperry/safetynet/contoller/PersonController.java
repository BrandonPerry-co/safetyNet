package name.brandonperry.safetynet.contoller;

import name.brandonperry.safetynet.DataFile;
import name.brandonperry.safetynet.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handle person request
 */
@RestController
public class PersonController {
    private DataFile dataFile;
    Logger logger = LoggerFactory.getLogger(PersonController.class);
    @Autowired
    public PersonController(DataFile dataFile) {
        this.dataFile= dataFile;
    }
    /**
     * Get all people
     *
     * @return people found
     */
    @GetMapping("/person")
    public List<Person> getPeople() {
        logger.error("Error please check controller");
        return dataFile.getPeople();
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        List<Person> people = dataFile.getPeople();
        people.add(person);
        return people.get(people.size() - 1);
    }

    @PutMapping("/person/{id}")
    public Person updatePerson(@PathVariable("id") String id, @RequestBody Person person) {
        Person foundPerson = dataFile.updatePerson(id, person);
        return foundPerson;
    }

    @DeleteMapping("/person/{id}")
    public Person deletePerson(@PathVariable("id") String id) {
        Person removedPerson = dataFile.deletePerson(id);
        return removedPerson;
    }

}
