package pl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShopMyAddressesPage {
    private final WebDriver driver;

    public ShopMyAddressesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (className = "alert-success")
    public WebElement alert;

    @FindBy (className = "address")
    public WebElement address;

    public boolean isAlertVisible() {
        return this.alert != null;
    }


}
