package pageobjects;

import framework.WebDriverCommands;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by User on 18.08.2016.
 */
public class sendAsyncSearchRequestPage extends WebDriverCommands
{


    private final String ASYNC_SEARCH_STATUS = "//pre";

    public sendAsyncSearchRequestPage() throws Exception
    {
        switchToNewTab(1);

        By byAsyncSearchStatus = By.xpath(ASYNC_SEARCH_STATUS);
        waitForElementDisplayed(byAsyncSearchStatus, CONSTANT_3_SECONDS);
        String s = findElement(byAsyncSearchStatus).getText().toLowerCase();
        String status = s.substring(s.indexOf("success") + 9, s.indexOf("result") - 2);
        referencePage.session = s.substring(s.indexOf("session") + 10, s.indexOf("message") - 4);
        Assert.assertTrue(status.equals("true"), "success false");

        driver.close();
    }


}
