package pl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.text.html.CSS;
import java.io.ObjectInputStream;

public class ShopAuthPage {
    private final WebDriver driver;

    public ShopAuthPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "_mobile_user_info")
    private WebElement accountLoginBtn;

    @FindBy(id = "field-email")
    public WebElement loginEmailInput;

    @FindBy(name = "password")
    private WebElement loginPasswordInput;

    @FindBy(id = "submit-login")
    private WebElement loginButton;

    public ShopAuthPage(WebDriver driver, WebElement accountLoginBtn, WebElement loginEmailInput, WebElement loginPasswordInput, WebElement loginButton) {
        this.driver = driver;
        this.accountLoginBtn = accountLoginBtn;
        this.loginEmailInput = loginEmailInput;
        this.loginPasswordInput = loginPasswordInput;
        this.loginButton = loginButton;
    }

    public void loginAs(String login, String passwd) {
        loginEmailInput.clear();
        loginEmailInput.sendKeys(login);

        loginPasswordInput.clear();
        loginPasswordInput.sendKeys(passwd);

        loginButton.click();
    }
}
