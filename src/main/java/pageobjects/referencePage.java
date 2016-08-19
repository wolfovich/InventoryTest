package pageobjects;

import framework.WebDriverCommands;
import org.openqa.selenium.By;

/**
 * Created by User on 16.08.2016.
 */
public class referencePage extends WebDriverCommands
{

    private final String ASYNC_SEARCH_LINK = "//td[contains(text(), 'Async search')]/following-sibling::td/a";
    private final String ASYNC_SEARCH_STATUS_LINK = "//td[contains(text(), 'Check Async Status')]/following-sibling::td/a";
    private final String DEFAULT_SEARCH_LINK = "//td[contains(text(), 'Default search (mobile usage too, SERP)')]/following-sibling::td/a";


    protected static String session;

    protected static String inventory;
    protected static String inventory2;
    protected static String inventory4;
    protected static String inventory8;
    protected static String inventory10;
    protected static String inventory11;


    public sendAsyncSearchRequestPage selectAsyncSearchLink() throws Exception
    {
        By byAsyncSearchLink = By.xpath(ASYNC_SEARCH_LINK);
        waitForElementDisplayed(byAsyncSearchLink, CONSTANT_3_SECONDS);
        click(byAsyncSearchLink);

        return new sendAsyncSearchRequestPage();
    }

    public checkAsyncSearchStatusPage selectAsyncSearchStatusLink() throws Exception
    {
        By byAsyncSearchLink = By.xpath(ASYNC_SEARCH_STATUS_LINK);
        waitForElementDisplayed(byAsyncSearchLink, CONSTANT_3_SECONDS);
        click(byAsyncSearchLink);

        return new checkAsyncSearchStatusPage();
    }

    public searchPage selectDefaultSearchLink() throws Exception
    {
        By byAsyncSearchLink = By.xpath(DEFAULT_SEARCH_LINK);
        waitForElementDisplayed(byAsyncSearchLink, CONSTANT_3_SECONDS);
        click(byAsyncSearchLink);

        return new searchPage();
    }


}
