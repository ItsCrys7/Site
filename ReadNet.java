import java.net.URL;
import java.util.Scanner;

public class ReadNet {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {

        try {
            URL address = new URL("https://operationworld.org/locations/europe/");
            try (Scanner scan = new Scanner(address.openStream())) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();

                    int index = line.indexOf("list-group-item list-group-item-action");

                    while (index != -1) {

                        int index2 = line.indexOf("list-group-item list-group-item-action", index + 1);

                        String link = line.substring(index, index2).trim();

                        // System.out.println(link);
                        int indexExpresie1 = link.indexOf("href=");
                        int indexExpresie2 = link.indexOf("\">");

                        String location = link.substring(indexExpresie1 + "href=".length() + 1,
                                indexExpresie2).trim();

                        // System.out.println(location);

                        try {
                            URL secondaryAddress = new URL("https://operationworld.org" + location);
                            try (Scanner secondaryScan = new Scanner(secondaryAddress.openStream())) {
                                while (secondaryScan.hasNextLine()) {
                                    String secondaryLine = secondaryScan.nextLine();

                                    if (secondaryLine.contains("Capital:")) {
                                        System.out.println(secondaryScan.nextLine());
                                        System.out.println("");

                                    }

                                }
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }

                        index = index2;

                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

