package frontend.pages;

import frontend.helperlibs.Methods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class WatchlistPage {

    WebDriver driver;
    int wait_time = 60;
    public WatchlistPage(WebDriver driver){

        this.driver = driver;
    }

    By table_data = By.xpath("//table[contains(@class, 'cmc-table')]/*//p");



public void verifyFavs()
{
    WebDriverWait wait = new WebDriverWait(driver, wait_time);
    List<List<String>> fav_table_data = new ArrayList<>();
    wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(("(//tbody//child::tr)[" + HomePage.markedFavs.size() +"]")))));
    fav_table_data = Methods.getTableData(driver, table_data);
    //Compare with markedFavs
    int count = 0;
    do {
        Assert.assertEquals(fav_table_data.get(count+1).get(1), HomePage.markedFavs.get(count));
        count++;
    }while (count != HomePage.markedFavs.size());
}
}
