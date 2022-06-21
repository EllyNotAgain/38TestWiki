package lib.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ListsPage extends MainPage {

    final static String ARTICLES = "xpath://*[contains(@text,'{TEXT}')]";
    final static String MENU_OVERFLOW_ITEM = "id:org.wikipedia:id/item_overflow_menu";
    final static String RENAME_LIST = "xpath://*[./*[@text='Rename']]";
    final static String LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
    final static String OK_BUTTON = "id:android:id/button1";
    final static String LIST_TITLE = "id:org.wikipedia:id/item_title";
    final static String DELETE_LIST = "xpath://*[./*[@text='Delete list']]";
    final static String EMPTY_TITLE = "id:org.wikipedia:id/empty_title";

    public ListsPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void findArticle(String articleName) {
        this.waitForElementPresent(
                ARTICLES.replace("{TEXT}", articleName),
                "Article " + articleName + " not found",
                5
        );
    }

    public void menuOverflowClick() {
        this.waitForElementAndClick(
                MENU_OVERFLOW_ITEM,
                "Menu overflow not found",
                5
        );
    }

    public void renameList(String newName) {
        this.waitForElementAndClick(
                RENAME_LIST,
                "Menu item 'Rename' not found",
                5
        );
        listNameInput(newName);
        this.waitForElementAndClick(
                OK_BUTTON,
                "'OK' button not found",
                5
        );
    }

    private void listNameInput(String name) {
        WebElement nameInput = this.waitForElementPresent(
                LIST_NAME_INPUT,
                "List name input not found",
                5
        );
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public String getListTitle() {
        WebElement listTitle = this.waitForElementPresent(
                LIST_TITLE,
                "List title input not found",
                5
        );
        return listTitle.getText();
    }

    public void deleteList() {
        this.waitForElementAndClick(
                DELETE_LIST,
                "Menu item 'Delete list' not found",
                5
        );
    }

    public String getEmptyTitle() {
        return this.waitForElementPresent(
                EMPTY_TITLE,
                "Empty title not found",
                5
        ).getText();
    }
}
