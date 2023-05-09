package name.brandonperry.safetynet.models;

import lombok.*;

//@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    //TODO firstname, lastname, birthdate, see data.json
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;

    public static void addPerson(Person person) {

    }

    //FIXME
}
