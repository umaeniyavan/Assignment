import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CapitalizeFirstLetter {
    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try {
            // Read the entire text from the input file
            String text = Files.readString(Paths.get(inputFile));

            // Capitalize the first letter of each word in the text
            String modifiedText = capitalizeFirstLetter(text);

            // Write the modified text to the output file
            Path outputPath = Paths.get(outputFile);
            Files.write(outputPath, modifiedText.getBytes());

            System.out.println("Text file processed successfully!");

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static String capitalizeFirstLetter(String text) {
        return Arrays.stream(text.split("\\s+"))
                .map(CapitalizeFirstLetter::capitalizeFirstCharacter)
                .collect(Collectors.joining(" "));
    }

    private static String capitalizeFirstCharacter(String word) {
        if (word.isEmpty()) {
            return word;
        }
        char firstChar = Character.toUpperCase(word.charAt(0));
        return firstChar + word.substring(1);
    }
}
