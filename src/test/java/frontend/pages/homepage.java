package frontend.pages;

import frontend.helperLibs.methods;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class homepage {

    WebDriver driver;
    int waitTime = 60;
    By tableBody = By.xpath("//*[contains(@class, 'cmc-table-homepage___2_guh')]/tbody");
    By tableClass = By.xpath("//table[contains(@class, 'cmc-table-homepage___2_guh')]");
    By watchlistBtn = By.xpath("//a[@href='/watchlist/' and @class='cmc-link']");
    By favIcon = By.xpath("//table/*//span[@class= 'icon-Star']");
    By cookieClose = By.className("cmc-cookie-policy-banner__close");
    By cryptoTab = By.xpath("//button[text()='Cryptocurrencies' and contains(@class, '1a0iugo')]");
    By coin = By.xpath("//h6[text()='Coins']");

    public homepage(WebDriver driver){

        this.driver = driver;
    }

    public static List<String> markedFavs = new ArrayList<String>();

    //Find number of rows in Cryptocurrencies
    public int findRows(){
        WebDriverWait wait = new WebDriverWait(driver,waitTime);
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableClass));
        return Integer.parseInt(driver.findElement(tableBody).getAttribute("childElementCount"))-1;
    }

    public void clickFavs() {
        WebDriverWait wait = new WebDriverWait(driver,waitTime);

        List<WebElement> favs = driver.findElements(favIcon);

        Collections.shuffle(favs); //Shuffle
        int randomNumber = methods.getRandomNumber(5,10);

        for(int x=0;x<randomNumber;x++)
        {
            //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", favs.get(x));
            Actions actions = new Actions(driver);
            actions.moveToElement(favs.get(x));
            actions.perform();
            wait.until(ExpectedConditions.visibilityOfElementLocated(favIcon));
            favs.get(x).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int i=0;i<randomNumber;i++)
        {
            String favXPath1 = "(//table[contains(@class, 'cmc-table')]//child::span[(@class= 'icon-Star-Filled')])[";
            String favXPath2 = "]//ancestor::tr/*//p[contains(@class,'iJjGCS')]";
            String finalPath = favXPath1 + (i+1) + favXPath2;
            markedFavs.add(i,driver.findElement(By.xpath(finalPath)).getText());
        }
    }

    public void openWatchlistInNewTab()
    {
        WebDriverWait wait = new WebDriverWait(driver,waitTime);
        String urlWatchlist = driver.findElement(watchlistBtn).getAttribute("href");
        WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
        newTab.get(urlWatchlist);

    }

    public void hoverCryptoTab()
    {
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(cryptoTab)).perform();

    }

    public void clickCoins()
    {
        driver.findElement(coin).click();
    }

    public void closeCookieBanner()
    {
        WebDriverWait wait = new WebDriverWait(driver,waitTime);
        driver.findElement(cookieClose).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieClose));
    }

}
