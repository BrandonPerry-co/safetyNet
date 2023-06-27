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

    //    public class Firestation {
//    @GetMapping("/firestation")
//    public String getFirestation() {
//        return "This URL should return a list of people serviced by the corresponding fire station. So if station number = 1," +
//                "it should return the people serviced by station number 1. The list of people should include these specific" +
//                "pieces of information: first name, last name, address, phone number. As well, it should provide a" +
//                "summary of the number of adults in the service area and the number of children (anyone aged 18 or" +
//                "younger).";
//    }

//    }

    //    @RestController
//    public class Fire {
    @GetMapping("/fire")
    public String getStationNum() {
        return "This URL should return the fire station number that services the provided address as well as a list of all of" +
                "the people living at the address. This list should include each person’s name, phone number, age," +
                "medications with dosage, and allergies";
    }
//    }

    //    @RestController
//    public class Flood {
    @GetMapping("/flood/stations")
    public String getEveryOne() {
        return "This should return a list of all the households in each fire station’s jurisdiction. This list needs to group\n" +
                "people by household address, include name, phone number, and age of each person, and any\n" +
                "medications (with dosages) and allergies beside each person’s name.";
    }
//    }


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

    }
