package com.example;

import java.security.PrivateKey;

import org.json.JSONObject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalorieHandler extends Application {
    private Stage primaryStage;
    private App app;

    
    public CalorieHandler(App app) {
        this.app = app;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
      this.primaryStage=primaryStage;
        calorieCal();
    }
    

    public void calorieCal() {
        // Back Button
        Button backButton = new Button("");
        String imgPath=getClass().getResource("backBtn.png").toExternalForm();
        Image backImg=new Image(imgPath);
        ImageView backImageView=new ImageView(backImg);
        backImageView.setFitWidth(30);
        backImageView.setFitHeight(30);
        backButton.setGraphic(backImageView);
        
       
        backButton.setOnAction(e -> app.properApp());
    
       // String sex, int age, int feet, int inches, int lbs, String activityLevel
       Label genderLabel = new Label("Gender");
       genderLabel.getStyleClass().add("cal-label");
    
       ToggleGroup toggleGroup = new ToggleGroup();
       RadioButton maleButton = new RadioButton("male");
       maleButton.setToggleGroup(toggleGroup);
       RadioButton femaleButton = new RadioButton("female");
       femaleButton.setToggleGroup(toggleGroup);
    
       Label age = new Label("Age");
       TextField ageField = new TextField();
    
       Label feetLabel = new Label("Feet");
       TextField feetField = new TextField();
    
       Label inchesLabel = new Label("Inches");
       TextField inchesField = new TextField();
    
       Label lbsLabel = new Label("Lbs");
       TextField lbsField = new TextField();
    
  
    
       Button submitDataBtn = new Button("Check");
    
       
       submitDataBtn.setOnAction(e -> {
        try {
            
            Toggle selectedToggle = toggleGroup.getSelectedToggle();
            if (selectedToggle == null) {
                System.out.println("Please select a gender.");
                return;
            }
            RadioButton selectedRadioButton = (RadioButton) selectedToggle;
            String gender = selectedRadioButton.getText();
    
            // Validate inputs
            if (ageField.getText().isEmpty() || !ageField.getText().matches("\\d+")) {
                System.out.println("Please enter a valid age.");
                return;
            }
            if (feetField.getText().isEmpty() || !feetField.getText().matches("\\d+")) {
                System.out.println("Please enter a valid height in feet.");
                return;
            }
            if (inchesField.getText().isEmpty() || !inchesField.getText().matches("\\d+")) {
                System.out.println("Please enter a valid height in inches.");
                return;
            }
            if (lbsField.getText().isEmpty() || !lbsField.getText().matches("\\d+")) {
                System.out.println("Please enter a valid weight in lbs.");
                return;
            }
    
            
            int userAge = Integer.parseInt(ageField.getText().trim());
            int userFeet = Integer.parseInt(feetField.getText().trim());
            int userInches = Integer.parseInt(inchesField.getText().trim());
            int userlbs = Integer.parseInt(lbsField.getText().trim());
            String activityString = "Active";
    
         
            String result = NutritionAPI.getNutritionInfo(gender, userAge, userFeet, userInches, userlbs, activityString);
            double protein = proteinCal(userlbs);
    
            if (result.startsWith("Error")) {
                System.out.println(result);
                return;
            }
    
            String calories = NutritionDataHandler.extractCalorieNeeds(new JSONObject(result));
            String bmi = NutritionDataHandler.extractBMI(new JSONObject(result));
            String waterIntake = NutritionDataHandler.extractWaterIntake(new JSONObject(result));
    
            double calorieGoal = 3500.0; 
            double proteinGoal = 200.0;  
            double waterGoal = 4.0;      
    
            String cleanCalories = calories.replaceAll("[^0-9.]", ""); // Remove all non-numeric characters except "."
            double calorieProgress = Math.min(Double.parseDouble(cleanCalories) / calorieGoal, 1.0);
            double proteinProgress = Math.min(protein / proteinGoal, 1.0);
            String cleanWaterIntake = waterIntake.replaceAll("[^0-9.]", ""); // Remove non-numeric characters except "."
            if (cleanWaterIntake.isEmpty()) {
                throw new NumberFormatException("Invalid water intake format.");
            }
            double waterProgress = Math.min(Double.parseDouble(cleanWaterIntake) / waterGoal, 1.0);
            
    
            // Back Button
            Button backButton1 = new Button("Back");
            backButton1.getStyleClass().add("back-btn");
            backButton1.setOnAction(t -> app.properApp());
    
            // Labels
            Label calorieLabel = new Label("Calories");
            Label calorieValue = new Label(calories + " kcal/day");
            ProgressBar calorieBar = new ProgressBar(calorieProgress);
            calorieBar.getStyleClass().add("progress-bar");
    
            Label proteinLabel = new Label("Protein");
            Label proteinValue = new Label(String.format("%.2f g", protein));
            ProgressBar proteinBar = new ProgressBar(proteinProgress);
    
            Label waterLabel = new Label("Water Intake");
            Label waterValue = new Label(waterIntake + " L");
            ProgressBar waterBar = new ProgressBar(waterProgress);
    
            VBox calorieBox = new VBox(5, calorieLabel, calorieValue, calorieBar);
            VBox proteinBox = new VBox(5, proteinLabel, proteinValue, proteinBar);
            VBox waterBox = new VBox(5, waterLabel, waterValue, waterBar);
    
            VBox resultVBox = new VBox(15, backButton1, calorieBox, proteinBox, waterBox);
            resultVBox.getStyleClass().add("result-container");
            resultVBox.setAlignment(Pos.CENTER);
    
            Scene resultScene = new Scene(resultVBox, 350, 500);
            resultScene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
    
            primaryStage.setScene(resultScene);
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("An error occurred. Please check your inputs and try again.");
        }
    });
    

    
       VBox calVBox = new VBox(15);
       calVBox.setAlignment(Pos.CENTER);
       calVBox.getChildren().addAll(backButton,genderLabel, maleButton, femaleButton, age, ageField, feetLabel, feetField,
           inchesLabel, inchesField, lbsLabel, lbsField,  submitDataBtn);
    
       Scene newsScene = new Scene(calVBox,350,600);
       newsScene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
       primaryStage.setScene(newsScene);
       primaryStage.show();
       // primaryStage.setFullScreen(true);
     }

     public double proteinCal(int weight) {
        double weightInKg = weight / 2.205;
        double protein = weightInKg * 2;
        return Math.round(protein * 100.0) / 100.0; 
    }
    
    
}
