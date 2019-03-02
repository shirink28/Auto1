package test;

import base.PageTest;
import object.SearchPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import util.CONSTANTS;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FilterAndSortTest extends PageTest {

    SearchPage home = null;

    @Before
    public void initDriver() {
        /*Initialising the Chrome driver*/
        WebDriver driver = initChromeDriver();
        /*Launching the application*/
        driver.get(CONSTANTS.URL);
        /*Creating Instances of  Page Object*/
        home = new SearchPage(driver);
    }

    @Test
    public void verifyFilterAndSortResults() {
        /*Step 1: Select registration from year*/
        home.selectRegistrationYear(CONSTANTS.YEAR);
        /*Step 2: Select sort option*/
        home.selectSort(CONSTANTS.SortOptions.HighestPrice);
        List<Integer> yearList = new ArrayList<>();
        List<Float> priceList = new ArrayList<>();
        int j = 1;
        do {
            if (j > 1) {
                home.selectPage(j);
            }
            yearList.addAll(home.getYear());
            priceList.addAll(home.getPrice());
            j++;
        } while (home.pageExists((j)));

        /*Verifying results filtered by registration from year*/
        List result = yearList.stream().filter(s -> s < Integer.valueOf(CONSTANTS.YEAR)).collect(Collectors.toList());
        boolean b = result.size() == 0;
        Assert.assertEquals("Records are not as per registration from year filter", b, true);

        /*Verifying results sorted in descending order of price*/
        List<Float> priceComparator = new ArrayList<>();
        priceComparator.addAll(priceList);
        Collections.sort(priceComparator, Collections.reverseOrder());
        Assert.assertArrayEquals("records are not sorted ", priceList.toArray(), priceComparator.toArray());
    }

    @After
    public void exitDriver() {
        /*Closing the driver*/
        driver.quit();

    }

}
