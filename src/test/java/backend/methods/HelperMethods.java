package backend.methods;

import io.restassured.path.json.JsonPath;

public class HelperMethods {

    public static int getIds(String curr, JsonPath jsonpath)
    {
        int count = 0;
        int id = 0;
        while(jsonpath.getJsonObject("data[" + count + "].name") != null && id == 0)
        {
            if(jsonpath.get("data[" + count +"].name").toString().equals(curr))
            {
                id = jsonpath.get("data[" + count +"].id");
            }


            count ++;

        }

        return id;

    }

}
