package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends LogInPage {
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @Getter
    @FindBy(id = "first-name")
    private WebElement firstName;

    @Getter
    @FindBy(id = "last-name")
    private WebElement lastName;

    @Getter
    @FindBy(id = "postal-code")
    private WebElement postalCode;

    @Getter
    @FindBy(id = "continue")
    private WebElement continueButton;

    @Getter
    @FindBy(xpath = "//h3[@data-test=\"error\"]")
    private WebElement errorMessageContainer;

    @Getter
    @FindBy(className = "error-button")
    private WebElement closeErrorButton;

    @Getter
    @FindBy(id = "cancel")
    private WebElement cancelButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public String getCheckoutPageTitle(){
        return getTitle().getText();
    }

    public void enterFirstName(String clientName){
        firstName.sendKeys(clientName);
    }

    public void enterLastName(String clientLastName){
        lastName.sendKeys(clientLastName);
    }

    public void enterPostalCode(String clientPostalCode){
        postalCode.sendKeys(clientPostalCode);
    }

    public CheckoutOverviewPage clickContinueButton(){
        continueButton.click();
        return new CheckoutOverviewPage(driver);
    }

    public void checkoutValidCredentials(String firstName, String lastName, String postalCode){
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        clickContinueButton();
    }

    public void checkoutWithoutFirstName(String lastName, String postalCode){
        enterLastName(lastName);
        enterPostalCode(postalCode);
        clickContinueButton();
    }

    public YourCardPage cancelCheckout(){
        cancelButton.click();
        return  new YourCardPage(driver);
    }
}
