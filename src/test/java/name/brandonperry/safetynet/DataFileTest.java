package name.brandonperry.safetynet;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class DataFileTest {
    @Test
    public void testLoad() throws IOException {
        DataFile.load();
        // TODO Find out how many people are in the DATA FILE
        assertThat(DataFile.getPeople().size()).isEqualTo(23);
    }
}
