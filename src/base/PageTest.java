package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import util.CONSTANTS;

import java.util.concurrent.TimeUnit;

/* Base test class for common methods*/

public class PageTest {

    public static WebDriver driver;

    public static WebDriver initChromeDriver() {

        System.setProperty(CONSTANTS.CHROMEDRIVER, CONSTANTS.CHROMEDRIVERPATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(CONSTANTS.WAIT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
