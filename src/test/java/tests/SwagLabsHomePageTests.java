package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static commonUsed.DriverSetUp.chromeDriver;

public class SwagLabsHomePageTests {

    WebDriver driver = chromeDriver();
    LogInPage logIn = new LogInPage(driver);

    @BeforeMethod
    public void logInSwagLabsPage(){
        logIn.logInSwagLabsPage("standard_user","secret_sauce" );

    }

    @Test
    public void verifyProductsOnPage(){

    }
}
