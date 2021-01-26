package backend.steps;

import backend.ENUMs.apiPaths;
import backend.base.baseUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

import static backend.methods.helperMethods.getIds;
import static io.restassured.RestAssured.given;

public class backendTasks extends baseUtil{

    protected Response response;
    protected String idBTC,idETH,idUSDT;
    protected int[] topIDS = new int[10];
    protected int[] mineTagIds = new int[10];
    protected String[] mineTagNames = new String[10];


    @Given("User gets the response from cryptocurrency call")
    public void User_gets_crypto_response()
    {
        System.err.close();
        System.setErr(System.out);

        response = given()
                .header("X-CMC_PRO_API_KEY", baseUtil.api_Key)
                .get(baseUtil.baseURL +baseUtil.crypto_curr_map)
                .then().assertThat().statusCode(200)
                .extract().response();

    }

    @Then("Save Ids of BTC,ETH and USDT")
    public void save_ids()
    {

        idBTC = "" + getIds("Bitcoin",response.jsonPath());
        idETH = "" + getIds("Ethereum",response.jsonPath());
        idUSDT = "" + getIds("Tether", response.jsonPath());
    }

    @And("Convert the IDs to Bolivian Boliviano")
    public void convert_IDs_to_Boliviano() {
        response = given()
                .header("X-CMC_PRO_API_KEY", baseUtil.api_Key)
                .param("amount", "1")
                .param("id", idBTC)
                .param("convert_id", "2832")
                .get( baseUtil.baseURL  + baseUtil.crypto_conv)
                .then().assertThat().statusCode(200)
                .extract().response();

        logger.info("Converted value of 1 BTC into BOB = " + response.jsonPath().getString(apiPaths.quotePrice_Path.getConfigPath()));
        response = given()
                .header("X-CMC_PRO_API_KEY", baseUtil.api_Key)
                .param("amount", "1")
                .param("id", idETH)
                .param("convert_id", "2832")
                .get(baseUtil.baseURL + baseUtil.crypto_conv)
                .then().assertThat().statusCode(200)
                .extract().response();

        logger.info("Converted value of 1 ETH into BOB = " + response.jsonPath().getString(apiPaths.quotePrice_Path.getConfigPath()));


        response = given()
                .header("X-CMC_PRO_API_KEY", baseUtil.api_Key)
                .param("amount", "1")
                .param("id", idUSDT)
                .param("convert_id", "2832")
                .get(baseUtil.baseURL + baseUtil.crypto_conv)
                .then().assertThat().statusCode(200)
                .extract().response();

        logger.info("Converted value of 1 USDT into BOB = " + response.jsonPath().getString(apiPaths.quotePrice_Path.getConfigPath()));
    }

    @Given("User gets info response for Etherium ID {int}")
    public void get_info_response_for_ETH(int id)
    {
        logger.info("Getting info response for ETH");
        response = given()
                .header("X-CMC_PRO_API_KEY", baseUtil.api_Key)
                .param("id",id)
                .get(baseUtil.baseURL + baseUtil.crypto_info)
                .then().assertThat().statusCode(200)
                .extract().response();
        logger.info("Infor response for ETH received");
    }

    @Then("Verify logo URL is present")
    public void logo_URL_Verification()
    {
        logger.info("Verifying logoURL presence");
        String logoURL = "https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png";
        Assert.assertEquals(response.jsonPath().get(apiPaths.logo_Path.getConfigPath()).toString(), logoURL);
        logger.info("logoURL is present in response");
    }

    @And("Verify technical_doc URI is present")
    public void tech_URI_Verification()
    {
        logger.info("Verifying techURI presence");
        String techURI = "[https://github.com/ethereum/wiki/wiki/White-Paper]";
        Assert.assertEquals(response.jsonPath().get(apiPaths.techDoc_Path.getConfigPath()).toString(), techURI);
        logger.info("techURI exists in response");
    }

    @And("Verify symbol of currency is ETH")
    public void curr_symbol_verification()
    {
        logger.info("Verifying symbol presence");
        String symbol = "ETH";
        Assert.assertEquals(response.jsonPath().get(apiPaths.symbol_Path.getConfigPath()).toString(),symbol);
        logger.info("ETH symbol is present in response");
    }

    @And("Verify date added field")
    public void date_Field_Verification()
    {
        logger.info("Verifying presence of date added field");
        String date = "2015-08-07T00:00:00.000Z";
        Assert.assertEquals(response.jsonPath().get(apiPaths.date_Path.getConfigPath()).toString(),date);
        logger.info("date added field is present in response");
    }

    @And("Verify platform is null")
    public void platform_veriication()
    {
        logger.info("Verifying platform's value");
        Assert.assertNull(response.jsonPath().get(apiPaths.platform_Path.getConfigPath()));
        logger.info("platform's value is null in response as exepcted");
    }

    @And("Verify currency has mineable tag associated with it")
    public void mineable_tag_verification()
    {
        logger.info("Verifying mineable tag presence");
        Assert.assertEquals(response.jsonPath().get(apiPaths.mineableTag_Path.getConfigPath()).toString(),"mineable");
        logger.info("mineable tag is present in response");
    }

    @Given("User gets IDs for top 10 cryptocurrencies")
    public void get_top_10_IDs()
    {
        //User_gets_crypto_response();
        logger.info("Getting response");
        response = given()
                .header("X-CMC_PRO_API_KEY",baseUtil.api_Key)
                .get(baseUtil.baseURL +baseUtil.crypto_curr_map)
                .then().assertThat().statusCode(200)
                .extract().response();
        logger.info("Getting top 10 IDs");

        for(int i=0;i<10;i++)
        {
            logger.info("Fetching id: " + i+1);
            topIDS[i] = response.jsonPath().get("data[" +i +"].id");
        }

        logger.info("IDs fetched.");

    }

    @Then("Verify which currencies have mineable tag present")
    public void top_ids_tag_verification()
    {
        int index=0;
        for(int i=0;i<10;i++)
        {
            get_info_response_for_ETH(topIDS[i]);
            String mineableValue = response.jsonPath().getString("data." + topIDS[i] +".tags[0]");
            if(mineableValue.equals("mineable"))
            {
                mineTagIds[index] = topIDS[i];
                mineTagNames[index] = response.jsonPath().get("data." + topIDS[i] +".name");
                index ++;
            }

        }
    }

    @Then("Verify correct cryptocurrencies have been printed out")
    public void print_all_correct_cryptos()
    {
        int index =0;
        logger.info("Top 10 cryptocurrencies with mineable tag:");

        do{

            logger.info((index+1) + ": " + mineTagIds[index] + ", Name: " + mineTagNames[index]);
            index++;

        }while(mineTagIds[index] < 10 && mineTagIds[index]>0);

    }

}
