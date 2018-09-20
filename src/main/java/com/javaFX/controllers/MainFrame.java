package com.javaFX.controllers;

import com.engine.handlers.LanguageHandler;
import com.javaFX.handlers.FrameHandler;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainFrame {

    @FXML private StackPane stackPane;

    @FXML private void initialize() {
        FrameHandler.mainNode = stackPane;
        try {
            stackPane.getChildren().add(FXMLLoader.load(getClass().getResource("/frames/fxmls/WelcomeFrame.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
