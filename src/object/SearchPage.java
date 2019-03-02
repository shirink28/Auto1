package object;

import base.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

/*Page object of Search Page*/
public class SearchPage extends PageObject {

    @FindBy(xpath = "//div[@data-qa-selector='filter-year']")
    WebElement registrationFromText;

    @FindBy(xpath = "//select[@name='yearRange.min']")
    WebElement registrationFromFilter;

    @FindBy(xpath = "//select[@name='sort']")
    WebElement sortFilter;

    @FindBy(xpath = "//a/ul/li[1]")
    public List<WebElement> dateOfResult;

    @FindBy(xpath = "//div[@data-qa-selector='price']")
    public List<WebElement> priceOfResult;

    By loading = By.xpath("//div[@class='resultsAmount___3OrV7' and text()='Lädt... ']");

    /*Loading webElements*/
    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /*Selecting on registration from label*/
    public void clickRegsitration() {
        waitForElementVisibility(registrationFromText);
        registrationFromText.click();
    }

    /*Selecting year from registration from dropdown*/
    public void selectRegistrationYear(String year) {
        clickRegsitration();
        waitForElementVisibility(registrationFromFilter);
        registrationFromFilter.sendKeys(year);
        /*Added explicit wait since loading takes time to start*/
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        /*Waiting for loading to end*/
        waitForElementInVisibility(loading);
    }

    /*Selecting value from Sort dropdown*/
    public void selectSort(String sort) {
        waitForElementVisibility(sortFilter);
        sortFilter.sendKeys(sort);
        waitOnElement();
        waitForElementInVisibility(loading);
    }

    /*Selecting pages of results dynamically*/
    public void selectPage(int number) {
        driver.findElement(By.xpath("//a[@class='btn btn-link'][text()= " + number + "]")).click();
        waitOnElement();
        waitForElementInVisibility(loading);
    }

    /*Verifying pagination exists*/
    public boolean pageExists(int number) {
        int size = 0;
        size = driver.findElements(By.xpath("//a[@class='btn btn-link'][text()= " + number + "]")).size();
        return size == 1;
    }

    /*Get year of all results*/
    public List<Integer> getYear() {
        List<Integer> year = dateOfResult.stream().map(s -> Integer.valueOf(s.getText().split("/", 2)[1])).collect(Collectors.toList());
        return year;
    }

    /*Get price of all results*/
    public List<Float> getPrice() {
        List<Float> price = priceOfResult.stream().map(s -> Float.valueOf(s.getText().split("€", 2)[0])).collect(Collectors.toList());
        return price;
    }

}
