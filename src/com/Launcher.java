package com;

import com.development.PanelConsole;
import com.window.Splash;
import com.window.WindowBuilder;
import com.window.panels.PanelCategorySelection;
import com.window.panels.PanelDisplay;
import com.window.panels.nodes.PanelLanguage;
import com.window.panels.nodes.PanelLogging;
import com.window.panels.nodes.PanelWelcome;
import com.window.panels.nodes.converter.PanelConverter;
import com.window.panels.nodes.vlsm.PanelVLSM;

public class Launcher {

	private static WindowBuilder windowBuilder;
	
	private static PanelCategorySelection panelCategorySelection;
	private static PanelDisplay panelDisplay;	
	private static PanelLanguage panelLanguage;	
	private static PanelLogging panelLogging;
	private static PanelWelcome panelWelcome;	
	private static PanelConverter panelConverter;
	private static PanelVLSM panelVLSM;
	private static PanelConsole panelConsole;
	
	public static void main(String[] args) {
		Settings.debug = false;
		Settings.versionRelease = "0.04.0024.059";
		init(0, 4, 59);
	}
	
	private static void init(int major, int minor, int bugfixes) {
		Splash splash = new Splash(20);
		
		//main frames
		splash.nextProgress("Init category panel");
		panelCategorySelection = new PanelCategorySelection();
		splash.nextProgress("Init window-builder utility");
		windowBuilder = new WindowBuilder(major, minor, bugfixes);
		splash.nextProgress("Init display panel");
		panelDisplay = new PanelDisplay();
		
		//nodes
		splash.nextProgress("Init node language");
		panelLanguage = new PanelLanguage();
		splash.nextProgress("Init node logging");
		panelLogging = new PanelLogging();
		splash.nextProgress("Init node welcome");
		panelWelcome = new PanelWelcome();
		splash.nextProgress("Init node converter");
		panelConverter = new PanelConverter();
		splash.nextProgress("Init node VLSM");
		panelVLSM = new PanelVLSM();
		splash.nextProgress("Init node Console");
		panelConsole = new PanelConsole();
		
		//adding nodes to display
		splash.nextProgress("Add node language");
		panelDisplay.addNodePanel(panelLanguage);
		splash.nextProgress("Add node Logging");
		panelDisplay.addNodePanel(panelLogging);
		splash.nextProgress("Add node Welcome");
		panelDisplay.addNodePanel(panelWelcome);
		splash.nextProgress("Add node Converter");
		panelDisplay.addNodePanel(panelConverter);
		splash.nextProgress("Add node VLSM");
		panelDisplay.addNodePanel(panelVLSM);
		splash.nextProgress("Add node Console");
		panelDisplay.addNodePanel(panelConsole);
		
		//merge the nodes into the panel itself
		splash.nextProgress("Activating display panel");
		panelDisplay.build();
		
		//preparing for launch
		splash.nextProgress("Merge main-frames");
		windowBuilder.init(panelCategorySelection, panelDisplay);
		splash.nextProgress("Starting SwingUtilities");
		splash.setWindowBuilder(windowBuilder);
		splash.finishProgress();
	}
}