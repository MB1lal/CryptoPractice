package frontend.steps;

import com.aventstack.extentreports.gherkin.model.Feature;
import frontend.base.extentReportUtil;
import frontend.base.baseUtil;
import frontend.helperLibs.methods;
import io.cucumber.java.*;

public class hook extends baseUtil {

    public frontend.base.baseUtil base;

    frontend.base.extentReportUtil extentReportUtil = new extentReportUtil();
    public hook(baseUtil base)
    {
        this.base = base;
    }

    @Before
    public void InitializeTest(Scenario scenario)
    {
        try {
            extentReportUtil.ExtentReport();
            features = extentReportUtil.extent.createTest(Feature.class, scenario.getName());
            scenarioDef = features.createNode(scenario.getName());
            base.Driver = methods.getBrowser(); // Returns the browser
            base.Driver.manage().window().maximize();
            logger.info("Opening the browser: " + base.Driver.toString());
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }


    @After
    public void TestTearDown(Scenario scenario) {
        try
        {
            extentReportUtil.FlushReport();
            logger.info("Closing the browser: " + base.Driver.toString());
            base.Driver.quit();
        }
        catch (Exception e)
        {
            logger.error(e.toString());
        }
    }



    @BeforeStep
    public void beforeStep(Scenario scenario) throws  Throwable
    {

    }


    @AfterStep
    public void afterStep(Scenario scenario) throws Throwable
    {
        /*
        if(scenario.isFailed())
        {
            try{
                scenarioDef.createNode(gherkinWord, stepName).fail("Fail");
                extentReportUtil.ExtentReportScreenshot(base.Driver, "Failed.");
                logger.error("Taking screenshot on test failure.");


            }catch (IOException e){
                e.printStackTrace();
            }
        }
        else
        {

            scenarioDef.createNode(gherkinWord, stepName).pass("Pass");
        }*/
    }



}
