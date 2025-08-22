package pages;

import data.DataProviderClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.testng.annotations.*;
import verifications.Verifications;

import static constants.Constants.*;

public class LoginPageTests extends BaseTest {
    LogInPage logIn;

    @BeforeMethod
    public void setUp() {
        logIn = new LogInPage(driver);
    }

    @Test(description = "Verify page logo")
    @Description("This test verifies page logo")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login as a registered user")
    @Feature("Page LOGO")
    public void verifyHomePageLogo() {
        logIn.gotTo();
        Verifications.verifyLoginLogoPresence(logIn.getLoginPageLogo());
        Verifications.verifyLoginPageLogo(logIn.getLoginPageLogo());
    }

    @Test(description = "Verify login functionality")
    @Description("This test verifies that a user can log in with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login as a registered user")
    @Feature("Login")
    public void validLogINTest() {
        logIn.logInSwagLabsPage(STANDARD_USER_USERNAME, PASSWORD);
        Verifications.verifyProductTitle(logIn.getTitle());
        Verifications.verifyAppPageLogo(logIn.getAppLogo());
    }

    @Description("This test verifies that a user cannot log in with invalid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login as a broken user")
    @Feature("Invalid Login")
    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "invalidLogIn")
    public void verifyJsonInvalidLogInData(String username, String password, String keyErrorFromJson) {
        logIn.logInSwagLabsPage(username, password);
        Verifications.verifyInvalidLogInMessage(logIn.getErrorMessage(), keyErrorFromJson);
    }
}