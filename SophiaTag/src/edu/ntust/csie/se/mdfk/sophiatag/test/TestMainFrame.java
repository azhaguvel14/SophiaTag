package edu.ntust.csie.se.mdfk.sophiatag.test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.border.EmptyBorder;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Insets;

import javax.swing.JTextField;

import java.awt.FlowLayout;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;

public class TestMainFrame {

	private JFrame frame;
	private JTextField queryField;
	private JButton logoutButton;
	private JLabel rootDirLabel;
	private JButton changeDirButton;
	private JButton searchButton;
	private JLabel fileNameLabel;
	private JLabel fileDirLabel;
	private JButton openDirButton;
	private JPanel materialProfilePanel;
	private JButton newTagButton;
	private JPanel tagPanel;
	private JButton discardButton;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					TestMainFrame window = new TestMainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestMainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{1.0};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JPanel userPanel = new JPanel();
		FlowLayout userFlowLayout = (FlowLayout) userPanel.getLayout();
		userFlowLayout.setVgap(0);
		GridBagConstraints gbc_userPanel = new GridBagConstraints();
		gbc_userPanel.insets = new Insets(0, 0, 5, 5);
		gbc_userPanel.anchor = GridBagConstraints.WEST;
		gbc_userPanel.gridx = 0;
		gbc_userPanel.gridy = 0;
		mainPanel.add(userPanel, gbc_userPanel);
		
		JLabel userHeader = new JLabel("User:");
		userPanel.add(userHeader);
		
		JLabel userLabel = new JLabel();
		userPanel.add(userLabel);
		
		logoutButton = new JButton("Log Out");
		userPanel.add(logoutButton);
		
		JPanel rootDirPanel = new JPanel();
		FlowLayout rootDirFlowLayout = (FlowLayout) rootDirPanel.getLayout();
		rootDirFlowLayout.setVgap(0);
		GridBagConstraints gbc_rootDirPanel = new GridBagConstraints();
		gbc_rootDirPanel.insets = new Insets(0, 0, 5, 5);
		gbc_rootDirPanel.fill = GridBagConstraints.VERTICAL;
		gbc_rootDirPanel.anchor = GridBagConstraints.WEST;
		gbc_rootDirPanel.gridx = 0;
		gbc_rootDirPanel.gridy = 1;
		mainPanel.add(rootDirPanel, gbc_rootDirPanel);
		
		JLabel rootDirHeader = new JLabel("Root Directory:");
		rootDirPanel.add(rootDirHeader);
		
		rootDirLabel = new JLabel();
		rootDirPanel.add(rootDirLabel);
		
		changeDirButton = new JButton("Change");
		rootDirPanel.add(changeDirButton);
		
		JPanel searchPanel = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridwidth = 2;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 2;
		mainPanel.add(searchPanel, gbc_panel_3);
		GridBagLayout gbl_searchPanel = new GridBagLayout();
		gbl_searchPanel.columnWidths = new int[]{0, 0};
		gbl_searchPanel.rowHeights = new int[]{0};
		gbl_searchPanel.columnWeights = new double[]{1.0, 0.0};
		gbl_searchPanel.rowWeights = new double[]{0.0};
		searchPanel.setLayout(gbl_searchPanel);
		
		queryField = new JTextField();
		GridBagConstraints gbc_queryField = new GridBagConstraints();
		gbc_queryField.fill = GridBagConstraints.BOTH;
		gbc_queryField.insets = new Insets(0, 0, 0, 5);
		gbc_queryField.gridx = 0;
		gbc_queryField.gridy = 0;
		searchPanel.add(queryField, gbc_queryField);
		queryField.setColumns(10);
		
		searchButton = new JButton("Search");
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.anchor = GridBagConstraints.EAST;
		gbc_searchButton.fill = GridBagConstraints.VERTICAL;
		gbc_searchButton.gridx = 1;
		gbc_searchButton.gridy = 0;
		searchPanel.add(searchButton, gbc_searchButton);
		
		materialProfilePanel = new JPanel();
		GridBagConstraints gbc_materialProfilePanel = new GridBagConstraints();
		gbc_materialProfilePanel.insets = new Insets(10, 0, 10, 0);
		gbc_materialProfilePanel.gridwidth = 2;
		gbc_materialProfilePanel.fill = GridBagConstraints.BOTH;
		gbc_materialProfilePanel.gridx = 0;
		gbc_materialProfilePanel.gridy = 3;
		mainPanel.add(materialProfilePanel, gbc_materialProfilePanel);
		GridBagLayout gbl_materialProfilePanel = new GridBagLayout();
		gbl_materialProfilePanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_materialProfilePanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_materialProfilePanel.columnWeights = new double[]{0.0, 0.0, 0, 1.0};
		gbl_materialProfilePanel.rowWeights = new double[]{0.0, 0.0, 0, 1.0};
		materialProfilePanel.setLayout(gbl_materialProfilePanel);
//		materialProfilePanel.setVisible(false);
		
