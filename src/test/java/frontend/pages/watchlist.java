package frontend.pages;

import frontend.helperLibs.methods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class watchlist {

    WebDriver driver;
    int waitTime = 60;
    public watchlist(WebDriver driver){

        this.driver = driver;
    }

    By tableData = By.xpath("//table[contains(@class, 'cmc-table')]/*//p");



public void verifyFavs()
{
    WebDriverWait wait = new WebDriverWait(driver,waitTime);
    List<List<String>> favTableData = new ArrayList<>();
    wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(("(//tbody//child::tr)[" + homepage.markedFavs.size() +"]")))));
    favTableData = methods.getTableData(driver,tableData);
    //Compare with markedFavs
    int count = 0;
    do {
        Assert.assertEquals(favTableData.get(count+1).get(1),homepage.markedFavs.get(count));
        count++;
    }while (count != homepage.markedFavs.size());
}
}
