package com.javaFX.controllers;

import com.Settings;
import com.engine.handlers.LanguageHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WelcomeFrame {

    @FXML private Label label_welcome;
    @FXML private Label label_version;
    @FXML private Label label_release;

    @FXML private void initialize() {
        System.out.println(getClass().getSimpleName() + ": Initializing");
        label_welcome.setText(LanguageHandler.getKey("tab_welcome") + "!");
        label_version.setText(LanguageHandler.getKey("word_version") + ": " + Settings.version);
        label_release.setText(LanguageHandler.getKey("word_releaseDate") + ": " + Settings.releaseDate);
    }

}
