package pageobjects;

import framework.WebDriverCommands;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

        JSONParser parser = new JSONParser();

        Object jsonParse = new Object();
        JSONObject jsonObjects;
        JSONObject jsonResultObject;

        String response;
        String json;
        String success;
        String session;

        By byAsyncSearchStatus = By.xpath(ASYNC_SEARCH_STATUS);
        waitForElementDisplayed(byAsyncSearchStatus, CONSTANT_3_SECONDS);

        response = findElement(byAsyncSearchStatus).getText().toLowerCase();


        json = fromJsonpToJsonParse(response);

        try
        {
            jsonParse = parser.parse(json);
        }
        catch(ParseException e)
        {
            System.out.println("Something wrong with json response");
        }

        jsonObjects = (JSONObject) jsonParse;
        jsonResultObject =(JSONObject) jsonObjects.get("result");


        success = jsonObjects.get("success").toString();
        session = jsonResultObject.get("session").toString();

        referencePage.session = session;

        Assert.assertTrue(success.equals("true"), "sending async search request is false, source: " + inventoryErrorSource(inventory));

        driver.close();
    }


}
