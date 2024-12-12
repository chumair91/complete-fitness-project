package com.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class VideoHandler extends Application {
Stage primaryStage;
App app;


public  VideoHandler(App app1){
    this.app = app1;
}
    @Override
    public void start(Stage primaryStage)  {
        this.primaryStage = primaryStage;
        videoMenu();
    }
    public void videoMenu(){
    Button backBtn=new Button("Back");
    backBtn.setOnAction(e -> {
        app.properApp();
    });
        Label abs=new Label("Abs");
        HBox absBox=new HBox(abs);
       absBox.setAlignment(Pos.CENTER);

       abs.setOnMouseClicked(y->{
           absVideos();
       });
        Label back=new Label("Back");
        HBox backBox=new HBox(back);
        backBox.setAlignment(Pos.CENTER);


        Label bicep=new Label("Biceps");
        HBox bicepBox=new HBox(bicep);
        bicepBox.setAlignment(Pos.CENTER);

        Label calf=new Label("Calf");
        HBox calfBox=new HBox(calf);
        calfBox.setAlignment(Pos.CENTER);

        Label chest=new Label("Chest");
        HBox chestBox=new HBox(chest);
        chestBox.setAlignment(Pos.CENTER);

        Label forearm=new Label("Forearm");
        HBox foreBox=new HBox(forearm);
        foreBox.setAlignment(Pos.CENTER);

        VBox videoMenuVbox=new VBox();
        videoMenuVbox.setSpacing(15);
        videoMenuVbox.setAlignment(Pos.CENTER);
        videoMenuVbox.getChildren().addAll(backBtn,absBox,backBox,bicepBox,calfBox,chestBox,foreBox);
        Scene scene=new Scene(videoMenuVbox,350,600);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    public  void absVideos(){
        Button backButton = new Button("<-");
        backButton.getStyleClass().add("backButtonStyle");
        backButton.setOnAction(e -> videoMenu());

        Label videoLabel = new Label("videos");
        String videoUrl = getClass().getResource("/video/abs1.mp4").toExternalForm();
        System.out.println(videoUrl);
        Media media = new Media(videoUrl);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.play();

        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));


        // control height and width
        mediaView.setFitHeight(200);
        mediaView.setFitWidth(200);

        mediaView.setOnMouseClicked(j -> {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.play();
            }
        });

        VBox videoVBox = new VBox();
        videoVBox.setStyle("-fx-background-color: black");
        videoVBox.setId("videoVBox");
        videoVBox.getChildren().addAll(backButton, mediaView, videoLabel);
        videoVBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(videoVBox, 350, 600);
        primaryStage.setScene(scene);
    }
}
