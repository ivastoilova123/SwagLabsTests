package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends LogInPage{

    @Getter
    @FindBy(className = "complete-header")
    private WebElement checkoutCompleteMessage;

    @Getter
    @FindBy(id = "back-to-products")
    private WebElement backToHome;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String checkoutSuccessMessage(){
        return checkoutCompleteMessage.getText();
    }

    public SwagLabsHomePage backToHomePage(){
        backToHome.click();
        return  new SwagLabsHomePage(driver);
    }
}
