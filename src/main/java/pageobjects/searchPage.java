package pageobjects;

import framework.WebDriverCommands;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by User on 19.08.2016.
 */
public class searchPage extends WebDriverCommands
{
    private final String DEFAULT_SEARCH_RESULT = "//pre";

    public  searchPage(String inventory) throws Exception
    {
        switchToNewTab(1);

        By byDefaultSearchResult = By.xpath(DEFAULT_SEARCH_RESULT);
        waitForElementDisplayed(byDefaultSearchResult, CONSTANT_3_SECONDS);

        String s = findElement(byDefaultSearchResult).getText().toLowerCase();
        String status = s.replaceFirst(".*?success\":", "").replaceFirst(",.*", "");

        Assert.assertTrue(status.equals("true"), "default search is false, source: " + inventory.replaceFirst(".*\\/\\/", "").replaceFirst("\\..*", ""));

        String toursQuantity = s.replaceFirst(".*toursQuantity\":", "").replaceFirst(",.*", "");
        Assert.assertTrue(Integer.parseInt(toursQuantity) > 0);

        driver.close();
    }
}
