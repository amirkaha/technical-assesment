import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import java.io.File;
import java.io.IOException;

public class Contact extends Abstract {
    private final static By CONTACT = By.xpath("//a[normalize-space()='Contact']");
    private final static By FORENAME = By.xpath("//input[@id='forename']");
    private final static By SURNAME = By.xpath("//input[@id='surname']");
    private final static By EMAIL = By.xpath("//input[@id='email']");
    private final static By TELEPHONE = By.xpath("//input[@id='telephone']");
    private final static By MESSAGE = By.xpath("//textarea[@id='message']");
    private final static By SUBMIT = By.xpath("//a[normalize-space()='Submit']");
    private final static By SUCCESS_MESSAGE = By.xpath("//div[@class='alert alert-success']");

    public Contact(WebDriver driver) {
        super(driver);
    }

    public Contact loadPage() {
        get("http://jupiter.cloud.planittesting.com");
        return this;
    }

    public Contact navigateToContact() {
        waitFor(CONTACT);
        clickOn(CONTACT);
        waitFor(SUBMIT);
        return this;
    }

    public Contact fillInContactDetails() {
        waitFor(FORENAME);
        typeIn(FORENAME,"Joe");
        typeIn(SURNAME, "Peter");
        typeIn(EMAIL,"JoePeter@planit.net.au");
        typeIn(TELEPHONE,"0432123456");
        typeIn(MESSAGE,"This is an automated message");
        return this;
    }

    public Contact clickSubmit() {
        waitFor(SUBMIT);
        clickOn(SUBMIT);
        return this;
    }

    public Contact verifySuccessMessage() {
        waitFor(SUCCESS_MESSAGE);
        elementShouldBeDisplayed(SUCCESS_MESSAGE);
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
