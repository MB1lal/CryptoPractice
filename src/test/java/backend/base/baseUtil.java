package backend.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class baseUtil {

    public static String baseURL;
    public static String api_Key;
    public static String crypto_curr_map ="/cryptocurrency/map";
    public static String crypto_conv = "/tools/price-conversion";
    public static String crypto_info =  "/cryptocurrency/info";

    public ExtentReports extent;

    public static ExtentTest scenarioDef; // Holds the frontEnd.backendTasks

    public static ExtentTest features; // Holds the Scenario

    public static String reportLocation = System.getProperty("user.dir") + "/target/test-output/APIReport/";

    public static Logger logger = LogManager.getLogger(backend.driver.runner.class);

    public static String stepName = "";
    public static String gherkinWord = null;

}
