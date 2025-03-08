package lekton.deniill.tools;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Map;
import lekton.deniill.exceptions.InvalidFileFormatException;
import org.junit.jupiter.api.Test;

class FileParamsReaderTest {

    @Test
    void getParams_shouldReadParamsFromFile() throws InvalidFileFormatException {
        // Arrange
        String filePath = "src/test/resources/params.txt";
        ParamsProvider paramsProvider = new FileParamsReader(filePath);

        // Act
        Map<String, Double> params = paramsProvider.getParams();

        // Assert
        assertThat(params).containsOnlyKeys("sum", "percentage", "multiplier");
        assertThat(params.get("sum")).isEqualTo(1000.0);
        assertThat(params.get("percentage")).isEqualTo(5.0);
        assertThat(params.get("multiplier")).isEqualTo(2.0);
    }

    @Test
    void getParams_shouldThrowException_ifFileDoesNotExist() {
        // Arrange
        String filePath = "src/test/resources/nonexistent.txt";
        ParamsProvider paramsProvider = new FileParamsReader(filePath);

        // Act & Assert
        assertThatThrownBy(paramsProvider::getParams)
                .isInstanceOf(InvalidFileFormatException.class)
                .hasMessageContaining("Ошибка при чтении файла");
    }

    @Test
    void getParams_shouldThrowException_ifFileFormatIsInvalid() {
        // Arrange
        String filePath = "src/test/resources/invalid_format.txt";
        ParamsProvider paramsProvider = new FileParamsReader(filePath);

        // Act & Assert
        assertThatThrownBy(paramsProvider::getParams)
                .isInstanceOf(InvalidFileFormatException.class)
                .hasMessage("Некорректный формат файла: строка должна содержать '='.");
    }

    @Test
    void getParams_shouldThrowException_ifValueIsNotANumber() {
        // Arrange
        String filePath = "src/test/resources/invalid_value.txt";
        ParamsProvider paramsProvider = new FileParamsReader(filePath);

        // Act & Assert
        assertThatThrownBy(paramsProvider::getParams)
                .isInstanceOf(InvalidFileFormatException.class)
                .hasMessageContaining("Некорректное значение в файле");
    }
}
