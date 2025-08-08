package data;

import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Getter
    public final class LoadDataFromJson {
        private static final JSONParser JSON_PARSER = new JSONParser();
        private static final String FILE_LOCATION  = "src/test/resources/" ;
        private LoadDataFromJson() {

        }
        public static String getKeyValue(String jsonFile, String key) {
            try (FileReader fileReader = new FileReader(FILE_LOCATION + jsonFile)) {
                JSONObject jsonObject = (JSONObject) JSON_PARSER.parse(fileReader);
                String value = (String) jsonObject.get(key);
                if (value == null || value.isEmpty()) {
                    Assert.fail("Key is not existing in json! Fail!");
                }
                return value;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public static List<String> getDropdownOptions(String jsonFile, String option) {
            try (FileReader fileReader = new FileReader(FILE_LOCATION + jsonFile)) {
                JSONObject jsonObject = (JSONObject) JSON_PARSER.parse(fileReader);
                JSONArray dropdownOptions = (JSONArray) jsonObject.get(option);
                if (dropdownOptions == null) {
                    Assert.fail("Dropdown option is not existing in json! Fail!");
                }
                List<String> options = new ArrayList<>();
                for (Object dropdown : dropdownOptions) {
                    options.add((String) dropdown);
                }
                return options;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
