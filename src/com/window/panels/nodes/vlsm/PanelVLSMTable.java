package com.window.panels.nodes.vlsm;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Arrays;
import java.util.Comparator;

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
import com.utils.calculators.VLSMSpecializedCalculator;

public class PanelVLSMTable extends JFrame {

	private static final long serialVersionUID = -4010383195907305739L;
	
	private Table table;
	private JTextField[][] data;
	
	public void build(String majorNetwork, JTextField[][] data) {
		setTitle(Settings.title + " - VLSM table");
		initScreen();
		table = new Table(majorNetwork, data);
		add(table);
		
		this.setData(data);
		
		validate();
		
		pack();
		setSize((getWidth() + 250), getHeight());
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void initScreen() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		initLookAndFeel();
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
		"CIDR",
		"Netmask",
		"Assignable range",
		"Broadcast"
	};
	
	public Table(String majorNetwork, JTextField[][] data) {
		super(new BorderLayout());
		table = new JTable(createDataHolder(majorNetwork, data), names);
		
		table.setEnabled(false);
			
		JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
		resizeColumnWidth(table);
		add(table.getTableHeader(), BorderLayout.NORTH);
		add(table, BorderLayout.CENTER);
	}
	
	public void refreshTable() {
		table.repaint();
		table.revalidate();
	}
	
	private JTextField[][] sortArrayDependingOnRequestedSize(JTextField[][] data) {
		Arrays.sort(data, Comparator.comparingInt(arr -> Integer.parseInt(((JTextField)arr[1]).getText())));
		data = reverseArrayOrder(data);
		return data;
	}
	
	private JTextField[][] reverseArrayOrder(JTextField[][] data) {
		JTextField[][] result = new JTextField[data.length][data[0].length];
		int value = 0;
		for (int i = data.length-1; i >= 0; i--) {
			for (int x = 0; x < data[0].length; x++) {
				result[value][x] = data[i][x];
			}
			value++;
		}
		return result;
	}
	
	public String[][] createDataHolder(String majorNetwork, JTextField[][] data) {
		String[][] result = new String[data.length][8];
		data = sortArrayDependingOnRequestedSize(data);
		String network = majorNetwork.split("/")[0];
		int mask = Integer.parseInt(majorNetwork.split("/")[1]);
		String[] net = VLSMSpecializedCalculator.calculateNetwork(network, mask);
		int cidr = 0;
		for (int i = 0; i < data.length; i++) {
			cidr = Integer.parseInt(VLSMSpecializedCalculator.getCIDR(Integer.parseInt(VLSMSpecializedCalculator.getValidHost(Integer.parseInt(data[i][1].getText())))));
			if (i != 0) 
				net = VLSMSpecializedCalculator.calculateNetwork(VLSMSpecializedCalculator.ipAdd(net[3], 1), cidr);
			else
				net = VLSMSpecializedCalculator.calculateNetwork(network, cidr);
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
					result[i][x] = net[0];
					break;
				case 4: //CIDR
					result[i][x] = String.valueOf(cidr);
					break;
				case 5: //Netmask
					result[i][x] = VLSMSpecializedCalculator.getNetmask(Integer.parseInt(result[i][(x-3)]));
					break;
				case 6: //Assignable range
					result[i][x] = net[1] + " - " + net[2];
					break;
				case 7: //Broadcast
					result[i][x] = net[3];
					break;
				}
			}
		}
		return result;
	}
	
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 70;
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width + 1 , width);
	        }
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