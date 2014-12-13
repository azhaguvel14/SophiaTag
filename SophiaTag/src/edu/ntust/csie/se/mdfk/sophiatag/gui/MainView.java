/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.MVCGlue;
import edu.ntust.csie.se.mdfk.sophiatag.user.FuntionalLimitation;
import edu.ntust.csie.se.mdfk.sophiatag.user.User;

/**
 * @author maeglin89273
 *
 */
public class MainView extends View {
	
	private Map<FuntionalLimitation.LimitableFunction, Boolean> limitedFlagMap;
	
	private JTextField queryField;
	private JTable materialTable;
	private JButton logoutButton;
	private JLabel rootDirLabel;
	private JButton changeRootDirButton;
	private JButton searchButton;
	private JLabel fileNameLabel;
	private JLabel fileDirLabel;
	private JButton openDirButton;
	private JPanel materialProfilePanel;
	private JButton addTagButton;
	private JPanel tagPanel;
	private JButton discardButton;
	private JLabel userLabel;

	private JPanel searchPanel;
	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public MainView(User user, String rootDir) {
		super(100, 100, 600, 600);
		this.initializeLimitedFlagMap(user.getFunctionalLimitation());
		this.initializeComponentState(user, rootDir);
	}
	
	private void initializeLimitedFlagMap(FuntionalLimitation limitation) {
		this.limitedFlagMap = new HashMap<FuntionalLimitation.LimitableFunction, Boolean>();
		this.setupLimitedFlagMap(Arrays.asList(FuntionalLimitation.LimitableFunction.values()), true);
		this.setupLimitedFlagMap(limitation, false);
		
	}
	
	private void setupLimitedFlagMap(Iterable<FuntionalLimitation.LimitableFunction> iterable, boolean limited) {
		for (FuntionalLimitation.LimitableFunction component: iterable) {
			this.limitedFlagMap.put(component, limited);
		}
	}
	
	private void initializeComponentState(User user, String rootDir) {
		this.userLabel.setText(user.getTitle());
		this.rootDirLabel.setText(rootDir);
		
		this.logoutButton.setActionCommand("logout");
		this.changeRootDirButton.setActionCommand("change_root_dir");
		this.searchButton.setActionCommand("search");
		this.openDirButton.setActionCommand("open_dir");
		this.addTagButton.setActionCommand("add_tag");
		this.discardButton.setActionCommand("discard_material");
		
		this.changeRootDirButton.setVisible(limitedFlagMap.get(FuntionalLimitation.LimitableFunction.CHANGE_ROOT_DIRECTORY));
		this.searchPanel.setVisible(limitedFlagMap.get(FuntionalLimitation.LimitableFunction.SEARCH_BAR));
		this.addTagButton.setVisible(limitedFlagMap.get(FuntionalLimitation.LimitableFunction.CHANGE_TAG_ON_MATERIAL));
		this.discardButton.setVisible(limitedFlagMap.get(FuntionalLimitation.LimitableFunction.EDIT_MATERIAL_TABLE));
		
		this.discardButton.setEnabled(false);
	}
	
