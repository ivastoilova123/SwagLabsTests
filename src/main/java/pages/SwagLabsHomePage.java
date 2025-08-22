package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
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

    @Getter
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCartButton;

    @Getter
    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    @Getter
    @FindBy(xpath = "//div[@class =\"inventory_item_name \"]")
    private WebElement allProductsInPage;

    @Getter
    @FindBy(xpath = "//div[@class=\"inventory_item\"][1]//button[@id=\"add-to-cart-sauce-labs-backpack\"]")
    private WebElement addToCardFirstProduct;


    @FindBy(id = "item_4_title_link")
    private WebElement firstProductName;

    @Getter
    @FindBy(className = "title")
    private WebElement title;

    @Getter
    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement removeButton;

    @Getter
    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartBadge;


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

    public String selectDropdownElement(String text){
        wait.until(ExpectedConditions.visibilityOf(dropdownOptions));
        WebElement dropdown = driver.findElement
                (By.xpath("//select[@class=\"product_sort_container\"]"));
        dropdown.click();
        wait.until(ExpectedConditions.visibilityOf(dropdownOptions));
        Select select = new Select(dropdown);

        select.selectByVisibleText(text);
        WebElement selectedOption = select.getFirstSelectedOption();
        return selectedOption.getText();
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

    public YourCardPage openShoppingCard(){
        shoppingCard.click();
        return new YourCardPage(driver);
    }

 public String getFirstProductName(){
        return firstProductName.getText();
 }

    public String getHomePageTitle(){
        return getTitle().getText();
    }

    public void removeProduct(){
        removeButton.click();
    }

    public ProductPage openFirstProduct(){
        firstProductName.click();
        return new ProductPage(driver);
    }


}