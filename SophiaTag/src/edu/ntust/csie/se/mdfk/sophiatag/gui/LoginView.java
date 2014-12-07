/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialPool;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialSearcher;
import edu.ntust.csie.se.mdfk.sophiatag.service.RecordStorage;
import edu.ntust.csie.se.mdfk.sophiatag.user.Administrator;
import edu.ntust.csie.se.mdfk.sophiatag.user.Designer;
import edu.ntust.csie.se.mdfk.sophiatag.user.FuntionalLimitation;
import edu.ntust.csie.se.mdfk.sophiatag.user.IdentityAuthorizer;
import edu.ntust.csie.se.mdfk.sophiatag.user.User;

/**
 * @author maeglin89273
 *
 */
public class LoginView {
	
	private static final String ADMIN_COMMAND = "ADMIN";
	private static final String DESIGNER_COMMAND = "DESIGNER";
	
	private JFrame frame;
	private JTextField accountField;
	private JPasswordField passwordField;
	private JLabel trademarkLabel;
	private JLabel loginAsLabel;
	private JButton adminLoginButton;
	private JButton designerLoginButton;
	private JPanel mainPanel;
	
	private LoginGuard guard;
	/**
	 * 
	 */
	public LoginView() {
		this.guard = new LoginGuard();
		initializeView();
		
	}
	
	public Frame getLoginFrame() {
		return this.frame;
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeView() {
		frame = new JFrame();
		frame.setTitle("SophiaTag");
		frame.setBounds(100, 100, 270, 260);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{1.0, 1.0};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1, 0.0, Double.MIN_VALUE};
		
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setLayout(gbl_mainPanel);
		frame.getContentPane().add(mainPanel);
		
		JLabel accountLabel = new JLabel("Account");
		accountLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		GridBagConstraints gbc_accountLabel = new GridBagConstraints();
		gbc_accountLabel.insets = new Insets(5, 0, 0, 0);
		gbc_accountLabel.gridwidth = 2;
		gbc_accountLabel.gridx = 0;
		gbc_accountLabel.gridy = 0;
		mainPanel.add(accountLabel, gbc_accountLabel);
		
		accountField = new JTextField();
		accountField.setFont(new Font("Dialog", Font.PLAIN, 16));
		GridBagConstraints gbc_accountField = new GridBagConstraints();
		gbc_accountField.insets = new Insets(5, 5, 5, 5);
		gbc_accountField.fill = GridBagConstraints.BOTH;
		gbc_accountField.gridwidth = 2;
		gbc_accountField.gridx = 0;
		gbc_accountField.gridy = 1;
		mainPanel.add(accountField, gbc_accountField);
		accountField.setColumns(15);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.insets = new Insets(5, 0, 0, 0);
		gbc_passwordLabel.gridwidth = 2;
		gbc_passwordLabel.gridx = 0;
		gbc_passwordLabel.gridy = 2;
		mainPanel.add(passwordLabel, gbc_passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Dialog", Font.PLAIN, 16));
		passwordField.setColumns(15);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.insets = new Insets(5, 5, 5, 5);
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.gridx = 0;
		gbc_passwordField.gridy = 3;
		mainPanel.add(passwordField, gbc_passwordField);
		
		trademarkLabel = new JLabel("Powered by MDFK™");
		trademarkLabel.setFont(new Font("Dialog", Font.BOLD, 10));
		GridBagConstraints gbc_trademarkLabel = new GridBagConstraints();
		gbc_trademarkLabel.gridwidth = 2;
		gbc_trademarkLabel.insets = new Insets(0, 0, 5, 5);
		gbc_trademarkLabel.gridx = 0;
		gbc_trademarkLabel.gridy = 4;
		mainPanel.add(trademarkLabel, gbc_trademarkLabel);
		
		loginAsLabel = new JLabel("Login as");
		loginAsLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_loginAsLabel = new GridBagConstraints();
		gbc_loginAsLabel.insets = new Insets(5, 0, 5, 0);
		gbc_loginAsLabel.gridwidth = 2;
		gbc_loginAsLabel.gridx = 0;
		gbc_loginAsLabel.gridy = 7;
		mainPanel.add(loginAsLabel, gbc_loginAsLabel);
		
		ActionListener loginListener = new LoginProcessor();
		
		adminLoginButton = new JButton("Adminitrator");
		GridBagConstraints gbc_adminLoginButton = new GridBagConstraints();
		gbc_adminLoginButton.anchor = GridBagConstraints.WEST;
		gbc_adminLoginButton.insets = new Insets(5, 0, 5, 5);
		gbc_adminLoginButton.fill = GridBagConstraints.VERTICAL;
		gbc_adminLoginButton.gridx = 0;
		gbc_adminLoginButton.gridy = 8;
		mainPanel.add(adminLoginButton, gbc_adminLoginButton);
		adminLoginButton.setActionCommand(ADMIN_COMMAND);
		adminLoginButton.addActionListener(loginListener);
		
		
		designerLoginButton = new JButton("Designer");
		GridBagConstraints gbc_designerLoginButton = new GridBagConstraints();
		gbc_designerLoginButton.anchor = GridBagConstraints.EAST;
		gbc_designerLoginButton.insets = new Insets(5, 5, 5, 0);
		gbc_designerLoginButton.fill = GridBagConstraints.VERTICAL;
		gbc_designerLoginButton.gridx = 1;
		gbc_designerLoginButton.gridy = 8;
		mainPanel.add(designerLoginButton, gbc_designerLoginButton);
		designerLoginButton.setActionCommand(DESIGNER_COMMAND);
		designerLoginButton.addActionListener(loginListener);
		if (this.guard.isDesignerForbidden()) {
			designerLoginButton.setEnabled(false);
		}
	}
	
	private void clearFields() {
		accountField.setText("");
		passwordField.setText("");
	}
	
	private class LoginProcessor implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			User user = createUser(e.getActionCommand());
			
			if (IdentityAuthorizer.authorize(accountField.getText(), new String(passwordField.getPassword()), user)) {
				if (!guard.guardLoginProgress()) {
					return;
				}
				
				createMainUI(user).setVisible(true);
				frame.setVisible(false);
			}
			
			clearFields();
		}
		
		
		
		private User createUser(String actionCommand) {
			User user = null;
			if (actionCommand.equals(ADMIN_COMMAND)) {
				user = new Administrator();
			} else if (actionCommand.equals(DESIGNER_COMMAND)) {
				user = new Designer();
			}
	
			return user;
		}
		
		private JFrame createMainUI(User user) {
			
			MainUIController controller = new MainUIController();
			MainUIBuilder builder = new MainUIBuilder(user.getTitle(), user.getFunctionalLimitation());
			
			return builder.build(controller);	
		}
		
		
	}
	
	private class LoginGuard {
		private RecordStorage storage;
		public LoginGuard() {
			this.storage = new RecordStorage();
		}
		
		public boolean isDesignerForbidden() {
			return !storage.hasSavedRecord();
		}
		
		//return true indicates that it's valid to progress, else it should return back to the original dialog with no side effects
		public boolean guardLoginProgress() {
			if (storage.hasSavedRecord()) {
				return true;
			}
			
			String path = SelectDirectoryDialog.showSelectDirectoryDialog(getLoginFrame(), "Choose the root directory of the materials:");
			if (path == null) {
				return false;
			}
			
			RecordStorage.NecessaryRecord record = new RecordStorage.NecessaryRecord(path, new MaterialPool(), new MaterialSearcher.TagDatabase());
			this.storage.saveRecord(record);
			return true;
		}
	}
}
