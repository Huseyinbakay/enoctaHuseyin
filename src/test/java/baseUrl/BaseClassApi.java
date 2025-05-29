package baseUrl;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import utilities.ConfigReader;

public class BaseClassApi {
    protected static RequestSpecification spec;
    @Before
    public void setUp() throws Exception {



        String url= ConfigReader.getProperty("apiUrl");

        spec= new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri(url).build();


    }
}
