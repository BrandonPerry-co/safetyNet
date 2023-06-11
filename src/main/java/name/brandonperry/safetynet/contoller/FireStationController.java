package name.brandonperry.safetynet.contoller;


import name.brandonperry.safetynet.DataFile;
import name.brandonperry.safetynet.models.Firestation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return stations.get(stations.size() - 1);
    }

    @PutMapping("/firestation/{id}")
    public Firestation updateStation(@PathVariable("id") String id, @RequestBody Firestation firestation) {
        Firestation foundStation = dataFile.updateStation(id, firestation);
        return foundStation;
    }

    @DeleteMapping("/firestation/{id}")
    public Firestation deleteStation(@PathVariable("id") String id) {
        Firestation removedStation = dataFile.deleteStation(id);
        return removedStation;
    }
}
