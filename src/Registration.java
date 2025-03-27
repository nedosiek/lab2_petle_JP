import java.io.*;
import java.util.*;


public class Registration {
    protected static List<Guests> guestsRegistration(String filename) {
        List<Guests> guests = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Rozdzielenie linii na ID, posiadane i poszukiwane cechy
                String[] parts = line.split("\t");
                int id = Integer.parseInt(parts[0]);

                // Posiadane cechy (rozdzielone przecinkami)
                ArrayList<String> ownedAttributes = new ArrayList<>(Arrays.asList(parts[1].split(",")));

                // Poszukiwane cechy (rozdzielone przecinkami)
                ArrayList<String> desiredAttributes = new ArrayList<>(Arrays.asList(parts[2].split(",")));

                // Tworzenie nowego uczestnika
                guests.add(new Guests(id, ownedAttributes, desiredAttributes));

            }
        } catch (IOException e) {
            System.out.println("Reading file is failed: " + e.getMessage());
        }
        return guests;
    }
}
