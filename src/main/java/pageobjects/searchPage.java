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

    public  searchPage() throws Exception
    {
        switchToNewTab(1);

        By byDefaultSearchResult = By.xpath(DEFAULT_SEARCH_RESULT);
        waitForElementDisplayed(byDefaultSearchResult, CONSTANT_3_SECONDS);

        String s = findElement(byDefaultSearchResult).getText().toLowerCase();
        String status = s.substring(s.indexOf("success") + 9, s.indexOf("result") - 2);

        Assert.assertTrue(status.equals("true"), "success false");

        String toursQuantity = s.substring(s.lastIndexOf("toursquantity") + 15, s.indexOf("operators") - 2);

        Assert.assertTrue(Integer.parseInt(toursQuantity) > 0);

        driver.close();
    }
}
