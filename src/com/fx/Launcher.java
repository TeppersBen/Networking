package com.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Launcher extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			mainScreenController.currentStage = stage;
			stage.initStyle(StageStyle.UNDECORATED);
			Parent root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
			Scene scene = new Scene(root);
			stage.setMinWidth(600);
			stage.setMinHeight(400);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
