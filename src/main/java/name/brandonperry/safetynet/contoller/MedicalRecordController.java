package name.brandonperry.safetynet.contoller;


import name.brandonperry.safetynet.DataFile;
import name.brandonperry.safetynet.models.MedicalRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalRecordController {
    private DataFile dataFile;
    Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

    @Autowired
    public MedicalRecordController(DataFile dataFile) {
        this.dataFile = dataFile;
    }

    /**
     * Get all Records
     *
     * @return Records found
     */
    @GetMapping("/medicalRecord")
    public List<MedicalRecord> getRecords() {
        logger.error("Error please check controller");
        return dataFile.getRecords();
    }

    @PostMapping("/medicalRecord")
    public MedicalRecord addMedicalRecords(@RequestBody MedicalRecord medicalRecord) {
        List<MedicalRecord> medicalRecords = dataFile.getRecords();
        medicalRecords.add(medicalRecord);
        return medicalRecords.get(medicalRecords.size() - 1);
    }

    @PutMapping("/medicalRecord/{id}")
    public MedicalRecord updateMedicalRecords(@PathVariable("id") String id, @RequestBody MedicalRecord medicalRecord) {
        MedicalRecord foundMedicalRecord = dataFile.updateMedicalRecord(id, medicalRecord);
        return foundMedicalRecord;
    }

    @DeleteMapping("/medicalRecord/{id}")
    public MedicalRecord deleteMedicalRecord(@PathVariable("id") String id) {
        MedicalRecord removedMedicalRecord = dataFile.deleteMedicalRecord(id);
        return removedMedicalRecord;
    }
}
