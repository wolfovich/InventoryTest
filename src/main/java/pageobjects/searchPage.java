package pageobjects;

import framework.WebDriverCommands;
import org.openqa.selenium.By;
import org.testng.Assert;
import java.util.regex.*;

/**
 * Created by User on 19.08.2016.
 */
public class searchPage extends WebDriverCommands
{
    private final String DEFAULT_SEARCH_RESULT = "//pre";

    public  searchPage(String inventory) throws Exception
    {
        switchToNewTab(1);

        Pattern toursQuantityPattern = Pattern.compile("\\d*$");
        Pattern statusPattern = Pattern.compile("\\w*");
        Pattern errorSourcePattern = Pattern.compile("inventory\\w*");
        Matcher matcher;

        String statusRegExp = ".*?success\":";
        String toursQuantityRegExp = ",\"operators.*";
        String response;
        String status = "";
        String toursQuantity = "";

        By byDefaultSearchResult = By.xpath(DEFAULT_SEARCH_RESULT);
        waitForElementDisplayed(byDefaultSearchResult, CONSTANT_3_SECONDS);

        response = findElement(byDefaultSearchResult).getText().toLowerCase();

        matcher = statusPattern.matcher(response.replaceFirst(statusRegExp, ""));
        if (matcher.find())
        {
            status = matcher.group(0);
        }

        matcher = errorSourcePattern.matcher(inventory);
        matcher.find();
        Assert.assertTrue(status.equals("true"), "default search is false, source: " + matcher.group(0));

        matcher = toursQuantityPattern.matcher(response.replaceFirst(toursQuantityRegExp, ""));
        if(matcher.find())
        {
            toursQuantity = matcher.group(0);
        }

        matcher = errorSourcePattern.matcher(inventory);
        matcher.find();
        Assert.assertTrue(Integer.parseInt(toursQuantity) > 0, "Tours quantity is no > 0, source: " + matcher.group(0));


        driver.close();
    }
}
