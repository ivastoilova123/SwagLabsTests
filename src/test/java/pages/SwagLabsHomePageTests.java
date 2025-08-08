package pages;

import data.LoadDataFromJson;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static constants.Constants.*;


public class SwagLabsHomePageTests extends LoginPageTests {
    LogInPage logIn;
    SwagLabsHomePage swagLabsHomePage;
    ShoppingCardPage shoppingCardPage;
  //  LoadDataFromJson testDataProvider = new LoadDataFromJson();;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        super.setUp(browser);
        logIn = new LogInPage(driver);
        logIn.logInSwagLabsPage(STANDARD_USER_USERNAME, PASSWORD);
    }

    @Test
    public void logoutSwagLabsHomePage() {
        swagLabsHomePage = new SwagLabsHomePage(driver);
        swagLabsHomePage.openMenuList();
        swagLabsHomePage.clickLogoutButton();
        String expectedUrl = logIn.getURL();
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualURL, "User is not logout");
    }

    @Test
    public void verifyMenuItemsPresence() {
        swagLabsHomePage = new SwagLabsHomePage(driver);
        swagLabsHomePage.openMenuList();
        List<String> menuElementsExpectedList =
                LoadDataFromJson.getDropdownOptions(JSON_FILE_DROPDOWN_DATA_LOCATION, MAIN_MENU_DROPDOWN_OPTIONS);
        Assert.assertTrue(menuElementsExpectedList.equals(swagLabsHomePage.getListOfMenuItems()));
    }

    @Test
    public void verifyDropDownSortElementsPresence() {
        swagLabsHomePage = new SwagLabsHomePage(driver);
        List<String> dropdownMenuElementsExpectedList =
                LoadDataFromJson.getDropdownOptions(JSON_FILE_DROPDOWN_DATA_LOCATION, DROPDOWN_DATA_OPTIONS);
        Assert.assertTrue(dropdownMenuElementsExpectedList.equals(swagLabsHomePage.getDropDownSortElements()));
    }

    @Test
    public void verifyDefaultSelectedDropdownValue() {
        swagLabsHomePage = new SwagLabsHomePage(driver);
        String expectedSelectedValue = LoadDataFromJson.getKeyValue(JSON_FILE_DROPDOWN_DATA_LOCATION, DROPDOWN_NAMES_ASC_ORDER);
        Assert.assertTrue(expectedSelectedValue.equals(swagLabsHomePage.getSelectedDropdownValue()));
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

    @Test(dataProvider = "selectDropdownElements")
    public void selectDropdownElements(String dropdownOption){
        swagLabsHomePage = new SwagLabsHomePage(driver);
        swagLabsHomePage.selectDropdownElement
                (LoadDataFromJson.getKeyValue(JSON_FILE_DROPDOWN_DATA_LOCATION, dropdownOption));
        Assert.assertTrue(dropdownOption.equals(swagLabsHomePage.getSelectedDropdownValue()));
    }

    @Test
    public void verifyProductsAlphabeticalOrder() {
        swagLabsHomePage = new SwagLabsHomePage(driver);
        List<String> productsBeforeOrdering =  swagLabsHomePage.getProductsInPage();
        Collections.sort(productsBeforeOrdering);
        swagLabsHomePage.selectDropdownElement(LoadDataFromJson.getKeyValue(JSON_FILE_DROPDOWN_DATA_LOCATION, DROPDOWN_PRICE_ASC_ORDER));
        Assert.assertTrue(productsBeforeOrdering.equals(swagLabsHomePage.getProductsInPage()));
        Collections.reverse(productsBeforeOrdering);
        swagLabsHomePage.selectDropdownElement(LoadDataFromJson.getKeyValue(JSON_FILE_DROPDOWN_DATA_LOCATION, DROPDOWN_PRICE_DESC_ORDER));
        Assert.assertTrue(productsBeforeOrdering.equals(swagLabsHomePage.getProductsInPage()));
    }

    @Test
     public void addToCardElements(){
        swagLabsHomePage = new SwagLabsHomePage(driver);
        String productNameToBuy =  swagLabsHomePage.getFirstProductName();
        swagLabsHomePage.addFirstElementToCard();
        shoppingCardPage = swagLabsHomePage.openShoppingCard();
        String productNameInCard = shoppingCardPage.getFirstProductName();
        Assert.assertTrue(productNameToBuy.equals(productNameInCard));
        shoppingCardPage.continueShoppingSwagPage();
    }
}
