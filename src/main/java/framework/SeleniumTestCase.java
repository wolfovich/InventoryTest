package framework;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 16.08.2016.
 */
public class SeleniumTestCase extends WebDriverCommands
{
    /*public static String fileName = "";
    Cookie cookie = new Cookie("mobileDevice", "true");
    Cookie cookie2 = new Cookie("discountTourBanner", "true");
*/
    @BeforeMethod
    public void setUp(){

        FirefoxBinary binary = new FirefoxBinary(new File("/usr/bin/firefox"));
        binary.setEnvironmentProperty("DISPLAY",System.getProperty("lmportal.xvfb.id", ":99"));
        FirefoxProfile profile = new FirefoxProfile();
        driver = new FirefoxDriver(binary, profile);
 //       driver = new FirefoxDriver();


   //     driver = new FirefoxDriver(profile);
       // System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
       // driver = new ChromeDriver();
        //System.setProperty("webdriver.firefox.bin", "xvfb-run --auto-servernum --server-num=0    /usr/bin/firefox");
//        driver = new InternetExplorerDriver();
//        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_11);
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
//        driver.manage().window().setSize(new Dimension(1100, 1100));
        driver.manage().window().maximize();
        //driver.get("https://mda2.invia.ru:1003/login/login/?referer=%2F");
        //driver.get("http://inventory.travellata.ru/demo/references");

       /* driver.manage().addCookie(cookie);
        driver.manage().addCookie(cookie2);
        driver.navigate().refresh();*/

    }


    @AfterMethod
    public void tearDown() {
        //driver.manage().deleteCookie(cookie);
        driver.quit();
    }
}
