package pl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ShopCreatingNewAddressPage {
    private final WebDriver driver;

    @FindBy(id = "field-alias")
    private WebElement aliasInput;

    @FindBy(id = "field-address1")
    private WebElement addressInput;

    @FindBy(id = "field-city")
    private WebElement cityInput;

    @FindBy(id = "field-postcode")
    private WebElement postCodeInput;

    @FindBy(id = "field-id_country")
    private WebElement countryDropdown;

    @FindBy(id = "field-phone")
    private WebElement phoneInput;

    @FindBy(css = "#content > div > div > form > footer > button")
    private WebElement saveBtn;

    public ShopCreatingNewAddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterNewAddress(String alias, String address, String city, String postcode, String phone) {
        aliasInput.clear();
        aliasInput.sendKeys(alias);

        addressInput.clear();
        addressInput.sendKeys(address);

        cityInput.clear();
        cityInput.sendKeys(city);

        postCodeInput.clear();
        postCodeInput.sendKeys(postcode);

        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText("United Kingdom");

        phoneInput.clear();
        phoneInput.sendKeys(phone);

        saveBtn.click();
    }
}

