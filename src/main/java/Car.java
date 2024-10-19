import java.util.Scanner;

public class Car {

    String name;
    int speed;

    public Car(String name, int speed) {
        this.name = name;
        this.speed = speed;
    }

    /**
     * Создаем машину по введенным пользователем значениям параметров "Наименование" и "Скорость"
     *
     * @param scanner Ссылка на объект Scanner
     * @return Ссылка на объект Car
     */

    public static Car createCarFromUserInput(Scanner scanner) {
        int minCarSpeed = 1;
        int maxCarSpeed = 250;

        String carName = InputFeatures.getCorrectStringFromUserInput(scanner,
                "Название автомобиля");

        int carSpeed = InputFeatures.getCorrectIntFromUserInput(scanner,
                "Скорость автомобиля, км/ч", minCarSpeed, maxCarSpeed);

        return new Car(carName, carSpeed);
    }

}
