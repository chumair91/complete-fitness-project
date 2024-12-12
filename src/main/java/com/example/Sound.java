package com.example;
// import javafx.application.Platform;
import javafx.scene.media.AudioClip;

public class Sound {
   
    public void soundRun() {
        try {
            String soundFile = getClass().getResource("/sound/btnSound.wav").toString();
             AudioClip notificationSound = new AudioClip(soundFile);
            notificationSound.play();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

   
}
