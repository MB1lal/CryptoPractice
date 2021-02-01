package frontend.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class BaseUtil {


    public WebDriver driver;

    public ExtentReports extent;

    public static ExtentTest scenario_def; // Holds the frontEnd.BETasks

    public static ExtentTest features; // Holds the Scenario

    public static String report_location = System.getProperty("user.dir") + "/target/test-output/HtmlReport/";

    public static Logger logger = LogManager.getLogger(BaseUtil.class);

    public static String step_name = "";
    public static String gherkin_word = null;


}
