package com.mpouch.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomTimeHandler {
    
    public static void handleCustomTimer(
        ActionEvent event,
        Button btnConfirm,
        TextField inpMin,
        TextField inpSec,
        Label lblError) {
        
        if (event.getSource() instanceof Button) {
            
            try {
                String minText = inpMin.getText();
                String secText = inpSec.getText();
                
                if (minText.isEmpty() || secText.isEmpty()) {
                    lblError.setText("Both fields are required");
                    lblError.setVisible(true);
                    return;
                }
                
                int minutes = Integer.parseInt(minText);
                int seconds = Integer.parseInt(secText);
                
                // Validate that input values are within the acceptable range
                if (minutes >= 60 || minutes <= 0 || seconds >= 60 || seconds <= 0) {
                    lblError.setText("Enter values between 0 and 60"); // Show range error
                    lblError.setVisible(true); // Show error label
                } else {
                    TimerHandler.setCustomTimer(minutes, seconds);
                    lblError.setVisible(false); // Hide error label
                    
                    // Close the timer window
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                }
            } catch (NumberFormatException e) {
                // Handles case where input values are not numeric
                lblError.setText("Enter numeric values");
                lblError.setVisible(true); // Show error label
            }
        }
    }
}
