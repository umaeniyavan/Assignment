import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SpecialCharacterFilter {

    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try {
            filterSpecialCharacters(inputFile, outputFile);
            System.out.println("Special characters filtered successfully.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void filterSpecialCharacters(String inputFile, String outputFile) throws IOException {
        Scanner scanner = new Scanner(new File(inputFile));
        FileWriter writer = new FileWriter(outputFile);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String filteredLine = line.replaceAll("[^a-zA-Z0-9\\s]", "");
            writer.write(filteredLine);
            writer.write(System.lineSeparator());
        }

        scanner.close();
        writer.close();
    }
}
