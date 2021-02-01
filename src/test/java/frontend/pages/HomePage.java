package frontend.pages;

import frontend.helperlibs.Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePage {

    WebDriver driver;
    int waitTime = 60;
    By table_body = By.xpath("//*[contains(@class, 'cmc-table-homepage___2_guh')]/tbody");
    By table_class = By.xpath("//table[contains(@class, 'cmc-table-homepage___2_guh')]");
    By watch_list_btn = By.xpath("//a[@href='/WatchlistPage/' and @class='cmc-link']");
    By fav_icon = By.xpath("//table/*//span[@class= 'icon-Star']");
    By cookie_close = By.className("cmc-cookie-policy-banner__close");
    By crypto_tab = By.xpath("//button[text()='Cryptocurrencies' and contains(@class, '1a0iugo')]");
    By coin = By.xpath("//h6[text()='Coins']");

    public HomePage(WebDriver driver){

        this.driver = driver;
    }

    public static List<String> markedFavs = new ArrayList<String>();

    //Find number of rows in Cryptocurrencies
    public int findRows(){
        WebDriverWait wait = new WebDriverWait(driver,waitTime);
        wait.until(ExpectedConditions.visibilityOfElementLocated(table_class));
        return Integer.parseInt(driver.findElement(table_body).getAttribute("childElementCount"))-1;
    }

    public void clickFavs() {
        WebDriverWait wait = new WebDriverWait(driver,waitTime);

        List<WebElement> favs = driver.findElements(fav_icon);

        Collections.shuffle(favs); //Shuffle
        int randomNumber = Methods.getRandomNumber(5,10);

        for(int x=0;x<randomNumber;x++)
        {
            //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", favs.get(x));
            Actions actions = new Actions(driver);
            actions.moveToElement(favs.get(x));
            actions.perform();
            wait.until(ExpectedConditions.visibilityOfElementLocated(fav_icon));
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
        String urlWatchlist = driver.findElement(watch_list_btn).getAttribute("href");
        WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
        newTab.get(urlWatchlist);

    }

    public void hoverCryptoTab()
    {
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(crypto_tab)).perform();

    }

    public void clickCoins()
    {
        driver.findElement(coin).click();
    }

    public void closeCookieBanner()
    {
        WebDriverWait wait = new WebDriverWait(driver,waitTime);
        driver.findElement(cookie_close).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cookie_close));
    }

}
