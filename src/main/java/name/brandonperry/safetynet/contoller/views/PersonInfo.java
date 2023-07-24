package name.brandonperry.safetynet.contoller.views;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class PersonInfo {
    private String name;
    private String address;
    private String age;
    private String email;
    private List<String> medications;
    private List<String> allergies;
    private String firstName;
    private String lastName;

    public PersonInfo(String firstName, String lastName) {
    }
}
