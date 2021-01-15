package backend.steps;

import backend.base.baseUtil;
import backend.base.extentReportUtil;
import backend.config.configProperties;
import com.aventstack.extentreports.gherkin.model.Feature;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class hook extends baseUtil{

    backend.base.extentReportUtil extentReportUtil = new extentReportUtil();


    @Before
    public void intializeTest(Scenario scenario)
    {
        logger.info("Fetching properties from file.");
        baseUtil.baseURL = configProperties.readPropertyFile("baseURLAPI");
        baseUtil.api_Key = configProperties.readPropertyFile("API_KEY");

        extentReportUtil.ExtentReport();
        features = extentReportUtil.extent.createTest(Feature.class, scenario.getName());
        scenarioDef = features.createNode(scenario.getName());



    }

    @After
    public void TestTearDown(Scenario scenario) throws Exception
    {
        extentReportUtil.FlushReport();
    }


}