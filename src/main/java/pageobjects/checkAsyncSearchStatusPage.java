package pageobjects;

import framework.WebDriverCommands;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

        int iterationCount = 60;

        Matcher matcher;

        Pattern defaultSessionPattern = Pattern.compile("\\w*");
        String sessionRegExp = ".*session=";

        JSONParser parser = new JSONParser();
        Object jsonParse = new Object();
        JSONObject jsonObjects;
        JSONObject jsonResultObject;

        String response;
        String jobsCount = "";
        String jobsSuccess = "";
        String currentURL = driver.getCurrentUrl();
        String newURL;
        String defaultSession = "";
        String json;
        String success;

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


        success = jsonObjects.get("success").toString();

        Assert.assertTrue(success.equals("true"), "async search status is false, source: " + inventoryErrorSource(inventory));


        matcher = defaultSessionPattern.matcher(currentURL.replaceFirst(sessionRegExp, ""));
        if (matcher.find())
        {
            defaultSession = matcher.group(0);
        }

        newURL = currentURL.replace(defaultSession, referencePage.session);

        for(int jobSuccessIterator = 0; jobSuccessIterator != iterationCount; jobSuccessIterator++)
        {
            driver.get(newURL);

            waitTime(1);

            response = findElement(byAsyncSearchStatus).getText().toLowerCase();
            waitForElementDisplayed(byAsyncSearchStatus, CONSTANT_3_SECONDS);

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
            jsonResultObject = (JSONObject) jsonObjects.get("result");

            jobsCount = jsonResultObject.get("jobscount").toString();
            jobsSuccess = jsonResultObject.get("jobssuccess").toString();

            if(jobsCount.equals(jobsSuccess) )
            {
                break;
            }
        }

        Assert.assertTrue(jobsCount.equals(jobsSuccess), "jobs count is not equal to jobs success, source: " + inventoryErrorSource(inventory));


        driver.close();

    }
}

