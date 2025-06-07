package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.AmazonCartPage;
import pages.AmazonHomePage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMetods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class UiStepDefinition {




    AmazonHomePage amazonHomePage=new AmazonHomePage();

    AmazonCartPage amazonCartPage=new AmazonCartPage();

    String actualPhoneName;
    String expectedPhoneName;
    Actions actions=new Actions(Driver.getDriver());

    JavascriptExecutor js= (JavascriptExecutor) Driver.getDriver();






    @Given("Kullanıcı e-ticaret sitesini ziyaret eder")
    public void kullanıcıETicaretSitesiniZiyaretEder() {

        Driver.getDriver().get(ConfigReader.getProperty("url"));
        ReusableMetods.explicitlyWait(amazonHomePage.loginButton);
        Driver.getDriver().navigate().refresh();//HandleCaptcha

    }

    @And("Kullanıcı gecerli kullanıcı adi ve sifre ile giris yapar")
    public void kullanıcıGecerliKullanıcıAdiVeSifreIleGirisYapar() {
        ReusableMetods.explicitlyWait(amazonHomePage.cookie);
        amazonHomePage.cookie.click();

        actions.moveToElement(amazonHomePage.loginButton).perform();
        amazonHomePage.loginButton.click();

        ReusableMetods.explicitlyWait(amazonHomePage.email);
        amazonHomePage.email.
                sendKeys(ConfigReader.getProperty("email"), Keys.ENTER);

        amazonHomePage.password.
                sendKeys(ConfigReader.getProperty("password"), Keys.ENTER);

    }

    @When("Kullanıcı arama kutusuna {string} yazarak arama yapar")
    public void kullanıcıAramaKutusunaYazarakAramaYapar(String str) {
        ReusableMetods.explicitlyWait(amazonHomePage.searchbox);

        amazonHomePage.searchbox.click();
        amazonHomePage.searchbox.sendKeys(str,Keys.ENTER);
    }

    @And("Arama sonuçlarında fiyat filtresi olarak {string} ile {string} TL aralığı seçilir")
    public void aramaSonuçlarındaFiyatFiltresiOlarakIleTLAralığıSeçilir(String arg0, String arg1) throws InterruptedException {

        ReusableMetods.explicitlyWait(amazonHomePage.priceFilterGo);
        actions.scrollToElement(amazonHomePage.priceFilterGo).perform();

        js.executeScript("arguments[0].value = arguments[1];",
                amazonHomePage.minPrice, "90.4");


        js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                amazonHomePage.minPrice);


        js.executeScript("arguments[0].value = arguments[1];",
                amazonHomePage.maxPrice, "101.4");

        js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                amazonHomePage.maxPrice);



        amazonHomePage.priceFilterGo.click();

        ReusableMetods.explicitlyWait(amazonHomePage.memoryFilter);


        try {
            amazonHomePage.memoryFilter.click();
            Thread.sleep(1000);
            amazonHomePage.memoryFilter.click();

        }catch (Exception e){

            Driver.getDriver().navigate().refresh();
            amazonHomePage.memoryFilter.click();
            Thread.sleep(1000);
            amazonHomePage.memoryFilter.click();


        }



    }

    @And("Filtrelenmis ürün listesinden en alttaki rastgele bir ürün seçilerek detay sayfasına gidilir")
    public void filtrelenmisÜrünListesindenEnAlttakiRastgeleBirÜrünSeçilerekDetaySayfasınaGidilir() {

        ReusableMetods.explicitlyWait(amazonHomePage.pageNumber);
        actions.scrollToElement(amazonHomePage.pageNumber).perform();

        int phoneCount=amazonHomePage.phoneList.size();

        Random random=new Random();
        int randomNumber=random.nextInt(4);
        System.out.println("randomNumber = " + randomNumber);

        WebElement choosePhone=Driver.getDriver().
                findElement(By.xpath("(//a[contains(text(),'Seçenekleri gör')])["+(phoneCount-randomNumber)+"]"));

        ReusableMetods.explicitlyWait(choosePhone);



        ReusableMetods.jsClick(choosePhone);
        expectedPhoneName=amazonHomePage.choosePhoneName.getText();
        amazonHomePage.optionsBuy.click();


    }

    @And("Ürün detayında en düşük puanlı satıcının ürünü sepete eklenir")
    public void ürünDetayındaEnDüşükPuanlıSatıcınınÜrünüSepeteEklenir() {


        List<Integer> storePoint=new ArrayList<>();//Mağaza puanları için list oluşturuldu

        for (WebElement element : amazonHomePage.storeList) {


            if (element.getText().contains("%")){
                String str = element.getText().split("%")[1];
              String pointStr= str.substring(0, 2);
                System.out.println("pointStr = " + pointStr);
                Integer point=Integer.valueOf(pointStr);
              storePoint.add(point);
            }

            else  {
                System.out.println();
            }
        }


        Collections.sort(storePoint);//Mağaza puanları sıralandı

        int lowPoint=storePoint.get(0);//En düşük puanlı seçldi
        System.out.println("lowPoint = " + lowPoint);
        String lowPointStr=String.valueOf(lowPoint);



        int i=1;
        for (WebElement element : amazonHomePage.storeList) {

            if (element.getText().contains(lowPointStr)){

                WebElement addToCart=Driver.getDriver().
                        findElement(By.xpath("(//input[@name='submit.addToCart'])["+i+"]"));

                addToCart.click();
                break; //StaleElementException Alındığı için Atıldı

            }

            else  {
                System.out.println();
            }
            i++;
        }

        }



    @Then("Ürünün sepete doğru bir şekilde eklendiği doğrulanır")
    public void ürününSepeteDoğruBirŞekildeEklendiğiDoğrulanır() {

        ReusableMetods.explicitlyWait(amazonCartPage.cartButton);
        amazonCartPage.cartButton.click();

     actualPhoneName=amazonCartPage.phoneNameCart.getText();

        Assert.assertEquals(expectedPhoneName,actualPhoneName);

        amazonCartPage.removePhoneFromCart.click();

    }


}
