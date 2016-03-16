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
public class CellTable {
    private final WebDriver DRIVER;

    @FindBy(xpath = "//div[3]/div/div/div/table/tbody/tr/td/table/tbody/tr/td[2]")
    private WebElement first_name;

    @FindBy(xpath = "//td[3]/div")
    private WebElement last_name;

    @FindBy(xpath = "//td[4]/div/select")
    private WebElement category;

    @FindBy(xpath = "//input")
    private List<WebElement> checkbox;

    @FindBy(xpath = "//td[5]/img")
    @CacheLookup
    private WebElement button_to_end_page;

    @FindBy(css = "th.GNHGC04CHD.GNHGC04CFE")
    @CacheLookup
    private WebElement sort_first_name;

    @FindBy(css = "td.GNHGC04CNH > div.gwt-HTML")
    private WebElement count_page;

    @FindBy(xpath = "//td[4]/img")
    @CacheLookup
    private WebElement next_page;

    @FindBy(xpath = "//tr[2]/td/table/tbody/tr/td/img")
    private WebElement button_first_page;

    @FindBy(xpath = "//th[3]")
    private WebElement sort_last_name;

    @FindBy(xpath = "//th[5]")
    private WebElement sort_address;

    @FindBy(xpath = "//td[5]/div")
    private WebElement address;

    public CellTable(WebDriver driver){
        this.DRIVER = driver;
        PageFactory.initElements(driver, this);
    }

    public void editContact(String first_name, String last_name, String category) {
        editSpecialField(this.first_name, first_name);
        editSpecialField(this.last_name, last_name);
        new Select(this.category).selectByVisibleText(category);
    }

    public void goToEndPage(){
        button_to_end_page.click();
    }

    public void sortedByFirstName(){
        sort_first_name.click();
    }

    public void sortedByLastName() { sort_last_name.click(); }

    public void sortedByAddress() { sort_address.click();}

    public String getDownField() {
        new Actions(DRIVER).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_RIGHT).perform();
        return DRIVER.switchTo().activeElement().getText();
    }

    public String getFirstNameContact(){
        return first_name.getText();
    }

    public String getLastNameContact() { return last_name.getText(); }

    public String getAddress() { return address.getText(); }

    public String getCategory() { return new Select(category).getFirstSelectedOption().getText(); }

    public void loadPage() {
        DRIVER.navigate().to("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCellTable");
        DRIVER.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public int getCountContactsAllPage() {
        String countStr = count_page.getText();
        return Integer.parseInt(countStr.substring(countStr.indexOf('f')+1, countStr.length()).trim());
    }

    public int getActuallyCountContactsPage() {
        String countStr = count_page.getText();
        return Integer.parseInt(countStr.substring(countStr.indexOf('-')+1, countStr.indexOf(' ')).trim());
    }

    public void nextPage() {
        new Actions(DRIVER).sendKeys(Keys.PAGE_DOWN).perform();
    }

    private void editSpecialField(WebElement element, String field) {
        Actions actions = new Actions(DRIVER);
        actions.sendKeys(element, Keys.ENTER).perform();
        actions.sendKeys(element, field).perform();
        actions.sendKeys(element, Keys.ESCAPE).perform();
    }

    public void driverQuit() {
        DRIVER.quit();
    }

    public List<WebElement> getAllCheckbox() {
        return checkbox;
    }

    public void goToFirstPage() {
        button_first_page.click();
    }

    public void editAddress(String address) {
        this.address.sendKeys(address);
    }
}
