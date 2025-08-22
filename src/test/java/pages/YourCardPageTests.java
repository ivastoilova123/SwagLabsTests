package pages;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import verifications.Verifications;

import static constants.Constants.PASSWORD;
import static constants.Constants.STANDARD_USER_USERNAME;

public class YourCardPageTests extends BaseTest {

    LogInPage logIn;
    SwagLabsHomePage swagLabsHomePage;
    YourCardPage yourCardPage;

    @BeforeMethod
    public void setUp() {
        logIn = new LogInPage(driver);
        logIn.logInSwagLabsPage(STANDARD_USER_USERNAME, PASSWORD);
        swagLabsHomePage = new SwagLabsHomePage(driver);
        swagLabsHomePage.openShoppingCard();
    }

    @Test
    public void verifyContinueShoppingButton(){
        yourCardPage = new YourCardPage(driver);
        yourCardPage.clickContinueShopping();
        Verifications.verifyProductTitle(logIn.getTitle());
    }
}
