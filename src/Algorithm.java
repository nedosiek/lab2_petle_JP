import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.*;
import java.util.stream.Collectors;

public class Algorithm {

    // Funkcja dopasowania (score) - liczy wspólne cechy poszukiwane i posiadane
    public static int matchScore(Guests g1, Guests g2) {
        List<String> intersection = new ArrayList<>(g2.ownedAttributes);
        intersection.retainAll(g1.desiredAttributes); // Posiadane przez g2, ale szukane przez g1
        return intersection.size();
    }

    // Funkcja fitness - ocenia jakość dopasowań
    public static int calculateFitness(Map<Guests, List<Guests>> matches) {
        int totalScore = 0;
        for (Map.Entry<Guests, List<Guests>> entry : matches.entrySet()) {
            for (Guests match : entry.getValue()) {
                int scoreToG = matchScore(entry.getKey(), match);
                int scoreToMatch = matchScore(match, entry.getKey());

                if (scoreToG > 0 && scoreToMatch > 0) {
                    totalScore += 3; // Wzajemne dopasowanie - wyższa wartość
                } else if (scoreToG > 0) {
                    totalScore += 1; // Jednostronne dopasowanie
                }
            }
        }
        return totalScore;
    }


    // Tworzenie losowych początkowych dopasowań
    public static Map<Guests, List<Guests>> randomMatch(List<Guests> guests) {
        Map<Guests, List<Guests>> matches = new HashMap<>();

        for (Guests g : guests) {
            List<Guests> potentialMatches = new ArrayList<>(guests);
            potentialMatches.remove(g); // Usunięcie samego siebie

            // Wybieranie 5 losowych dopasowań
            Collections.shuffle(potentialMatches);
            matches.put(g, new ArrayList<>(potentialMatches.subList(0, Math.min(5, potentialMatches.size()))));
        }
        return matches;
    }

    // Selekcja rodziców - wybór najlepszych rozwiązań
    public static List<Map<Guests, List<Guests>>> selection(List<Map<Guests, List<Guests>>> population) {
        // Sortujemy populację według fitness
        population.sort(Comparator.comparingInt(Algorithm::calculateFitness).reversed());

        // Wybieramy 50% najlepszych rozwiązań do dalszej ewolucji
        return population.subList(0, population.size() / 2);
    }

    // Krzyżowanie (crossover) - mieszanie dwóch rozwiązań
    public static Map<Guests, List<Guests>> crossover(Map<Guests, List<Guests>> parent1, Map<Guests, List<Guests>> parent2) {
        Map<Guests, List<Guests>> child = new HashMap<>();
        Random rand = new Random();

        for (Guests g : parent1.keySet()) {
            Set<Guests> combinedMatches = new HashSet<>(parent1.get(g)); // Unikalne dopasowania z parent1
            combinedMatches.addAll(parent2.get(g)); // Dodajemy unikalne dopasowania z parent2

            List<Guests> childMatches = new ArrayList<>(combinedMatches.stream().distinct().collect(Collectors.toList()));
            Collections.shuffle(childMatches); // Mieszamy dopasowania

            // Wybieramy maksymalnie 5 dopasowań
            int matchCount = Math.min(5, childMatches.size());
            child.put(g, new ArrayList<>(childMatches.subList(0, matchCount)));
        }

        return child;
    }

    // Mutacja - zamiana losowa na podstawie posiadanych i poszukiwanych cech
    public static void mutate(Map<Guests, List<Guests>> individual, List<Guests> guests) {
        Random rand = new Random();

        for (Guests g : individual.keySet()) {
            if (rand.nextDouble() < 0.2) { // Prawdopodobieństwo mutacji 20%
                List<Guests> potentialMatches = new ArrayList<>(guests);
                potentialMatches.remove(g);

                // Mutacja - wybierz jedną osobę z listy i wymień ją na inną losową osobę z podobnymi cechami
                int indexToMutate = rand.nextInt(individual.get(g).size()); // Zmienione na size()
                Guests oldMatch = individual.get(g).get(indexToMutate);

                List<Guests> betterMatches = potentialMatches.stream()
                        .filter(p -> matchScore(g, p) > matchScore(g, oldMatch))
                        .filter(p -> !individual.get(g).contains(p)) // Upewniamy się, że nie ma duplikatów
                        .collect(Collectors.toList());

                if (!betterMatches.isEmpty()) {
                    Guests newMatch = betterMatches.get(rand.nextInt(betterMatches.size()));
                    individual.get(g).set(indexToMutate, newMatch);
                }
            }
        }
    }
}
