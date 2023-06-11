package name.brandonperry.safetynet.contoller.views;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Area serviced by a fire station
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceArea {
    private List<ServicedPerson> servicedPeople;
    private int numberOfAdults;
    private int numberOfChildren;



}