	@Override
	protected void buildView(JFrame frame) {
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
		
		userLabel = new JLabel();
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
		rootDirPanel.add(getRootDirLabel());
		
		changeRootDirButton = new JButton("Change");
		rootDirPanel.add(changeRootDirButton);
		
		searchPanel = new JPanel();
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
		searchPanel.add(getQueryField(), gbc_queryField);
		getQueryField().setColumns(10);
		
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
		mainPanel.add(getMaterialProfilePanel(), gbc_materialProfilePanel);
		GridBagLayout gbl_materialProfilePanel = new GridBagLayout();
		gbl_materialProfilePanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_materialProfilePanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_materialProfilePanel.columnWeights = new double[]{0.0, 0.0, 0, 1.0};
		gbl_materialProfilePanel.rowWeights = new double[]{0.0, 0.0, 0, 1.0};
		getMaterialProfilePanel().setLayout(gbl_materialProfilePanel);
		getMaterialProfilePanel().setVisible(false);
		
		JLabel fileNameHeader = new JLabel("File Name:");
		GridBagConstraints gbc_fileNameHeader = new GridBagConstraints();
		gbc_fileNameHeader.gridwidth = 2;
		gbc_fileNameHeader.anchor = GridBagConstraints.WEST;
		gbc_fileNameHeader.insets = new Insets(0, 0, 5, 5);
		gbc_fileNameHeader.gridx = 0;
		gbc_fileNameHeader.gridy = 0;
		getMaterialProfilePanel().add(fileNameHeader, gbc_fileNameHeader);
		
		fileNameLabel = new JLabel();
		GridBagConstraints gbc_fileNameLabel = new GridBagConstraints();
		gbc_fileNameLabel.anchor = GridBagConstraints.WEST;
		gbc_fileNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fileNameLabel.gridx = 2;
		gbc_fileNameLabel.gridy = 0;
		getMaterialProfilePanel().add(getFileNameLabel(), gbc_fileNameLabel);
		
		JLabel directoryHeader = new JLabel("Directory:");
		GridBagConstraints gbc_directoryHeader = new GridBagConstraints();
		gbc_directoryHeader.gridwidth = 2;
		gbc_directoryHeader.anchor = GridBagConstraints.WEST;
		gbc_directoryHeader.insets = new Insets(0, 0, 5, 5);
		gbc_directoryHeader.gridx = 0;
		gbc_directoryHeader.gridy = 1;
		getMaterialProfilePanel().add(directoryHeader, gbc_directoryHeader);
		
		fileDirLabel = new JLabel();
		GridBagConstraints gbc_fileDirectoryLabel = new GridBagConstraints();
		gbc_fileDirectoryLabel.anchor = GridBagConstraints.WEST;
		gbc_fileDirectoryLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fileDirectoryLabel.gridx = 2;
		gbc_fileDirectoryLabel.gridy = 1;
		getMaterialProfilePanel().add(getFileDirLabel(), gbc_fileDirectoryLabel);
		
		openDirButton = new JButton("Open");
		GridBagConstraints gbc_openDirButton = new GridBagConstraints();
		gbc_openDirButton.anchor = GridBagConstraints.WEST;
		gbc_openDirButton.insets = new Insets(0, 0, 5, 0);
		gbc_openDirButton.gridx = 3;
		gbc_openDirButton.gridy = 1;
		getMaterialProfilePanel().add(openDirButton, gbc_openDirButton);
		
		
		JLabel tagHeader = new JLabel("Tag:");
		GridBagConstraints gbc_tagHeader = new GridBagConstraints();
		gbc_tagHeader.anchor = GridBagConstraints.WEST;
		gbc_tagHeader.insets = new Insets(0, 0, 5, 5);
		gbc_tagHeader.gridx = 0;
		gbc_tagHeader.gridy = 2;
		getMaterialProfilePanel().add(tagHeader, gbc_tagHeader);
		
		addTagButton = new JButton("+");
		GridBagConstraints gbc_addTagButton = new GridBagConstraints();
		gbc_addTagButton.anchor = GridBagConstraints.WEST;
		gbc_addTagButton.insets = new Insets(0, 0, 5, 5);
		gbc_addTagButton.gridx = 1;
		gbc_addTagButton.gridy = 2;
		getMaterialProfilePanel().add(addTagButton, gbc_addTagButton);
		
		tagPanel = new JPanel();
		GridBagConstraints gbc_tagPanel = new GridBagConstraints();
		gbc_tagPanel.gridwidth = 4;
		gbc_tagPanel.insets = new Insets(0, 0, 0, 5);
		gbc_tagPanel.fill = GridBagConstraints.BOTH;
		gbc_tagPanel.gridx = 0;
		gbc_tagPanel.gridy = 3;
		getMaterialProfilePanel().add(getTagPanel(), gbc_tagPanel);
		getTagPanel().setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
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
		gbl_tablePanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_tablePanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		tablePanel.setLayout(gbl_tablePanel);
		
		JLabel resultHeader = new JLabel("Search Results");
		GridBagConstraints gbc_resultHeader = new GridBagConstraints();
		gbc_resultHeader.insets = new Insets(0, 0, 5, 0);
		gbc_resultHeader.anchor = GridBagConstraints.WEST;
		gbc_resultHeader.gridx = 0;
		gbc_resultHeader.gridy = 0;
		tablePanel.add(resultHeader, gbc_resultHeader);
		
		materialTable = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.insets = new Insets(0, 0, 5, 0);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 1;
		tablePanel.add(getMaterialTable(), gbc_table);
		
		discardButton = new JButton("Discard");
		GridBagConstraints gbc_discardButton = new GridBagConstraints();
		gbc_discardButton.anchor = GridBagConstraints.SOUTHEAST;
		gbc_discardButton.gridx = 0;
		gbc_discardButton.gridy = 2;
		tablePanel.add(discardButton, gbc_discardButton);	
	}

	@Override
	public void resortEventToGlue(final MVCGlue glue) {
		ActionListener sharedListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				glue.handleEvent(e.getActionCommand(), e);
			}
		};
		
		this.logoutButton.addActionListener(sharedListener);
		this.changeRootDirButton.addActionListener(sharedListener);
		this.searchButton.addActionListener(sharedListener);
		this.openDirButton.addActionListener(sharedListener);
		this.addTagButton.addActionListener(sharedListener);
		this.discardButton.addActionListener(sharedListener);
		
	}

	public JTextField getQueryField() {
		return this.queryField;
	}

	public JTable getMaterialTable() {
		return materialTable;
	}

	public JLabel getRootDirLabel() {
		return rootDirLabel;
	}

	public JLabel getFileNameLabel() {
		return fileNameLabel;
	}

	public JLabel getFileDirLabel() {
		return fileDirLabel;
	}

	public JPanel getMaterialProfilePanel() {
		return materialProfilePanel;
	}

	public JPanel getTagPanel() {
		return tagPanel;
	}

}
