
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by alinpana on 15/02/16.
 * Updated by razvan on 18/02
 */



public class ReadJson {

    /*
        Read Json from file
        Return :  string
     */
    public static JSONObject readJson (String path) {
        try {

            String jsonData = "";
            BufferedReader br = null;
            try {
                String line;
                br = new BufferedReader(new FileReader(path));
                while ((line = br.readLine()) != null) {
                    jsonData += line + "\n";
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null)
                        br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            return new JSONObject(jsonData);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
        Parse and print a Json string
     */
    public static void parseJson (JSONObject node , int level) {
        Iterator keys = node.keys();
        level ++ ;
        while(keys.hasNext()) {
            // loop to get the dynamic key
            String currentDynamicKey = (String)keys.next();
            // get the value of the dynamic key
            try {
                JSONObject currentDynamicValue = node.getJSONObject(currentDynamicKey);
                System.out.println(String.format("%"+ level * 7 +"s","") +  currentDynamicKey + " (level : " + level + ")");
                if (currentDynamicValue.keys().hasNext()) {
                    parseJson(currentDynamicValue , level);
                }

            } catch (JSONException e) {
                System.out.println(String.format("%"+ level * 7 +"s","") +  "--> { " + currentDynamicKey + " : " + node.get(currentDynamicKey) + " } ");
            }
        }
    }
    public static void main (String[] Args) {

        JSONObject readJsonFile = readJson("test.json");
        parseJson(readJsonFile,0);

    }

}
