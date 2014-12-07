package edu.ntust.csie.se.mdfk.sophiatag.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Component;

import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialScanner;
import edu.ntust.csie.se.mdfk.sophiatag.user.IdentityAuthorizer;

import java.awt.FlowLayout;
import java.io.File;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JPasswordField;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;

public class SophiaTagLauncher {

	private static final String ADMIN_COMMAND = "ADMIN";
	private static final String DESIGNER_COMMAND = "DESIGNER";
	
	private JFrame frmSophiatag;
	private JTextField accountField;
	private JPasswordField passwordField;
	private JLabel loginAsLabel;
	private JButton adminLoginButton;
	private JButton designerLoginButton;
	private JPanel mainPanel;
	private JLabel trademarkLabel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					
//					SophiaTagLauncher loginForm = new SophiaTagLauncher();
					LoginView loginForm = new LoginView();
//					loginForm.frmSophiatag.setVisible(true);
					loginForm.getLoginFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public SophiaTagLauncher() {
//		initialize();
	}
	
	private void initialize() {
		frmSophiatag = new JFrame();
		frmSophiatag.setTitle("SophiaTag");
		frmSophiatag.setBounds(100, 100, 270, 260);
		frmSophiatag.setResizable(false);
		frmSophiatag.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{1.0, 1.0};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1, 0.0, Double.MIN_VALUE};
		
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setLayout(gbl_mainPanel);
		frmSophiatag.getContentPane().add(mainPanel);
		
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
//		ActionListener loginListener = new LoginListener();
		
		adminLoginButton = new JButton("Adminitrator");
		GridBagConstraints gbc_adminLoginButton = new GridBagConstraints();
		gbc_adminLoginButton.anchor = GridBagConstraints.WEST;
		gbc_adminLoginButton.insets = new Insets(5, 0, 5, 5);
		gbc_adminLoginButton.fill = GridBagConstraints.VERTICAL;
		gbc_adminLoginButton.gridx = 0;
		gbc_adminLoginButton.gridy = 8;
		mainPanel.add(adminLoginButton, gbc_adminLoginButton);
		adminLoginButton.setActionCommand(ADMIN_COMMAND);
//		adminLoginButton.addActionListener(loginListener);
				
		designerLoginButton = new JButton("Designer");
		GridBagConstraints gbc_designerLoginButton = new GridBagConstraints();
		gbc_designerLoginButton.anchor = GridBagConstraints.EAST;
		gbc_designerLoginButton.insets = new Insets(5, 5, 5, 0);
		gbc_designerLoginButton.fill = GridBagConstraints.VERTICAL;
		gbc_designerLoginButton.gridx = 1;
		gbc_designerLoginButton.gridy = 8;
		mainPanel.add(designerLoginButton, gbc_designerLoginButton);
		designerLoginButton.setActionCommand(DESIGNER_COMMAND);
//		designerLoginButton.addActionListener(loginListener);
		
	}
}
