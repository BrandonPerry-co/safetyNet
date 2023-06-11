package name.brandonperry.safetynet.contoller.views;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicedPerson {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;

}
