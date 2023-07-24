package name.brandonperry.safetynet.contoller.views;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class FloodStationsPersonInfo {
    private String name;
    private String phone;
    private String birthdate;
    private String age;
    private List<String> medications;
    private List<String> allergies;
}
