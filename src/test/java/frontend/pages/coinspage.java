package frontend.pages;

import frontend.helperLibs.methods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class coinspage {

    WebDriver driver;
    int waitTime = 60;
    By tableDataPath = By.xpath("//table[contains(@class, 'cmc-table')]/*//p");
    By btnMineFilter = By.xpath("//button[text()='Mineable']");
    By tableLoading = By.id("nprogress");
    By btnMinFilterSelected = By.xpath("//button[text()='Mineable' and contains(@class, 'cmc-filter-selected')]");


    public static List<List<String>> userTable = new ArrayList<>();
    public coinspage(WebDriver driver){

        this.driver = driver;
    }

    public void getTableData()
    {
        WebDriverWait wait = new WebDriverWait(driver,waitTime);
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableDataPath));
        userTable = methods.getTableData(driver, tableDataPath);
    }

    public void applyFilter(String filter)
    {
        WebDriverWait wait = new WebDriverWait(driver,waitTime);
        wait.until(ExpectedConditions.visibilityOfElementLocated(btnMineFilter));

        if(filter.equals("mineable"))
            driver.findElement(btnMineFilter).click();
    }

    public void filterVerification()
    {
        WebDriverWait wait = new WebDriverWait(driver,waitTime);
        wait.until(ExpectedConditions.visibilityOfElementLocated(btnMinFilterSelected));
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(tableLoading)));

        List<List<String>> filteredTable;
        try
        {
            filteredTable = methods.getTableData(driver, tableDataPath);
        }
        catch (StaleElementReferenceException e)
        {
            filteredTable = methods.getTableData(driver, tableDataPath);
        }



        Assert.assertEquals(filteredTable.get(1).get(1), userTable.get(1).get(1));// Verifying Bitcoin presence
        Assert.assertEquals(filteredTable.get(2).get(1), userTable.get(2).get(1));//Verifying Ethereuum presence
        Assert.assertEquals(filteredTable.get(3).get(1), userTable.get(5).get(1));//Verifying Litecount presence

    }

}
