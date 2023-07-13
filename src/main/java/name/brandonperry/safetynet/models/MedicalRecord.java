package name.brandonperry.safetynet.models;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class MedicalRecord {

    private String firstName;
    private String lastName;
    private String birthdate;
    private String age;
    private List<String> medications;
    private List<String> allergies;
}
