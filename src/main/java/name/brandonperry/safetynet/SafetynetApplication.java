package name.brandonperry.safetynet;

import com.fasterxml.jackson.databind.ObjectMapper;
import name.brandonperry.safetynet.models.MedicalRecord;
import name.brandonperry.safetynet.models.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class SafetynetApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SafetynetApplication.class, args);
		AllRecords();
		AllPeople();
		AllStations();
	}
	public static void AllRecords() throws IOException {
		DataFile.load();
		System.out.println(DataFile.getPeople());
	}

	public static void AllStations() throws IOException {
		DataFile.load();
        System.out.println(DataFile.getStation());
	}

	public static void AllPeople() throws IOException {
		DataFile.load();
		System.out.println(DataFile.getPeople());
	}

}
