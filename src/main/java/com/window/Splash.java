package com.window;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import com.engine.utils.LookAndFeelManager;

public class Splash extends JFrame {

	private static final long serialVersionUID = 6434098313007678321L;

	private JProgressBar progressBar;
	
	private boolean running = true;
	
	private WindowBuilder windowBuilder;
	
	private int step;
	
	public Thread thread = new Thread(() -> {
		while (running) {
			if (getValue() >= 100 && windowBuilder != null) {
				windowBuilder.build();
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
	
	public void nextProgressMessage(String message) {
		progressBar.setString(message + "...");
	}
	
	public void nextProgress() {
		progressBar.setValue(progressBar.getValue() + step);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void finishProgress() {
		progressBar.setValue(100);
		progressBar.setString("Launching Application...");
	}
	
	public synchronized int getValue() {
		return progressBar.getValue();
	}
	
	public void setWindowBuilder(WindowBuilder windowBuilder) {
		this.windowBuilder = windowBuilder;
	}
}