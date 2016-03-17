import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;


/**
 * Created by Develop on 11.02.2016.
 */
public class CellTableTest {
    private final CellTable cellTableTest = new CellTable(new FirefoxDriver());

    @Before
    public void openPage() {
        cellTableTest.loadPage();
    }

    /*
    * TestCase userCanGoToEndPageAndGoBack
    * 1. Получаем количество контактов первой страницы
    * 2. Получаем количество контактов последней страницы
    * 3. Переход к последней странице
    * 4. Проверка: количество контактов текущей страницы сравниваем с количеством контактов последней страницы. Ожидаемый результат: true (Равны)
    * 5. Переход к первой странице
    * 6. Проверка: количество контактов текущей страницы сравниваем с количеством контактов первой страницы. Ожидаемый результат: true (Равны)
     */
    @Test
    public void userCanGoToEndPageAndGoBack(){
        int countContactFirstPage = cellTableTest.getActuallyCountContactsPage();
        int countContactEndPage = cellTableTest.getCountContactsAllPage();
        cellTableTest.goToEndPage();
        assertEquals(cellTableTest.getActuallyCountContactsPage(), countContactEndPage);
        cellTableTest.goToFirstPage();
        assertEquals(cellTableTest.getActuallyCountContactsPage(), countContactFirstPage);
    }

    /*
    * TestCase userCanSortedContact
    * 1. Сортируем контакт по имени.
    * 2. Проверка: Сравниваем текущее имя контакта с предидущим, Ожидаемый: результат true (Больше или равен - для контактов с одинаковыми именами)
    * 3. Сортируем контакт по фамилии.
    * 4. Проверка: Сравниваем текущую фамилию контакта с предидущей, Ожидаемый: результат true (Больше или равен - для контактов с одинаковыми фамилиями)
    * 5. Сортируем контакт по адресу.
    * 6. Проверка: Сравниваем текущий адрес контакта с предидущим, Ожидаемый: результат true (Меньше или равен - для контактов с одинаковыми фамилиями)
     */
    @Test
    public void userCanSortedContacts() {
        cellTableTest.sortedByFirstName();
        for (int i = 2; i <= cellTableTest.getActuallyCountContactsPage(); i++){
            assertTrue(cellTableTest.getFirstNameContact(i).compareTo((cellTableTest.getFirstNameContact(i - 1))) >= 0);
        }
        cellTableTest.sortedByLastName();
        for (int i = 2; i <= cellTableTest.getActuallyCountContactsPage(); i++){
            assertTrue(((cellTableTest.getLastNameContact(i)).compareTo((cellTableTest.getLastNameContact(i - 1)))) >= 0);
        }
        cellTableTest.sortedByAddress();
        for (int i = 2; i <= cellTableTest.getActuallyCountContactsPage(); i++){
            assertTrue(((cellTableTest.getAddress(i)).compareTo((cellTableTest.getAddress(i - 1)))) <= 0);
        }
    }

    /*
    * TestCase userCanCheckAllContactsAndSafeCheckAfterSorted
    * 1. Получаем текущее количество доступных страниц
    * 2. Последовательно выделяем все контакты доступных страниц
    * 3. Переходим к первой странице
    * 4. Сортируем контакты по имени
    * 5. Проверка: Последовательно проверяем выделение всех контактов доступных страниц. Ожидаемый результат: true (Выделены)
     */
    @Test
    public void userCanCheckAllContactsAndSafeCheckAfterSorted() {
        int count_page = cellTableTest.getCountContactsAllPage() / cellTableTest.getActuallyCountContactsPage();
        for (int i = 0; i <= count_page; i++) {
            int k = 0;
            List<WebElement> trueElement = cellTableTest.getAllCheckbox();
            for (WebElement element : cellTableTest.getAllCheckbox()) {
                if (trueElement.get(k).isSelected() == false) {
                    element.click();
                }
                k++;
            }
            cellTableTest.nextPage();
        }
        cellTableTest.goToFirstPage();
        cellTableTest.sortedByFirstName();
        for (int i = 0; i <= count_page; i++) {
            List<WebElement> trueElement = cellTableTest.getAllCheckbox();
            for (WebElement e : trueElement) {
                assertTrue(e.isSelected());
            }
            cellTableTest.nextPage();
        }
    }

    /*
    * TestCase userCanEditContact
    * 1. Создать контакт с входными данными:
    * Имя, Фамилия, Категория(enum Category.Категория), номер контакта(формат int).
    * Номер контакта может быть произвольным в диапазоне от 1 до cellTableTest.getCountContactAllPage - возвращает текущее количество контактов.
    * 2. Поверка: Получаем выходные данные контакта с указанным номером (Имя Фамилия Адрес).
    * Выходные данные сравниваем с входными. Ожидаемый результат: true (Равны).
     */
    @Test
    public void userCanEditContact() {
        cellTableTest.editContact("Dima", "Sedykh", Category.COWORKERS, 1);
        assertEquals(cellTableTest.getFirstNameContact(1), "Dima");
        assertEquals(cellTableTest.getLastNameContact(1), "Sedykh");
        assertEquals(cellTableTest.getCategory(1), Category.COWORKERS);
    }

    /*
    * TestCase userCanNotEditAddress
    * 1. Получаем адрес контакта с текущим номером, до редактирования
    * Номер контакта может быть произвольным в диапазоне от 1 до cellTableTest.getCountContactAllPage - возвращает текущее количество контактов.
    * 2. Редактируем адрес контакта на новый.
    * 3. Поверка: Сравниваем адрес контакта до редактирования с текущим адресом. Ожидаемый результат: true (Равны).
     */
    @Test
    public void userCanNotEditAddress() {
        String address = cellTableTest.getAddress(1);
        cellTableTest.editAddress(1, "545 Stroiteley street");
        assertEquals(address, cellTableTest.getAddress(1));
    }

    @After
    public void driverQuit() {
        cellTableTest.driverQuit();
    }
}
