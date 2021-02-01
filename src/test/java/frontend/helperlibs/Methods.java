package frontend.helperlibs;

import frontend.config.ConfigProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;


public class Methods {


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

        return ConfigProperties.readPropertyFile("baseURL");

    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static List<List<String>> getTableData(WebDriver driver, By table_element)
    {
        List<List<String>> data_table = new ArrayList<>();
        List<WebElement> web_element_list;
        web_element_list = driver.findElements(table_element);

        int row_count = 0;
        int col_count = 0;
        String counter = "1";

        data_table.add(new ArrayList<>());

        for(int i=0;i<web_element_list.size();i++)
        {
            if(counter.equals(web_element_list.get(i).getText()))
            {
                data_table.add(new ArrayList<>());
                ++row_count;
                col_count = 0;
                counter = String.valueOf(Integer.parseInt(counter) + 1);
                data_table.get(row_count).add(col_count, web_element_list.get(i).getAttribute("innerText"));
                col_count++;
            } else
            {
                data_table.get(row_count).add(col_count,web_element_list.get(i).getAttribute("innerText"));
                col_count++;
            }
        }




        return data_table;
    }

}
