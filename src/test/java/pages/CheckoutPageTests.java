package pages;

import data.DataProviderClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import verifications.Verifications;

import static constants.Constants.*;

public class CheckoutPageTests extends BaseTest  {
    LogInPage logIn;
    SwagLabsHomePage swagLabsHomePage;
    CheckoutPage checkoutPage;
    YourCardPage yourCardPage;
    CheckoutOverviewPage checkoutOverviewPage;

    @BeforeMethod
    public void setUp() {
        logIn = new LogInPage(driver);
        logIn.logInSwagLabsPage(STANDARD_USER_USERNAME, PASSWORD);
        swagLabsHomePage = new SwagLabsHomePage(driver);
        swagLabsHomePage.openShoppingCard();
        yourCardPage = new YourCardPage(driver);
        yourCardPage.clickCheckoutButton();
    }

    @Test
    public void verifyCheckoutPageTitle(){
        checkoutPage = new CheckoutPage(driver);
        Verifications.verifyProductTitle(checkoutPage.getTitle());
        Verifications.verifyPageTitleText(checkoutPage.getCheckoutPageTitle(),CHECKOUT_PAGE_TITLE );
    }

    @Test
    public void verifyCheckoutWithValidCredentials(){
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.checkoutValidCredentials(FIRST_NAME, LAST_NAME, POSTAL_CODE);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        Verifications.verifyPageTitleText(checkoutOverviewPage.getCheckoutOverviewTitle(),CHECKOUT_OVERVIEW_TITLE );
    }

    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "invalidCredentials")
    public void verifyCheckoutWithoutFirstName(String invalidCredential1, String invalidCredential2, String errorMessage){
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.checkoutWithoutFirstName(invalidCredential1, invalidCredential2);
        Verifications.verifyInvalidLogInMessage(checkoutPage.getErrorMessageContainer(), errorMessage);
    }

    @Test
    public void verifyCancelCheckout(){
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.cancelCheckout();
        Verifications.verifyPageTitleText(yourCardPage.getYourCartPageTitle(), YOURCART_PAGE_TITLE );
    }
}
