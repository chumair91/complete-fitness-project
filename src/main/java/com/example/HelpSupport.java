package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.*;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class HelpSupport extends Application {

    private App app;
    private Stage primaryStage;

    public HelpSupport(App app1) {

        this.app = app1;
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        helpMenu();
    }

    public void helpMenu() {
        Button backButton = new Button("");
        String imgPath = getClass().getResource("backBtn.png").toExternalForm();
        Image backImg = new Image(imgPath);
        ImageView backImageView = new ImageView(backImg);
        backImageView.setFitWidth(30);
        backImageView.setFitHeight(30);
        backButton.setGraphic(backImageView);

        Button faqsBtn = new Button("Frequently Asked Questions");
        Button contactAt = new Button("Contact Us");
        Button emailBtn = new Button("Send Email");

        // Common button styling
        String buttonStyle = "-fx-background-color: #4CAF50; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 14px; " +
                "-fx-font-weight: bold; " +
                "-fx-border-radius: 10px; " +
                "-fx-background-radius: 10px; " +
                "-fx-padding: 10px 20px;";

        // Apply styles to buttons
        faqsBtn.setStyle(buttonStyle);
        contactAt.setStyle(buttonStyle);
        emailBtn.setStyle(buttonStyle);

        // Adjust button widths for consistency
        faqsBtn.setMaxWidth(250);
        contactAt.setMaxWidth(250);
        emailBtn.setMaxWidth(250);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(backButton, faqsBtn, contactAt, emailBtn);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: linear-gradient(to bottom, #ece9e6, #ffffff);"); // Light gradient
                                                                                              // background

        contactAt.setOnAction(o -> contactUs());
        emailBtn.setOnAction(y -> openHelpSupport());
      faqsBtn.setOnAction(e->faqs());
        Scene scene = new Scene(vBox, 350, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openHelpSupport() {
        Button emailBtn = new Button("Send Email");
        emailBtn.setStyle("-fx-background-color: #28a745; " +
                          "-fx-text-fill: white; " +
                          "-fx-font-size: 14px; " +
                          "-fx-font-weight: bold; " +
                          "-fx-border-radius: 5px; " +
                          "-fx-background-radius: 5px; " +
                          "-fx-padding: 10px 20px;");
    
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #0078D7; " +
                         "-fx-text-fill: white; " +
                         "-fx-font-size: 14px; " +
                         "-fx-font-weight: bold; " +
                         "-fx-border-radius: 5px; " +
                         "-fx-background-radius: 5px; " +
                         "-fx-padding: 10px 20px;");
    
        Label subjectLabel = new Label("Subject:");
        subjectLabel.setStyle("-fx-font-size: 16px; " +
                              "-fx-font-weight: bold; " +
                              "-fx-text-fill: #2c3e50;");
    
        TextArea subjectField = new TextArea();
        subjectField.setPromptText("Enter subject");
        subjectField.setWrapText(true);
        subjectField.setMaxHeight(50);
        subjectField.setStyle("-fx-border-color: #0078D7; " +
                              "-fx-border-radius: 5px; " +
                              "-fx-background-radius: 5px; " +
                              "-fx-padding: 5px;");
    
        Label bodyLabel = new Label("Body:");
        bodyLabel.setStyle("-fx-font-size: 16px; " +
                           "-fx-font-weight: bold; " +
                           "-fx-text-fill: #2c3e50;");
    
        TextArea bodyField = new TextArea();
        bodyField.setPromptText("Enter body text");
        bodyField.setWrapText(true);
        bodyField.setStyle("-fx-border-color: #0078D7; " +
                           "-fx-border-radius: 5px; " +
                           "-fx-background-radius: 5px; " +
                           "-fx-padding: 5px;");
    
        VBox helpsupport = new VBox(15, subjectLabel, subjectField, bodyLabel, bodyField, emailBtn, backBtn);
        helpsupport.setAlignment(Pos.CENTER);
        helpsupport.setPadding(new Insets(20));
        helpsupport.setStyle("-fx-background-color: linear-gradient(to bottom, #ffffff, #ecf0f1);");
    
        Scene helpSupportScene = new Scene(helpsupport, 400, 600);
    
        backBtn.setOnAction(e -> app.AcessPage());
    
        emailBtn.setOnAction(e -> {
            String subject = subjectField.getText();
            String body = bodyField.getText();
            openGmail(subject, body);
        });
    
        primaryStage.setScene(helpSupportScene);
        primaryStage.setTitle("Help & Support");
        primaryStage.show();
    }
    
    public void openGmail(String subject, String body) {
        String recipientEmail = "huzaifaliaquat62@gmail.com";

        // URL encode the subject and body to handle special characters
        String encodedSubject = URLEncoder.encode(subject, StandardCharsets.UTF_8);
        String encodedBody = URLEncoder.encode(body, StandardCharsets.UTF_8);

        // Construct the Gmail URL
        String gmailURL = "https://mail.google.com/mail/?view=cm&fs=1&to=" + recipientEmail + "&su=" + encodedSubject
                + "&body=" + encodedBody;

        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {

                try {
                    desktop.browse(new URI(gmailURL));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace(); 
                }
            } else {
                System.out.println("BROWSE action is not supported!");
            }
        } else {
            System.out.println("Desktop is not supported on this platform.");
        }
    }

    public void contactUs() {
        // Back button styling
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #0078D7; " + // Blue background
                "-fx-text-fill: white; " + // White text
                "-fx-font-size: 14px; " + // Font size
                "-fx-font-weight: bold; " + // Bold text
                "-fx-border-radius: 5px; " + // Rounded border
                "-fx-background-radius: 5px; " + // Rounded background
                "-fx-padding: 10px 20px;"); // Padding inside button
        backBtn.setOnAction(i -> helpMenu());

        // Labels for contact information
        Label headingLabel = new Label("Contact Us");
        headingLabel.setStyle("-fx-font-size: 24px; " + // Large font size
                "-fx-font-weight: bold; " + // Bold text
                "-fx-text-fill: #2c3e50;"); // Dark blue-gray color

        Label subheadingLabel = new Label("We are here to help you!");
        subheadingLabel.setStyle("-fx-font-size: 16px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #34495e;");

        Label phoneHeadingLabel = new Label("Phone Numbers:");
        phoneHeadingLabel.setStyle("-fx-font-size: 18px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #2c3e50;");

        Label phoneLabel1 = new Label("0332-8307060");
        Label phoneLabel2 = new Label("0332-8307061");
        Label phoneLabel3 = new Label("0332-8307062");
        String phoneLabelStyle = "-fx-font-size: 16px; " +
                "-fx-text-fill: #333333;";
        phoneLabel1.setStyle(phoneLabelStyle);
        phoneLabel2.setStyle(phoneLabelStyle);
        phoneLabel3.setStyle(phoneLabelStyle);

        Label noteLabel = new Label("Our team is available 24/7 to assist you.");
        noteLabel.setStyle("-fx-font-size: 14px; " +
                "-fx-font-style: italic; " +
                "-fx-text-fill: #7f8c8d;");

        VBox contactVbox = new VBox(15);
        contactVbox.setAlignment(Pos.CENTER);
        contactVbox.getChildren().addAll(
                headingLabel,
                subheadingLabel,
                phoneHeadingLabel,
                phoneLabel1, phoneLabel2, phoneLabel3,

                noteLabel,
                backBtn);
        contactVbox.setPadding(new Insets(20));
        contactVbox.setStyle("-fx-background-color: linear-gradient(to bottom, #ffffff, #ecf0f1);"); // Light gradient
                                                                                                     // background

        // Scene setup
        Scene scene = new Scene(contactVbox, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Contact Us");
        primaryStage.show();
    }
    public void faqs() {
        TextArea testArea=new TextArea();
        testArea.setVisible(false);
        // Question 1
        Label question1 = new Label("What is the recommended daily calorie \n intake?");
        question1.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #0056b3; -fx-cursor: hand;");
       
      
       
        question1.setOnMouseClicked(e -> {
            testArea.setVisible(!testArea.isVisible());
            testArea.setText("The recommended daily calorie intake depends on your \n age, gender, and activity level. For an average adult, \n it is about 2000-2500 calories.");
        });
       
    
        // Question 2
        Label question2 = new Label("How can I calculate  my macros?");
        question2.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #0056b3; -fx-cursor: hand;");
    
       
    
        question2.setOnMouseClicked(e -> {
            testArea.setVisible(!testArea.isVisible());
            testArea.setText("You can calculate your macros using the \n" + //
                                " Macro Calculator available in this app. \n" + //
                                " Input your details like age, weight, \n" + //
                                " and fitness goals to get personalized recommendations");
        });
    
        // Question 3
        Label question3 = new Label("Where can I find workout videos?");
        question3.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #0056b3; -fx-cursor: hand;");
    
        
        question3.setOnMouseClicked(e -> {
            testArea.setVisible(!testArea.isVisible());
            testArea.setText("Workout videos are available in the 'Workout Videos' \n section of the app. Navigate to the section to explore a \n variety of exercises.");
        });
    
        // Question 4
        Label question4 = new Label("What is the 30-day workout plan?");
        question4.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #0056b3; -fx-cursor: hand;");
    
       
    
        question4.setOnMouseClicked(e -> {
            testArea.setVisible(!testArea.isVisible());
            testArea.setText("The 30-day workout plan is a comprehensive guide \n for daily exercises designed to improve strength, \n endurance, and overall fitness.");
        });
            

        // Back button
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #0078D7; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-padding: 10px 20px;");
        backBtn.setOnAction(e -> helpMenu());
    
        // VBox layout
        VBox faqsVBox = new VBox(15, question1,  question2,  question3,  question4,  testArea,backBtn);
        faqsVBox.setAlignment(Pos.TOP_CENTER);
        faqsVBox.setPadding(new Insets(20));
        faqsVBox.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f8ff, #e6f7ff);");
    
        // ScrollPane for better viewing
        ScrollPane scrollPane = new ScrollPane(faqsVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #333; -fx-border-color: #333; -fx-padding: 10px;");
    
        Scene faqsScene = new Scene(scrollPane, 450, 650);
        primaryStage.setScene(faqsScene);
        primaryStage.setTitle("FAQs");
        primaryStage.show();
    }
    

}
