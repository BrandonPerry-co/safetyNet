package name.brandonperry.safetynet.contoller;

import name.brandonperry.safetynet.DataFile;
import name.brandonperry.safetynet.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handle person request
 */
@RestController
public class PersonContoller {

    Logger logger = LoggerFactory.getLogger(PersonContoller.class);

    /**
     * Get all people
     *
     * @return people found
     */
    @GetMapping("/person")
    public List<Person> getPeople() {
        logger.error("Error please check controller");
        return DataFile.getPeople();
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        List<Person> people = DataFile.getPeople();
        people.add(person);
        return people.get(people.size() - 1);
    }

    @PutMapping("/person/{id}")
    public Person updatePerson(@PathVariable("id") String id, @RequestBody Person person) {
        Person foundPerson = DataFile.updatePerson(id, person);
        return foundPerson;
    }

    @DeleteMapping("/person/{id}")
    public Person deletePerson(@PathVariable("id") String id) {
        Person removedPerson = DataFile.deletePerson(id);
        return removedPerson;
    }

//    @DeleteMapping(path = "/person{id}")
//    public Person deletePerson(@PathVariable("id") String id) {
//      person.remove(id);
//
//      return Person.noContent().build();
//    }


//    http://localhost:8080/person
//    This endpoint will provide the following via Http Post/Put/Delete:
//    Add a new person.
//    Update an existing person (at this time, assume that firstName and lastName do not change, but other
//fields can be modified).
//    Delete a person. (Use a combination of firstName and lastName as a unique identifier)

}
