package backend.driver;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/BackendTasks"} ,
        glue = "backend/steps",
        plugin = {"json:target/cucumber-report.json"}
        )

public class Runner {



}
