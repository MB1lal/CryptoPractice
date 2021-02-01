package backend.base;

import backend.driver.Runner;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseUtil {

    public static String baseurl;
    public static String api_key;
    public static String crypto_curr_map ="/cryptocurrency/map";
    public static String crypto_conv = "/tools/price-conversion";
    public static String crypto_info =  "/cryptocurrency/info";

    public ExtentReports extent;

    public static ExtentTest scenario_def; // Holds the frontEnd.BackendTasks

    public static ExtentTest features; // Holds the Scenario

    public static String report_location = System.getProperty("user.dir") + "/target/test-output/APIReport/";

    public static Logger logger = LogManager.getLogger(Runner.class);

    public static String step_name = "";
    public static String gherkin_word = null;

}
