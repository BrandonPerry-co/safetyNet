package name.brandonperry.safetynet.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Firestation {
    private String address;
    private String station;

}
