package com.mpouch.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TimerHandler {
    
    private static boolean isRunning = false;
    
    // Default timer values
    private static int min = 25;
    private static int sec = 0;
    
    // Used by the Reset button to restore values to previous state
    private static int minFixed = min;
    private static int secFixed = sec;
    
    // Timeline
    private static Timeline timeline;
    
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
                    startTimer(label, btnStart);
                    break;
                case "Pause":
                    pauseTimer(btnStart);
                    break;
                case "Reset":
                    resetTimer(label, btnStart);
                    break;
            }
        }
    }
    
    // Set value for timer
    
    // Stop timer and set timer values
    private static void setTime(Label label, int minInput, int secInput) {
        isRunning = false;
        min = minInput;
        sec = secInput;
        minFixed = 25;
        secFixed = 0;
        label.setText(String.format("%02d", min) + ":" + String.format("%02d", sec));
    }
    
    private static void setPomodoro(
        Label label,
        Button btnPomodoro,
        Button btnShortBreak,
        Button btnLongBreak,
        Button btnCustomTimer,
        Button btnStart) {
        
        setTime(label, 25, 0);
        
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
        
        setTime(label, 5, 0);
        
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
        
        setTime(label, 15, 0);
        
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
        
        setTime(label, 35, 0);
        
        // Disable pressed button and enable previously pressed ones
        btnPomodoro.setDisable(false);
        btnShortBreak.setDisable(false);
        btnLongBreak.setDisable(false);
        btnCustomTimer.setDisable(true);
        btnStart.setDisable(false);
    }
    
    
    // Start, pause or reset timer
    
    private static void startTimer(Label label, Button btnStart) {
        isRunning = true;
        btnStart.setDisable(true);
        
        // Timer start immediately if stopped or reset
        if (min == minFixed) {
            updateTimer(label);
        }
        
        if (timeline == null) {
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer(label)));
            timeline.setCycleCount(Timeline.INDEFINITE);
        }
        
        timeline.play();
    }
    
    private static void pauseTimer(Button btnStart) {
        if (timeline != null) {
            timeline.pause();
            isRunning = false;
            btnStart.setDisable(false);
        }
    }
    
    private static void resetTimer(Label label, Button btnStart) {
        if (timeline != null) {
            timeline.stop();
            isRunning = false;            
            btnStart.setDisable(false);
        }
        
        min = minFixed;
        sec = secFixed;
        label.setText(String.format("%02d", min) + ":" + String.format("%02d", sec));
    }
    
    private static void updateTimer(Label label) {
        if (isRunning) {
            if (min == 0 && sec == 0) {
                label.setText(String.format("%02d", min) + ":" + String.format("%02d", sec));
                isRunning = false;
                timeline.stop();
            } else if (sec == 0) {
                label.setText(String.format("%02d", min) + ":" + String.format("%02d", sec));
                min -= 1;
                sec = 59;
            } else {
                label.setText(String.format("%02d", min) + ":" + String.format("%02d", sec));
                sec -= 1;
            }
        }
    }
}