package com;

import com.engine.handlers.LanguageHandler;
import com.engine.utils.Logger;
import com.engine.utils.Popup;
import com.window.Splash;
import com.window.WindowBuilder;
import com.window.panels.PanelCategorySelection;
import com.window.panels.PanelDisplay;
import com.window.panels.nodes.PanelLogging;
import com.window.panels.nodes.PanelWelcome;
import com.window.panels.nodes.converter.PanelConverter;
import com.window.panels.nodes.settings.PanelSettings;
import com.window.panels.nodes.simulation.PanelConsole;
import com.window.panels.nodes.smartAI.PanelDHCP;
import com.window.panels.nodes.smartAI.PanelRIPv2;
import com.window.panels.nodes.smartAI.PanelVLAN;
import com.window.panels.nodes.smartAI.PanelsACL;
import com.window.panels.nodes.vlsm.PanelVLSM;

public class Launcher {

	private static WindowBuilder windowBuilder;
	private static LanguageHandler languageHandler;
	
	private static PanelCategorySelection panelCategorySelection;
	private static PanelDisplay panelDisplay;	
	private static PanelSettings panelSettings;	
	private static PanelLogging panelLogging;
	private static PanelWelcome panelWelcome;	
	private static PanelConverter panelConverter;
	private static PanelVLSM panelVLSM;
	private static PanelConsole panelConsole;
	private static PanelDHCP panelDHCP;
	private static PanelRIPv2 panelRIPv2;
	private static PanelsACL panelsACL;
	private static PanelVLAN panelVLAN;
	
	public static void main(String[] args) {
		init();
	}
	
	private static void init() {
		Splash splash = new Splash(31);
		
		//handlers
		splash.nextProgressMessage("Initializing handlers");
		languageHandler = new LanguageHandler();
		splash.nextProgress();
		Popup.setLanguageHandler(languageHandler);
		splash.nextProgress();
		
		//main frames
		splash.nextProgressMessage("Creating main-frames");
		panelCategorySelection = new PanelCategorySelection(languageHandler);
		splash.nextProgress();
		windowBuilder = new WindowBuilder(languageHandler);
		splash.nextProgress();
		panelDisplay = new PanelDisplay(languageHandler);
		
		//nodes
		splash.nextProgressMessage("Creating nodes");
		splash.nextProgress();
		panelSettings = new PanelSettings(languageHandler);
		splash.nextProgress();
		panelLogging = new PanelLogging(languageHandler);
		splash.nextProgress();
		panelWelcome = new PanelWelcome(languageHandler);
		splash.nextProgress();
		panelConverter = new PanelConverter(languageHandler);
		splash.nextProgress();
		panelVLSM = new PanelVLSM(languageHandler);
		splash.nextProgress();
		panelConsole = new PanelConsole(languageHandler);
		splash.nextProgress();
		panelDHCP = new PanelDHCP(languageHandler);
		splash.nextProgress();
		panelRIPv2 = new PanelRIPv2(languageHandler);
		splash.nextProgress();
		panelsACL = new PanelsACL(languageHandler);
		splash.nextProgress();
		panelVLAN = new PanelVLAN(languageHandler);
		
		//adding nodes to display
		splash.nextProgressMessage("Adding nodes to NodeManager");
		splash.nextProgress();
		panelDisplay.addNodePanel(panelSettings);
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
		splash.nextProgress();
		panelDisplay.addNodePanel(panelDHCP);
		splash.nextProgress();
		panelDisplay.addNodePanel(panelRIPv2);
		splash.nextProgress();
		panelDisplay.addNodePanel(panelsACL);
		splash.nextProgress();
		panelDisplay.addNodePanel(panelVLAN);
		
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