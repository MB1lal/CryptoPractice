package backend.steps;

import backend.base.BaseUtil;
import backend.base.ExtentReportUtil;
import backend.config.ConfigProperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends BaseUtil {

    ExtentReportUtil extentreportutil = new ExtentReportUtil();
    private static boolean dUnit = false;



    @Before
    public void initializeTest(Scenario scenario)
    {
        if(!dUnit)
        {
            logger.info("Fetching properties from file.");
            BaseUtil.baseurl = ConfigProperties.readPropertyFile("baseURLAPI");
            BaseUtil.api_key = ConfigProperties.readPropertyFile("API_KEY");

            //ExtentReportUtil.ExtentReport();
            //features = ExtentReportUtil.extent.createTest(Feature.class, scenario.getName());
            dUnit = true;
        }
       // scenarioDef = features.createNode(scenario.getName());
    }

    @After
    public void TestTearDown(Scenario scenario)
    {
       // ExtentReportUtil.FlushReport();
    }


}
