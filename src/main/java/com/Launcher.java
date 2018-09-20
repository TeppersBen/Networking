package com;

import com.javaFX.handlers.FrameHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FrameHandler.primaryStage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("/frames/fxmls/MainFrame.fxml"));
		Scene scene = new Scene(root, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle(Settings.TITLE);
		primaryStage.show();
	}
}