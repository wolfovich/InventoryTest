package framework;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.Console;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 16.08.2016.
 */
public class WebDriverCommands
{
    public final int CONSTANT_3_SECONDS = 3;
    public static WebDriver driver;


    public String fromJsonpToJsonParse(String JsonpResponse)
    {
        String json = JsonpResponse.replaceFirst(".*?\\(", "").replaceFirst("\\);", "");

        return json;
    }

    public String inventoryErrorSource(String inventoryUrl)
    {
        Matcher matcher;
        Pattern errorSourcePattern = Pattern.compile("inventory\\w*");
        matcher = errorSourcePattern.matcher(inventoryUrl);
        matcher.find();

        return matcher.group(0);
    }

    public String getKeyFromValue(Map hm, Object value)
    {
        Set ref = hm.keySet();
        Iterator iterator = ref.iterator();
        String key = new String();

        while (iterator.hasNext())
        {
            Object o = iterator.next();
            if (hm.get(o).equals(value))
            {
                key = o.toString();
            }
        }
        return key;
    }


    /**
     * Method waits for timeout interval
     * @param secsToWait   Integer Interval in sec to wait
     */
    public void waitTime(int secsToWait) {

        try {
            for (int second = 0; ; second++) {
                if ((second > secsToWait)) {
                    break;
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }

    }

    /**
     * Method switch to new browser tab
     */
    public void switchToNewTab(int tabNumber){ //tab number starts from 0

        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
//        newTab.remove(oldTab);
        // change focus to new tab
        driver.switchTo().window(newTab.get(tabNumber));
    }

    public void closeOldTab()
    {
        ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tab.get(0));
        driver.close();
        tab.remove(tab.size());
    }

    /**
     * Method clear element
     * @param by By Element
     */
    public void clear(By by){
        if (isElementPresent(by)){
            driver.findElement(by).clear();
        }
    }

    /**
     * Method send keys to element
     * @param by    By Element
     * @param value String value
     */
    public void sendKeys(By by, String value){
        if (isElementPresent(by)){
            driver.findElement(by).sendKeys(value);
        }
    }

    public static String  getFullDateFromCurrent(int daysCount){
        Calendar c = Calendar.getInstance();
        Date dt = new Date();
        c.setTime(dt);
        c.add(Calendar.DATE, daysCount);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(c.getTime());
    }

    public String deleteFirstNullFromDate(String dayValue){
        dayValue = dayValue.replaceFirst("^0", "");
        return dayValue;
    }

    /**
     * Click some element
     * @param by    By  Element that is needed to be clicked
     */
    public void click(By by)
    {
        if (isElementDisplayed(by))
        {
            driver.findElement(by).click();
        }

    }

    /**
     * Check if element is displayed on page
     * @param by    By  Element that is needed to be checked
     * @return  true:   if element is displayed
     *          false:  otherwise
     */
    public boolean isElementDisplayed(By by)
    {
        return driver.findElement(by).isDisplayed();
    }

    /**
     * Wait for element not to be visible
     * @param by        By  Needed element to be waited for
     * @param timeOut   Integer Time limit for element not to be visible
     */
    public void waitForElementNotVisible(final By by, int timeOut)
    {
        (new WebDriverWait(driver, timeOut))
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(by));
    }

    /**
     * Check if element is present
     * @param by    By  Needed element to be checked
     * @return  true:   element is present
     *          false:  otherwise
     */
    public boolean isElementPresent(By by)
    {
        try
        {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e)
        {
            return false;
        }
    }

    /*public void waitForElementPresent(By by, int timeOut)
    {
        for (int i=0; i<timeOut; i++){
            if (isElementPresent(by)) break;
        }
    }*/

    /**
     * Wait for element to be displayed
     * @param by        By  Needed element to be waited for
     * @param timeOut   Integer Time limit for element to be displayed
     */
    public void waitForElementDisplayed(final By by, int timeOut)
    {
        try
        {
            (new WebDriverWait(driver, timeOut)).until(ExpectedConditions.visibilityOfElementLocated(by));
        }
        catch (org.openqa.selenium.TimeoutException timeout)
        {
            String currenyUrl = driver.getCurrentUrl();
            System.out.println("Error source: " + currenyUrl);
        }

    }


    /**
     * Find element on page
     * @param by    By  Needed element to be found
     * @return  WebElement object
     */
    public WebElement findElement (By by)
    {
        return driver.findElement(by);
    }
}
