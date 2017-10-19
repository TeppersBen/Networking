package com.window;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import com.Settings;
import com.window.panels.PanelCategorySelection;
import com.window.panels.PanelDisplay;

public class WindowBuilder extends JFrame {

	private static final long serialVersionUID = -5846762065250950212L;

	private JLabel labelCategory;
	
	public WindowBuilder() {
		super(Settings.TITLE);
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	        	initScreen();
	    		layoutComponents();
	    		validate();
	    		setVisible(true);
	        }
	    });
	}
	
	private void initScreen() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Settings.MAX_WIDTH, Settings.MAX_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		initLookAndFeel();
	}
	
	private void layoutComponents() {
		labelCategory = new JLabel("Category:");
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(labelCategory, BorderLayout.NORTH);
		panel.add(new PanelCategorySelection(), BorderLayout.WEST);
		panel.add(new PanelDisplay(), BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createEmptyBorder(7,7,7,7));
		add(panel);
	}
	
	private void initLookAndFeel() {
		try {
			String className = getLookAndFeelClassName("Windows");
			UIManager.setLookAndFeel(className);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} 
	}
	
	private String getLookAndFeelClassName(String nameSnippet) {
	    LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
	    for (LookAndFeelInfo info : plafs) {
	        if (info.getName().contains(nameSnippet)) {
	            return info.getClassName();
	        }
	    }
	    return null;
	}
	
}