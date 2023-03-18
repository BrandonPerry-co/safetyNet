package name.brandonperry.safetynet.models;

import lombok.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Firestation {

    private Set<String> addresses;
    private Set<String> firestationNum;

}
