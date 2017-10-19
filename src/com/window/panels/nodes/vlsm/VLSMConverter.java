package com.window.panels.nodes.vlsm;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.Settings;

public class VLSMConverter extends JFrame {

	private static final long serialVersionUID = -4010383195907305739L;

	private int x;
	private Table table;
	private JTextField[][] data;
	
	public void build(JTextField[][] data) {
		setTitle(Settings.TITLE + " - VLSM table");
		initScreen();
		table = new Table(data);
		add(table);
		
		this.setData(data);
		
		validate();
		
		pack();
		setSize((getWidth() + 250), getHeight());
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void addListener() {
		addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {}
			public void windowClosed(WindowEvent e) { x = 0; }
			public void windowActivated(WindowEvent e) {
				if (x >= 1) {
					return;
				}
				table.refreshTable();
				x++;
			}
		});
	}
	
	private void initScreen() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		initLookAndFeel();
		addListener();
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

	public JTextField[][] getData() {
		return data;
	}

	public void setData(JTextField[][] data) {
		this.data = data;
	}
}

class Table extends JPanel {

	private static final long serialVersionUID = -8218140888013072845L;
	
	private JTable table;
	
	private String[] names = { 
		"Subnet name",
		"Needed size",
		"Allocated size",
		"Address",
		"Mask",
		"Decimal mask",
		"Assignable range",
		"Broadcast"
	};
	
	public Table(JTextField[][] data) {
		super(new BorderLayout());
		table = new JTable(createDataHolder(data), names);
		
		table.setEnabled(false);
			
		JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
		resizeColumnWidth(table);
		add(table.getTableHeader(), BorderLayout.NORTH);
		add(table, BorderLayout.CENTER);
	}
	
	public void refreshTable() {
		table.repaint();
	}
	
	public String[][] createDataHolder(JTextField[][] data) {
		String[][] result = new String[data.length][8];
		for (int i = 0; i < data.length; i++) {
			for (int x = 0; x < result[0].length; x++) {
				switch(x) {
				case 0: //subnet name
					result[i][x] = data[i][0].getText();
					break;
				case 1: //Needed size
					result[i][x] = (data[i][1].getText().isEmpty()) ? "0" : data[i][1].getText();
					break;
				case 2: //Allocated size
					result[i][x] = VLSMSpecializedCalculator.getValidHost(Integer.parseInt(result[i][(x-1)]));
					break;
				case 3: //Address
				case 4: //Mask CIDR
				case 5: //Decimal mask
				case 6: //Assignable range
				case 7: //Broadcast
					result[i][x] = "null";
					break;
				}
			}
		}
		return result;
	}
	
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 70; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width + 1 , width);
	        }
	        if(width > 300)
	            width=300;
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
}

class JTableUtilities {
    public static void setCellsAlignment(JTable table, int alignment) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        table.getTableHeader().setDefaultRenderer(rightRenderer);
        
        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }
}