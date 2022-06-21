package lib.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LoginPage extends MainPage {

    final static String LOGIN = "id:org.wikipedia:id/login_username_text";
    final static String PASSWORD = "xpath://*[@text='Password']//*[@class='android.widget.EditText']";
    final static String LOGIN_BUTTON = "id:org.wikipedia:id/login_button";
    final static String ERROR_MESSAGE = "id:org.wikipedia:id/snackbar_text";

    public LoginPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void loginInput(String login) {
        WebElement logInput = this.waitForElementPresent(
                LOGIN,
                "Login input not found",
                5
        );
        logInput.sendKeys(login);
    }

    public void passwordInput(String pass) {
        WebElement passInput = this.waitForElementPresent(
                PASSWORD,
                "Login input not found",
                5
        );
        passInput.sendKeys(pass);
    }

    public void loginButtonClick() {
        this.waitForElementAndClick(
                LOGIN_BUTTON,
                "Login button not found",
                5
        );
    }

    public String getErrorMessage() {
        WebElement errorMessage = this.waitForElementPresent(
                ERROR_MESSAGE,
                "Error message not appear",
                5
        );
        return errorMessage.getText();
    }




}
