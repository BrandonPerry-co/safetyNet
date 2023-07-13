package name.brandonperry.safetynet.contoller;


import name.brandonperry.safetynet.DataFile;
import name.brandonperry.safetynet.contoller.views.StationInfo;
import name.brandonperry.safetynet.contoller.views.StationPersonInfo;
import name.brandonperry.safetynet.models.Firestation;
import name.brandonperry.safetynet.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FireStationController {
    Logger logger = LoggerFactory.getLogger(FireStationController.class);
    private DataFile dataFile;

    @Autowired
    public FireStationController(DataFile dataFile) {
        this.dataFile = dataFile;
    }

    /**
     * Get all stations
     *
     * @return stations found
     */
    @GetMapping("/firestation")
    public List<Firestation> getStation() {
        List<Firestation> fireStations = dataFile.getStation();
        logger.info("Successfully returned all FireStations.");
        return fireStations;
    }

    @PostMapping("/firestation")
    public Firestation addStation(@RequestBody Firestation firestation) {
        List<Firestation> stations = dataFile.getStation();
        stations.add(firestation);
        logger.info("Successfully added the following- ", stations.get(stations.size() - 1));
        return stations.get(stations.size() - 1);
    }

    @PutMapping("/firestation/{id}")
    public Firestation updateStation(@PathVariable("id") String id, @RequestBody Firestation firestation) {
        Firestation foundStation = dataFile.updateStation(id, firestation);
        logger.info("Successfully updated the following- ", foundStation);
        return foundStation;
    }

    @DeleteMapping("/firestation/{id}")
    public Firestation deleteStation(@PathVariable("id") String id) {
        Firestation removedStation = dataFile.deleteStation(id);
        logger.info("Successfully removed the following- ", removedStation);
        return removedStation;
    }

    @GetMapping("/firestations")
    public StationInfo getServicedArea(@RequestParam("stationNumber") String stationNumber) {
        List<Person> people = dataFile.getServicedArea(stationNumber);
        StationInfo stationInfo = new StationInfo();
        List<StationPersonInfo> stationPersonInfo = new ArrayList<>();
        stationPersonInfo = people.stream()
                .map(p -> {
                    var info = StationPersonInfo.builder()
                            .firstName(p.getFirstName()).build();
                    //TODO calculate age and put in info
                    return info;
                })
                .collect(Collectors.toList());
        stationInfo.setStationPersonInfo(stationPersonInfo);
        //toDo look at logic in dataFile and use it to figure out the number of adults and children
        logger.info("All residents for this area- ", people);
        return stationInfo;
    }
}
