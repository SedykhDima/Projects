import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.*;

/**
 * Created by Develop on 10.02.2016.
 */
public class CellListTest {
    private final CellList cellListTest = new CellList(new FirefoxDriver());

    @Test
    public void user01CanOpenPage(){ //01 для порядка теста, если тест не первый, то смысла в нём нет.
        cellListTest.loadPage();
        assertEquals(cellListTest.getCurrentURL(),
                "http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCellList");
    }

    @Test
    public void userCanCreateContact() {
        cellListTest.loadPage();
        cellListTest.createContact("Dima", "Sedykh", Category.BUSINESSES, "03 19 1994", "345 Ekaterinburg City");
        assertEquals(cellListTest.getCountContacts(), 251);
    }

    @Test
    public void userCanUpdateContact() {
        cellListTest.loadPage();
        cellListTest.updateContact("John", "Alvarez", Category.CONTACTS, "12 28 1987", "56 New York");
        assertEquals(cellListTest.getNameContactOne(), "John Alvarez");
        assertEquals(cellListTest.getCountContacts(), 250);
    }

    @Test
    public void userCanGenerateFiftyContacts() {
        cellListTest.loadPage();
        cellListTest.generateFiftyContacts();
        assertEquals(cellListTest.getCountContacts(), 300);
    }

    @Test
    public void userCanSelectAnyContact() {
        cellListTest.loadPage();
        cellListTest.goToIndexContact(250);
        assertEquals(cellListTest.getCurrentCountContact(), 250);
    }

    @After
    public void driverQuit() {
        cellListTest.driverQuit();
    }
}
