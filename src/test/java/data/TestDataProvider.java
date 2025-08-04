package data;

import lombok.Getter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class TestDataProvider {
    JSONParser jsonParser = new JSONParser();

    @Getter
    private String userPasswordDoestMatch = "Epic sadface: Username and password do not match any user in this service";
    @Getter
    private String userLockedOut = "Epic sadface: Sorry, this user has been locked out.";

     public String getExpectedMessage(String jsonFile, String key)
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

}
