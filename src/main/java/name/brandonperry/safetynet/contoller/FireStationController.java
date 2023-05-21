package name.brandonperry.safetynet.contoller;


import name.brandonperry.safetynet.DataFile;
import name.brandonperry.safetynet.models.Firestation;
import name.brandonperry.safetynet.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
