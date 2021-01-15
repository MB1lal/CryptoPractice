package backend.methods;

import io.restassured.path.json.JsonPath;

public class helperMethods {

    public static int getIds(String curr, JsonPath jsonPath)
    {
        int count = 0;
        int id = 0;
        while(jsonPath.getJsonObject("data[" + count + "].name") != null && id == 0)
        {
            if(jsonPath.get("data[" + count +"].name").toString().equals(curr))
            {
                id = jsonPath.get("data[" + count +"].id");
            }


            count ++;

        }

        return id;

    }

}
