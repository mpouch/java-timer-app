package com.mpouch.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CustomTimerController {
    
    @FXML
    private Button btnConfirm;

    @FXML
    private TextField inpMin;

    @FXML
    private TextField inpSec;

    @FXML
    private Label lblError;

    @FXML
    private AnchorPane parent;
    
    public void customTimer(ActionEvent event) {
        CustomTimeHandler.handleCustomTimer(
            event,
            btnConfirm,
            inpMin,
            inpSec,
            lblError
        );
    }
}
