package org.example.spring.mongodb.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GSONFileTest {

public static void main(String[] args)
{
   convertFileToJSON ("E://DEV//Amazon//mongodb-java//spring.mongodb//src//main//resources//reconditionne.json");
}

public static JsonObject convertFileToJSON (String fileName){

        // Read from File to String
        JsonObject jsonObject = new JsonObject();
        Gson gson = new Gson();
        try {
            JsonParser parser = new JsonParser();
            
            JsonElement jsonElement = parser.parse(new FileReader(fileName));
            jsonObject = jsonElement.getAsJsonObject();
            UrlListObject urlListObject = gson.fromJson(jsonObject, UrlListObject.class);
            System.out.println(urlListObject);
           // System.out.println(jsonObject);
            for (Map.Entry<String,JsonElement> entry : jsonObject.entrySet()) {
            	JsonObject subJsonObject = entry.getValue().getAsJsonObject();
            	Map<String,LinkInfo> subMap = new HashMap<>();

                for (Map.Entry<String,JsonElement> subEntry : subJsonObject.entrySet()) {
                	System.out.println(subEntry.getKey()  + " ++ " +subEntry.getValue() );
                //	subMap.put(subEntry.getKey(), new LinkInfo(subEntry.getValue().getAsJsonObject().))

                }
            
            }
        } catch (FileNotFoundException e) {
           
        } catch (IOException ioe){
        
        }
        
        
        return jsonObject;
    }

}