import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class BookingAPI {
    private static final String BOOKING_API_URL = "https://api.example.com/bookings";

    public static void main(String[] args) {
        try {
            String bookingIds = getBookingIds();
            System.out.println("Booking IDs: " + bookingIds);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static String getBookingIds() throws Exception {
        URL url = new URL(BOOKING_API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
            return response.toString();
        } else {
            throw new Exception("API request failed with response code: " + responseCode);
        }
    }
}
