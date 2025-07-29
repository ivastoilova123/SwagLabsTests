package pages;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SwagLabsHomePageTests extends BaseTest {

    LogInPage logIn;

    @BeforeMethod
    public void logInSwagLabsPage(){
        logIn.logInSwagLabsPage("standard_user","secret_sauce" );

    }

    @Test
    public void verifyProductsOnPage(){

    }
}
