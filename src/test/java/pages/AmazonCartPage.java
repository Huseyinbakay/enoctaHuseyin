package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class AmazonCartPage {
    public AmazonCartPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }




    @FindBy(xpath = "//a[contains (text(), '      Sepete Git')]")
    public WebElement cartButton;

    @FindBy(xpath = "//span[@class='a-truncate-cut']")
    public WebElement phoneNameCart;
    @FindBy(xpath = "//span[@class='a-icon a-icon-small-trash']")
    public WebElement removePhoneFromCart;
}
