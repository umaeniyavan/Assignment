import org.apache.commons.csv.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CSVSorting {
    public static void main(String[] args) {
        String inputCsvFile = "input.csv"; // Path to the input CSV file
        String outputCsvFile = "output.csv"; // Path to the output CSV file
        String columnToSort = "Word"; // Column containing the words to sort

        try {
            // Read the input CSV file
            FileReader fileReader = new FileReader(inputCsvFile);
            CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(fileReader);

            // Get the words from the specified column
            List<String> words = new ArrayList<>();
            for (CSVRecord record : csvParser) {
                String word = record.get(columnToSort);
                words.add(word);
            }

            // Sort the words in ascending order
            Collections.sort(words);

            // Write the sorted values to the output CSV file
            FileWriter fileWriter = new FileWriter(outputCsvFile);
            CSVPrinter csvPrinter = CSVFormat.DEFAULT.withHeader(columnToSort).print(fileWriter);
            for (String word : words) {
                csvPrinter.printRecord(word);
            }
            csvPrinter.flush();
            csvPrinter.close();

            System.out.println("Sorting completed. Sorted values are written to " + outputCsvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


