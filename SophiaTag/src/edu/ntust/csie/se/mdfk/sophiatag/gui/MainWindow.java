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

import java.awt.Component;

import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialScanner;
import edu.ntust.csie.se.mdfk.sophiatag.user.IdentityAuthorizer;

import java.awt.FlowLayout;
import java.io.File;

public class MainWindow {

	private JFrame frame;
	private JButton button;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainWindow window = new MainWindow();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 636, 399);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(167, 176, 260, 35);
		frame.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
		
		button = new JButton("New button");
		panel.add(button);
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);
		
	}
}
