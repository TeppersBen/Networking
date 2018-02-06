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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.Settings;
import com.engine.calculators.VLSMSpecializedCalculator;
import com.engine.handlers.LanguageHandler;
import com.engine.utils.LookAndFeelManager;

public class PanelVLSMTable extends JFrame {

	private static final long serialVersionUID = -4010383195907305739L;
	
	private Table table;
	private JTextField[][] data;
	
	public void build(String majorNetwork, JTextField[][] data, LanguageHandler handler) {
		setTitle(Settings.TITLE + " - VLSM table");
		initScreen();
		table = new Table(majorNetwork, data, handler);
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
		LookAndFeelManager.initLookAndFeel();
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
	private LanguageHandler languageHandler;
	
	private String[] names = new String[8];
	
	private void initTableNames() {
		names[0] = languageHandler.getKey("vlsm_table_subnetname");
		names[1] = languageHandler.getKey("vlsm_table_neededsize");
		names[2] = languageHandler.getKey("vlsm_table_allocatedsize");
		names[3] = languageHandler.getKey("vlsm_table_address");
		names[4] = languageHandler.getKey("vlsm_table_cidr");
		names[5] = languageHandler.getKey("vlsm_table_netmask");
		names[6] = languageHandler.getKey("vlsm_table_assignablerange");
		names[7] = languageHandler.getKey("vlsm_table_broadcast");
	}
	
	public Table(String majorNetwork, JTextField[][] data, LanguageHandler handler) {
		super(new BorderLayout());
		setLanguageHandler(handler);
		initTableNames();
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
	        int width = 90;
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width + 1 , width);
	        }
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
	
	private void setLanguageHandler(LanguageHandler handler) {
		languageHandler = handler;
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