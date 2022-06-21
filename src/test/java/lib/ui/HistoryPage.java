package lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;

public class HistoryPage extends MainPage {

    final static String ARTICLE_TITLE = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";

    public HistoryPage(RemoteWebDriver driver) {
        super(driver);
    }

    public ArrayList<String> getHistoryList() throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement> historyList = driver.findElements(By.xpath(ARTICLE_TITLE));
        ArrayList<String> articles = new ArrayList<>();
        for (WebElement article: historyList) {
            articles.add(article.getText());
        }
        return articles;
    }

}
