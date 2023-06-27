package name.brandonperry.safetynet.models;

import lombok.*;

//@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class PhoneAlert {
    private String address;
    private String station;
    private String phone;
}
