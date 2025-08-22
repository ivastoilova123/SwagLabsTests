package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends  LogInPage{

    @Getter
    @FindBy(className = "inventory_details_name large_size")
    private WebElement productNameDetails;

    @Getter
    @FindBy(className = "inventory_details_desc large_size")
    private WebElement productWholeDetails;

    @Getter
    @FindBy(className = "inventory_details_price")
    private WebElement productPriceDetails;

    @Getter
    @FindBy(id = "back-to-products")
    private WebElement backToProducts;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName(){
       return productNameDetails.getText();
    }

    public String getProductDetails(){
        return productWholeDetails.getText();
    }

    public String getProductPrice(){
        return productPriceDetails.getText();
    }

    public SwagLabsHomePage backToProducts(){
        backToProducts.click();
        return new SwagLabsHomePage(driver);
    }

}
