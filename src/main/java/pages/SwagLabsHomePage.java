package pages;

import driver.DriverSetUp;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwagLabsHomePage extends LogInPage {
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @FindBy(xpath = "//div//span[@class=\"title\"]")
    private WebElement productsTitle;

    @FindBy(xpath = "//select[@class=\"product_sort_container\"]")
    private WebElement dropDownContainer;

    @FindBy(xpath = "//select[@class=\"product_sort_container\"]//option")
    private WebElement dropdownOptions;

    @FindBy(xpath = "//span[@class=\"active_option\"]")
    private WebElement dropdownActiveOption;

    @Getter
    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "react-burger-cross-btn")
    private WebElement closeMenuButton;

    @Getter
    @FindBy(xpath = "//nav[@class=\"bm-item-list\"]//a")
    private List<WebElement> menuElements;

    @FindBy(xpath = "//div[@class=\"inventory_item\"]")
    private WebElement products;

    @FindBy(id = "shopping_cart_container")
    private WebElement shoppingCard;

    @FindBy(className = "btn btn_primary btn_small btn_inventory ")
    private WebElement addToCartButton;

    @Getter
    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    @Getter
    @FindBy(xpath = "//div[@class =\"inventory_item_name \"]")
    private WebElement allProductsInPage;

    @Getter
    @FindBy(xpath = "//div[@class=\"inventory_item\"][1]//button[@class=\"btn btn_primary btn_small btn_inventory \"]")
    private WebElement addToCardFirstProduct;


    @FindBy(xpath = "//div[@class=\"inventory_item\"][1]//div[@class=\"inventory_item_name \"]")
    private WebElement firstProductName;

    public SwagLabsHomePage(WebDriver driver) {
        super(driver);
    }

    public void openMenuList(){

        wait.until(ExpectedConditions.visibilityOf(menuButton));
        menuButton.click();
    }

    public void clickLogoutButton(){
        wait.until(ExpectedConditions.visibilityOf(logoutButton));
        logoutButton.click();
    }

    public List<String> getListOfMenuItems(){
        List<String> menuItemsNewList = new ArrayList<>();
        wait.until(ExpectedConditions.visibilityOf(logoutButton));
        List<WebElement> titles = driver.findElements(By.xpath("//nav[@class=\"bm-item-list\"]//a"));
        for(WebElement menuItem : titles){
            menuItemsNewList.add(menuItem.getText());
        }
        return menuItemsNewList;
    }

    public List<String> getDropDownSortElements(){
        List<String> dropdownNewList = new ArrayList<>();
        wait.until(ExpectedConditions.visibilityOf(dropdownOptions));
        List<WebElement> sortElements = driver.findElements(By.xpath("//select[@class=\"product_sort_container\"]//option"));

        for(WebElement sortElement : sortElements){
            dropdownNewList.add(sortElement.getText());
        }
        return dropdownNewList;
    }

    public String getSelectedDropdownValue(){
        wait.until(ExpectedConditions.visibilityOf(dropdownOptions));
        return dropdownActiveOption.getText();
    }

    public void selectDropdownElement(String text){
        wait.until(ExpectedConditions.visibilityOf(dropdownOptions));
        WebElement dropdown = driver.findElement
                (By.xpath("//select[@class=\"product_sort_container\"]"));
        dropdown.click();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        wait.until(ExpectedConditions.visibilityOf(dropdownOptions));
        Select select = new Select(dropdown);
        System.out.println(text);
        select.selectByVisibleText(text);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        select.selectByVisibleText(text);

    }

    public List<String> getProductsInPage(){
       List<WebElement> allProducts =
               driver.findElements(By.xpath("//div[@class =\"inventory_item_name \"]"));
      List<String> allProductsInPage = new ArrayList<>();

     for(WebElement products : allProducts){
         allProductsInPage.add(products.getText());
      }
        return  allProductsInPage;
    }

    public void addFirstElementToCard(){
        addToCardFirstProduct.click();
    }

    public ShoppingCardPage openShoppingCard(){
        shoppingCard.click();
        return new ShoppingCardPage(driver);
    }

 public String getFirstProductName(){
        return firstProductName.getText();
 }
}

