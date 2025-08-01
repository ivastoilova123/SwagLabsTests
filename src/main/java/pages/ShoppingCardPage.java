package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCardPage extends LogInPage {
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @Getter
    @FindBy(className = "btn btn_secondary back btn_medium")
    private WebElement continueShoppingButton;

    @Getter
    @FindBy(className = "btn btn_action btn_medium checkout_button ")
    private WebElement checkoutButton;

    @Getter
    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy (xpath = "//div[@class=\"cart_item\"][1]//div[@class=\"inventory_item_name\"]")
    private WebElement firstProductName;

    public ShoppingCardPage(WebDriver driver) {
        super(driver);
    }

    public String getFirstProductName(){
        return firstProductName.getText();
    }

    public SwagLabsHomePage continueShoppingSwagPage(){
        continueShoppingButton.click();
        return new SwagLabsHomePage(driver);
    }
}
