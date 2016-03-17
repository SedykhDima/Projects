import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.*;

/**
 * Created by Develop on 10.02.2016.
 */
public class CellListTest {
    private final CellList cellListTest = new CellList(new FirefoxDriver());

    /*
    * TestCase user01CanOpenPage
    * 1. Загрузка страницы браузера содержащей CellList
    * 2. Проверка: Адрес загруженной страницы сравниваем с нужным нам адресом. Ожидаемый результат: true (Равны).
     */
    @Test
    public void user01CanOpenPage(){ //01 для порядка теста, если тест не первый, то смысла в нём нет.
        cellListTest.loadPage();
        assertEquals(cellListTest.getCurrentURL(),
                "http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCellList");
    }

    /*
    * TestCase userCanCreateContact
    * 1. Загрузка страницы браузера содержащей CellList
    * 2. Создать контакт с входными данными: Имя, Фамилия, Категория(enum Category.Категория), Дата(формат:дд мм гггг), Адрес(формат:индекс город).
    * 3. Созданный контакт является последним, поэтому берём текущее количество контактов и переходим к последнему для проверки
    * Проверка: Получаем выходные данные контакта (Имя Фамилия Адрес). Выходные данные сравниваем с входными. Ожидаемый результат: true (Равны).
     */
    @Test
    public void userCanCreateContact() {
        cellListTest.loadPage();
        cellListTest.createContact("Dima", "Sedykh", Category.BUSINESSES, "03 19 1994", "345 Ekaterinburg City");
        assertEquals(cellListTest.getTextDataContact(cellListTest.getCountContacts()), "Dima Sedykh 345 Ekaterinburg City");
    }

    /*
    * TestCase userCanUpdateContact
    * 1. Загрузка страницы браузера содержащей CellList
    * 2. Обновить контакт с входными данными:
    * Имя, Фамилия, Категория(enum Category.Категория), Дата(формат:дд мм гггг), Адрес(формат:индекс город), номер контакта(формат int).
    * Номер контакта может быть произвольным в диапазоне от 1 до cellListTest.getCountContacts() - возвращает текущее количество контактов.
    * 3. Поверка: Получаем выходные данные контакта с указанным номером (Имя Фамилия Адрес).
    * Выходные данные сравниваем с входными. Ожидаемый результат: true (Равны).
     */
    @Test
    public void userCanUpdateContact() {
        cellListTest.loadPage();
        cellListTest.updateContact("John", "Alvarez", Category.CONTACTS, "12 28 1987", "56 New York", 1);
        assertEquals(cellListTest.getTextDataContact(1), "John Alvarez 56 New York") ;
    }

    /*
    * TestCase userCanGenerateFiftyContacts
    * 1. Загрузка страницы браузера содержащей CellList
    * 2. Получить количество контактов до операции создания пятидесяти контактов.
    * 3. Создать пятьдесят контактов.
    * 4. Проверка: Сравнить данные текущего количества контактов и контактов до операции + 50(подразумевается вновь созданных контактов).
    * Ожидаемый результат: true (Равны)
     */
    @Test
    public void userCanGenerateFiftyContacts() {
        cellListTest.loadPage();
        int beforeCountContacts = cellListTest.getCountContacts();
        cellListTest.generateFiftyContacts();
        assertEquals(cellListTest.getCountContacts(), beforeCountContacts+50);
    }

    /*
    * TestCase userCanSelectAllContacts
    * 1. Загрузка страницы браузера содержащей CellList
    * 2. Получение количества выбранных эллементов.
    * 3. Сравнение количества выбранных эллементов и текущего количества контактов. Ожидаемый результат: true (Равны).
     */
    @Test
    public void userCanSelectAllContacts() {
        cellListTest.loadPage();
        int countSelectedContacts = cellListTest.getCountSelectedContacts();
        assertEquals(countSelectedContacts, cellListTest.getCountContacts());
    }

    @After
    public void driverQuit() {
        cellListTest.driverQuit();
    }
}
