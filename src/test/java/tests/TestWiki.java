package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;


public class TestWiki extends CoreTestCase {

    @Test
    public void searchExistentArticle() {
        StartPage startPage = new StartPage(this.driver);
        SearchPage searchPage = new SearchPage(this.driver);
        startPage.initSearch();
        searchPage.findByText("Appium");
        searchPage.selectByText("Automation for Apps");
    }

    @Test
    public void searchNonExistentArticle() {
        StartPage startPage = new StartPage(this.driver);
        SearchPage searchPage = new SearchPage(this.driver);
        startPage.initSearch();
        searchPage.findByText("jhfgJHFGDS");
        searchPage.selectByText("No results found");
    }

    @ParameterizedTest(name = "{index} - Set {0} language testing")
    @ValueSource(strings = {"Русский", "Deutsch", "Esperanto"})
    public  void setLanguage(String language) {
        StartPage startPage = new StartPage(this.driver);
        SettingsPage settingsPage = new SettingsPage(this.driver);
        startPage.menuButtonClick();
        startPage.settingsExplore();
        settingsPage.setLanguage(language);
        Assertions.assertEquals(language, settingsPage.getPreferenceLanguage());
    }

    @Test
    public void loginWithInvalidData() {
        String login = "jhjhfew";
        String password = "hgfkhldwjlkj";
        StartPage startPage = new StartPage(this.driver);
        LoginPage loginPage = new LoginPage(this.driver);
        startPage.menuButtonClick();
        startPage.loginExplore();
        loginPage.loginInput(login);
        loginPage.passwordInput(password);
        loginPage.loginButtonClick();
        Assertions.assertEquals(
                "Incorrect username or password entered.\nPlease try again.",
                loginPage.getErrorMessage()
        );
    }

    @Test
    public void loginWithValidData() {
        String login = "QA-SF-2022";
        String password = "W1kiP@ssw0rd";
        StartPage startPage = new StartPage(this.driver);
        LoginPage loginPage = new LoginPage(this.driver);
        startPage.menuButtonClick();
        startPage.loginExplore();
        loginPage.loginInput(login);
        loginPage.passwordInput(password);
        loginPage.loginButtonClick();
        startPage.menuButtonClick();
        Assertions.assertEquals(login, startPage.getAccountName());
    }

    @Test
    public void searchInArticle() {
        StartPage startPage = new StartPage(this.driver);
        SearchPage searchPage = new SearchPage(this.driver);
        ArticlePage articlePage = new ArticlePage(this.driver);
        startPage.initSearch();
        searchPage.findByText("Java");
        searchPage.selectByText("Object-oriented programming language");
        articlePage.searchIconClick();
        articlePage.searchInArticleInput("syntax");
        Assertions.assertTrue(articlePage.findMatches() > 0, "Matches not found");
    }

    @Test
    public void saveList() {
        StartPage startPage = new StartPage(this.driver);
        SearchPage searchPage = new SearchPage(this.driver);
        ArticlePage articlePage = new ArticlePage(this.driver);
        ListsPage listsPage = new ListsPage(this.driver);
        startPage.initSearch();
        searchPage.findByText("Java");
        searchPage.selectByText("Object-oriented programming language");
        articlePage.listsIconClick();
        articlePage.gotItButtonClick();
        articlePage.listNameInput("My list");
        articlePage.viewListButtonClick();
        listsPage.findArticle("Object-oriented programming language");
    }

    @Test
    public void renameList() {
        StartPage startPage = new StartPage(this.driver);
        SearchPage searchPage = new SearchPage(this.driver);
        ArticlePage articlePage = new ArticlePage(this.driver);
        ListsPage listsPage = new ListsPage(this.driver);
        startPage.initSearch();
        searchPage.findByText("Appium");
        searchPage.selectByText("Automation for Apps");
        articlePage.listsIconClick();
        articlePage.gotItButtonClick();
        articlePage.listNameInput("My list");
        articlePage.closePage();
        startPage.myListsIconClick();
        listsPage.menuOverflowClick();
        listsPage.renameList("Appium");
        Assertions.assertEquals("Appium", listsPage.getListTitle());
    }

    @Test
    public void deleteList() {
        StartPage startPage = new StartPage(this.driver);
        SearchPage searchPage = new SearchPage(this.driver);
        ArticlePage articlePage = new ArticlePage(this.driver);
        ListsPage listsPage = new ListsPage(this.driver);
        startPage.initSearch();
        searchPage.findByText("Appium");
        searchPage.selectByText("Automation for Apps");
        articlePage.listsIconClick();
        articlePage.gotItButtonClick();
        articlePage.listNameInput("My list");
        articlePage.closePage();
        startPage.myListsIconClick();
        listsPage.menuOverflowClick();
        listsPage.deleteList();
        Assertions.assertEquals("No reading lists yet", listsPage.getEmptyTitle());
    }

    @Test
    public void saveHistory() throws InterruptedException {
        StartPage startPage = new StartPage(this.driver);
        ArticlePage articlePage = new ArticlePage(this.driver);
        HistoryPage historyPage = new HistoryPage(this.driver);
        ArrayList<String> articleList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            startPage.randomArticle();
            articleList.add(articlePage.getArticleTitle());
            articlePage.closePage();
        }
        startPage.historyExplore();
        ArrayList<String> historyList = historyPage.getHistoryList();
        Assertions.assertEquals(articleList.size(), historyList.size());
        for (int j = articleList.size(); j > 0; j--) {
            Assertions.assertEquals(articleList.get(j - 1), historyList.get(articleList.size() - j));
        }
    }

}
