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

    public sendAsyncSearchRequestPage(String inventory) throws Exception
    {
        switchToNewTab(1);

        By byAsyncSearchStatus = By.xpath(ASYNC_SEARCH_STATUS);
        waitForElementDisplayed(byAsyncSearchStatus, CONSTANT_3_SECONDS);
        String s = findElement(byAsyncSearchStatus).getText().toLowerCase();
        String status = s.replaceFirst(".*?success\":", "").replaceFirst(",.*", "");

        referencePage.session = s.replaceFirst(".*session\":\"", "").replaceFirst("\".*", "");
        Assert.assertTrue(status.equals("true"), "sending async search request is false, source: " + inventory.replaceFirst(".*\\/\\/", "").replaceFirst("\\..*", ""));

        driver.close();
    }


}
