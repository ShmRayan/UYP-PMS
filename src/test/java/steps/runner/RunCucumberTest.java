package steps.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/contracts",
    glue = {"steps"},
    plugin = {"pretty", "html:build/reports/cucumber.html"}
)
public class RunCucumberTest {
}
