package com.development;

import static java.awt.event.KeyEvent.VK_BACK_SPACE;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_UP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

import com.Settings;
import com.devices.Router;
import com.handlers.LanguageHandler;
import com.window.panels.PanelProtocol;

public class PanelConsole extends PanelProtocol {

	private static final long serialVersionUID = 8004163759463256854L;

	private JTextComponent consoleField;
	private JScrollPane scroll;
	private ConsoleHandler consoleHandler;

	private String title;

	public PanelConsole(LanguageHandler languageHandler) {
		super(languageHandler);
		title = Settings.title;
		consoleHandler = new ConsoleHandler(consoleField);
		consoleField.addKeyListener(consoleHandler.getKeyListener());
		add(setTitle(title), BorderLayout.NORTH);
	}

	@Override
	protected void initComponents() {
		consoleField = new JTextPane();
		scroll = new JScrollPane();
		scroll.setViewportView(consoleField);
	}

	@Override
	protected void layoutComponents() {
		add(scroll, BorderLayout.CENTER);
	}

	protected void initListeners() {}
}

class ConsoleHandler {

	private JTextComponent pane;
	private Router device;
	
	public ConsoleHandler(JTextComponent pane) {
		setPane(pane);
		device = new Router();
		pane.setText(device.getCurrentState());
		pane.setFont(new Font("Monospaced", Font.PLAIN, 11));
	}

	public KeyAdapter getKeyListener() {
		return new KeyAdapter() {
			private int caretLastPosition;
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case VK_ENTER:
					pane.setCaretPosition(pane.getText().length());
					break;
				case VK_BACK_SPACE:
					if (pane.getText().charAt(pane.getText().length() - 2) == '#')
						pane.setText(pane.getText() + " ");
					if (pane.getText().charAt(pane.getText().length() - 2) == '>')
						pane.setText(pane.getText() + " ");
					break;
				case VK_UP:
				case VK_DOWN:
					caretLastPosition = pane.getCaretPosition();
					pane.setCaretColor(Color.white);
					break;
				}
			}

			public void keyTyped(KeyEvent e) {
				switch (e.getKeyCode()) {
				
				}
			}

			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case VK_ENTER:
					try {
						pane.setText(pane.getText() + device.issueCommand(pane.getText()));
					} catch (Exception e1) {
						pane.setText(pane.getText() + e1.getMessage());
					}
					break;
				case VK_UP:
				case VK_DOWN:
					pane.setCaretPosition(caretLastPosition);
					pane.setCaretColor(Color.black);
					break;
				}
			}
		};
	}

	public JTextComponent getPane() {
		return pane;
	}

	public void setPane(JTextComponent pane) {
		this.pane = pane;
	}

}