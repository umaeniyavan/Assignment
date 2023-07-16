import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class XMLProcessor {
    public static void main(String[] args) {
        String inputFilePath = "input.xml"; // Path to the input XML file
        String outputFilePath = "output.txt"; // Path to the output file
        
        try {
            // Parse the input XML file
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(inputFilePath);
            document.getDocumentElement().normalize();
            
            // Remove empty nodes
            removeEmptyNodes(document.getDocumentElement());
            
            // Write node names to the output file
            FileWriter writer = new FileWriter(outputFilePath);
            writeNodeNames(document.getDocumentElement(), writer);
            writer.close();
            
            System.out.println("Empty nodes removed and node names written to the output file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Recursive method to remove empty nodes
    private static void removeEmptyNodes(Node node) {
        NodeList childNodes = node.getChildNodes();
        
        for (int i = childNodes.getLength() - 1; i >= 0; i--) {
            Node childNode = childNodes.item(i);
            
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                removeEmptyNodes(childNode);
                
                if (!childNode.hasChildNodes() && childNode.getTextContent().trim().isEmpty()) {
                    node.removeChild(childNode);
                }
            }
        }
    }
    
    // Recursive method to write node names to the output file
    private static void writeNodeNames(Node node, FileWriter writer) throws IOException {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            writer.write(node.getNodeName() + "\n");
            
            NodeList childNodes = node.getChildNodes();
            
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node childNode = childNodes.item(i);
                writeNodeNames(childNode, writer);
            }
        }
    }
}


