package pageobjects;

import framework.WebDriverCommands;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by User on 18.08.2016.
 */
public class checkAsyncSearchStatusPage extends WebDriverCommands
{
    private final String ASYNC_SEARCH_STATUS = "//pre";

    public checkAsyncSearchStatusPage() throws Exception
    {
        switchToNewTab(1);

        By byAsyncSearchStatus = By.xpath(ASYNC_SEARCH_STATUS);
        waitForElementDisplayed(byAsyncSearchStatus, CONSTANT_3_SECONDS);

        String s = findElement(byAsyncSearchStatus).getText().toLowerCase();
        String status = s.substring(s.indexOf("success") + 9, s.indexOf("result") - 2);

        Assert.assertTrue(status.equals("true"), "success false");

        String currentURL = driver.getCurrentUrl();
        String defaultSession = currentURL.substring(currentURL.indexOf("session") + 8, currentURL.indexOf("&_"));

        currentURL = currentURL.replace(defaultSession, referencePage.session);

        String jobsCount = "";
        String jobsSuccess = "";

        for(int jobSuccessIterator = 0; jobSuccessIterator != 10; jobSuccessIterator++)
        {
            driver.get(currentURL);
            waitTime(1);
            s = findElement(byAsyncSearchStatus).getText().toLowerCase();
            waitForElementDisplayed(byAsyncSearchStatus, CONSTANT_3_SECONDS);
            jobsCount = s.substring(s.indexOf("jobscount") + 12, s.indexOf("jobssuccess") - 3);
            jobsSuccess = s.substring(s.indexOf("jobssuccess") + 14, s.indexOf("tourscount") - 3);

            if(jobsCount.equals(jobsSuccess) )
            {
                break;
            }
        }
        Assert.assertTrue(jobsCount.equals(jobsSuccess), "jobs count not equal jobs success");
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

