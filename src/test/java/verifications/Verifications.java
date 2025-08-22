package verifications;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import data.LoadDataFromJson;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static constants.Constants.*;

public class Verifications {

    public static void verifyInvalidLogInMessage(WebElement errorMessage, String expectedError){
        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, LoadDataFromJson.getKeyValue
                (JSON_FILE_ERROR_MESSAGES_LOCATION, expectedError), "Something's wrong, check username and password");
    }

    public static void verifyLoginPageLogo(WebElement logoName){
        String actualLogoText = logoName.getText();;
        Assert.assertEquals(actualLogoText,"Swag Labs");
    }

    public static void verifyLoginLogoPresence(WebElement pageLogo){
        Assert.assertTrue(pageLogo.isDisplayed());
    }

    public static void verifyProductTitle(WebElement product){
        Assert.assertTrue(product.isDisplayed());
    }

    public static void verifyAppPageLogo(WebElement logoName){
        String actualLogoText = logoName.getText();;
        Assert.assertEquals(actualLogoText,"Swag Labs");
    }

    public static void verifyMenuElementsList(List<String> menuElementsActualList){
        List<String> menuElementsExpectedList =
                LoadDataFromJson.getDropdownOptions(JSON_FILE_DROPDOWN_DATA_LOCATION, MAIN_MENU_DROPDOWN_OPTIONS);
        Assert.assertEquals(menuElementsExpectedList, menuElementsActualList);
    }

    public static void verifyUserIsLogout(String expectedUrl, String actualURL){
        Assert.assertEquals(expectedUrl, actualURL, "User is not logout");
    }

    public static void verifyDropdownSelectedValue(List<String> dropdownActualValue){
        List<String> dropdownExpectedValue =
                LoadDataFromJson.getDropdownOptions(JSON_FILE_DROPDOWN_DATA_LOCATION, DROPDOWN_DATA_OPTIONS);
           Assert.assertEquals(dropdownExpectedValue, dropdownActualValue);
    }

    public static void verifyDefaultSelectedValue(String expectedSelectedValue, String actualSelectedValue){
        Assert.assertEquals(actualSelectedValue, expectedSelectedValue );
    }

    public static void verifyPageTitleText(String actualTitle, String expectedTitle){
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    public static void verifyProductSuccess(String actualMessage, String expectedMessage){
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    public static void verifyListOfProductsAreEqual(List<String> expectedList, List<String> actualList){
        Assert.assertEquals(expectedList, actualList);
    }

    public static void verifyButtonIsDisplayed(WebElement button){
        Assert.assertTrue(button.isDisplayed());
    }
    public static void verifyProductName(String actualProductName, String expectedProductName){
        Assert.assertEquals(actualProductName, expectedProductName);
    }
}
