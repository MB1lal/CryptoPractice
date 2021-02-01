package frontend.base;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ExtentReportUtil extends BaseUtil {




    String file_name = report_location + "ExtentHtml.html";



    public void ExtentReport() {

        extent = new ExtentReports();

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(file_name);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Test report for ecom-Automation");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Test report of Login/SignIn Flows");

        extent.attachReporter(htmlReporter);
    }

    public void ExtentReportScreenshot(WebDriver driver, String error) throws IOException {

        var scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(report_location + step_name + "-screenshot.png");
        Files.copy(scr.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        scenario_def.fail(error).addScreenCaptureFromPath(dest.toString());

    }


    public void FlushReport(){
       /* JsonFormatter json = new JsonFormatter(System.getProperty("user.dir") + "/target/cucumber-report.json");
        extent.attachReporter(json);
        try
        {
            extent.createDomainFromJsonArchive(System.getProperty("user.dir") + "/target/cucumber-report.json");
        }catch (Exception e)
        {
            logger.trace(e);
        }*/
        extent.flush();
    }




}
