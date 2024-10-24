package com.mpouch.controllers;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ThemeHandler {
    
    private static boolean isDarkTheme = true;
    
    public static void switchTheme(AnchorPane parent, ImageView iconTheme) {
        
        if (isDarkTheme) {
            setLightTheme(parent, iconTheme);
        } else {
            setDarkTheme(parent, iconTheme);
        }
        
        isDarkTheme = !isDarkTheme;
    }
    
    private static void setLightTheme(AnchorPane parent, ImageView iconTheme) {
        parent.getStylesheets().remove("fxml/mainDark.css");
        parent.getStylesheets().add("fxml/mainLight.css");
        Image image = new Image("img/dark_mode.png");
        iconTheme.setImage(image);
    }
    
    private static void setDarkTheme(AnchorPane parent, ImageView iconTheme) {
        parent.getStylesheets().remove("fxml/mainLight.css");
        parent.getStylesheets().add("fxml/mainDark.css");
        Image image = new Image("img/light_mode.png");
        iconTheme.setImage(image);
    }    
}
