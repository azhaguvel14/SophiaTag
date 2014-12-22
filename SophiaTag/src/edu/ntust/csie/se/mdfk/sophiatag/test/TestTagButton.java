package edu.ntust.csie.se.mdfk.sophiatag.test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D.Double;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;

import javax.swing.UIManager;

import edu.ntust.csie.se.mdfk.sophiatag.gui.custom.tagbutton.TagButton;
import edu.ntust.csie.se.mdfk.sophiatag.gui.custom.tagbutton.TagButtonBorder;
import edu.ntust.csie.se.mdfk.sophiatag.gui.custom.tagbutton.TagButtonUI;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class TestTagButton extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					TestTagButton frame = new TestTagButton();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestTagButton() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Panel.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setOpaque(false);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		TagButton tagButton = new TagButton();
		panel.add(tagButton);
		
		JLabel lblTest = new JLabel("<html>456, <span style=\"background:rgb(255, 192, 25);\">test</span>, 123</html>");
		panel.add(lblTest);
		
		TagButton tagButton_1 = new TagButton();
		panel.add(tagButton_1);
		
		TagButton tagButton_2 = new TagButton();
		panel.add(tagButton_2);
		
		this.getRootPane().add(contentPane);
		
	}
}

