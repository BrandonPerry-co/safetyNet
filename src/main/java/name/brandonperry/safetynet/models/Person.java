package name.brandonperry.safetynet.models;

import lombok.*;

//@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Person {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
