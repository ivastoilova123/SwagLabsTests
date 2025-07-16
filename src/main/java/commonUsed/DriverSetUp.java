package commonUsed;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetUp {

    public static WebDriver chromeDriver(){
        return (commonBrowserSetUp(new ChromeDriver()));
    }

    public static WebDriver firefoxDriver(){
        return (commonBrowserSetUp(new FirefoxDriver()));
    }

    private static WebDriver commonBrowserSetUp(WebDriver driver){
        driver.manage().window().maximize();
        return driver;
    }
}
