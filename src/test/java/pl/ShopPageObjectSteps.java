package pl;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class ShopPageObjectSteps {
    private WebDriver driver;

    @Given("I'm on the shop authentication page")
    public void imOnTheShopAuthPage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://mystore-testlab.coderslab.pl/index.php?");
        driver.findElement(By.className("user-info")).click();
    }

    public ShopPageObjectSteps(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ShopPageObjectSteps() {
    }


    @When("I login using {string} and {string}")
    public void iLoginUsingAnd(String login, String passwd) {
        ShopAuthPage authPage = new ShopAuthPage(driver);
        authPage.loginAs(login, passwd);
    }

    @And("I go to my addresses page")
    public void iGoToMyAddressPage() {
        ShopMyAccountPage myAccountPage = new ShopMyAccountPage(driver);
        myAccountPage.goToMyAddressPage();
        ShopCreatingNewAddressPage myAddressesPage = new ShopCreatingNewAddressPage(driver);
    }

    @And("I attempt to add a new address")
    public void clickingAddNewAddress() {
        driver.findElement(By.cssSelector("#content > div.addresses-footer > a > span")).click();
    }

    @And("I enter new address {word}, {string}, {string}, {word}, {word}")
    public void iEnterNewAddress(String alias, String address, String city, String postCode, String phone) {
        ShopCreatingNewAddressPage x = new ShopCreatingNewAddressPage(driver);
        x.enterNewAddress(alias, address, city, postCode, phone);
    }

    @Then("I can see a new address")
    public void iCanSeeNewAddress () {
        ShopMyAddressesPage myAddress = new ShopMyAddressesPage(driver);
        Assert.assertNotNull(myAddress.alert);
        Assert.assertTrue(myAddress.isAlertVisible());
    }

    @Then ("I verify created address has {word}, {string}, {string}, {word}, {word}")
    public void verifyAddress(String alias, String address, String city, String postCode, String phone) {
        ShopMyAddressesPage myAddress = new ShopMyAddressesPage(driver);
        String[] l = myAddress.address.findElement(By.tagName("address")).getText().split("\n");
        String aliasFromPage = myAddress.address.findElement(By.tagName("h4")).getText();
        Assert.assertEquals(aliasFromPage, alias);
        Assert.assertEquals(l[1], address);
        Assert.assertEquals(l[2], city);
        Assert.assertEquals(l[3], postCode);
        Assert.assertEquals(l[5], phone);
    }

    @And("I remove the address")
    public void deleteAddress() {
        //*[@id="address-4321"]/div[2]/a[2]
        /*<a href="//prod-kurs.coderslab.pl/index.php?controller=address&amp;id_address=4407&amp;delete=1&amp;token=e977f6f07a60b966cae3a93549efbc85" data-link-action="delete-address">
          <i class="material-icons"></i>
          <span>Delete</span>
        </a>*/
        List<WebElement> test = driver.findElements(By.xpath("//*[@data-link-action='delete-address']"));
        int length = test.size();
        test.get(length - 1).click();
    }

    @And ("I can see there is no addresses")
    public void verifyAddressIsDeleted () {
        ShopMyAddressesPage myAddresses = new ShopMyAddressesPage(driver);
        Assert.assertNotNull(myAddresses.alert);
        Assert.assertTrue(myAddresses.isAlertVisible());
        String expectedMessage = "Address successfully deleted!";
        Assert.assertEquals(expectedMessage, myAddresses.alert.getText());
    }

    @And("I close the browser")
    public void closeBrowser () {
        driver.quit();
    }
}

