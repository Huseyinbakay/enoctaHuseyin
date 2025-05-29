package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class AmazonHomePage {

    public AmazonHomePage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

 @FindBy(id = "nav-link-accountList-nav-line-1")
 public WebElement loginButton;

   @FindBy(id = "sp-cc-accept")
   public WebElement cookie;
    @FindBy(id = "ap_email_login")
    public WebElement email;

    @FindBy(id = "ap_password")
    public WebElement password;

    @FindBy(id = "twotabsearchtextbox")
    public WebElement searchbox;


    @FindBy(id = "p_36/range-slider_slider-item_lower-bound-slider")
    public WebElement minPrice;

    @FindBy(id = "p_36/range-slider_slider-item_upper-bound-slider")
    public WebElement maxPrice;
    @FindBy(xpath = "//input[@class='a-button-input']")
    public WebElement priceFilterGo;

    @FindBy(xpath = "//span[text()='128 GB']")
    public WebElement memoryFilter;

    @FindBy(xpath = "//a[contains(text(),'Seçenekleri gör')]")
    public List<WebElement> phoneList;

    @FindBy(id = "productTitle")
    public WebElement choosePhoneName;


    @FindBy(xpath = "//span[text()='Önceki']")
    public WebElement pageNumber;
    @FindBy(xpath = " //a[text()=' Satın Alma Seçeneklerini Gör ']")
    public WebElement optionsBuy;

    @FindBy(xpath = " //div[@id='aod-offer']")
    public List<WebElement>  storeList;







}
