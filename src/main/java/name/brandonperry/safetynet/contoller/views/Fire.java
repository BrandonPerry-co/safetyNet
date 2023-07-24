package name.brandonperry.safetynet.contoller.views;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Fire {
    private String station;
    private String name;
    private String phone;
    private String age;
    private List<String> medications;
    private List<String> allergies;
}
