package frontend.steps;

import frontend.base.baseUtil;
import frontend.helperLibs.methods;
import frontend.pages.coinspage;
import frontend.pages.homepage;
import frontend.pages.watchlist;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class frontendTasks extends baseUtil {

    private baseUtil base;
    public frontendTasks(baseUtil base)
    {
        this.base = base;
    }


    @Given("User navigates to the homepage")
    public void User_navigates_to_the_homepage()
    {
        try{
            stepName = "User Navigates To The homepage";
            gherkinWord = "Given";
            base.Driver.get(methods.getUrl());
            logger.info("Navigating to the home page: " + base.Driver.getCurrentUrl());
            frontend.pages.homepage hp = new homepage(base.Driver);
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
            stepName = "Verify 100 entries are displayed";
            gherkinWord = "Then";
            frontend.pages.homepage hp = new homepage(base.Driver);
            logger.info("Verifying number of displayed cryptocurrencies" );
            Assert.assertEquals(count,hp.findRows());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @And("Add random 5-10 currencies to watchlist")
    public void selectRandomRows()
    {
        frontend.pages.homepage hp = new homepage(base.Driver);
        logger.info("Marking cryptocurrencies favourite randomly");
        hp.clickFavs();
    }

    @Then("Verify marked currencies are appearing in watchlist")
    public void verify_marked_currencies_are_appearing_in_watchlist() {
        frontend.pages.homepage hp = new homepage(base.Driver);
        logger.info("Opening page in new tab");
        hp.openWatchlistInNewTab();
        frontend.pages.watchlist wp = new watchlist(base.Driver);
        logger.info("Verifying displayed watchlist items");
        wp.verifyFavs();
    }

    @And("User hovers over cryptocurrencies")
    public void user_hovers_over_crypto_tab()
    {
        logger.info("hovering over cryptocurrencies tab");
        frontend.pages.homepage hp = new homepage(base.Driver);
        hp.hoverCryptoTab();
    }

    @And("User clicks Coins button")
    public void user_clicks_on_coins_btn()
    {
        logger.info("clicking on coins option.");
        frontend.pages.homepage hp = new homepage(base.Driver);
        hp.clickCoins();
    }

    @Then("Save the table")
    public void saveTheTable()
    {
        logger.info("Saving the table entries");
        frontend.pages.coinspage cp = new coinspage(base.Driver);
        cp.getTableData();
    }

    @And("Apply the mineable filter")
    public void applyFilter()
    {
        logger.info("Applying mineable filter to displayed currencies");
        frontend.pages.coinspage cp = new coinspage(base.Driver);
        cp.applyFilter("mineable");
    }

    @Then("Compare some data")
    public void dataVerification()
    {
        logger.info("Comparing names of filtered data with old data");
        frontend.pages.coinspage cp = new coinspage(base.Driver);
        cp.filterVerification();
    }

}
