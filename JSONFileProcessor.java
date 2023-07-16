import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONFileProcessor {
    public static void main(String[] args) {
        String jsonFilePath = "path/to/input.json";
        String outputFilePath = "path/to/output.txt";

        try {
            // Read JSON file as a string
            String jsonData = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

            // Parse JSON string into a JSONObject
            JSONObject jsonObject = new JSONObject(jsonData);

            // Get the first JSON node name with only numbers
            String nodeName = getFirstNumericNodeName(jsonObject);

            // Write the node name to a new text file
            writeNodeNameToFile(nodeName, outputFilePath);

            System.out.println("Node name: " + nodeName + " has been written to " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFirstNumericNodeName(JSONObject jsonObject) {
        for (String key : jsonObject.keySet()) {
            if (key.matches("\\d+")) {  // Regex to match only numbers
                return key;
            }
        }
        return null;
    }

    private static void writeNodeNameToFile(String nodeName, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(nodeName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
