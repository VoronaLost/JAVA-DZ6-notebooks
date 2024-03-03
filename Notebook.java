import java.util.ArrayList;
import java.util.HashMap;

public class Notebook {
    private int ram, ssd;
    private String model, os, color;
    private HashMap<Integer, Object> field = new HashMap<>();

    @Override
    public String toString() {
        return String.format("ram:%d, ssd:%d, model:%s, os:%s, color:%s", ram, ssd, model, os, color);
    }
/**
 * Проверка, подходит ли конкретное поле под параметры фильтрации. В случае если принятое
 * значение второго элемента (index:1) массива является целочисленным, то сравниваются значение аргумента
 * и целочисленного поля массива, в ином случае проверяется входит ли аргумент в строку текстового поля.
 * @param keyAndValue массив из двух элементов. 1-ый (index:0) - ключ по которому определяется поле, 2-ой -
 * (index:1) - сформированный пользователем запрос для фильтрации по выбранному полю.
 * @return
 * true - поле входит в запрос, false - поле не соответствует критериям запроса.
 */
    public boolean searchCheck(ArrayList<Object> keyAndValue) {
        int key = (Integer)keyAndValue.get(0);
        if (keyAndValue.get(1) instanceof Integer) {
            int valueforcheck = (Integer)keyAndValue.get(1);
            return ((Integer)field.get(key) >= valueforcheck);
        } else {
        } 
        return ((field.get(key)).toString()).contains(keyAndValue.get(1).toString());
    }
/**
 * Заполнение каждого конкретного экземпляра класса, персональными значениям для 5 полей. 
 * Добавление каждого поля в персональный словарь экземпляра - HashMap field
 * @param ram
 * оперативная память
 * @param ssd
 * твердотельный накопитель
 * @param model
 * модель
 * @param os
 * операционная система
 * @param color
 * цвет
 */
    public Notebook(int ram, int ssd, String model, String os, String color) {
        this.ram = ram;
        this.field.put(1, ram);

        this.ssd = ssd;
        this.field.put(2, ssd);

        this.model = model;
        this.field.put(3, model);

        this.os = os;
        this.field.put(4, os);

        this.color = color;
        this.field.put(5, color);
        }
}