/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.data.Tag;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.MVCGlue;
import edu.ntust.csie.se.mdfk.sophiatag.gui.custom.MaterialListModel;
import edu.ntust.csie.se.mdfk.sophiatag.gui.custom.MaterialTable;
import edu.ntust.csie.se.mdfk.sophiatag.gui.custom.WrapLayout;
import edu.ntust.csie.se.mdfk.sophiatag.gui.tagbutton.TagButton;
import edu.ntust.csie.se.mdfk.sophiatag.gui.tagbutton.TagButton.TextChangedEvent;
import edu.ntust.csie.se.mdfk.sophiatag.gui.tagbutton.TagButton.TextChangedListener;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialList;
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
	private JLabel materialNameLabel;
	private JLabel materialDirLabel;
	private JButton openDirButton;
	private JPanel materialProfilePanel;
	private JButton addTagButton;
	private JPanel tagPanel;
	private JButton discardButton;
	private JLabel userLabel;

	private JPanel searchPanel;
	
	private MaterialListModel tableModel;
	
	private ActionListener sharedTagRemovedListner;
	private TextChangedListener sharedTagEditedListener;
	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public MainView(User user, String rootDir, MaterialList list) {
		super(100, 100, 1000, 700);
		
		this.initializeLimitedFlagMap(user.getFunctionalLimitation());
		this.initializeComponentState(user, rootDir, list);
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
	
	private void initializeComponentState(User user, String rootDir, MaterialList list) {
		
		this.getFrame().setResizable(true);
		this.getFrame().setMinimumSize(new Dimension(600, 700));
		materialProfilePanel.setVisible(false);
		
		this.userLabel.setText(user.getTitle());
		this.setRootDirText(rootDir);
		
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
		
		this.setDiscardButtonEnabled(false);
		
		this.getTableModel().setList(list);
		
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
		boldLabel(userHeader);
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
		boldLabel(rootDirHeader);
		rootDirPanel.add(rootDirHeader);
		
		rootDirLabel = new JLabel();
		rootDirPanel.add(rootDirLabel);
		
		changeRootDirButton = new JButton("Change");
		rootDirPanel.add(changeRootDirButton);
		
		searchPanel = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridwidth = 1;
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
		gbc_materialProfilePanel.weighty = 2;
		gbc_materialProfilePanel.insets = new Insets(10, 0, 10, 0);
		gbc_materialProfilePanel.gridwidth = 2;
		gbc_materialProfilePanel.fill = GridBagConstraints.BOTH;
		gbc_materialProfilePanel.gridx = 0;
		gbc_materialProfilePanel.gridy = 3;
		mainPanel.add(materialProfilePanel, gbc_materialProfilePanel);
		GridBagLayout gbl_materialProfilePanel = new GridBagLayout();
		gbl_materialProfilePanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_materialProfilePanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_materialProfilePanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		gbl_materialProfilePanel.rowWeights = new double[]{0.0, 0.0, 0, 1.0};
		materialProfilePanel.setLayout(gbl_materialProfilePanel);
		
		JLabel fileNameHeader = new JLabel("File Name:");
		boldLabel(fileNameHeader);
		GridBagConstraints gbc_fileNameHeader = new GridBagConstraints();
		gbc_fileNameHeader.gridwidth = 2;
		gbc_fileNameHeader.anchor = GridBagConstraints.WEST;
		gbc_fileNameHeader.insets = new Insets(0, 0, 5, 5);
		gbc_fileNameHeader.gridx = 0;
		gbc_fileNameHeader.gridy = 0;
		materialProfilePanel.add(fileNameHeader, gbc_fileNameHeader);
		
		materialNameLabel = new JLabel();
		GridBagConstraints gbc_fileNameLabel = new GridBagConstraints();
		gbc_fileNameLabel.anchor = GridBagConstraints.WEST;
		gbc_fileNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fileNameLabel.gridx = 2;
		gbc_fileNameLabel.gridy = 0;
		materialProfilePanel.add(materialNameLabel, gbc_fileNameLabel);
		
		JLabel directoryHeader = new JLabel("Directory:");
		boldLabel(directoryHeader);
		GridBagConstraints gbc_directoryHeader = new GridBagConstraints();
		gbc_directoryHeader.gridwidth = 2;
		gbc_directoryHeader.anchor = GridBagConstraints.WEST;
		gbc_directoryHeader.insets = new Insets(0, 0, 5, 5);
		gbc_directoryHeader.gridx = 0;
		gbc_directoryHeader.gridy = 1;
		materialProfilePanel.add(directoryHeader, gbc_directoryHeader);
		
		materialDirLabel = new JLabel();
		GridBagConstraints gbc_fileDirectoryLabel = new GridBagConstraints();
		gbc_fileDirectoryLabel.anchor = GridBagConstraints.WEST;
		gbc_fileDirectoryLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fileDirectoryLabel.gridx = 2;
		gbc_fileDirectoryLabel.gridy = 1;
		materialProfilePanel.add(materialDirLabel, gbc_fileDirectoryLabel);
		
		openDirButton = new JButton("Open");
		GridBagConstraints gbc_openDirButton = new GridBagConstraints();
		gbc_openDirButton.anchor = GridBagConstraints.WEST;
		gbc_openDirButton.insets = new Insets(0, 0, 5, 0);
		gbc_openDirButton.gridx = 3;
		gbc_openDirButton.gridy = 1;
		materialProfilePanel.add(openDirButton, gbc_openDirButton);
		
		
		JLabel tagHeader = new JLabel("Tags:");
		boldLabel(tagHeader);
		GridBagConstraints gbc_tagHeader = new GridBagConstraints();
		gbc_tagHeader.anchor = GridBagConstraints.WEST;
		gbc_tagHeader.insets = new Insets(0, 0, 5, 5);
		gbc_tagHeader.gridx = 0;
		gbc_tagHeader.gridy = 2;
		materialProfilePanel.add(tagHeader, gbc_tagHeader);
		
		addTagButton = new JButton("+");
		GridBagConstraints gbc_addTagButton = new GridBagConstraints();
		gbc_addTagButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_addTagButton.insets = new Insets(0, 0, 5, 5);
		gbc_addTagButton.gridx = 1;
		gbc_addTagButton.gridy = 2;
		materialProfilePanel.add(addTagButton, gbc_addTagButton);
		

		JScrollPane tagScrollPane = new JScrollPane();
		
		tagScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_tagScrollPane = new GridBagConstraints();
		gbc_tagScrollPane.gridwidth = 4;
		gbc_tagScrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_tagScrollPane.fill = GridBagConstraints.BOTH;
		gbc_tagScrollPane.gridx = 0;
		gbc_tagScrollPane.gridy = 3;
		materialProfilePanel.add(tagScrollPane, gbc_tagScrollPane);
		
		tagPanel = new JPanel();
		tagScrollPane.setViewportView(tagPanel);
		WrapLayout wl_tagPanel = new WrapLayout();
		wl_tagPanel.setAlignment(FlowLayout.LEFT);
		tagPanel.setLayout(wl_tagPanel);
		
		JPanel tablePanel = new JPanel();
		GridBagConstraints gbc_tablePanel = new GridBagConstraints();
		gbc_tablePanel.weighty = 10.0;
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		tablePanel.add(scrollPane, gbc_scrollPane);
		
		materialTable = new MaterialTable();
		materialTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(materialTable);
		
		tableModel = (MaterialListModel) materialTable.getModel();
			
		discardButton = new JButton("Discard");
		GridBagConstraints gbc_discardButton = new GridBagConstraints();
		gbc_discardButton.anchor = GridBagConstraints.SOUTHEAST;
		gbc_discardButton.gridx = 0;
		gbc_discardButton.gridy = 2;
		tablePanel.add(discardButton, gbc_discardButton);
		
		
	}
	
	private void boldLabel(JLabel label) {
		label.setFont(label.getFont().deriveFont(Font.BOLD));
	}
	
	@Override
	public void bindEvent(final MVCGlue glue) {
		this.sharedTagEditedListener = new TextChangedListener() {

			@Override
			public void textChanged(TextChangedEvent event) {
				glue.handleEvent("edit_tag", event);
			}
			
		};
		
		this.sharedTagRemovedListner = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				glue.handleEvent("remove_tag", event);
			}
			
		};
		
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
		
		this.queryField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					glue.handleEvent("search", null);
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				glue.handleEvent("search", null);
			}
		});
		
		this.materialTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				glue.handleEvent("material_selected", e);
			}
			
		});
		
		this.getFrame().addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				glue.handleEvent("window_closing", e);
				getTableModel().unsubscribeUpdates();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("closed");
				
			}
		});
		
	}
	
	public MaterialListModel getTableModel() {
		return tableModel;
	}
	public String getQueryText() {
		return queryField.getText();
	}
	
	public void setDiscardButtonEnabled(boolean enabled) {
		this.discardButton.setEnabled(enabled);
	}
	
	public void setRootDirText(String rootDir) {
		this.rootDirLabel.setText(rootDir);
		this.getTableModel().setRootDirectory(rootDir);
	}
	
	public void addTag(Tag tag) {
		this.addTagButton(tag, true).editTag();
	}
	
	public void removeTagButton(TagButton button) {
		this.removeListenersFromTagButton(button);
		this.tagPanel.remove(button);
		this.tagPanel.validate();
		this.tagPanel.repaint();
	}
	
	public void setMaterialToProfile(Material material) {
		// TODO: implemented by Tung, notice the helper method below
		// refer to the fields declared on top of this class
		// it may pass null as the argument, then you have to hide profilePanel 
		if (material != null)
		{
			setMaterialNameDirLabel(material);
			setupTagPanel(material.getTargetsView());
			materialProfilePanel.setVisible(true);
		}else
		{
			materialProfilePanel.setVisible(false);
		}
	}
	
	private void setupTagPanel(Iterable<Tag> tags) {
		/* you should clear buttons on tagPanel first (use clearTagPanel())
		* 1. loop through the tags (using "for each" loop),
		* 2. using "addTagButton" method to convert the tag into a button then add it into the panel.
		* 3. you should use "limitedFlagMap.get(FuntionalLimitation.LimitableFunction.CHANGE_TAG_ON_MATERIAL)" 
		* to set the editability of buttons
		* 4. after adding buttons, you have to call "validate()" and "repaint()" method of tagPanel to 
		* make sure the buttons are laied out and painted properly   
		*/
		clearTagPanel();
		boolean isEistable = limitedFlagMap.get(FuntionalLimitation.LimitableFunction.CHANGE_TAG_ON_MATERIAL);
		for (Tag tag: tags)
		{
			addTagButton(tag, isEistable);
		}
		tagPanel.validate();
		tagPanel.repaint();
	}
	
	private void setMaterialNameDirLabel(Material material)
	{
		materialNameLabel.setText(material.getName());
		materialDirLabel.setText(material.getDirectory());
	}
	
	private void clearTagPanel() {
		Component[] buttons = (Component[])this.tagPanel.getComponents();
		for (Component button: buttons) {
			removeListenersFromTagButton((TagButton) button);
		}
		
		this.tagPanel.removeAll();
		
		
	}
	
	private void removeListenersFromTagButton(TagButton button) {
		button.removeTextChangedListener(sharedTagEditedListener);
		button.removeRemoveListener(sharedTagRemovedListner);
	}
	
	private TagButton addTagButton(Tag tag, boolean editable) {
		TagButton button = new TagButton(tag, editable);
		button.addTextChangedListener(sharedTagEditedListener);
		button.addRemoveListener(sharedTagRemovedListner);
		this.tagPanel.add(button);
		return button;
	}
	
}
