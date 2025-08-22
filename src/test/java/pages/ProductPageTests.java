package pages;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import verifications.Verifications;

import static constants.Constants.PASSWORD;
import static constants.Constants.STANDARD_USER_USERNAME;

public class ProductPageTests extends BaseTest{
    LogInPage logIn;
    SwagLabsHomePage swagLabsHomePage;
    ProductPage productPage;

    @BeforeMethod
    public void setUp() {
        logIn = new LogInPage(driver);
        logIn.logInSwagLabsPage(STANDARD_USER_USERNAME, PASSWORD);
        swagLabsHomePage = new SwagLabsHomePage(driver);
        swagLabsHomePage.openFirstProduct();

    }

    @Test
    public void verifyProductDetails(){
        String productNameHomePage = swagLabsHomePage.getFirstProductName();
        swagLabsHomePage.openFirstProduct();
        ProductPage productPage = new ProductPage(driver);
        String productName = productPage.getProductName();
        Verifications.verifyProductName(productNameHomePage, productName);
        productPage.backToProducts();
    }
}
