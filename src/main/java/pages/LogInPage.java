package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class LogInPage {
    private final WebDriver driver;
    private static final String URL = "https://www.saucedemo.com/";

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @Getter
    @FindBy(className = "app_logo")
    private WebElement appLogo;

    @FindBy(className = "login_logo")
    private WebElement loginPageLogo;

    @FindBy(id = "login-button")
    private WebElement logInButton;

    @Getter
    @FindBy(className = "title")
    private WebElement productTitle;

    @Getter
    @FindBy(xpath = "//h3[@data-test=\"error\"]")
    private WebElement lockedUserMessage;

    public LogInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void gotTo() {
        driver.get(URL);
    }

    private void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLogInButton() {
        logInButton.click();
    }

    public void logInSwagLabsPage(String username, String password) {
        gotTo();
        enterUsername(username);
        enterPassword(password);
        clickLogInButton();

    }
}
