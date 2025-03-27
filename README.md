# lab2_petle_JP
Lab2 - loops, conditional statements, exceptions
# Treść programowa

Zaprojektowanie i implementacja aplikacji pobierającej dane wejściowe z linii komend, zawierającej pętle, instrukcje warunkowe oraz obsługę wyjątków.

# Cel zajęć

Zzaimplementować swój meta-heurystyczny algorytm problemu przydziału zasobów (resource-allocation).

Przebieg działania programu może być wyświetlany na standardowym wyjściu (konsola). Ważna parametry, takie jak np. aktualnie badana konfiguracja bądź wartość funkcji celu powinny być na bieżąco prezentowane w celu śledzenia przebiegu działania programu. Warunkiem terminującym działanie powinien być brak poprawy wyniku przez kolejną określoną ilość iteracji. Dane wejściowe będą czytane z pliku (przykładowe treści niżej).

Należy zwrócić też uwagę na sensowność generowanych wyników - np. dana osoba jako sugestie nie powinna otrzymać samej siebie, pracownik nie może być przypisany jednocześnie do dwóch projektów, każdy projekt musi mieć pracownika itp.

### Przykładowe klasy w projekcie

Należy zwrócić uwagę na poddział obowiązków klas w kodzie. Sugerowane minimum:

- obsługa programu z CLI (punkt wejścia do aplikacji - funkcja `main()`),
- parsowanie pliku wejściowego,
- semantyczna reprezentacja danych (POJO),
- implementacja algorytmu
- prezentacja rezultatu działania algorytmu (np. output, zapis do pliku,…)

### Java Stream API

**Bardzo zalecane** jest zastąpienie standardowych pętli `for` lub `while` (i tworzenie dodatkowych zmiennych do inkrementacji indeksów) poprzez mechanizm [Stream API](https://www.baeldung.com/java-8-streams) (dostępne od Java 8). Jego użycie sprawia, że kod jest bardziej zwięzły oraz ma funkcyjną naturę.

​
## Grupa A
Problem jaki należy rozwiązać to stworzenie inteligentnego systemu wspomagającego konferencje branżowe.
Standardowo podczas konferencji uczestnicy sami zajmują się nawiązywaniem relacji i nowych kontaktów. Nie wszyscy jednak wiedzą o sobie wystarczająco dużo, bądź mają pewne cechy osobowości które utrudniają im nawiązanie takich  relacji. W tym zadaniu zostanie stworzony specjalny algorytm który wniesie dodatkową wartość do takich spotkań.
Warunkiem wstępnym jest to aby każdy uczestnik określił zarówno (1) posiadane przez siebie atrybuty jaki i (2) poszukiwane cechy. Przykładowo osoba może być być inwestorem i znawcą sztuki, ale na konferencji z pewnych powodów będzie jest szczególnie zainteresowany rozmową także z inwestorem, ale także z potencjalnym programistą.

###Przykładowy plik wejściowy
Algorytm jako wejście otrzymuje listę gości, gdzie każdy z nich jest opisany poprzez (1) posiadane jak i (2) poszukiwane cechy (oddzielone białym symbolem \t).
+1	DEVELOPER	INVESTOR,DEVELOPER
+2	INVESTOR	SALES,MARKETING
+3	DEVELOPER	DEVELOPER,ARCHITECT
+4	PROJECT_MANAGER	DEVELOPER,ARCHITECT,PROBLEM_SOLVER,DESIGNER
+5	DESIGNER,MARKETING	PROJECT_MANAGER,INVESTOR
+6	ARCHITECT	INVESTOR,PROJECT_MANAGER,DEVELOPER
+7	INVESTOR	INVESTOR
+8	SALES	INVESTOR,PROJECT_MANAGER
+9	DEVELOPER	DEVELOPER,PROBLEM_SOLVER,ARCHITECT
+10	DEVELOPER	MARKETING,SALES
+11	PROJECT_MANAGER	ARCHITECT,PROBLEM_SOLVER
+12	PROBLEM_SOLVER	SALES
+13	MARKETING	MARKETING,SALES


Jego wynikiem jest zasugerowanie każdemu uczestnikowi 5 osób które będą dla niego interesujące. Kryterium dopasowania (funkcja celu) tak więc jest miara spełniania oczekiwań użytkowników konferencji i powinna być ona zmaksymalizowana w trakcie działania algorytmu.
