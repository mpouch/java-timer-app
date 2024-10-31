package com.mpouch.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TimerHandler {
    
    private static boolean isRunning = false;
    
    // References to UI elements from the MainController so this handler
    // has direct access
    private static Label timerLabel;
    private static Button pomodoroButton;
    private static Button shortBreakButton;
    private static Button longBreakButton;
    private static Button customTimerButton;
    private static Button startButton;
    private static Button pauseButton;
    private static Button resetButton;
    
    // Default timer values (pomodoro)
    private static int min = 25;
    private static int sec = 0;
    
    // Fixed timer values to restore the timer to its initial state when Reset is pressed
    // These are updated whenever a new timer mode is selected (pomodoro, short break, etc)
    private static int minFixed = min;
    private static int secFixed = sec;
    
    // Timeline to manage the passage of time
    private static Timeline timeline;

    
    // Setters
    
    public static void setMin(int minutes) {
        minFixed = min = minutes;
    }
    
    public static void setSec(int seconds) {
        secFixed = sec = seconds;
    }
    
    // Retrieves references of UI elements from MainController
    // and stores them in local variables for easy access
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
    
    // Handles timer-related button clicks by checking the text of the pressed button
    // and calls corresponding methods
    // TO DO: research a more efficient way to do this :(
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
                    startTimer(timerLabel);
                    break;
                case "Pause":
                    pauseTimer();
                    break;
                case "Reset":
                    resetTimer(timerLabel);
                    break;
            }
        }
    }
    
    // Set value for timer
    
    // Stops the timer, sets new timer values and
    // updates the label that shows the timer
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
    
    // Starts the timer and updates the UI components
    private static void startTimer(Label label) {
        
        // Set the timer state to Running
        isRunning = true;
        
        // Disable the start button and enables the pause and reset buttons
        startButton.setDisable(true);
        pauseButton.setDisable(false);
        resetButton.setDisable(false);
        
        // Updates the timer display if the timer is being started for the first time or has been reset
        if (min == minFixed) {
            updateTimer(label);
        }
        
        // Creates timeline to update the timer display every second
        if (timeline == null) {
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer(label)));
            timeline.setCycleCount(Timeline.INDEFINITE);
        }
        
        timeline.play();
    }
    
    private static void pauseTimer() {
        if (timeline != null) {
            timeline.pause();
            isRunning = false;
            startButton.setDisable(false);
            pauseButton.setDisable(true);
            resetButton.setDisable(false);
        }
    }
    
    private static void resetTimer(Label label) {
        if (timeline != null) {
            timeline.stop();
            isRunning = false;
            startButton.setDisable(false);
            pauseButton.setDisable(true);
            resetButton.setDisable(true);
        }
        
        // Reset current timer values to the fixed values and updates the UI
        min = minFixed;
        sec = secFixed;
        label.setText(String.format("%02d", min) + ":" + String.format("%02d", sec));
    }
    
    
    /*
    * updateTimer()
    *
    * The following method updates the timer display and manages the countdown logic
    *
    * If the timer has completed its countdown, the timer stops
    *
    * When seconds reach 0 but there are still minutes remaining, decrement
    * the minutes and set seconds to 59 (represents minute transitions)
    *
    * If there are seconds remaining, simply decrements seconds
    */
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