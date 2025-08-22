package pages;

import data.DataProviderClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static constants.Constants.*;
import static constants.Constants.LAST_NAME;
import static constants.Constants.POSTAL_CODE;

public class CheckoutOverviewPageTests extends BaseTest{
    LogInPage logIn;
    SwagLabsHomePage swagLabsHomePage;
    CheckoutPage checkoutPage;
    YourCardPage yourCardPage;
    CheckoutOverviewPage checkoutOverviewPage;

    @BeforeMethod
    public void setUp( ) {
        logIn = new LogInPage(driver);
        logIn.logInSwagLabsPage(STANDARD_USER_USERNAME, PASSWORD);
        swagLabsHomePage = new SwagLabsHomePage(driver);
        swagLabsHomePage.openShoppingCard();
        yourCardPage = new YourCardPage(driver);
        yourCardPage.clickCheckoutButton();
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.checkoutValidCredentials(FIRST_NAME, LAST_NAME, POSTAL_CODE);
    }

    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "summaryInfoTitle")
    public void verifySummaryInfoTitles(String summaryInfo){
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutOverviewPage.verifyLabelExists(summaryInfo);
    }


}
