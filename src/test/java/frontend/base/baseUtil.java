package frontend.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class baseUtil {


    public WebDriver Driver;

    public ExtentReports extent;

    public static ExtentTest scenarioDef; // Holds the frontEnd.BETasks

    public static ExtentTest features; // Holds the Scenario

    public static String reportLocation = System.getProperty("user.dir") + "/target/test-output/HtmlReport/";

    public static Logger logger = LogManager.getLogger(frontend.base.baseUtil.class);

    public static String stepName = "";
    public static String gherkinWord = null;


}
