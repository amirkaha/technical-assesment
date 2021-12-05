import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import java.io.File;
import java.io.IOException;

public class Shop extends Abstract {
    private final static By SHOP = By.xpath("//a[normalize-space()='Shop']");
    private final static By BUY_FUNNY_COW = By.xpath("(//a[@class='btn btn-success'][normalize-space()='Buy'])[6]");
    private final static By BUY_FLUFFY_BUNNY = By.xpath("(//a[@class='btn btn-success'][normalize-space()='Buy'])[4]");
    private final static By BUY_STUFFED_FROG = By.xpath("(//a[@class='btn btn-success'][normalize-space()='Buy'])[2]");
    private final static By BUY_VALENTINE_BEAR = By.xpath("(//a[@class='btn btn-success'][normalize-space()='Buy'])[7]");
    private final static By CART = By.xpath("//a[@href='#/cart']");
    private final static By EMPTY_CART = By.xpath("//a[normalize-space()='Empty Cart']");
    private final static By EMPTY_CART_CONFIRM = By.xpath("//a[normalize-space()='Yes']");
    private final static By FUNNY_COW = By.xpath("//td[normalize-space()='Funny Cow']");
    private final static By FLUFFY_BUNNY = By.xpath("//td[normalize-space()='Fluffy Bunny']");

    private final static int stuffedDog = 1;
    private final static int fluffyBunny = 2;
    private final static int valentineBear = 3;

    public Shop(WebDriver driver) {
        super(driver);
    }

    public Shop navigateToShop() {
        waitFor(SHOP);
        clickOn(SHOP);
        waitFor(BUY_FUNNY_COW);
        return this;
    }

    public Shop buyItems() {
        waitFor(BUY_FUNNY_COW);
        buy(BUY_FUNNY_COW,2);
        buy(BUY_FLUFFY_BUNNY, 1);
        return this;
    }

    public Shop verifyCart() {
        waitFor(CART);
        clickOn(CART);
        waitFor(EMPTY_CART);
            elementShouldBeDisplayed(FUNNY_COW);
            elementShouldBeDisplayed(FLUFFY_BUNNY);
        return this;
    }

    public Shop emptyCart() {
        waitFor(EMPTY_CART);
        clickOn(EMPTY_CART);
        waitFor(EMPTY_CART_CONFIRM);
        clickOn(EMPTY_CART_CONFIRM);
        return this;
    }

    public Shop buyItemsForSubtotal() {
        waitFor(BUY_STUFFED_FROG);
        buy(BUY_STUFFED_FROG,2);
        buy(BUY_FLUFFY_BUNNY,5);
        buy(BUY_VALENTINE_BEAR,3);
        clickOn(CART);
        return this;
    }

    public Shop verifyItemSubtotals() {
        waitFor(EMPTY_CART);
            checkItemSubtotal(stuffedDog, 2);
            checkItemSubtotal(fluffyBunny, 5);
            checkItemSubtotal(valentineBear, 3);
        return this;
    }

    public void captureScreen(String fileName) {
        try {
            WebDriver webDriver = new Augmenter().augment(driver);
            File source = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("src/main/resources/screenshots/" + fileName + ".png"));
        }
        catch(IOException e) {
        }
    }
}
