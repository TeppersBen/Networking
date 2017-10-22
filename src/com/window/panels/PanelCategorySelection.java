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

public class PanelCategorySelection extends PanelProtocol {

	private static final long serialVersionUID = 7399346587037443971L;
	private JTree tree;
	private JScrollPane treeView;
	private DefaultMutableTreeNode top;

	@Override
	protected void initComponents() {
		top = new DefaultMutableTreeNode("Make your selection.");
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
			PanelDisplay.setState("panel" + selectedNode.toString());
		});
	}

	private void createTree() {
		createNode("Session", Arrays.asList("Logging", "Language"));
		createNode("Calculators", Arrays.asList("Converter", "VLSM"));
		if (Settings.debug) {
			createNode("Development", Arrays.asList("Console"));
		}
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