package com.mpouch.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MainController {

    @FXML
    private Button btnCustomTimer;

    @FXML
    private Button btnLongBreak;

    @FXML
    private Button btnPause;

    @FXML
    private Button btnPomodoro;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnShortBreak;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnTheme;

    @FXML
    private Label lblTimer;
    
    @FXML
    private ImageView iconTheme;

    @FXML
    private AnchorPane parent;
    
    // Handlers
    
    private ThemeHandler themeHandler = new ThemeHandler();
    
    private boolean isDarkTheme = true;
    
    public void switchTheme(ActionEvent event) {
        themeHandler.switchTheme(parent, iconTheme);
    }
}
