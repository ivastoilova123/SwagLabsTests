package pages;

import data.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginPageTests extends BaseTest {
    LogInPage logIn;
    TestDataProvider dataProvider = new TestDataProvider();
    final String JSON_FILE_LOCATION = "src/test/java/data/testLoginData.json";
    final String JSON_FILE_DROPDOWN_LOCATION = "src/test/java/data/dropdownData.json";

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
        logIn.logInSwagLabsPage(dataProvider.getCorrectStandardUserUsername(), dataProvider.getCorrectPassword());
        Assert.assertTrue(logIn.getProductTitle().isDisplayed());
        Assert.assertTrue(logIn.getAppLogo().isDisplayed());
    }

    @DataProvider(name = "invalidLogInDataProvider")
    public Object[] createInvalidLogInDataDataProvider() {
        return new Object[][]{
                {dataProvider.getCorrectStandardUserUsername(), dataProvider.getWrongPassword(), dataProvider.getUserPasswordDoestMatch()},
                {dataProvider.getLockedoutUserUsername(), dataProvider.getCorrectPassword(), dataProvider.getUserLockedOut()},
        };
    }

    @Test(dataProvider = "invalidLogInDataProvider")
    public void verifyInvalidLogInDataDataProvider(String username, String password, String expectedErrorMessage) {
        logIn.logInSwagLabsPage(username, password);
        String actualErrorMessage = logIn.getErrorMessage().getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Something's wrong, check username and password");

    }

    @DataProvider(name = "invalidLogInReadFromJson")
    public Object[] readJsonInvalidLogInDataDataProvider() {
        return new Object[][]{
                  {"standardUserUsername", "wrongPassword", "userPasswordDoestMatch"},
                  {"lockedUserUsername", "correctPassword", "userLockedOut"},
        };
    }

    @Test(dataProvider = "invalidLogInReadFromJson")
    public void verifyJsonInvalidLogInDataDataProvider(String username, String password, String keyErrorFromJson) {
        logIn.logInSwagLabsPage(dataProvider.getKeyValue(JSON_FILE_LOCATION, username),
                dataProvider.getKeyValue(JSON_FILE_LOCATION, password));
        String actualErrorMessage = logIn.getErrorMessage().getText();
        Assert.assertEquals(actualErrorMessage, dataProvider.getKeyValue(JSON_FILE_LOCATION,keyErrorFromJson), "Something's wrong, check username and password");
    }


//    @DataProvider(name = "invalidLogIn")
//    public Object[] createInvalidLogInData() {
//        return new Object[][]{
//                {"standard_user", "secret_sauceee", "Epic sadface: Username and password do not match any user in this service"},
//                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
//        };
//    }

//    @Test(dataProvider = "invalidLogIn")
//    public void verifyInvalidLogInData(String username, String password, String expectedErrorMessage) {
//        logIn.logInSwagLabsPage(username, password);
//        String actualErrorMessage = logIn.getErrorMessage().getText();
//        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Something's wrong, check username and password");
//
//    }

}