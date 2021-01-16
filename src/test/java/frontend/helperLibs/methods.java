package frontend.helperLibs;

import frontend.config.configProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.ArrayList;
import java.util.List;


public class methods {


    public static WebDriver getBrowser()
    {
        /*
         * Returns the specified browser.
         * In case no browser is found, it runs chrome.
         */

        WebDriver driver;
        String browser = "chrome";

        switch (browser) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver","src/test/resources/webDrivers/geckodriver");
                return driver = new FirefoxDriver();


            case "chrome":

                System.setProperty("webdriver.chrome.driver", "src/test/resources/webDrivers/chromedriver");
                return driver = new ChromeDriver();


            default:
                System.out.println("No browser found. Hence using chrome.");
                System.setProperty("webdriver.chrome.driver", "src/test/resources/webDrivers/chromedriver");
                return driver = new ChromeDriver();

        }

    }

    public static String getUrl()
    {

        return configProperties.readPropertyFile("baseURL");

    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static List<List<String>> getTableData(WebDriver driver, By tableElement)
    {
        List<List<String>> dataTable = new ArrayList<>();
        List<WebElement> webElementList;
        webElementList = driver.findElements(tableElement);

        int rowCount = 0;
        int colCount = 0;
        String counter = "1";

        dataTable.add(new ArrayList<>());

        for(int i=0;i<webElementList.size();i++)
        {
            if(counter.equals(webElementList.get(i).getText()))
            {
                dataTable.add(new ArrayList<>());
                ++rowCount;
                colCount = 0;
                counter = String.valueOf(Integer.parseInt(counter) + 1);
                dataTable.get(rowCount).add(colCount, webElementList.get(i).getAttribute("innerText"));
                colCount++;
            } else
            {
                //dataTable.get(rowCount).add(String.valueOf(new ArrayList()));
                dataTable.get(rowCount).add(colCount,webElementList.get(i).getAttribute("innerText"));
                colCount++;
            }
        }




        return dataTable;
    }

}
