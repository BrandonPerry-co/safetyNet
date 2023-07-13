package name.brandonperry.safetynet.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

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

    @Test
    public void testAge() {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAge("38");
        assertThat(medicalRecord.getAge()).isEqualTo("38");
    }

    @Test
    public void testMedications() {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setMedications(Arrays.asList("Motrin", "Benadryl"));
        assertThat(medicalRecord.getMedications()).contains("Motrin", "Benadryl");
    }

    @Test
    public void testAllergies() {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(Arrays.asList("nillacilan"));
        assertThat(medicalRecord.getAllergies()).contains("nillacilan");
    }
}