package pages;

import data.DataProviderClass;
import data.LoadDataFromJson;
import org.testng.annotations.*;
import verifications.Verifications;

import java.util.Collections;
import java.util.List;

import static constants.Constants.*;


public class SwagLabsHomePageTests extends BaseTest {
    LogInPage logIn;
    SwagLabsHomePage swagLabsHomePage;
    YourCardPage yourCardPage;

    @BeforeMethod
    public void setUp() {
        logIn = new LogInPage(driver);
        logIn.logInSwagLabsPage(STANDARD_USER_USERNAME, PASSWORD);
    }

    @Test
    public void logoutSwagLabsHomePage() {
        swagLabsHomePage = new SwagLabsHomePage(driver);
        swagLabsHomePage.openMenuList();
        swagLabsHomePage.clickLogoutButton();
        Verifications.verifyUserIsLogout(logIn.getURL(),driver.getCurrentUrl() );
    }

    @Test
    public void verifyMenuItemsPresence() {
        swagLabsHomePage = new SwagLabsHomePage(driver);
        swagLabsHomePage.openMenuList();
        Verifications.verifyMenuElementsList(swagLabsHomePage.getListOfMenuItems());
    }

    @Test
    public void verifyDropDownSortElementsPresence() {
        swagLabsHomePage = new SwagLabsHomePage(driver);
        Verifications.verifyDropdownSelectedValue(swagLabsHomePage.getDropDownSortElements());
    }

    @Test
    public void verifyDefaultSelectedDropdownValue() {
        swagLabsHomePage = new SwagLabsHomePage(driver);
        String expectedSelectedValue = LoadDataFromJson.getKeyValue
                (JSON_FILE_DROPDOWN_DATA_LOCATION, DROPDOWN_NAMES_ASC_ORDER);
        Verifications.verifyDefaultSelectedValue(expectedSelectedValue, swagLabsHomePage.getSelectedDropdownValue());
    }

    @Test(dataProvider = "selectDropdownElements", dataProviderClass = DataProviderClass.class)
    public void selectDropdownElements(String dropdownOption){
        swagLabsHomePage = new SwagLabsHomePage(driver);
       String dropdown = swagLabsHomePage.selectDropdownElement
                (LoadDataFromJson.getKeyValue(JSON_FILE_DROPDOWN_DATA_LOCATION, dropdownOption));
        Verifications.verifyDefaultSelectedValue(dropdown, swagLabsHomePage.getSelectedDropdownValue());
    }

    @Test
    public void verifyProductsAlphabeticalOrder() {
        swagLabsHomePage = new SwagLabsHomePage(driver);
        List<String> productsBeforeOrdering =  swagLabsHomePage.getProductsInPage();
        Collections.sort(productsBeforeOrdering);
        swagLabsHomePage.selectDropdownElement(LoadDataFromJson.getKeyValue(JSON_FILE_DROPDOWN_DATA_LOCATION, DROPDOWN_PRICE_ASC_ORDER));
        Verifications.verifyListOfProductsAreEqual(swagLabsHomePage.getProductsInPage(),productsBeforeOrdering);
        Collections.reverse(productsBeforeOrdering);
        swagLabsHomePage.selectDropdownElement(LoadDataFromJson.getKeyValue(JSON_FILE_DROPDOWN_DATA_LOCATION, DROPDOWN_PRICE_DESC_ORDER));
        Verifications.verifyListOfProductsAreEqual(swagLabsHomePage.getProductsInPage(), productsBeforeOrdering);
    }

    @Test
     public void addToCardElements(){
        swagLabsHomePage = new SwagLabsHomePage(driver);
        String productNameToBuy =  swagLabsHomePage.getFirstProductName();
        swagLabsHomePage.addFirstElementToCard();
        yourCardPage = swagLabsHomePage.openShoppingCard();
        String productNameInCard = yourCardPage.getFirstProductName();
        Verifications.verifyProductSuccess(productNameToBuy,productNameInCard);
        yourCardPage.clickContinueShopping();
    }

    @Test
    public void verifyRemoveButton(){
        swagLabsHomePage = new SwagLabsHomePage(driver);
        Verifications.verifyButtonIsDisplayed(swagLabsHomePage.getAddToCartButton());
        swagLabsHomePage.addFirstElementToCard();
        Verifications.verifyButtonIsDisplayed(swagLabsHomePage.getRemoveButton());
        Verifications.verifyButtonIsDisplayed(swagLabsHomePage.getShoppingCartBadge());
        swagLabsHomePage.removeProduct();
        Verifications.verifyButtonIsDisplayed(swagLabsHomePage.getAddToCartButton());
    }
}
