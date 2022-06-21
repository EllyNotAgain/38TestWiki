package lib.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.regex.Pattern;

public class ArticlePage  extends MainPage {

    final static String SEARCH_IN_ARTICLE_ICON = "xpath://*[./android.widget.ImageView[@content-desc='Find in page']]";
    final static String SEARCH_IN_ARTICLE_INPUT = "id:org.wikipedia:id/search_src_text";
    final static String FIND_MATCHES = "id:org.wikipedia:id/find_in_page_match";
    final static String LISTS_ICON = "xpath://*[./android.widget.ImageView[@content-desc='Add this article to a reading list']]";
    final static String GOT_IT_BUTTON = "id:org.wikipedia:id/onboarding_button";
    final static String LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
    final static String OK_BUTTON = "id:android:id/button1";
    final static String VIEW_LIST_BUTTON = "id:org.wikipedia:id/snackbar_action";
    final static String CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    final static String ARTICLE_TITLE = "id:org.wikipedia:id/view_page_title_text";

    public ArticlePage(RemoteWebDriver driver) {
        super(driver);
    }

    public void searchIconClick() {
        this.waitForElementAndClick(
                SEARCH_IN_ARTICLE_ICON,
                "Search icon not found",
                5
        );
    }

    public void searchInArticleInput(String searchString) {
        WebElement searchInput = this.waitForElementPresent(
                SEARCH_IN_ARTICLE_INPUT,
                "Search input not found",
                5
        );
        searchInput.sendKeys(searchString);
    }

    public int findMatches() {
        WebElement matches = this.waitForElementPresent(
                FIND_MATCHES,
                "Match item not found",
                5
        );
        String[] matchesSplit = matches.getText().split(Pattern.quote("/"), 2);
        if (matchesSplit.length != 2) throw new IllegalArgumentException("Wrong match count");
        String matchCount = matchesSplit[1];
        return Integer.parseInt(matchCount);
    }

    public void listsIconClick() {
        this.waitForElementAndClick(
                LISTS_ICON,
                "Lists icon not found",
                5
        );
    }

    public void gotItButtonClick() {
       this.waitForElementAndClick(
                GOT_IT_BUTTON,
                "'Got it' button not found",
                5
        );
    }

    public void listNameInput(String listName) {
        WebElement listInput = this.waitForElementPresent(
                LIST_NAME_INPUT,
                "Name of list input not found",
                5
        );
        listInput.clear();
        listInput.sendKeys(listName);
        okButtonClick();
    }

    private void okButtonClick() {
        this.waitForElementAndClick(
                OK_BUTTON,
                "'OK' button not found",
                5
        );
    }

    public void viewListButtonClick() {
        this.waitForElementAndClick(
                VIEW_LIST_BUTTON,
                "'View list' button not found",
                5
        );
    }

    public void closePage() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "'Close page' button not found",
                5
        );
    }

    public String getArticleTitle() {
        WebElement articleTitle = this.waitForElementPresent(
                ARTICLE_TITLE,
                "Article title not found",
                5
        );
        return articleTitle.getText();
    }


}
