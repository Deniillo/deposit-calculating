package lekton.deniill.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lekton.deniill.exceptions.InvalidFileFormatException;

public class FileParamsReader implements ParamsProvider {

    private final String filePath;

    public FileParamsReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Map<String, Double> getParams() throws InvalidFileFormatException {
        Map<String, Double> params = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length != 2) {
                    throw new InvalidFileFormatException(
                            "Некорректный формат файла: строка должна содержать '='.");
                }
                try {
                    params.put(parts[0].trim(), Double.parseDouble(parts[1].trim()));
                } catch (NumberFormatException e) {
                    throw new InvalidFileFormatException(
                            "Некорректное значение в файле: " + parts[1]);
                }
            }
        } catch (IOException e) {
            throw new InvalidFileFormatException("Ошибка при чтении файла: " + e.getMessage());
        }

        return params;
    }
}
