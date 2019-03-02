package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.CONSTANTS;

/*Base page object class for common methods*/
public class PageObject {

    public static WebDriver driver = PageTest.driver;

    public static void waitForElementInVisibility(By element) {
        WebDriverWait wait = new WebDriverWait(driver, CONSTANTS.EXPLICITWAIT);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
        waitOnElement();
    }

    public void waitForElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, CONSTANTS.EXPLICITWAIT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitOnElement() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
