package name.brandonperry.safetynet.models;
import lombok.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

//@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Firestation {

    private String address;
    private String station;

    public String getAddresses() {
        return null;
    }

    public String getStation() {
        return null;
    }
}
