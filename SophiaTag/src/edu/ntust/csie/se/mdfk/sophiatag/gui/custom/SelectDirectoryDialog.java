package edu.ntust.csie.se.mdfk.sophiatag.gui.custom;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JTextField;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

public class SelectDirectoryDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField pathField;
	private String pathValue = null;
	JButton okButton;
	/**
	 * Create the dialog.
	 */
	public SelectDirectoryDialog(Frame parentFrame, String message) {
		super(parentFrame, "Select a Directory", true);
		initialize(message);
	}
	
	private void initialize(String message) {
		setResizable(false);
		this.setMinimumSize(new Dimension(450, 130));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel messageLabel = new JLabel(message);
			GridBagConstraints gbc_messageLabel = new GridBagConstraints();
			gbc_messageLabel.anchor = GridBagConstraints.SOUTH;
			gbc_messageLabel.fill = GridBagConstraints.HORIZONTAL;
			gbc_messageLabel.insets = new Insets(20, 0, 10, 0);
			gbc_messageLabel.gridwidth = 2;
			gbc_messageLabel.gridx = 0;
			gbc_messageLabel.gridy = 0;
			contentPanel.add(messageLabel, gbc_messageLabel);
		}
		{
			pathField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.fill = GridBagConstraints.BOTH;
			gbc_textField.gridx = 0;
			gbc_textField.gridy = 1;
			contentPanel.add(pathField, gbc_textField);
			pathField.setColumns(10);
			
		}
		{
			JButton browseButton = new JButton("Browse");
			GridBagConstraints gbc_browseButton = new GridBagConstraints();
			gbc_browseButton.insets = new Insets(0, 0, 5, 5);
			gbc_browseButton.gridx = 1;
			gbc_browseButton.gridy = 1;
			contentPanel.add(browseButton, gbc_browseButton);
			browseButton.addActionListener(new BrowseListener());
		}
		{	
			ActionListener hideListener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getActionCommand().equals("OK")) {
						if (!new File(pathField.getText()).isDirectory()) {
							JOptionPane.showMessageDialog(SelectDirectoryDialog.this, "This is not a directory", "Bad Directory", JOptionPane.ERROR_MESSAGE);
							return;
						}
						pathValue = pathField.getText();
					}
					SelectDirectoryDialog.this.setVisible(false);
				}
				
			};
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(hideListener);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(hideListener);
			}
		}
		
		this.pack();
	}
	
	public String getPathValue() {
		return pathValue;
	}
	
	public static String showSelectDirectoryDialog(Frame parentFrame, String message) {
		SelectDirectoryDialog dialog = new SelectDirectoryDialog(parentFrame, message);
		dialog.setVisible(true);
		dialog.dispose();
		
		
		return dialog.getPathValue();
	}
	
	private class BrowseListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser(); 
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if (JFileChooser.APPROVE_OPTION == chooser.showDialog(SelectDirectoryDialog.this, "Select")) {
				pathField.setText(chooser.getSelectedFile().getPath());
			}
		}	
	}

}
