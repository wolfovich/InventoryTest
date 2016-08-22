package pageobjects;

import framework.WebDriverCommands;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.regex.*;

/**
 * Created by User on 18.08.2016.
 */
public class checkAsyncSearchStatusPage extends WebDriverCommands
{
    private final String ASYNC_SEARCH_STATUS = "//pre";

    public checkAsyncSearchStatusPage(String inventory) throws Exception
    {
        switchToNewTab(1);

        Pattern statusPattern = Pattern.compile("\\w*");
        Pattern defaultSessionPattern = Pattern.compile("\\w*");
        Pattern errorSourcePattern = Pattern.compile("inventory\\w*");
        Matcher matcher;

        String statusRegExp = ".*?success\":";
        String sessionRegExp = ".*session=";
        String jobsCountRegExp = ".*?jobscount\":\"";
        String jobsSuccessRegExp = ".*?jobssuccess\":\"";

        String response;
        String status = "";
        String jobsCount = "";
        String jobsSuccess = "";
        String currentURL = driver.getCurrentUrl();
        String newURL;
        String defaultSession = "";

        By byAsyncSearchStatus = By.xpath(ASYNC_SEARCH_STATUS);
        waitForElementDisplayed(byAsyncSearchStatus, CONSTANT_3_SECONDS);

        response = findElement(byAsyncSearchStatus).getText().toLowerCase();

        matcher = statusPattern.matcher(response.replaceFirst(statusRegExp, ""));
        if (matcher.find())
        {
            status = matcher.group(0);
        }

        matcher = errorSourcePattern.matcher(inventory);
        matcher.find();
        Assert.assertTrue(status.equals("true"), "async search status is false, source: " + matcher.group(0));

        matcher = defaultSessionPattern.matcher(currentURL.replaceFirst(sessionRegExp, ""));
        if (matcher.find())
        {
            defaultSession = matcher.group(0);
        }

        newURL = currentURL.replace(defaultSession, referencePage.session);

        for(int jobSuccessIterator = 0; jobSuccessIterator != 15; jobSuccessIterator++)
        {
            driver.get(newURL);

            waitTime(1);

            response = findElement(byAsyncSearchStatus).getText().toLowerCase();
            waitForElementDisplayed(byAsyncSearchStatus, CONSTANT_3_SECONDS);

            jobsCount = response.replaceFirst(jobsCountRegExp, "").replaceFirst("\\W.*", "");
            jobsSuccess = response.replaceFirst(jobsSuccessRegExp, "").replaceFirst("\\W.*", "");


            if(jobsCount.equals(jobsSuccess) )
            {
                break;
            }
        }

        matcher = errorSourcePattern.matcher(inventory);
        matcher.find();
        Assert.assertTrue(jobsCount.equals(jobsSuccess), "jobs count not equal jobs success, source: " + matcher.group(0));


        /*try
        {
            Assert.assertTrue(jobsCount.equals(jobsSuccess), "jobsCount not equal jobsSuccess");

        }
        catch (AssertionError ae)
        {
            String message = ae.getMessage();
            System.out.print(message);
        }*/


        driver.close();

    }
}

