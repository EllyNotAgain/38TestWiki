package lib.ui;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPage extends MainPage {

    final static String SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
    final static String RESULT_ELEMENT_BY_TEXT_TPL = "xpath://*[./*[contains(@text,'{TEXT}')]]";

    public SearchPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void findByText(String text) {
        WebElement searchInput = this.waitForElementPresent(
                SEARCH_INPUT,
                "Search input not found"
        );
        searchInput.sendKeys(text);
    }

    public void selectByText(String text) {
        this.waitForElementAndClick(
                getResultElementByText(text),
                "Result: '" + text + "' not found",
                5
        );
    }

    private static String getResultElementByText(String text) {
        return RESULT_ELEMENT_BY_TEXT_TPL.replace("{TEXT}", text);
    }

}
