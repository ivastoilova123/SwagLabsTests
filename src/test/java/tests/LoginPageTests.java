package tests;

import commonUsed.BaseTest;
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
    public void validLogINTest(){
        logIn.logInSwagLabsPage("standard_user","secret_sauce" );
        Assert.assertTrue(logIn.getPageLogo().isDisplayed());
    //    Assert.assertTrue(logIn.getPageLogo().getText().contains("Swag Labs "), "Congrats! You are on right page");
        Assert.assertTrue(logIn.getProductTitle().isDisplayed());
    }

    @Test
    public void inValidLogInWithLockedUser(){
        logIn.logInSwagLabsPage("locked_out_user","secret_sauce" );
        Assert.assertTrue(logIn.getLockedUserMessage().getText().contains("locked out"));
    }

    @Test
    public void inValidLogInWithInvalidPassword(){
        logIn.logInSwagLabsPage("locked_out_user","secret_sauceee" );
        Assert.assertTrue(logIn.getLockedUserMessage().getText().contains("Username and password do not match any user in this service"));
    }
}
