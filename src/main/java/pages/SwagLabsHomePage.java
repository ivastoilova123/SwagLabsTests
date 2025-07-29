package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SwagLabsHomePage {

    @FindBy(xpath = "//div//span[@class=\"title\"]")
    private WebElement productsTitle;

    @FindBy(xpath = "//select[@class=\"product_sort_container\"]")
    private WebElement dropDownSortElements;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "react-burger-cross-btn")
    private WebElement closeMenuButton;

    @FindBy(xpath = "//nav[@class=\"bm-item-list\"]//a")
    private WebElement menuElements;

    @FindBy(xpath = "//div[@class=\"inventory_item\"]")
    private WebElement products;

    @FindBy(id = "shopping_cart_container")
    private WebElement shoppingCard;
}

