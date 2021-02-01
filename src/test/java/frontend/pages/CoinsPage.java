package frontend.pages;

import frontend.helperlibs.Methods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CoinsPage {

    WebDriver driver;
    int wait_time = 60;
    By table_data_path = By.xpath("//table[contains(@class, 'cmc-table')]/*//p");
    By btn_mine_filter = By.xpath("//button[text()='Mineable']");
    By table_loading = By.id("nprogress");
    By btn_min_filter_selected = By.xpath("//button[text()='Mineable' and contains(@class, 'cmc-filter-selected')]");


    public static List<List<String>> user_table = new ArrayList<>();
    public CoinsPage(WebDriver driver){

        this.driver = driver;
    }

    public void getTableData()
    {
        WebDriverWait wait = new WebDriverWait(driver, wait_time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(table_data_path));
        user_table = Methods.getTableData(driver, table_data_path);
    }

    public void applyFilter(String filter)
    {
        WebDriverWait wait = new WebDriverWait(driver, wait_time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_mine_filter));

        if(filter.equals("mineable"))
            driver.findElement(btn_mine_filter).click();
    }

    public void filterVerification()
    {
        WebDriverWait wait = new WebDriverWait(driver, wait_time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_min_filter_selected));
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(table_loading)));

        List<List<String>> filteredTable;
        try
        {
            filteredTable = Methods.getTableData(driver, table_data_path);
        }
        catch (StaleElementReferenceException e)
        {
            filteredTable = Methods.getTableData(driver, table_data_path);
        }



        Assert.assertEquals(filteredTable.get(1).get(1), user_table.get(1).get(1));// Verifying Bitcoin presence
        Assert.assertEquals(filteredTable.get(2).get(1), user_table.get(2).get(1));//Verifying Ethereuum presence
        Assert.assertEquals(filteredTable.get(3).get(1), user_table.get(5).get(1));//Verifying Litecount presence

    }

}
