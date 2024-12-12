package com.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiFetcher {
    public static String fetchMeals(String query) {
        String apiUrl = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + query;
        StringBuilder result = new StringBuilder();
        
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
    public static void main(String[] args) throws Exception{
        ApiFetcher apiClass=new ApiFetcher();
        
       System.out.println(ApiFetcher.fetchMeals("salad"));
    }
}