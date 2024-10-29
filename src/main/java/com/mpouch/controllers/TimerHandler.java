package com.mpouch.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TimerHandler {
    
    private static boolean isRunning = false;
    
    private static Label timerLabel;
    private static Button pomodoroButton;
    private static Button shortBreakButton;
    private static Button longBreakButton;
    private static Button customTimerButton;
    private static Button startButton;
    private static Button pauseButton;
    private static Button resetButton;
    
    // Default timer values
    private static int min = 25;
    private static int sec = 0;
    
    // Used by the Reset button to restore values to previous state
    private static int minFixed = min;
    private static int secFixed = sec;
    
    // Timeline
    private static Timeline timeline;

    
    // Setters
    
    public static void setMin(int minutes) {
        minFixed = min = minutes;
    }
    
    public static void setSec(int seconds) {
        secFixed = sec = seconds;
    }
    
    public static void sendParentValues(
        Label label,
        Button btnPomodoro,
        Button btnShortBreak,
        Button btnLongBreak,
        Button btnCustomTimer,
        Button btnStart,
        Button btnPause,
        Button btnReset) {
        
        timerLabel = label;
        pomodoroButton = btnPomodoro;
        shortBreakButton = btnShortBreak;
        longBreakButton = btnLongBreak;
        customTimerButton = btnCustomTimer;
        startButton = btnStart;
        pauseButton = btnPause;
        resetButton = btnReset;
    }
    
    public static void handleTime(ActionEvent event) {
        
        if (event.getSource() instanceof Button) {
            Button clickedButton = (Button) event.getSource();
            String buttonText = clickedButton.getText();
            
            switch (buttonText) {
                // Timer value
                case "Pomodoro":
                    setPomodoro();
                    break;
                case "Short Break":
                    setShortBreak();
                    break;
                case "Long Break":
                    setLongBreak();
                    break;
                    
                // Timer controls
                case "Start":
                    startTimer(timerLabel, startButton);
                    break;
                case "Pause":
                    pauseTimer(startButton);
                    break;
                case "Reset":
                    resetTimer(timerLabel, startButton);
                    break;
            }
        }
    }
    
    // Set value for timer
    
    // Stop timer and set timer values
    private static void setTime(int minInput, int secInput) {
        isRunning = false;
        minFixed = min = minInput;
        secFixed = sec = secInput;
        timerLabel.setText(String.format("%02d", min) + ":" + String.format("%02d", sec));
    }
    
    private static void setPomodoro() {        
        setTime(25, 0);
        
        // Disable pressed button and enable previously pressed ones
        pomodoroButton.setDisable(true);
        shortBreakButton.setDisable(false);
        longBreakButton.setDisable(false);
        customTimerButton.setDisable(false);
        startButton.setDisable(false);
    }
    
    private static void setShortBreak() {
        setTime(5, 0);
        
        // Disable pressed button and enable previously pressed ones
        pomodoroButton.setDisable(false);
        shortBreakButton.setDisable(true);
        longBreakButton.setDisable(false);
        customTimerButton.setDisable(false);
        startButton.setDisable(false);
    }
    
    private static void setLongBreak() {
        setTime(15, 0);
        
        // Disable pressed button and enable previously pressed ones
        pomodoroButton.setDisable(false);
        shortBreakButton.setDisable(false);
        longBreakButton.setDisable(true);
        customTimerButton.setDisable(false);
        startButton.setDisable(false);
    }
    
    public static void setCustomTimer(int minInput, int secInput) {
        setTime(minInput, secInput);
        
        pomodoroButton.setDisable(false);
        shortBreakButton.setDisable(false);
        longBreakButton.setDisable(false);
        customTimerButton.setDisable(true);
        startButton.setDisable(false);
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