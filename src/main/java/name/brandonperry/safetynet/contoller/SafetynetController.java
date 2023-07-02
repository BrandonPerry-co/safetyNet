package name.brandonperry.safetynet.contoller;

import name.brandonperry.safetynet.DataFile;
import name.brandonperry.safetynet.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Import(Person.class)
public class SafetynetController {
    @Autowired
    private DataFile dataFile;
    private Person person;

    @GetMapping("/communityEmail")
    public List<String> getCommunityEmails(@RequestParam("city") String city) {
        List<String> emails = dataFile.getCommunityEmails(city);
        return emails;
    }

    @GetMapping("/phoneAlert")
    public List<String> getPhoneAlerts(@RequestParam("fireStation_number") String fireStation_number) {
        List<String> phoneAlertList = dataFile.getPhoneAlerts(fireStation_number);
        return phoneAlertList;
    }

    @GetMapping("/childAlert")
    public List<String> childAlerts(@RequestParam("address") String address) {
        List<String> childAlertList = dataFile.getChildAlerts(address);
        return childAlertList;
    }

    @GetMapping("/personInfo")
    public List<String> getPersonInfo(@RequestParam("fullName") String fullName) {
        List<String> personalInfo = dataFile.getPersonInfo(fullName);
        return personalInfo;
    }

    @GetMapping("/fire")
    public List<String> getServicingStation(@RequestParam("address") String address) {
        List<String> servicedAddress = dataFile.getServicingStation(address);
        return servicedAddress;
    }


    @GetMapping("/flood/stations")
    public List<String> getEveryoneServicedByStation(@RequestParam("stations") String stations) {
        List<String> listOfAllStations = dataFile.getEveryoneServicedByStation(stations);
        return listOfAllStations;
    }
}
