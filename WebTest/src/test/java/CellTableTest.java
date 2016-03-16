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

    @Test
    public void userCanGoToEndPageAndGoBack(){
        cellTableTest.goToEndPage();
        assertEquals(cellTableTest.getActuallyCountContactsPage(), 250);
        cellTableTest.goToFirstPage();
        assertEquals(cellTableTest.getActuallyCountContactsPage(), 15);
    }

    @Test
    public void userCanSortedContacts() {
        cellTableTest.sortedByFirstName();
        assertTrue((cellTableTest.getFirstNameContact().compareTo(cellTableTest.getDownField())) < 0);
        cellTableTest.sortedByLastName();
        assertTrue((cellTableTest.getLastNameContact().compareTo(cellTableTest.getDownField())) < 0);
        cellTableTest.sortedByAddress();
        assertTrue((cellTableTest.getAddress().compareTo(cellTableTest.getDownField())) < 0);
    }

    @Test
    public void userCanCheckAllContacts() {
        int count_page = cellTableTest.getCountContactsAllPage() / cellTableTest.getActuallyCountContactsPage();
        for (int i = 0; i <= count_page; i++) {
            int k = 0;
            List<WebElement> trueElement = cellTableTest.getAllCheckbox();
            for (WebElement element : cellTableTest.getAllCheckbox()) {
                assertFalse(trueElement.get(k).isSelected());
                element.click();
                assertTrue(trueElement.get(k).isSelected());
                k++;
            }
            cellTableTest.nextPage();
        }
    }

    @Test
    public void userCanEditContact() {
        cellTableTest.editContact("Dima", "Sedykh", Category.COWORKERS);
        assertEquals(cellTableTest.getFirstNameContact(), "Dima");
        assertEquals(cellTableTest.getLastNameContact(), "Sedykh");
        assertEquals(cellTableTest.getCategory(), Category.COWORKERS);
    }

    @Test
    public void userCanNotEditAddress() {
        String address = cellTableTest.getAddress();
        cellTableTest.editAddress("545 Stroiteley street");
        assertEquals(address, cellTableTest.getAddress());
    }

    @After
    public void driverQuit() {
        cellTableTest.driverQuit();
    }
}
