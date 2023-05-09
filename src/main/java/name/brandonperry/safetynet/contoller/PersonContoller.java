package name.brandonperry.safetynet.contoller;

import name.brandonperry.safetynet.DataFile;
import name.brandonperry.safetynet.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class PersonContoller {

    Logger logger = LoggerFactory.getLogger(PersonContoller.class);

    @GetMapping("/person")
    public List<Person> getPeople() {
        logger.error("Error please check controller");
        return DataFile.getPeople();
    }

    @PostMapping(value = "/person")
    public Person addPerson(@RequestBody Person person) {
        List<Person> people = DataFile.getPeople();
        people.add(person);
        return people.get(people.size() - 1);
    }

    @PutMapping(value = "/person")
    public Person updatePerson(@RequestBody Person person) {
        List<Person> people = DataFile.getPeople();
        return people.stream()
                .filter(p -> "John".equals(p.getFirstName()) && "Boyd".equals(p.getLastName()))
                .findAny()
                .orElse(null);
    }


//    http://localhost:8080/person
//    This endpoint will provide the following via Http Post/Put/Delete:
//    Add a new person.
//    Update an existing person (at this time, assume that firstName and lastName do not change, but other
//fields can be modified).
//    Delete a person. (Use a combination of firstName and lastName as a unique identifier)

}
