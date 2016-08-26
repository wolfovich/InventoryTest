package pageobjects;

import framework.WebDriverCommands;
import org.testng.Assert;
import org.openqa.selenium.By;

import java.util.regex.Matcher;

/**
 * Created by User on 16.08.2016.
 */
public class referencePage extends WebDriverCommands
{

    private final String ASYNC_SEARCH_LINK = "//td[contains(text(), 'Async search')]/following-sibling::td/a";
    private final String ASYNC_SEARCH_STATUS_LINK = "//td[contains(text(), 'Check Async Status')]/following-sibling::td/a";
    private final String DEFAULT_SEARCH_LINK = "//td[contains(text(), 'Default search (mobile usage too, SERP)')]/following-sibling::td/a";


    protected static String session;

    public sendAsyncSearchRequestPage selectAsyncSearchLink(String inventory) throws Exception
    {
        By byAsyncSearchLink = By.xpath(ASYNC_SEARCH_LINK);
        waitForElementDisplayed(byAsyncSearchLink, CONSTANT_3_SECONDS);

        click(byAsyncSearchLink);

        return new sendAsyncSearchRequestPage(inventory);
    }

    public checkAsyncSearchStatusPage selectAsyncSearchStatusLink(String inventory) throws Exception
    {
        By byAsyncSearchLink = By.xpath(ASYNC_SEARCH_STATUS_LINK);
        waitForElementDisplayed(byAsyncSearchLink, CONSTANT_3_SECONDS);

        click(byAsyncSearchLink);

        return new checkAsyncSearchStatusPage(inventory);
    }

    public searchPage selectDefaultSearchLink(String inventory) throws Exception
    {
        By byAsyncSearchLink = By.xpath(DEFAULT_SEARCH_LINK);
        waitForElementDisplayed(byAsyncSearchLink, CONSTANT_3_SECONDS);

        click(byAsyncSearchLink);

        return new searchPage(inventory);
    }


}
