package name.brandonperry.safetynet.contoller.views;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class StationPersonInfo {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String age;
}
