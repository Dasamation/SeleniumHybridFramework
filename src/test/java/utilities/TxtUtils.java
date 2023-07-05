package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TxtUtils {

    public static String[][] readTxtData(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        // Contar las filas y columnas del archivo
        int rowCount = 0;
        int colCount = 0;
        String line;
        boolean isFirstLine = true; // Variable para ignorar la primera línea
        while ((line = reader.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue; // Ignorar la primera línea
            }
            rowCount++;
            String[] data = line.split("\t"); // Puedes ajustar el separador según tu archivo
            colCount = Math.max(colCount, data.length);
        }

        // Volver a leer el archivo y almacenar los datos en una matriz
        String[][] dataMatrix = new String[rowCount][colCount];
        reader = new BufferedReader(new FileReader(filePath));
        isFirstLine = true; // Reiniciar la variable para ignorar la primera línea
        int rowIndex = 0;
        while ((line = reader.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue; // Ignorar la primera línea
            }
            String[] data = line.split("\t"); // Puedes ajustar el separador según tu archivo
            for (int colIndex = 0; colIndex < data.length; colIndex++) {
                dataMatrix[rowIndex][colIndex] = data[colIndex];
            }
            rowIndex++;
        }

        reader.close();
        return dataMatrix;
    }
}

