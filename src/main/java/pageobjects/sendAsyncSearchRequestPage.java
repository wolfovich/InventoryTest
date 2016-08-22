package pageobjects;

import framework.WebDriverCommands;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.regex.*;
import org.json.simple.*;


/**
 * Created by User on 18.08.2016.
 */
public class sendAsyncSearchRequestPage extends WebDriverCommands
{

    private final String ASYNC_SEARCH_STATUS = "//pre";

    public sendAsyncSearchRequestPage(String inventory) throws Exception
    {
        switchToNewTab(1);

        Pattern statusPattern = Pattern.compile("\\w*");
        Pattern sessionPattern = Pattern.compile("\\w*");
        Pattern errorSourcePattern = Pattern.compile("\\w*");

        Matcher matcher;

        String statusRegExp = ".*?success\":";
        String sessionRegExp = ".*session\":\"";
        String response;
        String status = "";

        By byAsyncSearchStatus = By.xpath(ASYNC_SEARCH_STATUS);
        waitForElementDisplayed(byAsyncSearchStatus, CONSTANT_3_SECONDS);

        response = findElement(byAsyncSearchStatus).getText().toLowerCase();




        matcher = statusPattern.matcher(response.replaceFirst(statusRegExp, ""));
        if (matcher.find())
        {
            status = matcher.group(0);
        }

        matcher = sessionPattern.matcher(response.replaceFirst(sessionRegExp, ""));
        if (matcher.find())
        {
            referencePage.session = matcher.group(0);
        }

        matcher = errorSourcePattern.matcher(inventory);
        matcher.find();
        Assert.assertTrue(status.equals("true"), "sending async search request is false, source: " + matcher.group(0));

        driver.close();
    }


}
