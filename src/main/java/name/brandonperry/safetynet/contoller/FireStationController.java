package name.brandonperry.safetynet.contoller;


import name.brandonperry.safetynet.DataFile;
import name.brandonperry.safetynet.contoller.views.StationInfo;
import name.brandonperry.safetynet.contoller.views.StationPersonInfo;
import name.brandonperry.safetynet.models.Firestation;
import name.brandonperry.safetynet.models.MedicalRecord;
import name.brandonperry.safetynet.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
        List<MedicalRecord> medicalRecords = dataFile.getRecords();

        final int[] adultsCount = {0};
        final int[] childrenCount = {0};

        List<StationPersonInfo> stationPersonInfo = people.stream()
                .map(p -> {
                    MedicalRecord medicalRecord = dataFile.getRecords()
                            .stream()
                            .filter(record -> record.getFirstName().equals(p.getFirstName()) && record.getLastName().equals(p.getLastName()))
                            .findAny()
                            .orElse(null);
                    LocalDate birthDate = null;
                    int age = 0;
                    if (medicalRecord != null) {
                        birthDate = LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                        age = calculateAge(birthDate);
                        if (age <= 18) {
                            childrenCount[0]++;
                        } else {
                            adultsCount[0]++;
                        }
                    }
                    String ageString = String.valueOf(age);

                    var info = StationPersonInfo.builder()
                            .firstName(p.getFirstName())
                            .lastName(p.getLastName())
                            .address(p.getAddress())
                            .phone(p.getPhone())
                            .age(ageString)
                            .build();

                    return info;
                })
                .collect(Collectors.toList());

        stationInfo.setStationPersonInfo(stationPersonInfo);
        // ToDo: Look at logic in dataFile and use it to figure out the number of adults and children
        stationInfo.setNumOfAdults(adultsCount[0]);
        stationInfo.setNumOfChildren(childrenCount[0]);

        logger.info("All residents for this area- ", people);
        return stationInfo;
    }

    private int calculateAge(LocalDate birthdate) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(birthdate, now);
        return period.getYears();
    }
}
