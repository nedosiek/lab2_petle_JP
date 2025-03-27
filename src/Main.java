import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
//        // Wczytywanie danych z pliku "guests.txt"
//        String fileName = "guest_list.txt";
//        List<Guests> guests = Registration.guestsRegistration(fileName);

        // Sprawdzanie, czy podano argument z nazwą pliku
        if (args.length == 0) {
            System.out.println("Błąd: Nie podano ścieżki do pliku wejściowego.");
            System.out.println("Użycie: java -jar projekt.jar <ścieżka_do_pliku_wejściowego>");
            return;
        }

        // Pobranie nazwy pliku z argumentów
        String fileName = args[0];

        // Sprawdzanie, czy plik istnieje i czy jest czytelny
        File file = new File(fileName);
        if (!file.exists() || !file.canRead()) {
            System.out.println("Błąd: Plik " + fileName + " nie istnieje lub nie można go odczytać.");
            return;
        }

        // Wczytywanie danych z pliku
        List<Guests> guests = Registration.guestsRegistration(fileName);

        // Parametry algorytmu genetycznego
        int populationSize = 50;
        int generations = 200;

        // Inicjalizacja populacji
        List<Map<Guests, List<Guests>>> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            population.add(Algorithm.randomMatch(guests));
        }

        for (int gen = 0; gen < generations; gen++) {
            // Selekcja najlepszych
            List<Map<Guests, List<Guests>>> selected = Algorithm.selection(population);

            // Krzyżowanie i tworzenie nowej populacji
            population = new ArrayList<>(selected);
            while (population.size() < populationSize) {
                Map<Guests, List<Guests>> parent1 = selected.get(new Random().nextInt(selected.size()));
                Map<Guests, List<Guests>> parent2 = selected.get(new Random().nextInt(selected.size()));
                Map<Guests, List<Guests>> child = Algorithm.crossover(parent1, parent2);
                Algorithm.mutate(child, guests);
                population.add(child);
            }

            // Najlepsze dopasowanie w bieżącej generacji
            Map<Guests, List<Guests>> best = population.getFirst();
            System.out.println("Generacja " + gen + " - najlepszy wynik: " + Algorithm.calculateFitness(best));
        }

        // Najlepsze dopasowanie po zakończeniu
        Map<Guests, List<Guests>> best = population.getFirst();
        System.out.println("Najlepsze dopasowanie:");
        for (Map.Entry<Guests, List<Guests>> entry : best.entrySet()) {
            System.out.println("Gość " + entry.getKey().id + " -> " + entry.getValue().stream().map(p -> String.valueOf(p.id)).toList());
        }
    }
}