import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Develop on 11.02.2016.
 */
public class CellList {
    private final WebDriver DRIVER;

    @FindBy(className = "GNHGC04CJJ") //как мне кажется, тут я попытался реазизовать компонентный подход к локации элементов.
    private WebElement tableСontact;

    @FindBy(xpath = "//input[@type='text']")
    private WebElement firstName;

    @FindBy(xpath = "//tr[3]/td[2]/input")
    private WebElement lastName;

    @FindBy(xpath = "//tr[4]/td[2]/select")
    private WebElement category;

    @FindBy(xpath = "//tr[5]/td[2]/input")
    private WebElement date;

    @FindBy(xpath = "//div[2]/div/div[3]/div/div/div")
    @CacheLookup
    private WebElement background;

    @FindBy(xpath = "//textarea")
    private WebElement address;

    @FindBy(xpath = "//button[2]")
    @CacheLookup
    private WebElement buttonCreateContact;

    @FindBy(xpath = "//button")
    @CacheLookup
    private WebElement buttonUpdateContact;

    @FindBy(xpath = "//td/div[2]")
    private WebElement countContacts;

    @FindBy(xpath = "//td[2]/button")
    @CacheLookup
    private WebElement buttonGenerateFiftyContacts;

    /*
    * Конструктор принимает параметр WebDriver - любой поддерживаемый драйвер браузера.
    * 1. Инициализирует текущий драйвер с переданным драйвером.
    * 2. Инициализирует текущий обьект с переданным драйвером.
     */
    public CellList(WebDriver driver){
        this.DRIVER = driver;
        PageFactory.initElements(driver, this);
    }

    /*
    * Метод создает контакт с входными параметрами:
    * firstName - Имя
    * lastName - Фамилия
    * category - Категория (enum Category)
    * date - дата (формат:дд мм гггг)
    * address - адрес (формат:почтовый_индекс город)
     */
    public void createContact(String firstName, String lastName, String category, String date, String address) {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        new Select(this.category).selectByVisibleText(category);
        this.date.sendKeys(date);
        this.address.sendKeys(address);
        background.click();
        buttonCreateContact.click();
    }

    /*
    * Обновляет контакт с заданным индексом(index) с входными параметрами:
    * firstName - Имя
    * lastName - Фамилия
    * category - Категория (enum Category)
    * date - дата (формат:дд мм гггг)
    * address - адрес (формат:почтовый_индекс город)
    * index - номер контакта по списку
     */
    public void updateContact(String firstName, String lastName, String category, String date, String address, int index) {
        getContactElement(index).click();
        clearContactData(this.firstName).sendKeys(firstName);
        clearContactData(this.lastName).sendKeys(lastName);
        new Select(this.category).selectByVisibleText(category);
        clearContactData(this.date).sendKeys(date);
        clearContactData(this.address).sendKeys(address);
        background.click();
        buttonUpdateContact.click();
    }

    private WebElement clearContactData(WebElement element){
        element.clear();
        return element;
    }

    /*
    * 1. Загружает страницу браузера с заданным адресом.
    * 2. Ждет прогрузки элементов на странице с задержкой до 8 секунд.
     */
    public void loadPage(){
        DRIVER.navigate().to("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCellList");
        DRIVER.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS); // может это особенность моего ПК, но без задержки эллементы не успевают загрузиться
    }

    /*
    * Возвращает адрес текущей, открытой страницы браузера.
     */
    public String getCurrentURL(){
        return DRIVER.getCurrentUrl();
    }

    /*
    * Возвращает текущее количество контактов страницы.
     */
    public int getCountContacts(){
        String countStr = countContacts.getText();
        return Integer.parseInt(countStr.substring(countStr.indexOf(':')+1, countStr.length()).trim());
    }

    /*
    * Создает пятьдесят новых, случайных контактов.
    * При каждом создании контакты различны и случайны.
     */
    public void generateFiftyContacts(){
        buttonGenerateFiftyContacts.click();
    }

    /*
    * Завершает работу со страницей браузера.
     */
    public void driverQuit(){
        DRIVER.quit();
    }

    /*
    * Принимает параметр index(номер контакта в списке).
    * Последовательно, начиная с первого контакта, переходит к контакту с данным index.
     */
    public void goToIndexContact(int index){
        tableСontact.findElement(By.tagName("table")).click();
        Actions downKey = new Actions(DRIVER);
        for (int i = 1; i <= index; i++) {
            downKey.sendKeys(Keys.ARROW_DOWN).perform();
        }
    }

    /*
    * Возвращает количество контактов, которые можно выбрать.
     */
    public int getCountSelectedContacts() {
        goToIndexContact(getCountContacts()); //используется для загрузки контактов в кэш table_contact (изначально в кэше 30 контактов).
        return tableСontact.findElements(By.tagName("table")).size();
    }


    private WebElement getContactElement(int index) {
        goToIndexContact(getCountContacts()); //используется для загрузки контактов в кэш table_contact (изначально в кэше 30 контактов).
        List<WebElement> list = tableСontact.findElements(By.tagName("table"));
        return list.get(index-1);
    }

    /*
    * Принимает параметр index (номер контакта в списке).
    * Возвращает все данные, в виде текста, который содержит контакт с заданным index.
     */
    public String getTextDataContact(int index) {
        String dataContact = getContactElement(index).getText();
        return dataContact.replaceAll("\n", " ");
    }
}
