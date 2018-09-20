package com.javaFX.handlers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FrameHandler {

    public static StackPane mainNode = null;
    public static Stage primaryStage = null;

    public enum Frames {
        WELCOME, VLSM, CONVERTORS, SETTINGS;

        public String getName() {
            return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
        }
    }

    public static void switchFrame(Frames frame) throws IOException {
        mainNode.getChildren().clear();
        mainNode.getChildren().add(loadFrame(frame));
    }

    private static Parent loadFrame(Frames frame) throws IOException {
        return FXMLLoader.load(FrameHandler.class.getResource("/frames/fxmls/" + frame.getName() + "Frame.fxml"));
    }

}
