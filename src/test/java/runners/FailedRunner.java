package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",//==> console da scenariolar ile ilgili ayrintili bilgi verir
                "html:target/default-cucumber-reports.html",
                "json:target/json-reports/cucumber.json",
                "junit:target/xml-report/cucumber.xml",
                "rerun:TestOutPut/failed_scenario.txt"},
        features = "@TestOutPut/failed_scenario.txt", // yol olarak dosya verirsek başına @ koyarız
        glue = {"stepDefinitions", "hooks"},
        dryRun = false,
        monochrome = false

)



public class FailedRunner {


}
