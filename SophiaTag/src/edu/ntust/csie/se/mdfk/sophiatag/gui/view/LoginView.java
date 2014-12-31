/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.view;

import java.awt.Color;
import java.awt.Font;
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

import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.MVCGlue;
import edu.ntust.csie.se.mdfk.sophiatag.gui.custom.ColorSwatch;

/**
 * @author maeglin89273
 *
 */
public class LoginView extends View {
	
	public static final String ADMIN_COMMAND = "ADMIN";
	public static final String DESIGNER_COMMAND = "DESIGNER";
	
	private JTextField accountField;
	private JPasswordField passwordField;
	private JLabel trademarkLabel;
	private JLabel loginAsLabel;
	private JButton adminLoginButton;
	private JButton designerLoginButton;
	private JPanel mainPanel;
	private JLabel errorLabel;
	
	/**
	 * 
	 */
	public LoginView(boolean isDesignerForbidden) {
		super(100, 100, 270, 260);
		this.initializeButtonState(isDesignerForbidden);
	}
	
	private void initializeButtonState(boolean isDesignerForbidden) {
		adminLoginButton.setDefaultCapable(true);
		getFrame().getRootPane().setDefaultButton(adminLoginButton);
		designerLoginButton.setEnabled(isDesignerForbidden);
	}
	
	@Override
	protected void buildView(JFrame frame) {
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
		
		errorLabel = new JLabel("Wrong account or password");
		errorLabel.setFont(errorLabel.getFont().deriveFont(Font.BOLD));
		errorLabel.setForeground(Color.RED);
		GridBagConstraints gbc_errorLabel = new GridBagConstraints();
//		gbc_errorLabel.insets = new Insets(5, 0, 5, 0);
		gbc_errorLabel.gridwidth = 2;
		gbc_errorLabel.gridx = 0;
		gbc_errorLabel.gridy = 6;
		mainPanel.add(errorLabel, gbc_errorLabel);
		errorLabel.setVisible(false);
		
		loginAsLabel = new JLabel("Login as");
		loginAsLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_loginAsLabel = new GridBagConstraints();
		gbc_loginAsLabel.insets = new Insets(5, 0, 5, 0);
		gbc_loginAsLabel.gridwidth = 2;
		gbc_loginAsLabel.gridx = 0;
		gbc_loginAsLabel.gridy = 7;
		mainPanel.add(loginAsLabel, gbc_loginAsLabel);
				
		adminLoginButton = new JButton("Administrator");
		GridBagConstraints gbc_adminLoginButton = new GridBagConstraints();
		gbc_adminLoginButton.anchor = GridBagConstraints.WEST;
		gbc_adminLoginButton.insets = new Insets(5, 0, 5, 5);
		gbc_adminLoginButton.fill = GridBagConstraints.VERTICAL;
		gbc_adminLoginButton.gridx = 0;
		gbc_adminLoginButton.gridy = 8;
		mainPanel.add(adminLoginButton, gbc_adminLoginButton);
		adminLoginButton.setActionCommand(ADMIN_COMMAND);
		
		designerLoginButton = new JButton("Designer");
		GridBagConstraints gbc_designerLoginButton = new GridBagConstraints();
		gbc_designerLoginButton.anchor = GridBagConstraints.EAST;
		gbc_designerLoginButton.insets = new Insets(5, 5, 5, 0);
		gbc_designerLoginButton.fill = GridBagConstraints.VERTICAL;
		gbc_designerLoginButton.gridx = 1;
		gbc_designerLoginButton.gridy = 8;
		mainPanel.add(designerLoginButton, gbc_designerLoginButton);
		designerLoginButton.setActionCommand(DESIGNER_COMMAND);
		
	}
	
	public void setError(String msg) {
		this.resetFields();
		this.errorLabel.setText(msg);
		this.errorLabel.setVisible(true);
	}
	public void clearError() {
		this.resetFields();
		this.errorLabel.setVisible(false);
	}
	
	private void resetFields() {
		accountField.setText("");
		passwordField.setText("");
		accountField.requestFocusInWindow();
	}
	
	public String getAccount() {
		return this.accountField.getText();
	}
	
	public char[] getPassword() {
		return this.passwordField.getPassword();
	}

	@Override
	public void bindEvent(final MVCGlue glue) {

		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				glue.handleEvent("login", e);
				
			}
			
		};
		
		adminLoginButton.addActionListener(listener);
		designerLoginButton.addActionListener(listener);
	}
}

