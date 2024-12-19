package com.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class VideoHandler extends Application {
    Stage primaryStage;
    App app;

    public VideoHandler(App app1) {
        this.app = app1;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        // videoMenu();
        newMenu();
    }

    public void newMenu() {
        Button backButton = new Button("");

        backButton.getStyleClass().add("backButtonStyle");
        backButton.setTranslateX(-65);
        backButton.setTranslateY(-20);
        backButton.setOnAction(e -> {
            app.properApp();
        });

        Label title = new Label("Muscle-Area Training");
        HBox topBar = new HBox(backButton, title);
        title.getStyleClass().add("title");

        // HBox topBar = new HBox(title);
        topBar.getStyleClass().add("top-bar");

        // ListView for exercises
        ListView<HBox> listView = new ListView<>();
        listView.getStyleClass().add("exercise-list");

        // Add items to the ListView
        listView.getItems().addAll(
            createExerciseItem("Abs", 3, "abs.png"),
            createExerciseItem("Back", 2, "back muscle.png"),
            createExerciseItem("Arms(Biceps,Triceps)", 24, "muscle.png"),
            createExerciseItem("Calf", 3, "calf.png"),
            createExerciseItem("Chest", 3, "chestmuscle.jpg"),
            createExerciseItem("Shoulder", 3, "shoulder.png")); 
        

        // Layout
        BorderPane root6 = new BorderPane();
        root6.setTop(topBar);
        root6.setCenter(listView);
        root6.setStyle("-fx-background-color: #FF6F61;");
        // Scene and Stage
        Scene scene = new Scene(root6, 400, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Muscle Area Training");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createExerciseItem(String muscleGroup, int exercises, String iconPath) {
        Label icon = new Label();
        String imagePath = getClass().getResource("/com/example/" + iconPath).toExternalForm();

        Image menuImg = new Image(imagePath);
        ImageView menuView = new ImageView(menuImg);
        menuView.setFitHeight(50);
        menuView.setFitWidth(50);
        icon.setGraphic(menuView);

        Label text = new Label(muscleGroup + " - " + exercises + " exercises");
        text.getStyleClass().add("exercise-text");

        HBox item = new HBox(icon, text);
        item.setOnMouseClicked(i -> {
            handleExerciseClick(muscleGroup);
        });
        item.getStyleClass().add("exercise-item");
        return item;
    }

    private void handleExerciseClick(String muscleGroup) {
        switch (muscleGroup) {
            case "Abs":
                absMenu();
                break;
            case "Back":
                backMenu();
                break;
            case "Arms(Biceps,Triceps)":
                armMenu();
                break;
            case "Calf":
                calfMenu();
                break;
            case "Chest":
                chestMenu();
                break;
            case "Shoulder": 
                 shoulderMenu();
                break;
            default:
                System.out.println("No videos available for " + muscleGroup);
        }
    }
    

    public void absMenu() {
        Button backButton = new Button("");

        backButton.getStyleClass().add("backButtonStyle");
        backButton.setTranslateX(-100);
        backButton.setTranslateY(-20);
        backButton.setOnAction(e -> {
            newMenu();
        });

        Label absLabel = new Label("Abs Exercises");
        HBox topHBox = new HBox(backButton, absLabel);
        absLabel.getStyleClass().add("title");
        topHBox.getStyleClass().add("top-bar");

        BorderPane borderLayout = new BorderPane();

        ListView<HBox> listView = new ListView<>();
        listView.getStyleClass().add("exercise-list");

        borderLayout.setTop(topHBox);
        borderLayout.setCenter(listView);
        borderLayout.setStyle("-fx-background-color: #FF6F61;");

        Scene scene = new Scene(borderLayout, 400, 600);
        listView.getItems().addAll(
                helperMethod("abs.png", "Exercise 1", "abs2.mp4", borderLayout, scene),
                helperMethod("abs.png", "Exercise 2", "abs1.mp4", borderLayout, scene),
                helperMethod("abs.png", "Exercise 3", "abs3.mp4", borderLayout, scene));
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Abs Training");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void backMenu() {
        Button backButton = new Button("");

        backButton.getStyleClass().add("backButtonStyle");
        backButton.setTranslateX(-100);
        backButton.setTranslateY(-20);
        backButton.setOnAction(e -> {
            newMenu();
        });

        Label absLabel = new Label("Back Exercises");
        HBox topHBox = new HBox(backButton, absLabel);
        absLabel.getStyleClass().add("title");
        topHBox.getStyleClass().add("top-bar");

        BorderPane borderLayout = new BorderPane();

        ListView<HBox> listView = new ListView<>();
        listView.getStyleClass().add("exercise-list");

        borderLayout.setTop(topHBox);
        borderLayout.setCenter(listView);
        borderLayout.setStyle("-fx-background-color: #FF6F61;");

        Scene scene = new Scene(borderLayout, 400, 600);
        listView.getItems().addAll(
                helperMethod("back muscle.png", "Exercise 1", "tricep1.mp4", borderLayout, scene),
                helperMethod("back muscle.png", "Exercise 2", "backEx2.mp4", borderLayout, scene)
        // absHelperMethod("back muscle.png", "Exercise 3", "abs3.mp4", borderLayout,
        // scene)
        );
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Back Training");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void calfMenu() {
        Button backButton = new Button("");

        backButton.getStyleClass().add("backButtonStyle");
        backButton.setTranslateX(-100);
        backButton.setTranslateY(-20);
        backButton.setOnAction(i -> {
            newMenu();
        });

        Label absLabel = new Label("Legs Exercises");
        HBox topHBox = new HBox(backButton, absLabel);
        absLabel.getStyleClass().add("title");
        topHBox.getStyleClass().add("top-bar");

        BorderPane borderLayout = new BorderPane();

        ListView<HBox> listView = new ListView<>();
        listView.getStyleClass().add("exercise-list");

        borderLayout.setTop(topHBox);
        borderLayout.setCenter(listView);
        borderLayout.setStyle("-fx-background-color: #FF6F61;");

        Scene scene = new Scene(borderLayout, 400, 600);
        listView.getItems().addAll(
                helperMethod("calf.png", "Exercise 1", "calfEx1.mp4", borderLayout, scene),
                helperMethod("calf.png", "Exercise 2", "calfEx2.mp4", borderLayout, scene),
                helperMethod("calf.png", "Exercise 3", "legsEx1.mp4", borderLayout, scene)
              
                );
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Calf Training");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void armMenu() {
        Button backButton = new Button("");

        backButton.getStyleClass().add("backButtonStyle");
        backButton.setTranslateX(-100);
        backButton.setTranslateY(-20);
        backButton.setOnAction(i -> {
            newMenu();
        });

        Label absLabel = new Label("arms Exercises");
        HBox topHBox = new HBox(backButton, absLabel);
        absLabel.getStyleClass().add("title");
        topHBox.getStyleClass().add("top-bar");

        BorderPane borderLayout = new BorderPane();

        ListView<HBox> listView = new ListView<>();
        listView.getStyleClass().add("exercise-list");

        borderLayout.setTop(topHBox);
        borderLayout.setCenter(listView);
        borderLayout.setStyle("-fx-background-color: #FF6F61;");

        Scene scene = new Scene(borderLayout, 400, 600);
        listView.getItems().addAll(

                // arms data here
                helperMethod("muscle.png", "Exercise 1", "tricep1.mp4", borderLayout, scene),
                helperMethod("muscle.png", "Exercise 2", "tricep2.mp4", borderLayout, scene),
                helperMethod("muscle.png", "Exercise 3", "tricep3.mp4", borderLayout, scene),
                helperMethod("muscle.png", "Hammer curl", "hammerCurl.mp4", borderLayout, scene),
                helperMethod("muscle.png", "Exercise 5", "bicep2.mp4", borderLayout, scene));
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Arm Training");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void chestMenu() {
        Button backButton = new Button("");
    
        backButton.getStyleClass().add("backButtonStyle");
        backButton.setTranslateX(-100);
        backButton.setTranslateY(-20);
        backButton.setOnAction(i -> {
            newMenu();
        });
    
        Label absLabel = new Label("Chest Exercises");
        HBox topHBox = new HBox(backButton, absLabel);
        absLabel.getStyleClass().add("title");
        topHBox.getStyleClass().add("top-bar");
    
        BorderPane borderLayout = new BorderPane();
    
        ListView<HBox> listView = new ListView<>();
        listView.getStyleClass().add("exercise-list");
    
        borderLayout.setTop(topHBox);
        borderLayout.setCenter(listView);
        borderLayout.setStyle("-fx-background-color: #FF6F61;");
    
        Scene scene = new Scene(borderLayout, 400, 600);
        listView.getItems().addAll(
                helperMethod("chestmuscle.jpg", "Exercise 1", "chestEx1.mp4", borderLayout, scene),
                helperMethod("chestmuscle.jpg", "Exercise 2", "chestEx2.mp4", borderLayout, scene),
                helperMethod("chestmuscle.jpg", "Exercise 3", "chestEx3.mp4", borderLayout, scene));
    
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Chest Training");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void shoulderMenu() {
        Button backButton = new Button("");
    
        backButton.getStyleClass().add("backButtonStyle");
        backButton.setTranslateX(-80);
        backButton.setTranslateY(-20);
        backButton.setOnAction(i -> {
            newMenu();
        });
    
        Label absLabel = new Label("Shoulder Exercises");
        HBox topHBox = new HBox(backButton, absLabel);
        absLabel.getStyleClass().add("title");
        topHBox.getStyleClass().add("top-bar");
    
        BorderPane borderLayout = new BorderPane();
    
        ListView<HBox> listView = new ListView<>();
        listView.getStyleClass().add("exercise-list");
    
        borderLayout.setTop(topHBox);
        borderLayout.setCenter(listView);
        borderLayout.setStyle("-fx-background-color: #FF6F61;");
    
        Scene scene = new Scene(borderLayout, 400, 600);
        listView.getItems().addAll(
                helperMethod("shoulder.png", "Exercise 1", "shoulderEx1.mp4", borderLayout, scene),
               
                helperMethod("shoulder.png", "Exercise 3", "chestEx3.mp4", borderLayout, scene));
    
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Shoulder Training");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public HBox helperMethod(String iconPath, String exercise, String videoPath, BorderPane pane, Scene scene) {
        Label icon = new Label();
        String imgPath = getClass().getResource("/com/example/" + iconPath).toExternalForm();
        Image absMenuImage = new Image(imgPath);
        ImageView absMenuView = new ImageView(absMenuImage);
        absMenuView.setFitHeight(50);
        absMenuView.setFitWidth(50);
        icon.setGraphic(absMenuView);

        Label text = new Label(exercise);
        text.getStyleClass().add("exercise-text");

        HBox item = new HBox(icon, text);
        item.setOnMouseClicked(e -> {
            videoDisplay(pane, videoPath, scene);
        });
        item.getStyleClass().add("exercise-item");
        return item;
    }

    public void videoDisplay(BorderPane layout, String videoPath, Scene scene1) {
        // Main StackPane to layer content
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(layout); // Add the original layout as the base

        // Video popup VBox
        VBox videoBox = new VBox();
      
        videoBox.getStyleClass().add("videoBox");
        videoBox.setPrefSize(300, 300);
        videoBox.setAlignment(Pos.TOP_CENTER);
        videoBox.setTranslateY(200); // Position the popup at translateY 200
        videoBox.setSpacing(10);

        // Media setup
        Media absMedia = new Media(getClass().getResource("/video/" + videoPath).toExternalForm());
        MediaPlayer absMediaPlayer = new MediaPlayer(absMedia);
        MediaView absMediaView = new MediaView(absMediaPlayer);
        absMediaPlayer.setOnEndOfMedia(() -> absMediaPlayer.seek(Duration.ZERO));
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
        final int[] seconds = { 0 };
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

        // Replace the existing layout with the stack pane
        Scene scene = scene1;
        if (scene != null) {
            scene.setRoot(stackPane);
        } else {
            System.out.println("Error: The layout is not attached to a Scene!");
        }
        absMediaPlayer.setOnReady(() -> {
            absMediaPlayer.play();
        });
        Platform.runLater(() -> absMediaPlayer.play());

    }

    public String formatTime(int seconds2) {
        int minutes = seconds2 / 60;
        int seconds = seconds2 % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
