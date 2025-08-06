package pages;

import data.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class SwagLabsHomePageTests extends LoginPageTests {
    LogInPage logIn;
    SwagLabsHomePage swagLabsHomePage;
    ShoppingCardPage shoppingCardPage;
    TestDataProvider testDataProvider = new TestDataProvider();;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        super.setUp(browser);
        logIn = new LogInPage(driver);
        logIn.logInSwagLabsPage(dataProvider.getCorrectStandardUserUsername(), dataProvider.getCorrectPassword());
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
        List<String> menuElementsExpectedList = Arrays.asList("All Items", "About", "Logout", "Reset App State");
        Assert.assertTrue(menuElementsExpectedList.equals(swagLabsHomePage.getListOfMenuItems()));
    }

    @Test
    public void verifyDropDownSortElementsPresence() {
        swagLabsHomePage = new SwagLabsHomePage(driver);
        List<String> dropdownMenuElementsExpectedList =
                testDataProvider.getDropdownOptions(JSON_FILE_DROPDOWN_LOCATION, "dropdownOptions");
        Assert.assertTrue(dropdownMenuElementsExpectedList.equals(swagLabsHomePage.getDropDownSortElements()));
    }

    @Test
    public void verifyDefaultSelectedDropdownValue() {
        swagLabsHomePage = new SwagLabsHomePage(driver);
        String expectedSelectedValue = testDataProvider.getKeyValue(JSON_FILE_DROPDOWN_LOCATION, "nameAscendingOrder");
        Assert.assertTrue(expectedSelectedValue.equals(swagLabsHomePage.getSelectedDropdownValue()));
    }

    @DataProvider(name = "selectDropdownElements")
    public Object[][] getData() {
        return new Object[][]{
                  {"nameAscendingOrder"},
                  {"nameDescendingOrder"},
                  {"priceAscendingOrder"},
                  {"priceDescendingOrder"}
        };
    }

    @Test(dataProvider = "selectDropdownElements")
    public void selectDropdownElements(String dropdownOption){
        swagLabsHomePage = new SwagLabsHomePage(driver);
        swagLabsHomePage.selectDropdownElement
                (dataProvider.getKeyValue(JSON_FILE_DROPDOWN_LOCATION, dropdownOption));
        Assert.assertTrue(dropdownOption.equals(swagLabsHomePage.getSelectedDropdownValue()));
    }

    @Test
    public void verifyProductsAlphabeticalOrder() {
        swagLabsHomePage = new SwagLabsHomePage(driver);
        List<String> productsBeforeOrdering =  swagLabsHomePage.getProductsInPage();
        Collections.sort(productsBeforeOrdering);
        swagLabsHomePage.selectDropdownElement(dataProvider.getKeyValue(JSON_FILE_DROPDOWN_LOCATION, "nameAscendingOrder"));
        Assert.assertTrue(productsBeforeOrdering.equals(swagLabsHomePage.getProductsInPage()));
        Collections.reverse(productsBeforeOrdering);
        swagLabsHomePage.selectDropdownElement(dataProvider.getKeyValue(JSON_FILE_DROPDOWN_LOCATION, "nameDescendingOrder"));
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
