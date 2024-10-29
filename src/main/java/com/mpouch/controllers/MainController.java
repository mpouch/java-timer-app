package com.mpouch.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    
    public void switchTheme(ActionEvent event) {
        ThemeHandler.switchTheme(parent, iconTheme);
    }
    
    public void handleTime(ActionEvent event) {
        TimerHandler.sendParentValues(
            lblTimer,
            btnPomodoro,
            btnShortBreak,
            btnLongBreak,
            btnCustomTimer,
            btnStart,
            btnPause,
            btnReset);
        
        TimerHandler.handleTime(event);
    }
    
    public void openCustomTimer(ActionEvent event) {
        TimerHandler.sendParentValues(
            lblTimer,
            btnPomodoro,
            btnShortBreak,
            btnLongBreak,
            btnCustomTimer,
            btnStart,
            btnPause,
            btnReset);
        
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/customTimer.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Custom Timer");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
