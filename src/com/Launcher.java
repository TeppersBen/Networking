package com;

import com.development.PanelConsole;
import com.utils.Logger;
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
		Settings.debug = true;
		Settings.versionRelease = "0.04.0024.060";
		init(0, 4, 60);
	}
	
	private static void init(int major, int minor, int bugfixes) {
		Splash splash = new Splash(20);
		
		//main frames
		splash.nextProgressMessage("Creating main-frames");
		panelCategorySelection = new PanelCategorySelection();
		splash.nextProgress();
		windowBuilder = new WindowBuilder(major, minor, bugfixes);
		splash.nextProgress();
		panelDisplay = new PanelDisplay();
		
		//nodes
		splash.nextProgressMessage("Creating nodes");
		splash.nextProgress();
		panelLanguage = new PanelLanguage();
		splash.nextProgress();
		panelLogging = new PanelLogging();
		splash.nextProgress();
		panelWelcome = new PanelWelcome();
		splash.nextProgress();
		panelConverter = new PanelConverter();
		splash.nextProgress();
		panelVLSM = new PanelVLSM();
		splash.nextProgress();
		panelConsole = new PanelConsole();
		
		//adding nodes to display
		splash.nextProgressMessage("Adding nodes to NodeManager");
		splash.nextProgress();
		panelDisplay.addNodePanel(panelLanguage);
		splash.nextProgress();
		panelDisplay.addNodePanel(panelLogging);
		splash.nextProgress();
		panelDisplay.addNodePanel(panelWelcome);
		splash.nextProgress();
		panelDisplay.addNodePanel(panelConverter);
		splash.nextProgress();
		panelDisplay.addNodePanel(panelVLSM);
		splash.nextProgress();
		panelDisplay.addNodePanel(panelConsole);
		
		//merge the nodes into the panel itself
		splash.nextProgressMessage("Getting nodes ready to be displayed");
		splash.nextProgress();
		panelDisplay.build();
		
		//preparing for launch
		splash.nextProgressMessage("Almost done, Preparing for launch");
		splash.nextProgress();
		windowBuilder.init(panelCategorySelection, panelDisplay);
		splash.nextProgress();
		splash.setWindowBuilder(windowBuilder);
		splash.finishProgress();
		Logger.log("Done...");
	}
}