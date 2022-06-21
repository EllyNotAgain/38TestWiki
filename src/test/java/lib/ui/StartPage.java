package lib.ui;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class StartPage extends MainPage {

    final static String INIT_SEARCH = "id:org.wikipedia:id/search_container";
    final static String MENU_BUTTON = "id:org.wikipedia:id/menu_overflow_button";
    final static String MENU_SETTINGS = "id:org.wikipedia:id/explore_overflow_settings";
    final static String MENU_LOGIN = "id:org.wikipedia:id/explore_overflow_account_container";
    final static String ACCOUNT_NAME = "id:org.wikipedia:id/explore_overflow_account_name";
    final static String LISTS_ICON = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
    final static String RANDOMIZER = "Randomizer";
    final static String HISTORY_ICON = "xpath://android.widget.FrameLayout[@content-desc='History']";

    public StartPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void initSearch() {
        this.waitForElementAndClick(
                INIT_SEARCH,
                "Init search input not found",
                5
        );
    }

    public void menuButtonClick() {
        this.waitForElementAndClick(
                MENU_BUTTON,
                "Menu button not found",
                5
        );
    }

    public void settingsExplore() {
        this.waitForElementAndClick(
                MENU_SETTINGS,
                "Menu item 'Settings' not found",
                5
        );
    }

    public void loginExplore() {
        this.waitForElementAndClick(
                MENU_LOGIN,
                "Menu item 'Log in' not found",
                5
        );
    }

    public String getAccountName() {
        WebElement accountName = this.waitForElementPresent(
                ACCOUNT_NAME,
                "Account name not found",
                5
        );
        return accountName.getText();
    }

    public void myListsIconClick() {
        this.waitForElementAndClick(
                LISTS_ICON,
                "'My lists' icon not found",
                5
        );
    }

    public void randomArticle() {
        driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"" + RANDOMIZER + "\"))")).click();
    }

    public void historyExplore() {
        this.waitForElementAndClick(
                HISTORY_ICON,
                "History icon in the feed",
                5
        );
    }

}
