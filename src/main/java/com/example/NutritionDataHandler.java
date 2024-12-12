package com.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NutritionDataHandler {
    public static void getHandledData(String result) {
        String jsonResponse = result; // API JSON response
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);

            // Extract Calorie Needs and BMI
            String calorieNeeds = extractCalorieNeeds(jsonObject);
            System.out.println("Estimated Daily Caloric Needs: " + calorieNeeds);

            String bmi = extractBMI(jsonObject);
            System.out.println("BMI: " + bmi);

            // Extract Macronutrients
            List<String> macronutrients = extractMacronutrients(jsonObject);
            System.out.println("Macronutrients: " + macronutrients);

            // Extract Water Intake
            String water = extractWaterIntake(jsonObject);
            System.out.println("Water Intake: " + water);

            // Extract Key Vitamins (e.g., Vitamin A, C, D, and B12)
            List<String> keyVitamins = extractKeyVitamins(jsonObject);
            System.out.println("Key Vitamins: " + keyVitamins);

            // Extract Key Minerals (e.g., Calcium, Iron, Potassium, and Zinc)
            List<String> keyMinerals = extractKeyMinerals(jsonObject);
            System.out.println("Key Minerals: " + keyMinerals);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String extractCalorieNeeds(JSONObject jsonObject) {
        return jsonObject.getJSONObject("BMI_EER").getString("Estimated Daily Caloric Needs");
    }

   public static String extractBMI(JSONObject jsonObject) {
        return jsonObject.getJSONObject("BMI_EER").getString("BMI");
    }

   public static List<String> extractMacronutrients(JSONObject jsonObject) {
        List<String> macronutrientList = new ArrayList<>();
        JSONArray macronutrientsTable = jsonObject
                .getJSONObject("macronutrients_table")
                .getJSONArray("macronutrients-table");

        for (int i = 1; i < macronutrientsTable.length(); i++) {
            JSONArray row = macronutrientsTable.getJSONArray(i);
            String nutrient = row.getString(0);
            if (nutrient.equalsIgnoreCase("Carbohydrate") ||
                nutrient.equalsIgnoreCase("Protein") ||
                nutrient.equalsIgnoreCase("Fat")) {
                macronutrientList.add(nutrient + ": " + row.getString(1));
            }
        }
        return macronutrientList;
    }

    public static String extractWaterIntake(JSONObject jsonObject) {
        JSONArray macronutrientsTable = jsonObject
                .getJSONObject("macronutrients_table")
                .getJSONArray("macronutrients-table");

        for (int i = 1; i < macronutrientsTable.length(); i++) {
            JSONArray row = macronutrientsTable.getJSONArray(i);
            if (row.getString(0).equalsIgnoreCase("Total Water")) {
                return row.getString(1); // Return water intake
            }
        }
        return "Water intake not found";
    }

    public static List<String> extractKeyVitamins(JSONObject jsonObject) {
        List<String> vitaminsList = new ArrayList<>();
        JSONArray vitaminsTable = jsonObject
                .getJSONObject("vitamins_table")
                .getJSONArray("vitamins-table");

        List<String> keyVitamins = List.of("Vitamin A", "Vitamin C", "Vitamin D", "Vitamin B12");
        for (int i = 1; i < vitaminsTable.length(); i++) {
            JSONArray row = vitaminsTable.getJSONArray(i);
            if (keyVitamins.contains(row.getString(0))) {
                vitaminsList.add(row.getString(0) + ": " + row.getString(1));
            }
        }
        return vitaminsList;
    }

   public static List<String> extractKeyMinerals(JSONObject jsonObject) {
        List<String> mineralsList = new ArrayList<>();
        JSONArray mineralsTable = jsonObject
                .getJSONObject("minerals_table")
                .getJSONArray("essential-minerals-table");

        List<String> keyMinerals = List.of("Calcium", "Iron", "Potassium", "Zinc");
        for (int i = 1; i < mineralsTable.length(); i++) {
            JSONArray row = mineralsTable.getJSONArray(i);
            if (keyMinerals.contains(row.getString(0))) {
                mineralsList.add(row.getString(0) + ": " + row.getString(1));
            }
        }
        return mineralsList;
    }
}
