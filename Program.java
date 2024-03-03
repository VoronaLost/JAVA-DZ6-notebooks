import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Program {

    private static HashMap<Integer, Object> field2 = new HashMap<>();
    private static String menuInfo = "Выберите один из предложенных варантов:\n1 - Поиск по полю 'оперативная память' (Гб)"  +
    "\n2 - поиск по полю 'объём твердотельного накопителя' (ГБ)\n3 - Поиск по модели" +
    "\n4 - поиск по операционной системе\n5 - поиск по цвету ноутбука";
 
    /**
     * Получение от пользователя индекса поля по которому будет проводится фильтрация.
     * В случае выбора не входящий в диапазон ключей (1, 2 ,3 ,4, 5), или ввода нечисловах данных
     * выводится сообщение об ошибке.
     * @param rightEdge
     * Верхняя граница допустимых для ввода значений, отображается при некорректном вводе.
     * @return
     * Ключ по которому будет выбираться необходимое поле для сортировки.
     */
    public static int getInputKey(int rightEdge) {   
        int number = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число: ");
        if (scanner.hasNextInt() && field2.containsKey(number = scanner.nextInt())) {
            return number;
        } else {
            System.out.println("Ошибка ввода! Введите число в заданом Диапазоне (1-" + rightEdge + ")");
            number = 0;
            return number;
        }
    }

/**
 * Получение от пользователей запроса для фильтрации по целочисленному полю.
 * В случае ввода не целочисленных значений выводится ошибка.
 * @param indexOfTitle
 * Ссылка на текстовое наименование поля - подсказка пользователю, по какому полю должен составляться запрос.
 * @return
 * Значение, с которым будет сравниваться целочсленное значение поля для фильтрации.
 */
    public static int getInputMin(int indexOfTitle) {   
        int number = -1;
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("\nВведите значение, ниже которого не будет отображаться\nинформация для поля '" + field2.get(indexOfTitle) + "'");
        System.out.print("Ввод: ");
        if (scanner2.hasNextInt()) {
            number = scanner2.nextInt();
            return number;
        } else {
            System.out.print("\nОшибка ввода! Введите целое число: ");
            return number;
        }
    }
/**
 * Получение от пользователей запроса для фильтрации по текстовому полю.
 * @param indexOfTitle
 * Ссылка на текстовое наименование поля - подсказка пользователю, по какому полю должен составляться запрос.
 * @return
 * Строковое значение, для дальнейшей проверки его вхождения в текстовое поле класса Notebook.
 */
    public static String getInputText(int indexOfTitle) {   
        String text;
        Scanner scanner3 = new Scanner(System.in);
        System.out.println("Введите текстовый запрос на английском языке для нахождения совпадения с полем '" + field2.get(indexOfTitle)  + "'");
        text = scanner3.nextLine();
        return text;
    }
/**
 * Формирование запроса пользователя. Реализованы циклы для проверки ввода пользователя (пока не будут введенные верные данные).
 * В зависимости от выбранного индекса выбираются разные методы для поискового запроса. getInputMin - для поиска по значению, 
 * и getInputText - проверка вхождения подстроки в наименование текстовых полей класса Notebook.
 * @return
 * Массив из двух элементов: индекс по которому выбирается поле для поиска
 * и значение по которому будет фильтроваться список ноутбуков.
 */
    public static ArrayList<Object> formRequest() {
        int request = 0;
        int requestMin = -1;
        String requestText;
        ArrayList<Object> requestArray = new ArrayList<Object>();
        System.out.println(menuInfo);
        while (request == 0) {
            request = getInputKey(field2.size());
        }
        requestArray.add(request);
        if (request == 1 || request == 2) {
            while (requestMin == -1) {    
                requestMin = getInputMin(request);
            }
            requestArray.add(requestMin);
            return requestArray;
        } else {
            requestText = getInputText(request);
            requestArray.add(requestText);
            return requestArray;
        }
    }

    /**
     * Заполнение данными экземпляров класса Notebook. Заполнение вспомогательного словаря field2, для вывода подсказок пользователю,
     * в методах считавания данных с терминала. Объединение всех заполненных экземпляров в массив, для обхода сформированного пользователем
     * запроса. В случае если экземпляр удоволетворяет требованиям - он выводится на экран.
     *  
     * @param args
     */
    public static void main(String[] args) {
        Notebook nb1 = new Notebook(16, 512, "Lenovo IdeaPad Gaming 3", "Windows 10", "black");
        Notebook nb2 = new Notebook(8, 512, "Apple MacBook Pro 14 M3", "Mac OS (Monterey)", "grey");
        Notebook nb3 = new Notebook(8, 256, "ASUS VivoBook 15", "Linux Endless", "black");
        Notebook nb4 = new Notebook(8, 256, "Acer Aspire 3", "Windows 10", "silver");
        Notebook nb5 = new Notebook(32, 1024, "Asus ROG Flow X13", "Windows 11", "black");
        field2.put(1, "оперативная память");
        field2.put(2, "твердотельный накопитель");
        field2.put(3, "модель");
        field2.put(4, "операционная система");
        field2.put(5, "цвет");
        ArrayList<Notebook> listofNotebooks = new ArrayList<>(Arrays.asList(nb1, nb2, nb3, nb4, nb5));
        ArrayList<Object> sendRequest = new ArrayList<>(formRequest());
        int count = 0;
        System.out.println("________");
        for (Notebook x : listofNotebooks) {
            if (x.searchCheck(sendRequest)) {
                System.out.println(x);
            } else count++;
        }
        if (count == 5) System.out.println("По данному запросу ничего не найденно");
        System.out.println("________");
    }
}
