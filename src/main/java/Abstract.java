import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Abstract {
    private final static int TIMEOUT = 30;
    protected final WebDriver driver;
    private final WebDriverWait wait;
    public Abstract(WebDriver driver)  {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT);
    }

    public void get(String url) {
        driver.get(url);
    }

    public void waitFor(By by) {
        wait.until(visibilityOfElementLocated(by));
    }

    public void clickOn(By by) { driver.findElement(by).click(); }

    public void typeIn(By by, String string) {
        driver.findElement(by).sendKeys(string);
    }

    public void buy(By item, int amount) {
        for (int i = 0; i < amount; i++) {
            clickOn(item);
        }
    }

    public void elementShouldBeDisplayed(By by) {
        WebElement element = driver.findElement(by);
        Assert.assertTrue(element.isDisplayed());
    }

    public void checkItemSubtotal(int xpathNo, float amount) {



            String priceString = driver.findElement(By.xpath("//tbody/tr[" + xpathNo + "]/td[2]")).getText();
            String priceStringNo$ = priceString.replace("$", "");
            double priceDouble = Double.parseDouble(priceStringNo$);

            String subtotalString = driver.findElement(By.xpath("//tbody/tr[" + xpathNo + "]/td[4]")).getText();
            String subtotalStringNo$ = subtotalString.replace("$", "");
            double actualSubtotal = Double.parseDouble(subtotalStringNo$);

            double expectedSubtotal = priceDouble * amount;
            System.out.println(actualSubtotal + " " + expectedSubtotal);
            Assert.assertEquals(actualSubtotal, expectedSubtotal);
        }

}