		JLabel fileNameHeader = new JLabel("File Name:");
		GridBagConstraints gbc_fileNameHeader = new GridBagConstraints();
		gbc_fileNameHeader.gridwidth = 2;
		gbc_fileNameHeader.anchor = GridBagConstraints.WEST;
		gbc_fileNameHeader.insets = new Insets(0, 0, 5, 5);
		gbc_fileNameHeader.gridx = 0;
		gbc_fileNameHeader.gridy = 0;
		materialProfilePanel.add(fileNameHeader, gbc_fileNameHeader);
		
		fileNameLabel = new JLabel();
		GridBagConstraints gbc_fileNameLabel = new GridBagConstraints();
		gbc_fileNameLabel.anchor = GridBagConstraints.WEST;
		gbc_fileNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fileNameLabel.gridx = 2;
		gbc_fileNameLabel.gridy = 0;
		materialProfilePanel.add(fileNameLabel, gbc_fileNameLabel);
		
		JLabel directoryHeader = new JLabel("Directory:");
		GridBagConstraints gbc_directoryHeader = new GridBagConstraints();
		gbc_directoryHeader.gridwidth = 2;
		gbc_directoryHeader.anchor = GridBagConstraints.WEST;
		gbc_directoryHeader.insets = new Insets(0, 0, 5, 5);
		gbc_directoryHeader.gridx = 0;
		gbc_directoryHeader.gridy = 1;
		materialProfilePanel.add(directoryHeader, gbc_directoryHeader);
		
		fileDirLabel = new JLabel();
		GridBagConstraints gbc_fileDirectoryLabel = new GridBagConstraints();
		gbc_fileDirectoryLabel.anchor = GridBagConstraints.WEST;
		gbc_fileDirectoryLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fileDirectoryLabel.gridx = 2;
		gbc_fileDirectoryLabel.gridy = 1;
		materialProfilePanel.add(fileDirLabel, gbc_fileDirectoryLabel);
		
		openDirButton = new JButton("Open");
		GridBagConstraints gbc_btnOpen = new GridBagConstraints();
		gbc_btnOpen.anchor = GridBagConstraints.WEST;
		gbc_btnOpen.insets = new Insets(0, 0, 5, 0);
		gbc_btnOpen.gridx = 3;
		gbc_btnOpen.gridy = 1;
		materialProfilePanel.add(openDirButton, gbc_btnOpen);
		
		JLabel tagHeader = new JLabel("Tag:");
		GridBagConstraints gbc_tagHeader = new GridBagConstraints();
		gbc_tagHeader.anchor = GridBagConstraints.WEST;
		gbc_tagHeader.insets = new Insets(0, 0, 5, 5);
		gbc_tagHeader.gridx = 0;
		gbc_tagHeader.gridy = 2;
		materialProfilePanel.add(tagHeader, gbc_tagHeader);
		
		newTagButton = new JButton("+");
		GridBagConstraints gbc_newTagButton = new GridBagConstraints();
		gbc_newTagButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_newTagButton.insets = new Insets(0, 0, 5, 5);
		gbc_newTagButton.gridx = 1;
		gbc_newTagButton.gridy = 2;
		materialProfilePanel.add(newTagButton, gbc_newTagButton);
		
		tagPanel = new JPanel();
		GridBagConstraints gbc_tagPanel = new GridBagConstraints();
		gbc_tagPanel.gridwidth = 4;
		gbc_tagPanel.insets = new Insets(0, 0, 0, 5);
		gbc_tagPanel.fill = GridBagConstraints.BOTH;
		gbc_tagPanel.gridx = 0;
		gbc_tagPanel.gridy = 3;
		materialProfilePanel.add(tagPanel, gbc_tagPanel);
		tagPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JPanel tablePanel = new JPanel();
		GridBagConstraints gbc_tablePanel = new GridBagConstraints();
		gbc_tablePanel.gridwidth = 2;
		gbc_tablePanel.fill = GridBagConstraints.BOTH;
		gbc_tablePanel.gridx = 0;
		gbc_tablePanel.gridy = 4;
		mainPanel.add(tablePanel, gbc_tablePanel);
		GridBagLayout gbl_tablePanel = new GridBagLayout();
		gbl_tablePanel.columnWidths = new int[]{0};
		gbl_tablePanel.rowHeights = new int[]{0, 0, 0};
		gbl_tablePanel.columnWeights = new double[]{1.0};
		gbl_tablePanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		tablePanel.setLayout(gbl_tablePanel);
		
		JLabel resultHeader = new JLabel("Search Results");
		GridBagConstraints gbc_resultHeader = new GridBagConstraints();
		gbc_resultHeader.insets = new Insets(0, 0, 5, 0);
		gbc_resultHeader.anchor = GridBagConstraints.WEST;
		gbc_resultHeader.gridx = 0;
		gbc_resultHeader.gridy = 0;
		tablePanel.add(resultHeader, gbc_resultHeader);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		tablePanel.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		discardButton = new JButton("Discard");
		GridBagConstraints gbc_discardButton = new GridBagConstraints();
		gbc_discardButton.anchor = GridBagConstraints.SOUTHEAST;
		gbc_discardButton.gridx = 0;
		gbc_discardButton.gridy = 2;
		tablePanel.add(discardButton, gbc_discardButton);	
	}
}
