package frontend.steps;

import com.aventstack.extentreports.gherkin.model.Feature;
import frontend.base.BaseUtil;
import frontend.base.ExtentReportUtil;
import frontend.helperlibs.Methods;
import io.cucumber.java.*;

public class Hooks extends BaseUtil {

    public BaseUtil base;

    ExtentReportUtil extentreportutil = new ExtentReportUtil();
    public Hooks(BaseUtil base)
    {
        this.base = base;
    }

    @Before
    public void InitializeTest(Scenario scenario)
    {
        try {
            extentreportutil.ExtentReport();
            features = extentreportutil.extent.createTest(Feature.class, scenario.getName());
            scenario_def = features.createNode(scenario.getName());
            base.driver = Methods.getBrowser(); // Returns the browser
            base.driver.manage().window().maximize();
            logger.info("Opening the browser: " + base.driver.toString());
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }


    @After
    public void TestTearDown(Scenario scenario) {
        try
        {
            extentreportutil.FlushReport();
            logger.info("Closing the browser: " + base.driver.toString());
            base.driver.quit();
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
                ExtentReportUtil.ExtentReportScreenshot(base.Driver, "Failed.");
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
