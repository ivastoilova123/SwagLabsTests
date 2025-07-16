package pages;

import commonUsed.DriverSetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static commonUsed.DriverSetUp.chromeDriver;

public class LoginPageTests {
    WebDriver driver = chromeDriver();
    LogInPage logIn = new LogInPage(driver);


    @Test
    public void validLogINTest(){
        logIn.gotTo();
        // Verify you are on swag page
        Assert.assertTrue(logIn.Logo().getText().contains("Swag Labs "), "Congrats! You are on right page");
        logIn.logInSwagLabsPage("standard_user","secret_sauce" );
        logIn.clickLogInButton();
       // Verify you are logged in
        Assert.assertTrue(logIn.ProductsTitle().isDisplayed());
    }

    @Test
    public void inValidLogInWithLockedUser(){
        logIn.gotTo();
        // Verify you are on swag page
        Assert.assertTrue(logIn.Logo().getText().contains("Swag Labs "), "Congrats! You are on right page");
        logIn.logInSwagLabsPage("locked_out_user","secret_sauce" );
        // Verify invalid log in message
        Assert.assertTrue(logIn.LockedUserInvalidLogInMessege().getText().contains("locked out"));
    }
}
