package com.window.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.Settings;
import com.engine.handlers.LanguageHandler;

public class PanelCategorySelection extends PanelProtocol {

	public PanelCategorySelection(LanguageHandler languageHandler) {
		super(languageHandler);
	}

	private static final long serialVersionUID = 7399346587037443971L;
	private JTree tree;
	private JScrollPane treeView;
	private DefaultMutableTreeNode top;

	@Override
	protected void initComponents() {
		top = new DefaultMutableTreeNode("");
		tree = new JTree(top);
		treeView = new JScrollPane(tree);

		createTree();

		expandNodes();
		removeDefaultNodeImages();
		setPreferredSize(new Dimension(110, 0));
	}

	@Override
	protected void layoutComponents() {
		setLayout(new BorderLayout());
		add(treeView, BorderLayout.CENTER);
	}

	@Override
	protected void initListeners() {
		tree.addTreeSelectionListener(e -> {
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if (!isValidNode(selectedNode.toString()))
				return;
			PanelDisplay.setState("panel" + selectedNode.toString());
		});
	}
	
	private boolean isValidNode(String node) {
		if (node.equalsIgnoreCase(languageHandler.getKey("tab_session")))
			return false;
		if (node.equalsIgnoreCase(languageHandler.getKey("tab_calculators")))
			return false;
		if (node.equalsIgnoreCase("Smart-AI"))
			return false;
		if (node.equalsIgnoreCase("Development"))
			return false;
		return true;
	}

	private void createTree() {
		createNode(languageHandler.getKey("tab_session"), Arrays.asList(languageHandler.getKey("tab_session_logging"),
				 														languageHandler.getKey("tab_session_settings")));
		createNode(languageHandler.getKey("tab_calculators"), Arrays.asList(languageHandler.getKey("tab_calculators_converter"), 
																			languageHandler.getKey("tab_calculators_vlsm")));
		if (Settings.debug)
			createNode("Smart-AI", Arrays.asList("RIPv2", "VLAN", "sACL", "DHCP"));
		if (Settings.debug)
			createNode("Development", Arrays.asList("Console"));
	}

	private void createNode(String title, List<String> list) {
		DefaultMutableTreeNode category = null;

		category = new DefaultMutableTreeNode(title);
		top.add(category);
		for (int i = 0; i < list.size(); i++) {
			category.add(new DefaultMutableTreeNode(list.get(i)));
		}
	}

	private void expandNodes() {
		tree.setRootVisible(false);
		TreeNode root = (TreeNode) tree.getModel().getRoot();
		expandAll(tree, new TreePath(root));
		tree.setShowsRootHandles(true);
	}

	@SuppressWarnings("rawtypes")
	private void expandAll(JTree tree, TreePath parent) {
		TreeNode node = (TreeNode) parent.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (Enumeration e = node.children(); e.hasMoreElements();) {
				TreeNode n = (TreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				expandAll(tree, path);
			}
		}
		tree.expandPath(parent);
	}

	private void removeDefaultNodeImages() {
		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
		renderer.setLeafIcon(null);
		renderer.setClosedIcon(null);
		renderer.setOpenIcon(null);
	}

}