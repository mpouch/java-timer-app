package com.mpouch.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TimerHandler {
    
    private static boolean isRunning = false;
    
    // Default timer values
    private static int min = 25;
    private static int sec = 0;
    
    // Used by the Reset button to restore values to previous state
    private static int minFixed = min;
    private static int secFixed = sec;
    
    public static void handleTime(
        ActionEvent event,
        Label label,
        Button btnPomodoro,
        Button btnShortBreak,
        Button btnLongBreak,
        Button btnCustomTimer,
        Button btnStart,
        Button btnPause,
        Button btnReset) {
        
        if (event.getSource() instanceof Button) {
            Button clickedButton = (Button) event.getSource();
            String buttonText = clickedButton.getText();
            
            switch (buttonText) {
                // Timer value
                case "Pomodoro":
                    setPomodoro(label, btnPomodoro, btnShortBreak, btnLongBreak, btnCustomTimer, btnStart);
                    break;
                case "Short Break":
                    setShortBreak(label, btnPomodoro, btnShortBreak, btnLongBreak, btnCustomTimer, btnStart);
                    break;
                case "Long Break":
                    setLongBreak(label, btnPomodoro, btnShortBreak, btnLongBreak, btnCustomTimer, btnStart);
                    break;
                case "Custom Timer":
                    setCustomTimer(label, btnPomodoro, btnShortBreak, btnLongBreak, btnCustomTimer, btnStart);
                    break;
                    
                // Timer controls
                case "Start":
                    startTimer();
                    break;
                case "Pause":
                    pauseTimer();
                    break;
                case "Reset":
                    resetTimer();
                    break;
            }
        }
    }
    
    
    // Set value for timer
    
    private static void setPomodoro(
        Label label,
        Button btnPomodoro,
        Button btnShortBreak,
        Button btnLongBreak,
        Button btnCustomTimer,
        Button btnStart) {
        
        // Stop timer and set timer values
        isRunning = false;
        min = 25;
        sec = 0;
        minFixed = 25;
        secFixed = 0;
        label.setText(String.format("%02d", min) + ":" + String.format("%02d", sec));
        
        // Disable pressed button and enable previously pressed ones
        btnPomodoro.setDisable(true);
        btnShortBreak.setDisable(false);
        btnLongBreak.setDisable(false);
        btnCustomTimer.setDisable(false);
        btnStart.setDisable(false);
    }
    
    private static void setShortBreak(
        Label label,
        Button btnPomodoro,
        Button btnShortBreak,
        Button btnLongBreak,
        Button btnCustomTimer,
        Button btnStart) {
        
        // Stop timer and set timer values
        isRunning = false;
        min = 5;
        sec = 0;
        minFixed = 5;
        secFixed = 0;
        label.setText(String.format("%02d", min) + ":" + String.format("%02d", sec));
        
        // Disable pressed button and enable previously pressed ones
        btnPomodoro.setDisable(false);
        btnShortBreak.setDisable(true);
        btnLongBreak.setDisable(false);
        btnCustomTimer.setDisable(false);
        btnStart.setDisable(false);
    }
    
    private static void setLongBreak(
        Label label,
        Button btnPomodoro,
        Button btnShortBreak,
        Button btnLongBreak,
        Button btnCustomTimer,
        Button btnStart) {
        
        // Stop timer and set timer values
        isRunning = false;
        min = 15;
        sec = 0;
        minFixed = 15;
        secFixed = 0;
        label.setText(String.format("%02d", min) + ":" + String.format("%02d", sec));
        
        // Disable pressed button and enable previously pressed ones
        btnPomodoro.setDisable(false);
        btnShortBreak.setDisable(false);
        btnLongBreak.setDisable(true);
        btnCustomTimer.setDisable(false);
        btnStart.setDisable(false);
    }
    
    private static void setCustomTimer(
        Label label,
        Button btnPomodoro,
        Button btnShortBreak,
        Button btnLongBreak,
        Button btnCustomTimer,
        Button btnStart) {
        
        // Stop timer and set timer values
        isRunning = false;
        min = 20;
        sec = 0;
        minFixed = 20;
        secFixed = 0;
        label.setText(String.format("%02d", min) + ":" + String.format("%02d", sec));
        
        // Disable pressed button and enable previously pressed ones
        btnPomodoro.setDisable(false);
        btnShortBreak.setDisable(false);
        btnLongBreak.setDisable(false);
        btnCustomTimer.setDisable(true);
        btnStart.setDisable(false);
    }
    
    
    // Start, pause or reset timer
    
    private static void startTimer() {
        //
    }
    
    private static void pauseTimer() {
        //
    }
    
    private static void resetTimer() {
        //
    }
    
    private static void updateTimer() {
        //
    }
}
