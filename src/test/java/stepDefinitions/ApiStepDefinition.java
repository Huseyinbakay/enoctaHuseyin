package stepDefinitions;


import hooks.Hook;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import baseUrl.BaseClassApi;

import org.junit.Assert;
import utilities.ConfigReader;
import utilities.Driver;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;


public class ApiStepDefinition extends BaseClassApi {


 Response response;
 JsonPath json;


  String ortakyol=System.getProperty("user.dir");
    String txtPath=ortakyol+"/response.txt";;


    @When("kullanıcı headera {string} ve {string} parametrelerini ekler")
    public void kullanıcıHeaderaVeParametreleriniEkler(String arg0, String arg1) {

        Map<String,String> addHeaders=new HashMap<>();

        addHeaders.put(arg0,ConfigReader.getProperty("user"));
        addHeaders.put(arg1,ConfigReader.getProperty("pass"));
        spec.headers(addHeaders);
    }




    @Then("Response içine {string} olduğu doğrulanır")
    public void responseIçineOlduğuDoğrulanır(String arg0) {
       assertEquals("token endpointi ile response status code 200'den farklı",response.statusCode(), 200);

        json=response.jsonPath();
        assertTrue("Response body"+arg0+"içermiyor",
                json.getMap("").containsKey(arg0));
    }


    @When("kullanıcı token için url i düzenler ve token post request gönderir")
    public void kullanıcıTokenIçinUrlIDüzenlerVeTokenPostRequestGönderir() {

        spec.pathParam("first","token");

        response = given(spec).when().post("/{first}");

    }





    @Given("kullanıcı {string} parametresi ve viewInvoice endpointi ile get request yapar")
    public void kullanıcıParametresiVeViewInvoiceEndpointiIleGetRequestYapar(String arg0) {

        int barcodeInt=Integer.valueOf(ConfigReader.getProperty("barcode"));

        spec.pathParams("first","viewInvoice").
                queryParam(arg0,barcodeInt);


        response = given(spec).when().get("/{first}");
        assertEquals("Status Code 200'den farklı",
                200, response.statusCode());

    }


    @And("response içerisinde {string} true değeri olduğu doğrulanır")
    public void responseIçerisindeTrueDeğeriOlduğuDoğrulanır(String arg0) {

        Assert.assertTrue("Response success false",response.jsonPath().getBoolean("Result.success") );

    }

    @Then("viewInvoıce başarılı ise verileri txt dosyaya yazılır")
    public void viewınvoıceBaşarılıIseVerileriTxtDosyayaYazılır() throws IOException {

        JsonPath json=response.jsonPath();
        String body1=json.getString("InvoiceLink");

        boolean isTrue=response.statusCode()==200;
        assertTrue("viewİnvoice başarısız " +
                "ve body respose.txt dosyasına yazdırılamadı",isTrue);

        if(isTrue){

            String  writeResponse= response.asPrettyString();

            FileWriter fileWriter=new FileWriter(txtPath);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            bufferedWriter.write(writeResponse);
            bufferedWriter.flush();
            bufferedWriter.close();

            System.out.println("viewİnvoice BAŞARILI " +
                    "ve response body respose.txt dosyasına yazdırıldı");

        }else {
            System.out.println("Request başarısız ve body yazdırılamadı");
        }



    }

    @When("kullanıcı headera  {string} ekler ve sendInvoice endpointi ile post request yapar")
    public void kullanıcıHeaderaEklerVeSendInvoiceEndpointiIlePostRequestYapar(String arg0) {

      spec.pathParam("first",arg0);

        response = given(spec).when().post("/{first}");

        JsonPath json=response.jsonPath();
        String actualToken=json.getString("token");



        spec.header(arg0,actualToken);

                String barCode=ConfigReader.getProperty("barcode");

                Map<String,String> payLoad=new HashMap<>();
                payLoad.put(arg0,barCode);

                response=given(spec).when().body(payLoad).post("/{first}");
    }

    @And("sendInvoice çağrısı başarılı olduğu doğrulanır")
    public void sendınvoiceÇağrısıBaşarılıOlduğuDoğrulanır() {

        assertTrue("sendInvoice status code 200'den farklı",
                response.statusCode()==200);
    }

    @Then("sendInvoıce başarılı ise verileri txt dosyaya yazılır")
    public void sendınvoıceBaşarılıIseVerileriTxtDosyayaYazılır() throws IOException {

        boolean isTrue=response.statusCode()==200;
        assertTrue("viewİnvoice başarısız " +
                "ve body respose.txt dosyasına yazdırılamadı",isTrue);

        if(isTrue){


            System.out.println("sendInvoice BAŞARILI " +
                    "ancak response body  boş");

        }else {
            System.out.println("Request başarısız ve body yazdırılamadı");
        }

    }

}
