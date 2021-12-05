import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ScenarioTest {
    private WebDriver driver;

    @BeforeTest
    public void setUp() { driver = new ChromeDriver(); }

    @Test(priority = 1, invocationCount = 5)
    public void successMessageIsDisplayed() {
        new Contact(driver)
                .loadPage().navigateToContact().fillInContactDetails().clickSubmit()
                        .verifySuccessMessage().captureScreen("successMessageContact");
    }

    @Test(priority = 2)
    public void itemsAppearInCart() {
       new Shop(driver)
                .navigateToShop().buyItems()
                        .verifyCart().captureScreen("itemsInCart");
    }

    @Test(priority = 3)
    public void itemsSubtotalInCart() {
        new Shop(driver)
                .emptyCart().navigateToShop().buyItemsForSubtotal()
                        .verifyItemSubtotals().captureScreen("itemSubtotals");
    }

    @AfterTest
    public void cleanUp() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
