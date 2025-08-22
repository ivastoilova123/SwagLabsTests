package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YourCardPage extends LogInPage {
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @Getter
    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @Getter
    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @Getter
    @FindBy(className = "title")
    private WebElement pageTitle;



    @FindBy (xpath = "//div[@class=\"cart_item\"][1]//div[@class=\"inventory_item_name\"]")
    private WebElement firstProductName;

    public YourCardPage(WebDriver driver) {
        super(driver);
    }

    public String getFirstProductName(){
        return firstProductName.getText();
    }

    public void clickContinueShopping(){
        wait.until(ExpectedConditions.visibilityOf(continueShoppingButton));
        continueShoppingButton.click();
    }

    public CheckoutPage clickCheckoutButton(){
        checkoutButton.click();
        return new CheckoutPage(driver);
    }
    public String getYourCartPageTitle(){
        return getTitle().getText();
    }




}
