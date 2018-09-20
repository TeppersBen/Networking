package com.fxcontrollers;

import com.engine.handlers.LanguageHandler;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class WelcomeFrame {
    @FXML private JFXButton button_converters;
    @FXML private JFXButton button_vlsm_calculator;

    @FXML private void initialize() {
        System.out.println(getClass().getSimpleName() + ": initializing");
        button_converters.setText(new LanguageHandler().getKey("tab_calculators_converter"));
        button_vlsm_calculator.setText(new LanguageHandler().getKey("tab_calculators_vlsm"));
    }

    @FXML private void go_to_converters_frame() {
        System.out.println("Go to converters frame");
    }

    @FXML private void go_to_vlsm_calculator_frame() {
        System.out.println("Go to vlsm calculator frame");
    }

}
