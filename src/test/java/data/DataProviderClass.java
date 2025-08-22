package data;

import lombok.experimental.Helper;
import org.testng.annotations.DataProvider;
import pages.BaseTest;
import pages.CheckoutPage;

import static constants.Constants.*;
import static constants.Constants.ERROR_MESSAGE_LOCKED_USER;
import static constants.Constants.LOCKED_USER_USERNAME;
import static constants.Constants.PASSWORD;

public class DataProviderClass extends BaseTest {

    @DataProvider(name = "invalidLogIn")
    public Object[] readJsonInvalidLogIn() {
        return new Object[][]{
                {STANDARD_USER_USERNAME, WRONG_PASSWORD, ERROR_MESSAGE_WRONG_PASS},
                {LOCKED_USER_USERNAME, PASSWORD, ERROR_MESSAGE_LOCKED_USER},
        };
    }

    @DataProvider(name = "selectDropdownElements")
    public Object[][] getData() {
        return new Object[][]{
                {DROPDOWN_NAMES_ASC_ORDER},
                {DROPDOWN_NAMES_DESC_ORDER},
                {DROPDOWN_PRICE_ASC_ORDER},
                {DROPDOWN_PRICE_DESC_ORDER}
        };
    }

    @DataProvider(name = "invalidCredentials")
    public Object[] checkoutInvalidCredentials() {
        return new Object[][]{
                {LAST_NAME, POSTAL_CODE, FIRST_NAME_REQUIRED},
        };
    }

        @DataProvider(name = "summaryInfoTitle")
        public Object[] summaryLevelInformation () {
            return new Object[][]{
                    {PAYMENT_INFORMATION},
                    {SHIPPING_INFORMATION},
                    {PRICE_TOTAL}
            };
        }
}
