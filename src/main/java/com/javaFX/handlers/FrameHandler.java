package com.javaFX.handlers;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrameHandler {

    public static StackPane mainNode = null;
    public static Stage primaryStage = null;

    private static Map<Frames, Parent> frames = new HashMap<>();

    public static void initialize() throws IOException {
        for (Frames frame : Frames.values()) {
            frames.put(frame, loadFrame(frame));
        }
    }

    public enum Frames {
        WELCOME, VLSM, CONVERTORS, SETTINGS;

        public String getName() {
            return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
        }
    }

    public static void switchFrame(Frames frame) {
        mainNode.getChildren().clear();
        mainNode.getChildren().add(frames.get(frame));
    }

    private static Parent loadFrame(Frames frame) throws IOException {
        return FXMLLoader.load(FrameHandler.class.getResource("/frames/fxmls/" + frame.getName() + "Frame.fxml"));
    }

}
