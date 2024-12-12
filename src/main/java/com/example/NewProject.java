package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewProject extends Application {

    private Stage primaryStage; 
    private App app; 

    // Constructor to receive the App instance
    public NewProject(App app) {
        this.app = app;
    }

    @Override
    public void start(Stage newStage) throws Exception {
        this.primaryStage = newStage; 
        showThings();
        primaryStage.show();
        primaryStage.setFullScreen(true);
    }

    public void showThings() {
        // Use the stored Stage instance
        Button btn = new Button("Hello");
        VBox vBox = new VBox(btn);
        
        btn.setOnAction(u -> {
            // Safely access App and call its method
            if (app != null) {
                app.AcessPage();
            }
        });
        
        Scene scene = new Scene(vBox,350,600);
        primaryStage.setScene(scene);
    }
}
