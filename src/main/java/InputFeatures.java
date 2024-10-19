import java.util.InputMismatchException;
import java.util.Scanner;

public class InputFeatures {

    /**
     * Обеспечивает ввод от пользователя значения параметра с типом int в нужном диапазоне
     *
     * @param scanner       Ссылка на объект Scanner
     * @param parameterName Наименование параметра, значение которого вводит пользователь
     * @param minValue      Минимальное допустимое значение вводимого параметра
     * @param maxValue      Максимальное допустимое значение вводимого параметра
     * @return Возвращает Int, ввод которого произведен с учетом установленных ограничений
     */
    public static int getCorrectIntFromUserInput(Scanner scanner, String parameterName,
                                                 int minValue, int maxValue) {
        int returnValue = 0;
        boolean correctInput = false;

        while (!correctInput) {
            try {
                System.out.printf("Введите %s от %d до %d и нажмите 'Enter': ",
                        parameterName, minValue, maxValue);
                returnValue = scanner.nextInt();

                if (returnValue < minValue | returnValue > maxValue) {
                    System.out.printf("Ошибка! %s должно быть от %d до %d\n",
                            parameterName, minValue, maxValue);
                } else {
                    correctInput = true;
                    scanner.nextLine();
                }

            } catch (InputMismatchException exeption) {
                System.out.printf("Ошибка! %s должно быть целым числом от %d до %d\n",
                        parameterName, minValue, maxValue);
                scanner.nextLine();
            }
        }

        return returnValue;
    }

    /**
     * Обеспечивает ввод от пользователя значения параметра с типом String
     *
     * @param scanner       Ссылка на объект Scanner
     * @param parameterName Наименование параметра, значение которого вводит пользователь
     * @return Возвращает String, ввод которого произведен с учетом установленных ограничений
     */
    public static String getCorrectStringFromUserInput(Scanner scanner, String parameterName) {
        String returnValue = "";
        boolean correctInput = false;

        while (!correctInput) {
            System.out.printf("Введите %s и нажмите 'Enter': ", parameterName);
            returnValue = scanner.nextLine().trim();

            if (returnValue.isEmpty()) {
                System.out.printf("%s не может быть пустым\n", parameterName);
            } else {
                correctInput = true;
            }
        }

        return returnValue;
    }
}
