package com.javaFX.controllers;

import com.engine.calculators.NetworkConverter;
import com.engine.handlers.LanguageHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ConvertorsFrame {

    @FXML private Label label_ipv4;
    @FXML private JFXTextField txt_ipv4;
    @FXML private JFXButton button_ipv4;
    @FXML private Label label_ipv4_result;

    @FXML
    private void initialize() {
        System.out.println(getClass().getSimpleName() + ": Initialize");
        label_ipv4.setText(LanguageHandler.getKey("converter_IPv4_label_IPv4_Address") + ":");
        txt_ipv4.setPromptText(LanguageHandler.getKey("word_example(short)") + ": [192.168.0.1 | 11000000.10101000.00000000.00000001]");
        button_ipv4.setText(LanguageHandler.getKey("converter_IPv4_button_ConvertAddress"));
        label_ipv4_result.setText(LanguageHandler.getKey("converter_IPv4_label_Address") + ":");
    }

    @FXML private void convert_ipv4() {
        System.out.println("Convert IPV4");
    }

}
