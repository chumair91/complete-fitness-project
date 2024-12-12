package com.example;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class NutritionAPI {
    private static final String API_URL = "https://nutrition-calculator.p.rapidapi.com/api/nutrition-info";
    private static final String API_KEY = "1ffac417e4mshe27a3240bec784bp12b9edjsnfaa20d391036";

    public static String getNutritionInfo(String sex, int age, int feet, int inches, int lbs, String activityLevel) {
        try {
            // Construct the URL
            String urlString = String.format(
                    "%s?measurement_units=std&sex=%s&age_value=%d&age_type=yrs&feet=%d&inches=%d&lbs=%d&activity_level=%s",
                    API_URL, sex, age, feet, inches, lbs, activityLevel);
            URL url = new URL(urlString);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-rapidapi-key", API_KEY);
            connection.setRequestProperty("x-rapidapi-host", "nutrition-calculator.p.rapidapi.com");

            // Check the response
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse and return the response
                JSONObject jsonResponse = new JSONObject(response.toString());
                return jsonResponse.toString(2); // Pretty print JSON
            } else {
                return "Error: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
