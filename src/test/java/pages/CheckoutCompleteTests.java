package pages;

import data.LoadDataFromJson;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import verifications.Verifications;

import static constants.Constants.*;
import static constants.Constants.LAST_NAME;
import static constants.Constants.POSTAL_CODE;

public class CheckoutCompleteTests extends BaseTest {
    LogInPage logIn;
    SwagLabsHomePage swagLabsHomePage;
    YourCardPage yourCardPage;
    CheckoutPage checkoutPage;
    CheckoutOverviewPage checkoutOverviewPage;
    CheckoutCompletePage checkoutCompletePage;

    @BeforeMethod
    public void setUp(String browser) {
        logIn = new LogInPage(driver);
        logIn.logInSwagLabsPage(STANDARD_USER_USERNAME, PASSWORD);

    }

    @Test
    public void verifyProductIsSell(){
        swagLabsHomePage = new SwagLabsHomePage(driver);
        String productNameToBuy =  swagLabsHomePage.getFirstProductName();
        swagLabsHomePage.addFirstElementToCard();
        yourCardPage = swagLabsHomePage.openShoppingCard();
        String productNameInCard = yourCardPage.getFirstProductName();
        Verifications.verifyProductSuccess(productNameToBuy,productNameInCard);
        yourCardPage.clickCheckoutButton();
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.checkoutValidCredentials(FIRST_NAME, LAST_NAME, POSTAL_CODE);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        String checkedProductName = checkoutOverviewPage.getFirstCheckedProductName();
        Verifications.verifyProductSuccess(productNameInCard,checkedProductName);
        checkoutOverviewPage.finishCheckout();
        checkoutCompletePage = new CheckoutCompletePage(driver);
        String expectedSelectedValue = LoadDataFromJson.getKeyValue
                (JSON_SUCCESS_MESSAGES, PRODUCT_ORDERED);
        Verifications.verifyProductSuccess(checkoutCompletePage.checkoutSuccessMessage(),expectedSelectedValue);
        checkoutCompletePage.backToHomePage();
        Verifications.verifyPageTitleText(swagLabsHomePage.getHomePageTitle(),HOMEPAGE_TITLE);
    }
}
