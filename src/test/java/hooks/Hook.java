package hooks;

import baseUrl.BaseClassApi;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.ConfigReader;
import utilities.Driver;

public class Hook  extends BaseClassApi{


      @Before()

    public void setUp() throws Exception {

          String url=ConfigReader.getProperty("apiUrl");

          spec= new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri(url).build();
      }




    @After("@UI")

    public void tearDown(Scenario scenario) throws Exception {
        if( scenario.isFailed()){
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            scenario.attach(ts.getScreenshotAs(OutputType.BYTES),"image/png","scenario"+scenario.getName());
            Driver.closeDriver();
        }
         Driver.closeDriver();
    }








}
