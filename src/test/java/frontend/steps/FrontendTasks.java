package frontend.steps;

import frontend.base.BaseUtil;
import frontend.helperlibs.Methods;
import frontend.pages.CoinsPage;
import frontend.pages.HomePage;
import frontend.pages.WatchlistPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class FrontendTasks extends BaseUtil {

    private BaseUtil base;
    public FrontendTasks(BaseUtil base)
    {
        this.base = base;
    }


    @Given("User navigates to the HomePage")
    public void userNavigatesToTheHomepage()
    {
        try{
            step_name = "User Navigates To The HomePage";
            gherkin_word = "Given";
            base.driver.get(Methods.getUrl());
            logger.info("Navigating to the home page: " + base.driver.getCurrentUrl());
            HomePage hp = new HomePage(base.driver);
            hp.closeCookieBanner();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    @Then("Verify {int} entries are displayed")
    public void verifyEntries(int count) {

        try
        {
            step_name = "Verify 100 entries are displayed";
            gherkin_word = "Then";
            HomePage hp = new HomePage(base.driver);
            logger.info("Verifying number of displayed cryptocurrencies" );
            Assert.assertEquals(count,hp.findRows());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @And("Add random 5-10 currencies to WatchlistPage")
    public void selectRandomRows()
    {
        HomePage hp = new HomePage(base.driver);
        logger.info("Marking cryptocurrencies favourite randomly");
        hp.clickFavs();
    }

    @Then("Verify marked currencies are appearing in WatchlistPage")
    public void verifyMarkedCurrenciesAreAppearingInWatchlist() {
        HomePage hp = new HomePage(base.driver);
        logger.info("Opening page in new tab");
        hp.openWatchlistInNewTab();
        WatchlistPage wp = new WatchlistPage(base.driver);
        logger.info("Verifying displayed WatchlistPage items");
        wp.verifyFavs();
    }

    @And("User hovers over cryptocurrencies")
    public void userHoversOverCryptoTab()
    {
        logger.info("hovering over cryptocurrencies tab");
        HomePage hp = new HomePage(base.driver);
        hp.hoverCryptoTab();
    }

    @And("User clicks Coins button")
    public void userClicksOnCoinsBtn()
    {
        logger.info("clicking on coins option.");
        HomePage hp = new HomePage(base.driver);
        hp.clickCoins();
    }

    @Then("Save the table")
    public void saveTheTable()
    {
        logger.info("Saving the table entries");
        CoinsPage cp = new CoinsPage(base.driver);
        cp.getTableData();
    }

    @And("Apply the mineable filter")
    public void applyMineableFilter()
    {
        logger.info("Applying mineable filter to displayed currencies");
        CoinsPage cp = new CoinsPage(base.driver);
        cp.applyFilter("mineable");
    }

    @Then("Compare some data")
    public void dataVerification()
    {
        logger.info("Comparing names of filtered data with old data");
        CoinsPage cp = new CoinsPage(base.driver);
        cp.filterVerification();
    }

}
