package data;

import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TestDataProvider {
    JSONParser jsonParser = new JSONParser();

    @Getter
    private String userPasswordDoestMatch = "Epic sadface: Username and password do not match any user in this service";
    @Getter
    private String userLockedOut = "Epic sadface: Sorry, this user has been locked out.";
    @Getter
    private final String correctStandardUserUsername = "standard_user";
    @Getter
    private final String correctPassword = "secret_sauce";
    @Getter
    private final String lockedoutUserUsername = "locked_out_user";
    @Getter
    private final String wrongPassword = "secret_sauceeee";


     public String getKeyValue(String jsonFile, String key)
     {
         String value = "";
         try {
             FileReader fileReader = new FileReader(jsonFile);
             JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);
             value = (String) jsonObject.get(key);

         } catch (Exception e) {
             throw new RuntimeException(e);
         }


        if (value.isEmpty()) {
            Assert.fail("Key is not existing in json! Fail!");

        }
      //   String value = "";
         return value;
     }


    public static List<String> getDropdownOptions(String jsonFile, String option) {
        List<String> options = new ArrayList<>();

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(jsonFile));


            JSONObject jsonObject = (JSONObject) obj;
            JSONArray dropdownOptions = (JSONArray) jsonObject.get(option);

            for (Object dropdown : dropdownOptions) {
                options.add((String) dropdown);
            }

        }
        catch (Exception e) {
            throw new RuntimeException(e);
    }
        return options;
}
}
