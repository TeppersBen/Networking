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
		Settings.versionRelease = "0.04.0024.054";
		init();
	}
	
	private static void init() {
		Splash splash = new Splash(10);
		splash.nextProgress("Init category panel...");
		panelCategorySelection = new PanelCategorySelection();
		splash.nextProgress("Init display panel...");
		panelDisplay = new PanelDisplay();
		splash.nextProgress("Init language panel...");
		panelLanguage = new PanelLanguage();
		splash.nextProgress("Init logging panel...");
		panelLogging = new PanelLogging();
		splash.nextProgress("Init welcome panel...");
		panelWelcome = new PanelWelcome();
		splash.nextProgress("Init converter panel...");
		panelConverter = new PanelConverter();
		splash.nextProgress("Init VLSM panel...");
		panelVLSM = new PanelVLSM();
		splash.nextProgress("Init Console panel...");
		panelConsole = new PanelConsole();
		splash.nextProgress("Init window-builder utility...");
		windowBuilder = new WindowBuilder(0, 4, 53);
		splash.offer(windowBuilder);
		splash.nextProgress("Assemble display panel...");
		panelDisplay.init(panelWelcome, panelConverter, panelVLSM, panelLogging, panelLanguage, panelConsole);
		splash.nextProgress("Assemble window-builder...");
		windowBuilder.build(panelCategorySelection, panelDisplay);
		splash.finishProgress();
	}
}