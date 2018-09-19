package com.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class mainScreenController {
	
	public static Stage currentStage;
	
	private boolean maximized;
	
	private double xOffset;
	private double yOffset;
	
	@FXML
	public void onMousePressed_TopBar(MouseEvent e) {
		xOffset = e.getSceneX();
		yOffset = e.getSceneY();
	}
	
	@FXML
	public void onMouseDragged_TopBar(MouseEvent e) {
		currentStage.setX(e.getScreenX() - xOffset);
		currentStage.setY(e.getScreenY() - yOffset);
	}
	
	@FXML
	public void onAction_MinimizeScreen(ActionEvent e) {
		System.out.println("Minimize Screen ...");
	}
	
	@FXML
	public void onAction_CloseScreen(ActionEvent e) {
		currentStage.close();
	}
	
	@FXML
	public void onAction_Maximize(ActionEvent e) {
		if (!maximized) {
			currentStage.setMaximized(maximized = !maximized);
		} else {
			currentStage.setMaximized(maximized = !maximized);
		}
	}
	
}