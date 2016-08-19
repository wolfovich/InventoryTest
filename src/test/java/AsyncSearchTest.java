import framework.SeleniumTestCase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.referencePage;

import java.util.ArrayList;

/**
 * Created by User on 16.08.2016.
 */
public class AsyncSearchTest extends SeleniumTestCase
{
    @Test(dataProvider = "inventoryURLs", enabled = true)

    private void asyncSearchTest(String inventory, String inventory2, String inventory4, String inventory8, String inventory10, String inventory11) throws Exception
    {
        ArrayList<String> inventoryList = createListOfInventoryUrls(inventory, inventory2, inventory4, inventory8, inventory10, inventory11);

        for(String temp: inventoryList)
        {
            driver.get(temp);

            referencePage referencePage = new referencePage();


            referencePage.selectAsyncSearchLink();
            switchToNewTab(0);

            referencePage.selectAsyncSearchStatusLink();
            switchToNewTab(0);

            referencePage.selectDefaultSearchLink();
            switchToNewTab(0);
        }


    }

    private ArrayList<String> createListOfInventoryUrls(String inventory, String inventory2, String inventory4, String inventory8, String inventory10, String inventory11) throws Exception
    {
        ArrayList<String> inventoryList = new ArrayList<String>();
        inventoryList.add(inventory);
        inventoryList.add(inventory2);
        inventoryList.add(inventory4);
        inventoryList.add(inventory8);
        inventoryList.add(inventory10);
        inventoryList.add(inventory11);

        return inventoryList;
    }


    @DataProvider
    public Object[][]  inventoryURLs()
    {
        return new Object[][]
                {
                        {
                                "http://inventory.travelata.ru/demo/references",
                                "http://inventory2.travelata.ru/demo/references",
                                "http://inventory4.travelata.ru/demo/references",
                                "http://inventory8.travelata.ru/demo/references",
                                "http://inventory10.travelata.ru/demo/references",
                                "http://inventory11.travelata.ru/demo/references",
                        }
                };

    }
}
