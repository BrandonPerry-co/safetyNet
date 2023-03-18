package name.brandonperry.safetynet.models;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class MedicalRecordTest {

    @Test
    public void testFirstName() {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Steve");
        assertThat(medicalRecord.getFirstName()).isEqualTo("Steve");
    }

    @Test
    public void testLastName() {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setLastName("Williams");
        assertThat(medicalRecord.getLastName()).isEqualTo("Williams");
    }

    @Test
    public void testBirthdate() {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBirthdate("01/03/1980");
        assertThat(medicalRecord.getBirthdate()).isEqualTo("01/03/1980");
    }

    //fix me
//    @Disabled("Fix me ")
    @Test
    public void testMedications() {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setMedications(Set.of("hydrapermazol:100mg"));
        assertThat(medicalRecord.getMedications()).contains("hydrapermazol:100mg");
    }

    //    @Disabled("Fix me ")
    @Test
    public void testAllergies() {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(Set.of("nillacilan"));
        assertThat(medicalRecord.getAllergies()).contains("nillacilan");
    }
}