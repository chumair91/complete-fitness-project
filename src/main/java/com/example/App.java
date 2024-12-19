package com.example;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.util.Duration;

import org.json.JSONObject;

public class App extends Application {
  Sound soundClass = new Sound();
  NewFile dataFile = new NewFile();
  ArrayList<UserData> userInfo = new ArrayList<>();

  private Stage primaryStage;

  @Override
  public void start(Stage primaryStage) throws IOException {
    this.primaryStage = primaryStage;
    // primaryStage.setFullScreen(true);
    showLoginScreen();
  }

  public void showLoginScreen() {
    // GridPane layout = new GridPane();
    VBox layout = new VBox();
    layout.getStyleClass().add("root1");
    layout.setSpacing(8);

    // Apply the CSS class
    // Welcome
    // Label wlcmLabel=new Label("Welcome to fitness journey");
    Image newWlcmImg = new Image(getClass().getResource("homepage.png").toExternalForm());
    ImageView newImageView = new ImageView(newWlcmImg);
    newImageView.setFitHeight(350);

    // HBox wlcmBox=new HBox(newImageView);
    // wlcmBox.setAlignment(Pos.CENTER);
    VBox.setMargin(newImageView, new Insets(10, -45, 0, 90));

    layout.setAlignment(Pos.CENTER);

    // Healthy text Label
    Label healthyLabel1 = new Label("Make");
    Label healthyLabel2 = new Label("Your Body");
    Label healthyLabel3 = new Label("Healthy & Fit");

    healthyLabel1.setId("healthyLabel1");
    healthyLabel2.setId("healthyLabel2");
    healthyLabel3.setId("healthyLabel3");
    // add in Hbox
    HBox healthyBox = new HBox();
    healthyBox.getChildren().addAll(healthyLabel1, healthyLabel2);
    healthyBox.setSpacing(10);
    healthyBox.setAlignment(Pos.CENTER);
    VBox.setMargin(healthyBox, new Insets(0, 0, -20, 0));

    // timeline
    Timeline timeline = new Timeline(
        new KeyFrame(Duration.ZERO, e -> healthyLabel1.setStyle("-fx-text-fill: orange")),
        new KeyFrame(Duration.seconds(2), e -> healthyLabel1.setStyle("-fx-text-fill: black ")),
        new KeyFrame(Duration.seconds(4), e -> healthyLabel1.setStyle("-fx-text-fill: orange")));
    timeline.setCycleCount(Timeline.INDEFINITE); // Repeat the animation
    timeline.play();

    // Timeline for healthyLabel2
    Timeline timeline1 = new Timeline(
        new KeyFrame(Duration.ZERO, e -> healthyLabel2.setStyle("- -fx-text-fill: Black")),
        new KeyFrame(Duration.seconds(2), e -> healthyLabel2.setStyle(" -fx-text-fill:Grey")),
        new KeyFrame(Duration.seconds(4), e -> healthyLabel2.setStyle("-fx-text-fill: Black"))

    );
    timeline1.setCycleCount(Timeline.INDEFINITE); // Repeat the animation
    timeline1.play();

    // Timeline for healthyLabel3
    Timeline timeline2 = new Timeline(
        new KeyFrame(Duration.ZERO, e -> healthyLabel3.setStyle("-fx-text-fill: Blue")),
        new KeyFrame(Duration.seconds(2), e -> healthyLabel3.setStyle("-fx-text-fill:orange")),
        new KeyFrame(Duration.seconds(4), e -> healthyLabel3.setStyle("-fx-text-fill: Blue"))

    );
    timeline2.setCycleCount(Timeline.INDEFINITE); // Repeat the animation
    timeline2.play();

    Button getStartedBtn = new Button("Get Started");
    getStartedBtn.setId("getStartedBtn");

    getStartedBtn.setOnAction(o -> {
      soundClass.soundRun();
      AcessPage();
    });

    layout.getChildren().addAll(newImageView, healthyBox, healthyLabel3, getStartedBtn);
    // VBox.setMargin(btnHBox, new Insets(0,0,0,50));
    Image logoimage = new Image(
        "file:C://Users//DELL//Documents//java ntoepad//table_tut2//src//main//resources//com//example//logo.jpg");

    // help button on action
    Scene scene1 = new Scene(layout, 350, 600);

    scene1.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    primaryStage.getIcons().add(logoimage);
    primaryStage.setScene(scene1);
    primaryStage.setTitle("Background Image Example");
    primaryStage.show();

    // boolean isFullScreen = primaryStage.isFullScreen();

    // primaryStage.setFullScreen(isFullScreen);
  }

