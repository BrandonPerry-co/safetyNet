package name.brandonperry.safetynet.contoller;


import name.brandonperry.safetynet.DataFile;
import name.brandonperry.safetynet.models.Firestation;
import name.brandonperry.safetynet.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FireStationController {

    Logger logger = LoggerFactory.getLogger(FireStationController.class);

    /**
     * Get all stations
     *
     * @return stations found
     */
    @GetMapping("/firestation")
    public List<Firestation> getStation() {
        logger.error("Error please check controller");
        return DataFile.getStation();
    }

    @PostMapping("/firestation")
    public Firestation addStation(@RequestBody Firestation firestation) {
        List<Firestation> stations = DataFile.getStation();
        stations.add(firestation);
        return stations.get(stations.size() - 1);
    }

    @PutMapping("/firestation/{id}")
    public Firestation updateStation(@PathVariable("id") String id, @RequestBody Firestation firestation) {
        Firestation foundStation = DataFile.updateStation(id, firestation);
        return foundStation;
    }

    @DeleteMapping("/firestation/{id}")
    public Firestation deleteStation(@PathVariable("id") String id) {
        Firestation removedStation = DataFile.deleteStation(id);
        return removedStation;
    }
}
