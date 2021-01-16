package frontend.driver;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features/frontendTasks"} , glue = "frontend/steps", plugin = {"json:target/cucumber-report.json"})
public class runner {
}
