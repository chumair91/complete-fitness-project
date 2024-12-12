package com.example;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import org.json.JSONObject;

public class BMICalculatorAPI {

    // Replace with your actual API key
    private static final String API_KEY = "1ffac417e4mshe27a3240bec784bp12b9edjsnfaa20d391036";
    private static final String API_URL = "https://body-mass-index-bmi-calculator.p.rapidapi.com/metric";

    public static String getBMI(double weight, double height) {
        try {
            // Construct the URL with query parameters
            String urlString = API_URL + "?weight=" + weight + "&height=" + height;
            URL url = new URL(urlString);

            // Open HTTP connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Add required headers
            connection.setRequestProperty("X-RapidAPI-Key", API_KEY);
            connection.setRequestProperty("X-RapidAPI-Host", "body-mass-index-bmi-calculator.p.rapidapi.com");

            // Get the response code
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) { // HTTP OK
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                // Read response line by line
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());
                double bmi = jsonResponse.getDouble("bmi");

                return "Your BMI is: " + bmi;
            } else {
                return "Error: Unable to calculate BMI. Response code: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error calculating BMI: " + e.getMessage();
        }
    }
}
