package com.window;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.Settings;
import com.utils.LookAndFeelManager;
import com.utils.VersionCreator;
import com.window.panels.PanelCategorySelection;
import com.window.panels.PanelDisplay;

public class WindowBuilder extends JFrame {

	private static final long serialVersionUID = -5846762065250950212L;

	private JLabel labelCategory;
	
	private PanelCategorySelection panelCategorySelection;
	private PanelDisplay panelDisplay;
	
	public WindowBuilder(int major, int minor, int bugs) {
	   	initScreen();
	   	createWindowTitle(major, minor, bugs);
	}
	
	public void init(PanelCategorySelection panelCategorySelection, PanelDisplay panelDisplay) {
		setPanelCategorySelection(panelCategorySelection);
		setPanelDisplay(panelDisplay);
	}
	
	public void build() {
		SwingUtilities.invokeLater(() -> {
			layoutComponents();
			revalidate();
			setVisible(true);
		});
	}
	
	private void createWindowTitle(int major, int minor, int bugs) {
		if (Settings.debug == true)
			Settings.title = Settings.getMainTitle(new VersionCreator(major, minor, bugs).toString());
		else 
			Settings.title = Settings.getMainTitle(Settings.versionRelease);
		setTitle(Settings.title);
	}
	
	private void initScreen() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Settings.MAX_WIDTH, Settings.MAX_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		LookAndFeelManager.initLookAndFeel();
	}
	
	private void layoutComponents() {
		labelCategory = new JLabel("Category:");
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(labelCategory, BorderLayout.NORTH);
		panel.add(panelCategorySelection, BorderLayout.WEST);
		panel.add(panelDisplay, BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createEmptyBorder(7,7,7,7));
		add(panel);
	}
	
	public PanelCategorySelection getPanelCategorySelection() {
		return panelCategorySelection;
	}
	
	public PanelDisplay getPanelDisplay() {
		return panelDisplay;
	}
	
	public void setPanelCategorySelection(PanelCategorySelection panelCategorySelection) {
		this.panelCategorySelection = panelCategorySelection;
	}
	
	public void setPanelDisplay(PanelDisplay panelDisplay) {
		this.panelDisplay = panelDisplay;
	}
}