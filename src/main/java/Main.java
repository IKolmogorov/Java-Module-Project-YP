import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int predefinedRaceDuration = 24;
        int predefinedNumberOfParticipants = 3;
        boolean showDetailedResults = true;

        Scanner scanner = new Scanner(System.in);

        // Поприветствуем пользователя
        Race.printWelcome();

        // Создадим гонку и получим участников
        Race race = Race.createRaceFromUserInput(scanner, predefinedRaceDuration,
                predefinedNumberOfParticipants);

        // Выясним победителя(ей) гонки
        race.findOutTheResults();

        // Выведем результаты гонки в консоль
        if (showDetailedResults) {
            race.printDetailedRaceResults();
        } else {
            race.printRaceWinners();
        }

        scanner.close();
    }
}
