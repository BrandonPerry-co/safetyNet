package name.brandonperry.safetynet.contoller.views;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class StationInfo {
    private List<StationPersonInfo> stationPersonInfo = new ArrayList<>();
    public int numOfAdults;
    private int numOfChildren;

}
