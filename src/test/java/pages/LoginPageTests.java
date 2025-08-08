package pages;

import data.LoadDataFromJson;
import org.testng.Assert;
import org.testng.annotations.*;

import static constants.Constants.*;

public class LoginPageTests extends BaseTest {
    LogInPage logIn;
    final String JSON_FILE_ERROR_MESSAGES_LOCATION = "errorMessages.json";
    final String JSON_FILE_DROPDOWN_DATA_LOCATION = "dropdownData.json";

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
        logIn.logInSwagLabsPage(STANDARD_USER_USERNAME, PASSWORD);
        Assert.assertTrue(logIn.getProductTitle().isDisplayed());
        Assert.assertTrue(logIn.getAppLogo().isDisplayed());
    }

    @DataProvider(name = "invalidLogIn")
    public Object[] readJsonInvalidLogIn() {
        return new Object[][]{
                {STANDARD_USER_USERNAME, WRONG_PASSWORD, ERROR_MESSAGE_WRONG_PASS},
                {LOCKED_USER_USERNAME, PASSWORD, ERROR_MESSAGE_LOCKED_USER},
        };
    }

    @Test(dataProvider = "invalidLogIn")
    public void verifyJsonInvalidLogInData(String username, String password, String keyErrorFromJson) {
        logIn.logInSwagLabsPage(username, password);
        String actualErrorMessage = logIn.getErrorMessage().getText();
        Assert.assertEquals(actualErrorMessage, LoadDataFromJson.getKeyValue
                (JSON_FILE_ERROR_MESSAGES_LOCATION, keyErrorFromJson), "Something's wrong, check username and password");
    }
}