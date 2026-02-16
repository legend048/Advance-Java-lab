import java.net.InetAddress;
import java.net.UnknownHostException;

public class Inet_Address {

    public static void main(String[] args) {
        try {
            printDetails("Local Host", InetAddress.getLocalHost());
            printDetails("www.google.com", InetAddress.getByName("www.google.com"));
            printDetails("8.8.8.8", InetAddress.getByAddress(new byte[]{8, 8, 8, 8}));
        } catch (UnknownHostException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void printDetails(String label, InetAddress ia) {
        System.out.println(label + ": " + ia.getHostName() + " (" + ia.getHostAddress() + ")");
    }
}
