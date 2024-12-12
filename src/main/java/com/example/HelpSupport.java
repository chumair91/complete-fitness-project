package com.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class HelpSupport extends Application{

    private App app; 
    private Stage primaryStage;

    public HelpSupport(App app1) {
      
        this.app=app1;
    }

    @Override
    public void start(Stage stage)  {
        this.primaryStage=stage;
        helpMenu();
    }
    public void helpMenu(){
        Button faqs=new Button("Frequently Asked Questions");
        Button contactAt = new Button("Contact Us ");
        Button emailBtn = new Button("Send Email");
        VBox vBox=new VBox();
        vBox.getChildren().addAll(faqs,contactAt,emailBtn);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);

        contactAt.setOnAction(o->{
            contactUs();
        });
        emailBtn.setOnAction(y->{
            openHelpSupport();
        });
        Scene scene=new Scene(vBox,350,600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void openHelpSupport() {
        

        Button emailBtn = new Button("Send Email");
        Button backBtn = new Button("Back");

        // Text Area for Subject and Body
        Label subjectLabel = new Label("Subject:");
        TextArea subjectField = new TextArea();
        subjectField.setPromptText("Enter subject");
        subjectField.setWrapText(true);
        subjectField.setMaxHeight(50);

        Label bodyLabel = new Label("Body:");
        TextArea bodyField = new TextArea();
        bodyField.setPromptText("Enter body text");
        bodyField.setWrapText(true);

        VBox helpsupport = new VBox( subjectLabel, subjectField, bodyLabel, bodyField, emailBtn, backBtn);
        helpsupport.setSpacing(10);
        helpsupport.setAlignment(Pos.CENTER);

        Scene helpSupportScene = new Scene(helpsupport, 350, 600);
        // stage.setScene(helpSupportScene);

        // Back button functionality
        backBtn.setOnAction(e -> {
            app.AcessPage();
        });

        // Email button functionality
        emailBtn.setOnAction(e -> {
            String subject = subjectField.getText();
            String body = bodyField.getText();
            openGmail(subject, body);
        });
        primaryStage.setScene(helpSupportScene);
        primaryStage.show();
    }

    public void openGmail(String subject, String body) {
        String recipientEmail = "huzaifaliaquat62@gmail.com";

        // URL encode the subject and body to handle special characters
        String encodedSubject = URLEncoder.encode(subject, StandardCharsets.UTF_8);
        String encodedBody = URLEncoder.encode(body, StandardCharsets.UTF_8);

        // Construct the Gmail URL
        String gmailURL = "https://mail.google.com/mail/?view=cm&fs=1&to=" + recipientEmail + "&su=" + encodedSubject + "&body=" + encodedBody;

        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
             if (desktop.isSupported(Desktop.Action.BROWSE)) {
           
                try {
                    desktop.browse(new URI(gmailURL));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace(); // Log the error for debugging
                }
            } 
            else {
                System.out.println("BROWSE action is not supported!");
            }
        } else {
            System.out.println("Desktop is not supported on this platform.");
        }
    }

   public void contactUs(){
    Button backBtn = new Button("Back");
    backBtn.setOnAction(i->{
        helpMenu();
    });
    Label phoneLabel1 = new Label("03328307060");
    Label phoneLabel2 = new Label("03328307061");
    Label phoneLabel3 = new Label("03328307062");
    VBox contactVbox=new VBox();
    contactVbox.setAlignment(Pos.CENTER);
    contactVbox.getChildren().addAll(backBtn,phoneLabel1,phoneLabel2,phoneLabel3);
    
    Scene scene=new Scene(contactVbox,350,600);
    primaryStage.setScene(scene);
    primaryStage.show();

   }
}
