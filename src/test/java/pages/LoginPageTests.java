package pages;

import org.testng.Assert;
import org.testng.annotations.*;

public class LoginPageTests extends BaseTest {
    LogInPage logIn;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        super.setUp(browser);
        logIn = new LogInPage(driver);
    }

    @Test
    public void verifyHomePageLogo() {
        logIn.gotTo();
        Assert.assertTrue(logIn.getLoginPageLogo().isDisplayed());
        Assert.assertTrue(logIn.getLoginPageLogo().getText().equals("Swag Labs"), "Something's wrong with logo text!");

    }

    @Test
    public void validLogINTest() {
        logIn.logInSwagLabsPage("standard_user", "secret_sauce");
        Assert.assertTrue(logIn.getProductTitle().isDisplayed());
        Assert.assertTrue(logIn.getAppLogo().isDisplayed());
    }

    @DataProvider(name = "invalidLogIn")
    public Object[] createInvalidLogInData() {
        return new Object[][]{
                {"standard_user", "secret_sauceee", "Epic sadface: Username and password do not match any user in this service"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
        };
    }

    @Test(dataProvider = "invalidLogIn")
    public void verifyInvalidLogInData(String username, String password, String expectedErrorMessage) {
        logIn.logInSwagLabsPage(username, password);
        String actualErrorMessage = logIn.getLockedUserMessage().getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Something's wrong, check username and password");

    }
}