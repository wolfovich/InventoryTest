package pageobjects;

import framework.WebDriverCommands;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

        JSONParser parser = new JSONParser();

        Object jsonParse = new Object();
        JSONObject jsonObjects;
        JSONObject jsonResultObject;

        String response;
        String toursQuantity;
        String json;
        String success;


        By byDefaultSearchResult = By.xpath(DEFAULT_SEARCH_RESULT);
        waitForElementDisplayed(byDefaultSearchResult, CONSTANT_3_SECONDS);

        response = findElement(byDefaultSearchResult).getText().toLowerCase();

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
        toursQuantity = jsonResultObject.get("toursquantity").toString();

        Assert.assertTrue(success.equals("true"), "default search is false, source: " + inventoryErrorSource(inventory));

        Assert.assertTrue(Integer.parseInt(toursQuantity) > 0, "Tours quantity is no > 0, source: " + inventoryErrorSource(inventory));


        driver.close();
    }
}
