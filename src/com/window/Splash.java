package com.window;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import com.utils.LookAndFeelManager;

public class Splash extends JFrame {

	private static final long serialVersionUID = 6434098313007678321L;

	private JProgressBar progressBar;
	private WindowBuilder windowBuilder;
	
	private boolean running = true;
	
	private int step;
	
	private Thread thread = new Thread(() -> {
		while (running) {
			if (getValue() >= 100) {
				windowBuilder.finish();
				running = false;
				dispose();
			}
		}
	});
	
	public Splash(int files) {
		super("Launcher");
		initScreen();
		step = 100/files;
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		add(progressBar);
		setVisible(true);
		thread.start();
	}
	
	public void offer(WindowBuilder windowBuilder) {
		this.windowBuilder = windowBuilder;
	}
	
	private void initScreen() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(250, 75);
		setResizable(false);
		setLocationRelativeTo(null);
		LookAndFeelManager.initLookAndFeel();
	}
	
	public void setProgress(int value, String message) {
		progressBar.setValue(value);
		progressBar.setString(message);
	}
	
	public void nextProgress(String message) {
		progressBar.setValue(progressBar.getValue() + step);
		progressBar.setString(message);
	}
	
	public void finishProgress() {
		progressBar.setValue(100);
		progressBar.setString("Starting, Please Wait...");
	}
	
	public synchronized int getValue() {
		return progressBar.getValue();
	}
}