package com.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HeavyApp extends Application {
 
    private Stage primaryStage;
    private Scene primaryScene;
    private App app;
    public HeavyApp(App app1){
    this.app=app1;
    }
    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        Button backButton = new Button("");
        String imgPath=getClass().getResource("backBtn.png").toExternalForm();
        Image backImg=new Image(imgPath);
        ImageView backImageView=new ImageView(backImg);
        backImageView.setFitWidth(30);
        backImageView.setFitHeight(30);
        // backButton.getStyleClass().add("backButtonStyle");
        backButton.setGraphic(backImageView);
        // backButton.setStyle("-fx-padding:5px");
        backButton.setTranslateX(-15);
        backButton.setTranslateY(-15);
        backButton.setOnAction(e -> {
            app.properApp();
        });
        HBox btnBox=new HBox(backButton);

        VBox mainLayout = new VBox();
        mainLayout.setStyle("-fx-background-color: #1C1C1C; -fx-padding: 10;");
        mainLayout.setAlignment(Pos.CENTER);

        StackPane topImage = new StackPane();
        topImage.setMinHeight(200);
        topImage.setMinWidth(200);

        String imagePath = getClass().getResource("/images/intro.jpg").toExternalForm();
        topImage.setStyle(
                "-fx-background-image: url('" + imagePath + "');" +
                        "-fx-background-size: contain;" +
                        "-fx-background-position: center center;" +
                        "-fx-background-repeat: no-repeat;" +
                        "-fx-border-color: #333;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 5;");

        VBox textOverlay = new VBox();
        textOverlay.setPadding(new Insets(10));
        textOverlay.setSpacing(10);
        textOverlay.setAlignment(Pos.CENTER);

        Text title = new Text("Intermediate");
        title.setFont(Font.font("Arial", 20));
        title.setFill(Color.WHITE);

        Text subtitle = new Text("10-20 min --- 30 days left");
        subtitle.setFont(Font.font("Arial", 14));
        subtitle.setFill(Color.LIGHTGRAY);

        textOverlay.getChildren().addAll(title, subtitle);
        topImage.getChildren().add(textOverlay);

        VBox buttonsContainer = new VBox(10);
        buttonsContainer.setPadding(new Insets(13));
        buttonsContainer.setAlignment(Pos.CENTER);
        buttonsContainer.setStyle("-fx-background-color: #1C1C1C");

        for (int i = 1; i <= 30; i++) {

            if (i % 7 == 1) {
                Text weekText = new Text("WEEK " + ((i / 7) + 1));
                weekText.setStyle("-fx-fill: green; -fx-font-size: 18;");
                buttonsContainer.getChildren().add(weekText);
            }

            TextFlow textFlow = new TextFlow();

            Text dayText = new Text("DAY " + i + "\n");
            dayText.setStyle("-fx-fill: white; -fx-font-size: 16");

            Text exerciseText = new Text((i % 4 == 0) ? "Rest day " : "12 exercises");
            exerciseText.setStyle("-fx-fill: red; -fx-font-size: 14");

            textFlow.getChildren().addAll(dayText, exerciseText);

            Button dayButton = new Button();
            dayButton.setGraphic(textFlow);
            dayButton.setMaxHeight(50);
            dayButton.setMaxWidth(400);
            dayButton.setStyle(
                    "-fx-background-color: #333; " +
                            "-fx-text-fill: white; " +
                            "-fx-font-size: 16; " +
                            "-fx-border-radius: 1;" +
                            "-fx-alignment: center-left");

            int finalDay = i;
            dayButton.setOnAction(e -> openDayScene(finalDay));

            buttonsContainer.getChildren().addAll(dayButton);
        }

        ScrollPane scrollPane = new ScrollPane(buttonsContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:  #1C1C1C;");

        mainLayout.getChildren().addAll(btnBox,topImage, scrollPane);

        primaryScene = new Scene(mainLayout, 400, 600);
        primaryStage.setTitle("Fitness App");
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    private void openDayScene(int day) {

        VBox dayLayout = new VBox(20);
        dayLayout.setStyle("-fx-background-color: #1C1C1C; -fx-padding: 20;");
        dayLayout.setAlignment(Pos.CENTER);

        Button backBtn = new Button("");
        String imgPath=getClass().getResource("backBtn.png").toExternalForm();
        Image backImg=new Image(imgPath);
        ImageView backImageView=new ImageView(backImg);
        backImageView.setFitWidth(30);
        backImageView.setFitHeight(30);
        backBtn.setGraphic(backImageView);
         backBtn.setOnAction(e -> {
           start(primaryStage);
         });

        Text videoText = new Text("DAY " + day);
        videoText.setStyle("-fx-fill: white; -fx-font-size: 22");

        Text title = new Text("Intermediate    10 MIN       135 KCAL  ");
        title.setStyle("-fx-fill: pink; -fx-font-size: 18");

        Text videoText1 = new Text("Exercises 12");
        videoText1.setStyle("-fx-fill: yellow; -fx-font-size: 16");

        VBox nextVBox = new VBox(10);
        nextVBox.setPadding(new Insets(13));
        nextVBox.setAlignment(Pos.CENTER);
        nextVBox.setStyle("-fx-background-color: #1C1C1C");

        for (int i = 1; i <= 12; i++) {

            // System.out.println("/images/gujjar"+ i +".jpg");
            String imagePath1 = getClass().getResource("/images/gujjar" + i + ".jpg").toExternalForm();
            // String imagePath1 =
            // getClass().getResource("/video/hasham.mp4").toExternalForm();
            Image video1 = new Image(imagePath1);
            ImageView imageView = new ImageView(video1);
            // MediaView videoView1 = new MediaView(videoPlay1);

            imageView.setFitWidth(150);
            imageView.setFitHeight(120);
            imageView.setPreserveRatio(true);
            imageView.setStyle("-fx-border-radius: 50; -fx-border: 3");

            // imageView.setOnReady(() -> {
            // videoPlay1.play();
            // });

            Text textFront = new Text("Exercise " + i);
            textFront.setStyle("-fx-fill: white; -fx-font-size: 16");

            HBox hBox = new HBox(20, imageView, textFront);
            int number = i;
            // loopReturn(number);
            hBox.setOnMouseClicked(e -> {
                videoDisplay(dayLayout, "abs1.mp4", primaryScene);
            });
            hBox.setAlignment(Pos.CENTER);

            nextVBox.getChildren().addAll(hBox);

        }

        ScrollPane scrollPane = new ScrollPane(nextVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:  #1C1C1C;");

        dayLayout.getChildren().addAll(backBtn, videoText, title, videoText1, scrollPane);

        Scene dayScene = new Scene(dayLayout, 400, 600);

        primaryStage.setScene(dayScene);
        primaryStage.show();
    }

    public void loopReturn(int i) {
        System.out.println("i" + i);
    }

    public void videoDisplay(VBox layout, String videoPath, Scene scene1) {
        // Main StackPane to layer content
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(layout); // Add the original layout as the base
    
        // Video popup VBox
        VBox videoBox = new VBox();
        videoBox.setStyle("-fx-background-color:white;");
        videoBox.getStyleClass().add("videoBox");
        videoBox.setPrefSize(300, 300);
        videoBox.setAlignment(Pos.TOP_CENTER);
        videoBox.setTranslateY(200);
        videoBox.setSpacing(10);
    
        // Media setup
        Platform.runLater(() -> {
            try {
                // Ensure Media is properly initialized
                Media absMedia = new Media(getClass().getResource("/video/" + videoPath).toExternalForm());
                MediaPlayer absMediaPlayer = new MediaPlayer(absMedia);
                MediaView absMediaView = new MediaView(absMediaPlayer);
                absMediaPlayer.setOnEndOfMedia(() -> absMediaPlayer.seek(Duration.ZERO));  // Loop video
                absMediaView.setFitHeight(200);
                absMediaView.setFitWidth(350);
    
                absMediaPlayer.setOnError(() -> {
                    System.out.println("Error loading video: " + absMediaPlayer.getError().getMessage());
                });
    
                absMediaView.setOnMouseClicked(j -> {
                    if (absMediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                        absMediaPlayer.pause();
                    } else {
                        absMediaPlayer.play();
                    }
                });
    
                // Stopwatch setup
                Label stopWatchLabel = new Label("StopWatch");
                stopWatchLabel.setId("stopWatchLabel");
                Label timerLabel = new Label("00:00");
                timerLabel.setStyle("-fx-font-size: 18; -fx-text-fill: #333;");
    
                Button startButton = new Button("Start");
                Button stopButton = new Button("Stop");
                Button resetButton = new Button("Reset");
    
                HBox timerControls = new HBox(10, startButton, stopButton, resetButton);
                timerControls.setAlignment(Pos.CENTER);
    
                // Stopwatch logic
                final int[] seconds = {0};
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                    seconds[0]++;
                    timerLabel.setText(formatTime(seconds[0]));
                }));
                timeline.setCycleCount(Timeline.INDEFINITE);
    
                startButton.setOnAction(e -> timeline.play());
                stopButton.setOnAction(e -> timeline.pause());
                resetButton.setOnAction(e -> {
                    timeline.pause();
                    seconds[0] = 0;
                    timerLabel.setText("00:00");
                });
    
                // Close button
                Button closeButton = new Button("Close");
                closeButton.setStyle("-fx-background-color: #FF6F61; -fx-text-fill: white; -fx-font-size: 14;");
                closeButton.setOnAction(event -> {
                    absMediaPlayer.stop();
                    stackPane.getChildren().remove(videoBox);
                });
    
                videoBox.getChildren().addAll(absMediaView, stopWatchLabel, timerLabel, timerControls, closeButton);
    
                stackPane.getChildren().add(videoBox);
                System.out.println(layout.getScene());
                // Update the scene with the new video content
                Scene scene = scene1;
                if (scene != null) {
                    scene.setRoot(stackPane);
                    primaryStage.setScene(scene);    
                } else {
                    System.out.println("Error: The layout is not attached to a Scene!");
                }
    
                // Play the video when ready
                absMediaPlayer.setOnReady(() -> absMediaPlayer.play());
            } catch (Exception e) {
                System.out.println("Failed to load video: " + e.getMessage());
            }
        });
    }
    

    public String formatTime(int seconds2) {
        int minutes = seconds2 / 60;
        int seconds = seconds2 % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

   
}

   
