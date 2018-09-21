package com.javaFX.controllers;

import com.engine.handlers.LanguageHandler;
import com.javaFX.handlers.FrameHandler;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class NavigationBar {
    @FXML private JFXButton button_home;
    @FXML private JFXButton button_converters;
    @FXML private JFXButton button_vlsm_calculator;
    @FXML private JFXButton button_quit;

    @FXML private Label logo;

    @FXML private void initialize() {
        logo.focusTraversableProperty().set(true);
        System.out.println(getClass().getSimpleName() + ": initializing");
        button_home.setText(LanguageHandler.getKey("tab_home"));
        button_converters.setText(LanguageHandler.getKey("tab_calculators_converter"));
        button_vlsm_calculator.setText(LanguageHandler.getKey("tab_calculators_vlsm"));
        //TODO implement button_quit.setText(new LanguageHandler().getKey(""));
    }

    @FXML private void go_to_home_frame() {
        System.out.println("Go to home frame");
        FrameHandler.switchFrame(FrameHandler.Frames.WELCOME);
    }

    @FXML private void go_to_converters_frame() {
        System.out.println("Go to converters frame");
        FrameHandler.switchFrame(FrameHandler.Frames.CONVERTORS);
    }

    @FXML private void go_to_vlsm_calculator_frame() {
        System.out.println("Go to vlsm calculator frame");
        FrameHandler.switchFrame(FrameHandler.Frames.VLSM);
    }

    @FXML private void go_to_settings_frame() {
        System.out.println("Go to settings frame");
        FrameHandler.switchFrame(FrameHandler.Frames.SETTINGS);
    }

    @FXML private void quit_application() {
        System.out.println("Quit application");
        FrameHandler.primaryStage.close();
    }
}
