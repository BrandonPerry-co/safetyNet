package name.brandonperry.safetynet.contoller;

import name.brandonperry.safetynet.DataFile;
import name.brandonperry.safetynet.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Import(Person.class)
public class SafetynetController {
    Logger logger = LoggerFactory.getLogger(PersonController.class);
    @Autowired
    private DataFile dataFile;
    private Person person;

    @GetMapping("/communityEmail")
    public List<String> getCommunityEmails(@RequestParam("city") String city) {
        List<String> emails = dataFile.getCommunityEmails(city);
        logger.info("Here's all the emails for this area! ", emails);
        return emails;
    }

    @GetMapping("/phoneAlert")
    public List<String> getPhoneAlerts(@RequestParam("fireStation_number") String fireStation_number) {
        List<String> phoneAlertList = dataFile.getPhoneAlerts(fireStation_number);
        logger.info("Here's all the phone numbers for this area! ", phoneAlertList);
        return phoneAlertList;
    }

    @GetMapping("/childAlert")
    public List<String> childAlerts(@RequestParam("address") String address) {
        List<String> childAlertList = dataFile.getChildAlerts(address);
        logger.info("Here's a list of children and adults at this location- ", childAlertList);
        return childAlertList;
    }

    @GetMapping("/personInfo")
    public List<String> getPersonInfo(@RequestParam("fullName") String fullName) {
        List<String> personalInfo = dataFile.getPersonInfo(fullName);
        logger.info("Here's all the records on file for this location- ", personalInfo);
        return personalInfo;
    }

    @GetMapping("/fire")
    public List<String> getServicingStation(@RequestParam("address") String address) {
        List<String> servicedAddress = dataFile.getServicingStation(address);
        logger.info("Here's all the records on file for this location- ", servicedAddress);
        return servicedAddress;
    }


    @GetMapping("/flood/stations")
    public List<String> getEveryoneServicedByStation(@RequestParam("stations") String stations) {
        List<String> listOfAllStations = dataFile.getEveryoneServicedByStation(stations);
        logger.info("Here's all residents for this area- ", listOfAllStations);
        return listOfAllStations;
    }
}