  public void AcessPage() {
    VBox accessVBox = new VBox();
    // back Button
    Button backButton = new Button("");
    backButton.getStyleClass().add("backButtonStyle");
    backButton.setOnAction(e -> showLoginScreen());
    // Signin Label
    Label signInLabel1 = new Label("Sign In");
    Label signInLabel2 = new Label("To Your");
    Label signInLabel3 = new Label("Account");

    signInLabel1.setId("signInLabel1");
    signInLabel2.setId("signInLabel2");
    signInLabel3.setId("signInLabel3");
    // VBox.setMargin(signInLabel3, new Insets(-20, 0, 0, 65));
    // add label to hbox
    HBox signInLabelBox = new HBox();
    signInLabelBox.getChildren().addAll(signInLabel1, signInLabel2);
    signInLabelBox.setAlignment(Pos.CENTER);
    signInLabelBox.setSpacing(10);
    // account Hbox
    HBox accountLabelBox = new HBox(signInLabel3);
    accountLabelBox.setAlignment(Pos.CENTER);
    // login method label
    Label loginMethodLabel = new Label("login with the following methods");
    loginMethodLabel.setId("loginMethodLabel");
    //
    HBox loginLabelHbox = new HBox(loginMethodLabel);
    loginLabelHbox.setAlignment(Pos.CENTER);

    // login buttons
    Button googleButton = new Button("");
    Button facebookButton = new Button("");
    Button githubButton = new Button("");

    // adding button to hbox
    HBox threeBoxesBtn = new HBox();
    threeBoxesBtn.getChildren().addAll(googleButton, facebookButton, githubButton);
    threeBoxesBtn.setSpacing(20);
    threeBoxesBtn.setAlignment(Pos.CENTER);
    // VBox.setMargin(threeBoxesBtn, new Insets(0, 0, 0, 15));
    // giving class to buttons
    googleButton.getStyleClass().add("loginBtn1");
    facebookButton.getStyleClass().add("loginBtn2");
    githubButton.getStyleClass().add("loginBtn3");

    // Or label
    Label orLabel = new Label("<------OR------>");

    orLabel.setId("orLabel");
    HBox orBox = new HBox();
    orBox.getChildren().add(orLabel);
    orBox.setAlignment(Pos.CENTER);
    VBox.setMargin(orBox, new Insets(15, 0, 0, 0));
    // UserName
    Label nameLabel = new Label("Username");
    TextField nameField = new TextField();
    nameField.setPromptText("Enter your username");
    nameField.setPrefWidth(15);
    nameField.setAlignment(Pos.CENTER);

    HBox nameLabelHBox = new HBox(nameLabel);
    nameLabelHBox.setAlignment(Pos.CENTER);
    // alignment
    nameLabelHBox.setTranslateY(-15);
    nameLabelHBox.setTranslateX(-75);

    nameLabel.setId("nameLabel");

    HBox nameBox = new HBox(nameField);
    nameBox.setAlignment(Pos.TOP_CENTER);
    nameBox.setTranslateY(-25);

    // Password
    Label passLabel = new Label("Password");
    PasswordField passwordField = new PasswordField();
    passwordField.setPromptText("Enter Your Password");
    passLabel.setId("passLabel");

    HBox passLabelBox = new HBox(passLabel);
    passLabelBox.setAlignment(Pos.CENTER);
    passLabelBox.setTranslateY(-25);
    passLabelBox.setTranslateX(-76);

    HBox passBox = new HBox(passwordField);
    passBox.setAlignment(Pos.CENTER);
    passwordField.setAlignment(Pos.CENTER);
    passBox.setTranslateY(-35);

    nameField.getStyleClass().add("text-field");
    passwordField.getStyleClass().add("password-field");

    // Forget Password
    Label forgetLabel = new Label("Forgot Password?");
    forgetLabel.setId("forgetLabel");
    HBox forgetLabelBox = new HBox(forgetLabel);
    forgetLabelBox.setAlignment(Pos.CENTER);
    forgetLabelBox.setTranslateY(-40);
    forgetLabelBox.setTranslateX(85);
    forgetLabel.setOnMouseClicked(e -> {
      ForgetPasswordMethod();
    });

    // Login
    Button signInBtn = new Button("Login");
    signInBtn.setId("signInBtn");
    HBox signInBtnBox = new HBox(signInBtn);
    signInBtnBox.setAlignment(Pos.CENTER);
    signInBtnBox.setTranslateY(-35);

    // SignUp
    Label newMemLabel = new Label("New Member?");
    newMemLabel.setId("newMemLabel");
    newMemLabel.setTranslateX(20);
    Label registerLabel = new Label("Register Now");
    registerLabel.setId("registerLabel");

    Button signUpBtn = new Button("");
    signUpBtn.setId("signUpBtn");

    Image signUpBtnImg = new Image(getClass().getResource("fast-forward.png").toExternalForm());
    ImageView signUpBtnImView = new ImageView(signUpBtnImg);
    signUpBtnImView.setFitWidth(10);
    signUpBtnImView.setFitHeight(10);
    // give img to button
    signUpBtn.setGraphic(signUpBtnImView);
    signUpBtn.setOnAction(e -> signUpScreen());
    HBox signUpBox = new HBox(registerLabel, signUpBtn);
    signUpBox.setSpacing(120);
    signUpBox.setAlignment(Pos.CENTER);

    VBox signUpVbox = new VBox();
    signUpVbox.getChildren().addAll(newMemLabel, signUpBox);
    signUpVbox.getStyleClass().add("signUpVbox");
    signUpVbox.setPrefHeight(110);
    signUpVbox.setAlignment(Pos.CENTER_LEFT);
    signUpVbox.setTranslateY(-30);

    signUpVbox.setPrefWidth(350);
    signUpVbox.setMinWidth(350);
    signUpVbox.setMaxWidth(350);

    // add vbox to hbox to bring it to center
    HBox signUpHBox = new HBox(signUpVbox);
    signUpHBox.setAlignment(Pos.CENTER);

    // Navbar
    HBox navHBox = new HBox();
    Button homeBtn = new Button("");
    Button helpButton = new Button("");
    navHBox.getChildren().addAll(homeBtn, helpButton);
    homeBtn.setId("homeBtn");
    helpButton.setId("helpButton");
    navHBox.setId("navHBox");

    navHBox.setPrefWidth(350);
    navHBox.setMinWidth(350);
    navHBox.setMaxWidth(350);
    navHBox.setTranslateY(-10);
    navHBox.setAlignment(Pos.CENTER);
    navHBox.setSpacing(40);
    HBox navParentHbox = new HBox(navHBox);
    navParentHbox.setAlignment(Pos.CENTER);

    homeBtn.setOnAction(i -> {
      showLoginScreen();
    });
    helpButton.setOnAction(j -> {
      HelpSupport helpSupport = new HelpSupport(this);
      try {
        helpSupport.start(primaryStage);
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });
    // add to main Vbox
    accessVBox.getChildren().addAll(signInLabelBox, accountLabelBox, loginLabelHbox, threeBoxesBtn, orBox,
        nameLabelHBox,
        nameBox, passLabelBox, passBox, forgetLabelBox, signInBtnBox, signUpHBox, navParentHbox);
    accessVBox.setSpacing(10);

    // creating a borderpane
    BorderPane borderLayout = new BorderPane();
    borderLayout.setTop(backButton);
    borderLayout.setCenter(accessVBox);

    borderLayout.requestLayout();

    signInBtn.setOnAction(e -> {

      String name = nameField.getText().trim();
      String pass = passwordField.getText().trim();
      if (name.contains("~") || pass.contains("~")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Violation");
        alert.setHeaderText("Invalid Entry");
        alert.setContentText("The symbol '~' is not allowed in any field.");
        alert.showAndWait();
        return;
      }
      try {
        if (dataFile.checkInFile(name, pass)) {
          properApp();
        } else {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("wrong entry");
          alert.setHeaderText("Incorrect username or password");
          alert.setContentText("Please try again");
          alert.showAndWait();
        }
      } catch (Exception j) {
        System.out.println(j.getMessage());
      }

    });

    // Set on Action on Sign Up button
    signUpBtn.setOnAction(e -> {
      signUpScreen();
    });
    borderLayout.setStyle("-fx-background-color:#272727");

    Scene accessScene = new Scene(borderLayout, 350, 630);

    accessScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    primaryStage.setTitle("Access Page");
    primaryStage.show();
    primaryStage.setScene(accessScene);
    // primaryStage.setFullScreen(true);
  }

  public void ForgetPasswordMethod() {
    VBox forgetVbox = new VBox(15);
    forgetVbox.setAlignment(Pos.CENTER);
    forgetVbox.setPadding(new Insets(20)); // Add padding around the VBox
    forgetVbox.setStyle("-fx-background-color: linear-gradient(to bottom, #ece9e6, #ffffff);"); // Gradient background

    // Back button
    Button backButton = new Button("");
    // backButton.getStyleClass().add("backButtonStyle");
    backButton.setOnAction(e -> AcessPage());
   
    String imgPath=getClass().getResource("backBtn.png").toExternalForm();
    Image backImg=new Image(imgPath);
    ImageView backImageView=new ImageView(backImg);
    backImageView.setFitWidth(30);
    backImageView.setFitHeight(30);
    backButton.setGraphic(backImageView);

    // Email label and field
    Label emailforgetLabel = new Label("Email");
    emailforgetLabel.getStyleClass().add("newLabelStyling");
    emailforgetLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

    TextField emailForget = new TextField();
    emailForget.setPromptText("Enter your email");
    emailForget.setMaxWidth(250); // Control field width
    emailForget.setStyle("-fx-border-color: #0078D7; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-padding: 5px;");

    // Movie label and field
    Label movieLabel = new Label("Which was your Favourite childhood movie?");
    movieLabel.getStyleClass().add("newLabelStyling");
    movieLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

    TextField movieField = new TextField();
    movieField.setPromptText("Enter the movie name here");
    movieField.setMaxWidth(250); // Control field width
    movieField.setStyle("-fx-border-color: #0078D7; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-padding: 5px;");

    // School label and field
    Label schoolLabel = new Label("Plz Tell Us Name of Your First School?");
    schoolLabel.getStyleClass().add("newLabelStyling");
    schoolLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

    TextField schoolField = new TextField();
    schoolField.setPromptText("Enter the school name here");
    schoolField.setMaxWidth(250); // Control field width
    schoolField.setStyle("-fx-border-color: #0078D7; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-padding: 5px;");

    // Reset button
    Button resetBtn = new Button("Proceed");
    resetBtn.setMaxWidth(200); // Control button width
    resetBtn.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-background-radius: 5px;");

    resetBtn.setOnKeyPressed(event -> {
        if (event.getCode() == KeyCode.ENTER) {
            String email = emailForget.getText().trim();
            String school = schoolField.getText().trim();
            String movie = movieField.getText().trim();
            if (dataFile.checkUser(email, school, movie)) {
                ResetPassword(email);
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("NO User registered");
                alert.setHeaderText("NO User found");
                alert.setContentText("You are not registered in our database");
                alert.showAndWait();
            }
        }
    });
    resetBtn.setOnAction(e -> {
        String email = emailForget.getText().trim();
        String school = schoolField.getText().trim();
        String movie = movieField.getText().trim();
        if (dataFile.checkUser(email, school, movie)) {
            ResetPassword(email);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NO User registered");
            alert.setHeaderText("NO User found");
            alert.setContentText("You are not registered in our database");
            alert.showAndWait();
        }
    });

    // Add all components to the VBox
    forgetVbox.getChildren().addAll(
        backButton,
        emailforgetLabel, emailForget,
        schoolLabel, schoolField,
        movieLabel, movieField,
        resetBtn
    );

    // Set the scene and stage
    Scene scene = new Scene(forgetVbox, 400, 600);
    scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
    primaryStage.setTitle("Forget Password");
    primaryStage.setScene(scene);
    primaryStage.show();
}


  public void ResetPassword(String email) {
    VBox resVBox = new VBox();
    resVBox.setSpacing(10);
    resVBox.setAlignment(Pos.CENTER);
    String newEmail = email;
    // button
    Button backButton = new Button("");
   
    String imgPath=getClass().getResource("backBtn.png").toExternalForm();
    Image backImg=new Image(imgPath);
    ImageView backImageView=new ImageView(backImg);
    backImageView.setFitWidth(30);
    backImageView.setFitHeight(30);
    backButton.setGraphic(backImageView);
    backButton.setOnAction(e -> AcessPage());

    Label newPasswordLabel = new Label("New Password");
    newPasswordLabel.getStyleClass().add("newLabelStyling");
    TextField newPasswordField = new TextField();
    newPasswordField.setMaxWidth(200);
    newPasswordField.setPromptText("Enter a new password");

    Label confirmPasswordLabel = new Label("Confirm Password");
    confirmPasswordLabel.getStyleClass().add("newLabelStyling");
    TextField confirmPasswordField = new TextField();
    confirmPasswordField.setMaxWidth(200);
    confirmPasswordField.setPromptText("confirm password");
    Button proceedButton = new Button("Proceed");
    resVBox.getChildren().addAll(backButton, newPasswordLabel, newPasswordField, confirmPasswordLabel,
        confirmPasswordField, proceedButton);

    proceedButton.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ENTER) {
        String newPass = newPasswordField.getText().trim();
        String confirmPass = confirmPasswordField.getText().trim();
        if (newPass.equals(confirmPass)) {
          dataFile.updataPassword(newEmail, newPass);
          // alert
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("Password Changed");
          alert.setHeaderText("Password Changed Successfully");
          alert.setContentText("Your password has been changed successfully");
          alert.showAndWait();

        } else {
          Alert alert = new Alert(AlertType.ERROR);
          alert.setTitle("Password Mismatch");
          alert.setHeaderText("Password Mismatch");
          alert.setContentText("Passwords do not match");
          alert.showAndWait();
        }
      }
    });

    proceedButton.setOnAction(m -> {
      String newPass = newPasswordField.getText().trim();
      String confirmPass = confirmPasswordField.getText().trim();
      if (newPass.equals(confirmPass)) {
        dataFile.updataPassword(newEmail, newPass);
        // alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Password Changed");
        alert.setHeaderText("Password Changed Successfully");
        alert.setContentText("Your password has been changed successfully");
        alert.showAndWait();
        AcessPage();
      } else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Password Mismatch");
        alert.setHeaderText("Password Mismatch");
        alert.setContentText("Passwords do not match");
        alert.showAndWait();
      }
    });
    Scene scene = new Scene(resVBox, 350, 600);
    scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
    primaryStage.setTitle("Reset Password");
    primaryStage.setScene(scene);
    primaryStage.show();

  }

  public void signUpScreen() {
    VBox newLayout = new VBox();
    newLayout.setSpacing(20);
    // newLayout.getStyleClass().add("root2");
    newLayout.setStyle("-fx-background-color:#CD9F61;");

    Button backButton = new Button("");
    backButton.getStyleClass().add("backButtonStyle");
    backButton.setOnAction(e -> AcessPage());

    // Create a Title Bar with the Back Button
    HBox topBar = new HBox();

    topBar.setSpacing(10); // Add some spacing if needed
    topBar.setPadding(new Insets(10, 10, 10, 10)); // Padding for the top bar
    topBar.getChildren().add(backButton);

    // Name
    Label nameClient = new Label("Name");
    nameClient.getStyleClass().add("newLabelStyling");
    TextField nameClientField = new TextField();
    nameClientField.setPromptText("Enter your username");

    // hbox for name
    HBox nameLabelBox = new HBox(nameClient);
    HBox nameBox = new HBox(nameClientField);
    nameBox.setSpacing(10);
    nameLabelBox.setAlignment(Pos.CENTER);
    nameBox.setAlignment(Pos.CENTER);

    // password
    Label passwordLabel1 = new Label("Password");
    passwordLabel1.getStyleClass().add("newLabelStyling");
    // passwordLabel1.setStyle("-fx-text-fill:black;");
    // passwordLabel1.setId("passwordLabel1");
    PasswordField passwordField1 = new PasswordField();
    passwordField1.setPromptText("Enter your new passsword");

    HBox passwordLabelBox = new HBox(passwordLabel1);
    HBox passwordBox = new HBox(passwordField1);
    passwordLabelBox.setAlignment(Pos.CENTER);
    passwordBox.setAlignment(Pos.CENTER);
    // Email
    Label emailClient = new Label("Email");
    emailClient.getStyleClass().add("newLabelStyling");
    TextField emailClientField = new TextField();
    emailClientField.setPromptText("Enter your email");

    HBox emailLabelBox = new HBox(emailClient);
    HBox emailBox = new HBox(emailClientField);
    emailBox.setSpacing(10);
    emailLabelBox.setAlignment(Pos.CENTER);
    emailBox.setAlignment(Pos.CENTER);

    // Label for identification
    Label idenLabel = new Label("These are the Questions For Password Recovery Next Time");
    
    HBox idenBox = new HBox(idenLabel);
    idenLabel.setStyle("-fx-background-color:yellow; -fx-font-size:14px; -fx-font-weight: bold;");
    idenBox.setAlignment(Pos.CENTER);

    Label schoolLabel = new Label("what was the name of your first high School?");
    schoolLabel.getStyleClass().add("newLabelStyling");
    HBox schoolBox = new HBox(schoolLabel);
    schoolBox.setAlignment(Pos.CENTER);

    TextField schoolField = new TextField();
    schoolField.setPromptText("movie name here");
    HBox schoolFieldBox = new HBox(schoolField);
    schoolFieldBox.setAlignment(Pos.CENTER);

    Label movieLabel = new Label("What is your favorite childhood movie?");
    movieLabel.getStyleClass().add("newLabelStyling");
    
    HBox movieBox = new HBox(movieLabel);
    movieBox.setAlignment(Pos.CENTER);

    TextField movieField = new TextField();
    movieField.setPromptText("movie name here");
    HBox movieFieldBox=new HBox(movieField);
    movieFieldBox.setAlignment(Pos.CENTER);

    // button register
    Button registerButton = new Button("Register");
    registerButton.setStyle("-fx-padding:10px; fx-background-radius:12px; -fx-border-radius:12px;");
    HBox registerBox=new HBox(registerButton);
    registerBox.setAlignment(Pos.CENTER);

    newLayout.getChildren().addAll(topBar, nameLabelBox, nameBox, passwordLabelBox, passwordBox, emailLabelBox,
        emailBox, idenBox, schoolBox, schoolFieldBox,
        movieBox, movieFieldBox, registerBox);

    registerButton.setOnAction(a -> {

      String regName = nameClientField.getText().trim();
      String regPassword = passwordField1.getText().trim();
      String regEmail = emailClientField.getText().trim();
      String school = schoolField.getText().trim();
      String movie = movieField.getText().trim();
      if (regName.isEmpty() || regPassword.isEmpty() || regEmail.isEmpty() || school.isEmpty() || movie.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText("Missing Information");
        alert.setContentText("All fields must be filled out.");
        alert.showAndWait();
        return;
      }
      if (regName.contains("~") || regPassword.contains("~") || regEmail.contains("~") || school.contains("~")
          || movie.contains("~")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Violation");
        alert.setHeaderText("Invalid Entry");
        alert.setContentText("The symbol '~' is not allowed in any field.");
        alert.showAndWait();
        return;
      }
      if (!(dataFile.authenticate(regEmail))) {
        userInfo.add(new UserData(regName, regPassword, regEmail, school, movie));
        dataFile.newFileWriter(userInfo);
        // Alert
        Alert alertRegister = new Alert(Alert.AlertType.CONFIRMATION);
        alertRegister.setTitle("REGISTRATION");
        alertRegister.setHeaderText("MISSION PASSED");
        alertRegister.setContentText("Register successfully");
        alertRegister.showAndWait();
      } else {
        Alert alertRegister = new Alert(Alert.AlertType.ERROR);
        alertRegister.setTitle("REGISTRATION Error");

        alertRegister.setContentText("User already registered");
        alertRegister.showAndWait();
      }

    });

    Scene signUpScene = new Scene(newLayout, 400, 600);
    signUpScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    // System.out.println(getClass().getResource("style.css").toExternalForm());

    // Stage stage = new Stage();
    primaryStage.setScene(signUpScene);
    primaryStage.setTitle("Enter required Data");
    primaryStage.show();

  }

  public void properApp() {
    // Create a VBox for scrollable content
    VBox vBox = new VBox();
    vBox.setAlignment(Pos.TOP_CENTER);
    vBox.setStyle(" -fx-background-color:rgb(17, 17, 28)");
    vBox.setSpacing(10);
    // Back Button
    Button backButton = new Button("<-");
    backButton.getStyleClass().add("backButtonStyle");
    backButton.setOnAction(e -> AcessPage());

    // Main Label
    Label mainLabel = new Label("What's Your \n Main Goal");
    mainLabel.setId("mainLabel");

    // Add Images (Diet Images)
    Image dietImg1 = new Image(getClass().getResource("calCalculator.jpg").toExternalForm());
    ImageView dietImageView1 = new ImageView(dietImg1);
    dietImageView1.setFitHeight(160);
    dietImageView1.setFitWidth(350);

    Label dieLabel1 = new Label("Calorie Calculator");
    StackPane dieStackPane1 = new StackPane();
    dieStackPane1.getChildren().addAll(dietImageView1, dieLabel1);
    dieStackPane1.setOnMouseClicked(e -> {
      CalorieHandler calorieHandler = new CalorieHandler(this);
      try {
        calorieHandler.start(primaryStage);
      } catch (Exception e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    });

    Image dietImg2 = new Image(getClass().getResource("workout.jpeg").toExternalForm());
    ImageView dietImageView2 = new ImageView(dietImg2);
    dietImageView2.setFitHeight(160);
    dietImageView2.setFitWidth(350);
    Label dieLabel2 = new Label("Workout videos");
    StackPane dieStackPane2 = new StackPane();
    dieStackPane2.getChildren().addAll(dietImageView2, dieLabel2);

    dieStackPane2.setOnMouseClicked(o -> {
      VideoHandler videoHandler = new VideoHandler(this);
      try {
        videoHandler.start(primaryStage);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }

    });

    Image dietImg3 = new Image(getClass().getResource("workoutPlan.png").toExternalForm());
    ImageView dietImageView3 = new ImageView(dietImg3);
    dietImageView3.setFitHeight(160);
    dietImageView3.setFitWidth(350);
    Label dieLabel3 = new Label("30 day workout plan");
    StackPane dieStackPane3 = new StackPane();
    dieStackPane3.getChildren().addAll(dietImageView3, dieLabel3);

    dieStackPane3.setOnMouseClicked(u -> {
      HeavyApp thirtyDHeavyApp = new HeavyApp(this);
      thirtyDHeavyApp.start(primaryStage);
    });

    vBox.getChildren().addAll(mainLabel, dieStackPane1, dieStackPane2, dieStackPane3);

    // styling of label
    dieLabel1.getStyleClass().add("properAppLabel");
    dieLabel2.getStyleClass().add("properAppLabel");
    dieLabel3.getStyleClass().add("properAppLabel");
    // styling of images
    dietImageView1.setId("dietImageView1");
    dietImageView2.setId("dietImageView2");
    dietImageView3.setId("dietImageVie3");
    // Add VBox to ScrollPane
    ScrollPane scrollPane = new ScrollPane(vBox);
    scrollPane.setId("PropScrollPane");
    scrollPane.setFitToWidth(true);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

    BorderPane borderPane = new BorderPane();
    borderPane.setStyle(" -fx-background-color:rgb(17, 17, 28)");
    borderPane.setTop(backButton);
    borderPane.setCenter(scrollPane); // Scrollable content

    // Create a Scene with the BorderPane
    Scene scene = new Scene(borderPane, 400, 550);

    scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    primaryStage.setScene(scene);
    primaryStage.setTitle("Proper App Layout");
    primaryStage.show();
    // primaryStage.setFullScreen(true);
  }

  public static void main(String[] args) {
    launch();
  }

}