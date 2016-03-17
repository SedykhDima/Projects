import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by Develop on 11.02.2016.
 */
public class CellTable {
    private final WebDriver DRIVER;

    @FindBy(css = "td.GNHGC04CLH")
    private List<WebElement> navigationPanel;

    @FindBy(tagName = "thead")
    private WebElement sortedForm;

    @FindBy(className = "GNHGC04CKJ")
    private WebElement fieldDataContact;

    @FindBy(xpath = "//input")
    private List<WebElement> checkbox;

    @FindBy(css = "td.GNHGC04CNH > div.gwt-HTML")
    private WebElement countPage;


    public CellTable(WebDriver driver){
        this.DRIVER = driver;
        PageFactory.initElements(driver, this);
    }

    /*
    * Редактирует контакт с новыми данными.
    * firstName - Имя
    * lastName - Фамилия
    * category - Категория (enum Category)
    * indexContact - номер контакта по списку
    * Диапазон indexContact от 1 до cellTableTest.getCountContactAllPage - возвращает текущее количество контактов.
     */
    public void editContact(String firstName, String lastName, String category, int indexContact) {
        editSpecialField(getFieldContact(indexContact, FieldContact.FIRST_NAME), firstName);
        editSpecialField(getFieldContact(indexContact, FieldContact.LAST_NAME), lastName);
        getSelect(indexContact).selectByVisibleText(category);
    }

    public void sortedByFirstName(){
        getSortedForm(FieldContact.FIRST_NAME).click();
    } // Сортирует контакт по имени

    public void sortedByLastName() { getSortedForm(FieldContact.LAST_NAME).click(); } // Сортирует контакт по фамилии

    public void sortedByAddress() { getSortedForm(FieldContact.ADDRESS).click();} // Сортирует контакт по адресу

    /*
    * Возвращает имя контакта с указанным номером (indexContact)
    * Диапазон indexContact от 1 до cellTableTest.getCountContactAllPage - возвращает текущее количество контактов.
     */
    public String getFirstNameContact(int indexContact){
        return getFieldContact(indexContact, FieldContact.FIRST_NAME).getText();
    }

    /*
    * Возвращает фамилию контакта с указанным номером (indexContact)
    * Диапазон indexContact от 1 до cellTableTest.getCountContactAllPage - возвращает текущее количество контактов.
     */
    public String getLastNameContact(int indexContact) { return getFieldContact(indexContact, FieldContact.LAST_NAME).getText(); }

    /*
    * Возвращает адрес контакта с указанным номером (indexContact)
    * Диапазон indexContact от 1 до cellTableTest.getCountContactAllPage - возвращает текущее количество контактов.
     */
    public String getAddress(int indexContact) { return getFieldContact(indexContact, FieldContact.ADDRESS).getText(); }

    /*
    * Возвращает категорию контакта с указанным номером (indexContact)
    * Диапазон indexContact от 1 до cellTableTest.getCountContactAllPage - возвращает текущее количество контактов.
     */
    public String getCategory(int indexContact) { return getSelect(indexContact).getFirstSelectedOption().getText(); }

    /*
    * Загрузка страницы браузера с таблицей CellTable
     */
    public void loadPage() {
        DRIVER.navigate().to("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCellTable");
        DRIVER.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /*
    * Возвращает количество контактов всех страниц.
     */
    public int getCountContactsAllPage() {
        String countStr = countPage.getText();
        return Integer.parseInt(countStr.substring(countStr.indexOf('f') + 1, countStr.length()).trim());
    }

    /*
    * Возвращает количество контактов открытой страницы.
     */
    public int getActuallyCountContactsPage() {
        String countStr = countPage.getText();
        return Integer.parseInt(countStr.substring(countStr.indexOf('-') + 1, countStr.indexOf(' ')).trim());
    }

    /*
    * Переходит к следующей странице
     */
    public void nextPage() {
        getNavigationButton(2).click();
    }

    /*
    * Переходит к последней странице
     */
    public void goToEndPage(){
        getNavigationButton(3).click();
    }

    /*
    * Переходит к первой странице
     */
    public void goToFirstPage() {
        getNavigationButton(0).click();
    }


    private void editSpecialField(WebElement element, String field) {
        Actions actions = new Actions(DRIVER);
        actions.sendKeys(element, field).perform();
        actions.sendKeys(Keys.ENTER).perform();
    }

    /*
    * Завершает работу с текущей страницей браузера
     */
    public void driverQuit() {
        DRIVER.quit();
    }

    /*
    * Возвращает список checkbox элементов
     */
    public List<WebElement> getAllCheckbox() {
        return checkbox;
    }

    /*
    * Редактирует адрес контакта с заданым номером (indexContact)
    * Диапазон indexContact от 1 до cellTableTest.getCountContactAllPage - возвращает текущее количество контактов.
    * Входные данные: address в формате(почтовый_индекс город)
     */
    public void editAddress(int indexContact, String address) {
        getFieldContact(indexContact, 4).sendKeys(address);
    }

    private WebElement getSortedForm(int index) {
        List<WebElement> elements = sortedForm.findElements(By.className("GNHGC04CHD"));
        return elements.get(index);
    }

    /*
    * 1 - first_name
    * 2 - last_name
    * 3 - select
    * 4 - address
     */
    private WebElement getFieldContact(int indexField, int indexKey) {
        List<WebElement> all_contacts = fieldDataContact.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        List<WebElement> elements = all_contacts.get(indexField-1).findElements(By.tagName("td"));
        return elements.get(indexKey);
    }

    private Select getSelect(int indexField) {
        List<WebElement> all_contacts = fieldDataContact.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        List<WebElement> elements = all_contacts.get(indexField-1).findElements(By.tagName("td"));
        return new Select(elements.get(3).findElement(By.tagName("select")));
    }

    private WebElement getNavigationButton(int indexKey){
        return navigationPanel.get(indexKey);
    }
}
