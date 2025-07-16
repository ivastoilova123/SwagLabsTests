package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogInPage {

    private final WebDriver driver;
    private static final String URL = "https://www.saucedemo.com/";

    public LogInPage(WebDriver driver) {
        this.driver = driver;
    }

    public static LogInPage Home(WebDriver driver){
        return new LogInPage(driver);
    }

    public void gotTo(){
        driver.get(URL);
    }

    public WebElement USERNAME(){
        return driver.findElement(By.id("user-name"));
    }

    public WebElement Password(){
        return  driver.findElement(By.id("password"));
    }

    public WebElement Logo(){
        return  driver.findElement(By.className("app_logo"));
    }

    public WebElement LogInButton(){
        return driver.findElement(By.id("login-button"));
    }

    public WebElement ProductsTitle(){
        return driver.findElement(By.className("title"));
    }

    public WebElement LockedUserInvalidLogInMessege(){
        return driver.findElement(By.xpath("//h3[@data-test=\"error\"]"));
    }

    public void enterUsername(String username){
        USERNAME().sendKeys(username);
    }

    public void enterPassword(String password){
        Password().sendKeys(password);
    }

    public void clickLogInButton() {
        LogInButton().click();
    }

    public void logInSwagLabsPage(String username, String password){
        USERNAME().sendKeys(username);
        Password().sendKeys(password);
    }
}
