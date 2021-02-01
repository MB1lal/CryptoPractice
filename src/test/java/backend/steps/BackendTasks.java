package backend.steps;

import backend.base.BaseUtil;
import backend.enums.API_PATHS;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

import static backend.methods.HelperMethods.getIds;
import static io.restassured.RestAssured.given;

public class BackendTasks extends BaseUtil {

    protected Response response;
    protected String id_btc, id_eth, id_usdt;
    protected int[] top_ids = new int[10];
    protected int[] mine_tag_ids = new int[10];
    protected String[] mine_tag_names = new String[10];


    @Given("User gets the response from cryptocurrency call")
    public void userGetsCryptoResponse()
    {
        System.err.close();
        System.setErr(System.out);

        response = given()
                .header("X-CMC_PRO_API_KEY", BaseUtil.api_key)
                .get(BaseUtil.baseurl + BaseUtil.crypto_curr_map)
                .then().assertThat().statusCode(200)
                .extract().response();

    }

    @Then("Save Ids of BTC,ETH and USDT")
    public void saveIds()
    {

        id_btc = "" + getIds("Bitcoin",response.jsonPath());
        id_eth = "" + getIds("Ethereum",response.jsonPath());
        id_usdt = "" + getIds("Tether", response.jsonPath());
    }

    @And("Convert the IDs to Bolivian Boliviano")
    public void convertIDsToBoliviano() {
        response = given()
                .header("X-CMC_PRO_API_KEY", BaseUtil.api_key)
                .param("amount", "1")
                .param("id", id_btc)
                .param("convert_id", "2832")
                .get( BaseUtil.baseurl + BaseUtil.crypto_conv)
                .then().assertThat().statusCode(200)
                .extract().response();

        logger.info("Converted value of 1 BTC into BOB = " + response.jsonPath().getString(API_PATHS.quotePrice_Path.getConfigPath()));
        response = given()
                .header("X-CMC_PRO_API_KEY", BaseUtil.api_key)
                .param("amount", "1")
                .param("id", id_eth)
                .param("convert_id", "2832")
                .get(BaseUtil.baseurl + BaseUtil.crypto_conv)
                .then().assertThat().statusCode(200)
                .extract().response();

        logger.info("Converted value of 1 ETH into BOB = " + response.jsonPath().getString(API_PATHS.quotePrice_Path.getConfigPath()));


        response = given()
                .header("X-CMC_PRO_API_KEY", BaseUtil.api_key)
                .param("amount", "1")
                .param("id", id_usdt)
                .param("convert_id", "2832")
                .get(BaseUtil.baseurl + BaseUtil.crypto_conv)
                .then().assertThat().statusCode(200)
                .extract().response();

        logger.info("Converted value of 1 USDT into BOB = " + response.jsonPath().getString(API_PATHS.quotePrice_Path.getConfigPath()));
    }

    @Given("User gets info response for Etherium ID {int}")
    public void getInfoResponseForETH(int id)
    {
        logger.info("Getting info response for ETH");
        response = given()
                .header("X-CMC_PRO_API_KEY", BaseUtil.api_key)
                .param("id",id)
                .get(BaseUtil.baseurl + BaseUtil.crypto_info)
                .then().assertThat().statusCode(200)
                .extract().response();
        logger.info("Info response for ETH received");
    }

    @Then("Verify logo URL is present")
    public void logoURLVerification()
    {
        logger.info("Verifying logo_url presence");
        String logo_url = "https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png";
        Assert.assertEquals(response.jsonPath().get(API_PATHS.logo_Path.getConfigPath()).toString(), logo_url);
        logger.info("logo_url is present in response");
    }

    @And("Verify technical_doc URI is present")
    public void techURIVerification()
    {
        logger.info("Verifying techURI presence");
        String tech_uri = "[https://github.com/ethereum/wiki/wiki/White-Paper]";
        Assert.assertEquals(response.jsonPath().get(API_PATHS.techDoc_Path.getConfigPath()).toString(), tech_uri);
        logger.info("techURI exists in response");
    }

    @And("Verify symbol of currency is ETH")
    public void currSymbolVerification()
    {
        logger.info("Verifying symbol presence");
        String symbol = "ETH";
        Assert.assertEquals(response.jsonPath().get(API_PATHS.symbol_Path.getConfigPath()).toString(),symbol);
        logger.info("ETH symbol is present in response");
    }

    @And("Verify date added field")
    public void dateFieldVerification()
    {
        logger.info("Verifying presence of date added field");
        String date = "2015-08-07T00:00:00.000Z";
        Assert.assertEquals(response.jsonPath().get(API_PATHS.date_Path.getConfigPath()).toString(),date);
        logger.info("date added field is present in response");
    }

    @And("Verify platform is null")
    public void platformVerification()
    {
        logger.info("Verifying platform's value");
        Assert.assertNull(response.jsonPath().get(API_PATHS.platform_Path.getConfigPath()));
        logger.info("platform's value is null in response as exepcted");
    }

    @And("Verify currency has mineable tag associated with it")
    public void mineableTagVerification()
    {
        logger.info("Verifying mineable tag presence");
        Assert.assertEquals(response.jsonPath().get(API_PATHS.mineableTag_Path.getConfigPath()).toString(),"mineable");
        logger.info("mineable tag is present in response");
    }

    @Given("User gets IDs for top 10 cryptocurrencies")
    public void getTop10IDs()
    {
        //User_gets_crypto_response();
        logger.info("Getting response");
        response = given()
                .header("X-CMC_PRO_API_KEY", BaseUtil.api_key)
                .get(BaseUtil.baseurl + BaseUtil.crypto_curr_map)
                .then().assertThat().statusCode(200)
                .extract().response();
        logger.info("Getting top 10 IDs");

        for(int i=0;i<10;i++)
        {
            logger.info("Fetching id: " + i+1);
            top_ids[i] = response.jsonPath().get("data[" +i +"].id");
        }

        logger.info("IDs fetched.");

    }

    @Then("Verify which currencies have mineable tag present")
    public void topIdsTagVerification()
    {
        int index=0;
        for(int i=0;i<10;i++)
        {
            getInfoResponseForETH(top_ids[i]);
            String mineable_value = response.jsonPath().getString("data." + top_ids[i] +".tags[0]");
            if(mineable_value.equals("mineable"))
            {
                mine_tag_ids[index] = top_ids[i];
                mine_tag_names[index] = response.jsonPath().get("data." + top_ids[i] +".name");
                index ++;
            }

        }
    }

    @Then("Verify correct cryptocurrencies have been printed out")
    public void printAllCorrectCryptos()
    {
        int index =0;
        logger.info("Top 10 cryptocurrencies with mineable tag:");

        do{

            logger.info((index+1) + ": " + mine_tag_ids[index] + ", Name: " + mine_tag_names[index]);
            index++;

        }while(mine_tag_ids[index] < 10 && mine_tag_ids[index]>0);

    }

}
