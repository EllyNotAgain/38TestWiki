package lib.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SettingsPage  extends MainPage {

    final static String LANGUAGE = "xpath://*[./*[contains(@text,'Wikipedia language')]]";
    final static String SEARCH_LANGUAGE_INPUT = "id:org.wikipedia:id/preference_languages_filter";
    final static String PREFERENCE_LANGUAGE = "id:org.wikipedia:id/preference_languages_list";
    final static String RESULT_LANGUAGE = "xpath://*[contains(@text,'Wikipedia language')]/following-sibling::*";

    public SettingsPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void setLanguage(String language) {
        this.waitForElementAndClick(
                LANGUAGE,
                "Language item not found",
                5
        );
        WebElement searchLanguage = this.waitForElementPresent(
                SEARCH_LANGUAGE_INPUT,
                "Search language input not found"
        );
        searchLanguage.sendKeys(language);
        this.waitForElementAndClick(
                PREFERENCE_LANGUAGE,
                "Preference language item not found",
                5
        );
    }

    public String getPreferenceLanguage() {
        return this.waitForElementPresent(
                RESULT_LANGUAGE,
                "Configured language not found"
        ).getText();
    }

}
