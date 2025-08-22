package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage extends LogInPage{

    @Getter
    @FindBy(id = "finish")
    private WebElement finishButton;

    @Getter
    @FindBy(xpath = "//div[contains(@class,\"summary_info_label\")]")
    private WebElement summaryInfo;

    @Getter
    @FindBy(xpath = "//a[@id=\"item_4_title_link\"]//div[@class=\"inventory_item_name\"]")
    private WebElement firstProduct;


    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public String getCheckoutOverviewTitle(){
        return getTitle().getText();
    }

    public boolean verifyLabelExists(String expectedText) {
        List<WebElement> summaryInfoTitles = driver.findElements(By.xpath("//div[contains(@class,\"summary_info_label\")]"));
        for (WebElement infoTitle : summaryInfoTitles) {
            if (infoTitle.getText().equals(expectedText)) {
                return true;
            }
        }
        return false;
    }
    public String getFirstCheckedProductName(){
        return  firstProduct.getText();
    }

    public CheckoutCompletePage finishCheckout(){
        finishButton.click();
        return new CheckoutCompletePage(driver);
    }

}
