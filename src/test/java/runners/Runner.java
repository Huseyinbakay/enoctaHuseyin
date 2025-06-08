package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",//==> console da scenariolar ile ilgili ayrintili bilgi verir

                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "rerun:TestOutPut/failed_scenario.txt"},
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "hooks","utilities"},
        tags = "@UI",
        dryRun = false,
        monochrome = false)



public class Runner {
}
