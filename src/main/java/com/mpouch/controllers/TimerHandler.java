package com.mpouch.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class TimerHandler {
    
    private static boolean isRunning = true;
    
    public static void handleTime(ActionEvent event) {
        
        if (event.getSource() instanceof Button) {
            Button clickedButton = (Button) event.getSource();
            String buttonText = clickedButton.getText();
        }
    }
    
    
    // Set value for timer
    
    public static void setPomodoro() {
        //
    }
    
    public static void setShortBreak() {
        //
    }
    
    public static void setLongBreak() {
        //
    }
    
    public static void setCustomTimer() {
        //
    }
    
    
    // Start, pause or reset timer
    
    public static void startTimer() {
        //
    }
    
    public static void pauseTimer() {
        //
    }
    
    public static void resetTimer() {
        //
    }
}
