import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Race {

    int duration;
    ArrayList<Car> participants;
    ArrayList<RaceResult> results = new ArrayList<>();
    ArrayList<RaceResult> winners = new ArrayList<>();

    public Race(int duration, ArrayList<Car> participants) {
        this.duration = duration;
        this.participants = participants;
    }

    /**
     * Выводит в консоль приветствие пользователю
     */
    public static void printWelcome() {
        System.out.println("-----------------------------------------------");
        System.out.println("Добро пожаловать на виртуальную гонку в Лемане:");
        System.out.println("-----------------------------------------------");
    }

    /**
     * Запрашивает кол-во участников гонки, параметры машин участников, создает объект Race
     * Если не получены значения параметров "Количестов участников" и "Продолжительность", то
     * запрашивает их у пользователя.
     *
     * @param scanner Ссылка на объект Scanner
     * @param predefinedDuration Продолжительность гонки
     * @param predefinedNumberOfParticipants Количество участников гонки
     * @return Race
     */
    public static Race createRaceFromUserInput(Scanner scanner, int predefinedDuration,
                                               int predefinedNumberOfParticipants) {

        ArrayList<Car> participants = new ArrayList<>();
        int duration, numberOfParticipants;
        int maxRaceDuration = 24;

        // Запросить у пользователя длительность гонки, если не задано значение по-умолчанию
        if (predefinedDuration == 0) {
            duration = InputFeatures.getCorrectIntFromUserInput(scanner,
                    "Длительность гонки, ч", 1, maxRaceDuration);
        } else {
            duration = predefinedDuration;
        }

        // Запросить у пользователя кол-во участников, если не задано значение по-умолчанию
        if (predefinedNumberOfParticipants == 0) {
            numberOfParticipants = InputFeatures.getCorrectIntFromUserInput(scanner,
                    "Кол-во участников", 1, 10);
        } else {
            numberOfParticipants = predefinedNumberOfParticipants;
        }

        // Запросить у пользователя наименование и скорость машин-участников
        for (int i = 1; i <= numberOfParticipants; i++) {
            Car curCar = Car.createCarFromUserInput(scanner);
            participants.add(curCar);
            System.out.printf("В список участников добавлен автомобиль '%s', скорость = %d км/ч\n",
                    curCar.name, curCar.speed);
        }

        return new Race(duration, participants);
    }

    /**
     * Определяет победителей гонки. Учитывает, что победителей м.б. несколько
     */
    public void findOutTheResults() {
        // Рассчитаем дистанцию и создадим результаты гонки
        for (Car currentCar : this.participants) {
            RaceResult newRaceResult = new RaceResult(currentCar,
                    this.duration * currentCar.speed);
            this.results.add(newRaceResult);
        }

        // Отсортируем результаты в порядке уменьшения дистанции
        Comparator<RaceResult> compareByDistance = Comparator.comparing(RaceResult::getRaceDistance);

        this.results = this.results.stream().sorted(compareByDistance.reversed())
                .collect(Collectors.toCollection(ArrayList::new));

        // Присвоим места с учетом возможных одинаковых результатов
        int currentPlace = 1;
        int lastDistance = 0;

        for (RaceResult currentRaceResult : this.results) {
            if (lastDistance == 0) {
                currentRaceResult.racePlace = 1;
                lastDistance = currentRaceResult.raceDistance;
            } else {
                if (currentRaceResult.raceDistance == lastDistance) {
                    currentRaceResult.racePlace = currentPlace;
                } else {
                    currentPlace += 1;
                    currentRaceResult.racePlace = currentPlace;
                }
            }

            if (currentRaceResult.racePlace == 1) {
                this.winners.add(currentRaceResult);
            }
        }
    }

    /**
     * Выводит в консоль информацию только о победивших машинах
     */
    public void printRaceWinners() {
        System.out.println("-----------------------------------------------");
        System.out.println("Результаты гонки:");
        System.out.println("-----------------------------------------------");

        if (this.winners.size() == 1) {
            for (RaceResult curRaceResult : this.winners) {
                double kmDistance = (double) curRaceResult.raceDistance / 1000;

                System.out.printf("Победитель: автомобиль '%s', дистанция %.2f км, скорость %d км/ч\n",
                        curRaceResult.car.name, kmDistance, curRaceResult.car.speed);
            }
        } else {
            System.out.println("Первое место разеделено между несколькими участниками:");
            for (RaceResult curRaceResult : this.winners) {
                double kmDistance = (double) curRaceResult.raceDistance / 1000;

                System.out.printf("Автомобиль '%s', дистанция %.2f км, скорость %d км/ч\n",
                        curRaceResult.car.name, kmDistance, curRaceResult.car.speed);
            }
        }
    }

    /**
     * Выводит в консоль информацию обо всех участниках с местами и дистанцией
     */
    public void printDetailedRaceResults() {
        System.out.println("-----------------------------------------------");
        System.out.println("Результаты гонки:");
        System.out.println("-----------------------------------------------");

        for (RaceResult curRaceResult : this.results) {
            double kmDistance = (double) curRaceResult.raceDistance / 1000;

            System.out.printf("Место %d - расстояние %.2f км, автомобиль %s, скорость %d км/ч\n",
                    curRaceResult.racePlace, kmDistance,
                    curRaceResult.car.name, curRaceResult.car.speed);
        }
    }
}
