import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

/**
 * Created by Develop on 11.02.2016.
 */
public class CellList {
    private final WebDriver DRIVER;

    @FindBy(xpath = "//input[@type='text']")
    private WebElement first_name;

    @FindBy(xpath = "//tr[3]/td[2]/input")
    private WebElement last_name;

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
    private WebElement button_create_contact;

    @FindBy(xpath = "//button")
    @CacheLookup
    private WebElement button_update_contact;

    @FindBy(css = "div.GNHGC04CCB")
    @CacheLookup
    private WebElement contact_number_one;

    @FindBy(xpath = "//td/div[2]")
    private WebElement count_contacts;

    @FindBy(xpath = "//td[2]/button")
    @CacheLookup
    private WebElement button_generate_fifty_contacts;

    @FindBy(xpath = "//td/div/div/div/div/div/table/tbody/tr/td[2]")
    @CacheLookup
    private WebElement name_contact_one;

    public CellList(WebDriver driver){
        this.DRIVER = driver;
        PageFactory.initElements(driver, this);
    }

    public void createContact(String first_name, String last_name, String category, String date, String address) {
        this.first_name.sendKeys(first_name);
        this.last_name.sendKeys(last_name);
        new Select(this.category).selectByVisibleText(category);
        this.date.sendKeys(date);
        this.address.sendKeys(address);
        background.click();
        button_create_contact.click();
    }

    public void updateContact(String first_name, String last_name, String category, String date, String address) {
        this.contact_number_one.click();
        clearContactData(this.first_name).sendKeys(first_name);
        clearContactData(this.last_name).sendKeys(last_name);
        new Select(this.category).selectByVisibleText(category);
        clearContactData(this.date).sendKeys(date);
        clearContactData(this.address).sendKeys(address);
        background.click();
        button_update_contact.click();
    }
    private WebElement clearContactData(WebElement element){
        element.clear();
        return element;
    }

    public void loadPage(){
        DRIVER.navigate().to("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCellList");
        DRIVER.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS); // может это особенность моего ПК, но без задержки эллементы не успевают загрузиться
    }

    public String getCurrentURL(){
        return DRIVER.getCurrentUrl();
    }

    public String getNameContactOne(){
        return name_contact_one.getText();
    }

    public int getCountContacts(){
        String countStr = count_contacts.getText();
        return Integer.parseInt(countStr.substring(countStr.indexOf(':')+1, countStr.length()).trim());
    }

    public void generateFiftyContacts(){
        button_generate_fifty_contacts.click();
    }

    public void driverQuit(){
        DRIVER.quit();
    }

    public void goToIndexContact(int index){
        contact_number_one.click();
        Actions downKey = new Actions(DRIVER);
        try {
            for (int i = 1; i <= index; i++) {
                downKey.sendKeys(Keys.ARROW_DOWN).perform();
                Thread.sleep(150);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getCurrentCountContact(){
        String countStr = count_contacts.getText();
        return Integer.parseInt(countStr.substring(countStr.indexOf('-')+1, countStr.indexOf(':')).trim());
    }


}
