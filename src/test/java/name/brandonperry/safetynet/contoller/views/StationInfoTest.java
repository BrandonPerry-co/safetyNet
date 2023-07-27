package name.brandonperry.safetynet.contoller.views;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StationInfoTest {

    @Test
    public void testStationInfoConstructorAndGetters() {
        List<StationPersonInfo> stationPersonInfo = new ArrayList<>();
        int numOfAdults = 2;
        int numOfChildren = 0;

        StationInfo stationInfo = new StationInfo(stationPersonInfo, numOfAdults, numOfChildren);

        assertEquals(stationPersonInfo, stationInfo.getStationPersonInfo());
        assertEquals(numOfAdults, stationInfo.getNumOfAdults());
        assertEquals(numOfChildren, stationInfo.getNumOfChildren());
    }

    @Test
    public void testStationInfoBuilder() {
        List<StationPersonInfo> stationPersonInfo = new ArrayList<>();
        int numOfAdults = 1;
        int numOfChildren = 0;

        StationInfo stationInfo = StationInfo.builder()
                .stationPersonInfo(stationPersonInfo)
                .numOfAdults(numOfAdults)
                .numOfChildren(numOfChildren)
                .build();

        assertEquals(stationPersonInfo, stationInfo.getStationPersonInfo());
        assertEquals(numOfAdults, stationInfo.getNumOfAdults());
        assertEquals(numOfChildren, stationInfo.getNumOfChildren());
    }

}