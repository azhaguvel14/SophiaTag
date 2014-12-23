/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.custom.tagbutton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventObject;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.plaf.metal.MetalTextFieldUI;
import javax.swing.plaf.synth.SynthTextFieldUI;

import edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger;
import edu.ntust.csie.se.mdfk.sophiatag.data.Tag;
import edu.ntust.csie.se.mdfk.sophiatag.gui.custom.tagbutton.TagButtonBorder.Mode;

/**
 * @author maeglin89273
 *
 */
public class TagButton extends JPanel {
	
	private static final String REMOVE_CHARACTOR = "×"; 
	private static final String TEXT_PROPERTY = "text";
	
	private static final Border LEFT_BORDER = TagButtonBorder.makeBorder(Mode.BUTTON, true);
	private static final Border RIGHT_BORDER = TagButtonBorder.makeBorder(Mode.BUTTON, false);
	private static final Border TEXT_BORDER = TagButtonBorder.makeBorder(Mode.TEXTBOX, true);
			
	private static final ButtonUI LEFT_UI = new TagButtonUI(true);
	private static final ButtonUI RIGHT_UI = new TagButtonUI(false);
	
	private JButton renameBtn;
	private JButton removeBtn;
	private JTextField renameBox;
	
	private final Tag tag;
	private final boolean editable;
	
	private TextChangedListener textChangeListener = null;
	private RemoveTagListener removeTagListener = null;
	public TagButton() {
		this(new Tag("New Tag"), true);
	}
	
	public TagButton(Tag tag, boolean editable) {
		this.tag = tag;
		this.editable = editable;
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		this.prepareViewAndLayout();
		this.initializeSubComponents();
		this.addButtons();
		this.pack();
		
		this.addInteractiveListeners();
	}
	
	public Tag getTag() {
		return this.tag;
	}
	
	public void editTag() {
		renameBtn.setVisible(false);
		renameBox.setVisible(true);
		renameBox.requestFocusInWindow();
		renameBox.selectAll();
	}
	
	private void prepareViewAndLayout() {
		this.setEnabled(false);
		this.setOpaque(false);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
	}
	
	private void initializeSubComponents() {
		renameBtn = new JButton(this.tag.getText());
		renameBtn.setForeground(this.isEditable()? Color.WHITE: Color.DARK_GRAY);
		renameBtn.setFont(boldIt(renameBtn.getFont()));
		renameBtn.setContentAreaFilled(false);
		renameBtn.setEnabled(this.isEditable());
		
		renameBtn.setUI(LEFT_UI);
		renameBtn.setBorder(LEFT_BORDER);
		
		if (this.isEditable()) {
			renameBox = new JTextField(this.tag.getText());
			renameBox.setUI(new BasicTextFieldUI());
			renameBox.setBackground(new Color(0xFCFCFC));
			renameBox.setBorder(TEXT_BORDER);
			renameBox.setVisible(false);
			
			removeBtn = new JButton(REMOVE_CHARACTOR);
			removeBtn.setForeground(TagButtonUIUtility.DARK_BLUE);
			removeBtn.setFont(boldIt(removeBtn.getFont()));
			removeBtn.setContentAreaFilled(false);
			
			removeBtn.setUI(RIGHT_UI);
			removeBtn.setBorder(RIGHT_BORDER);
		}
	}
	
	private Font boldIt(Font font) {
		return font.deriveFont(Font.BOLD);
	}
	
	private void addButtons() {
		this.add(renameBtn);
		
		if (this.isEditable()) {
			this.add(renameBox);
			this.add(removeBtn);
		}
	}
	
	private void pack() {
		this.setSize(this.getPreferredSize());
	}
	
	private void addInteractiveListeners() {
		removeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TagButton.this.removeTagListener.removeTag(new RemoveTagEvent(TagButton.this, getTag()));
			}
			
		});
		renameBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				editTag();
			}
			
		});
		
		if (renameBox != null) {
			renameBox.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					String changedText = changeText(renameBox.getText());
					
					renameBox.setVisible(false);
					renameBtn.setVisible(true);
					
					if (changedText != null) {
						TagButton.this.textChangeListener.textChanged(new TextChangedEvent(this, getTag(), changedText));
					}
					
				}
			});
			renameBox.addKeyListener(new KeyAdapter() {
				
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						TagButton.this.requestFocusInWindow();
					}
				}
				
			});
		}
	}
	
	private String changeText(String newText) {
		newText = newText.trim();
		if (newText.isEmpty() || this.tag.getText().equals(newText)) {
			this.renameBox.setText(this.tag.getText());
			return null;
		}
		
		this.renameBox.setText(newText);
		this.renameBtn.setText(newText);
		return newText;
		
	}
	
	public void setTextChangedListener(TextChangedListener listener) {
		if (this.isEditable()) {
			this.textChangeListener = listener;
		}
	}
	
	public void removeTextChangedListener() {
		if (this.isEditable()) {
			this.textChangeListener = null;
		}
	}
	
	
	public void setRemoveTagListener(RemoveTagListener listener) {
		if (this.isEditable()) {
			this.removeTagListener = listener;
		}
	}
	
	public void removeRemoveTagListener() {
		if (this.isEditable()) {
			this.removeTagListener = null;
		}
	}

	
	public boolean isEditable() {
		return editable;
	}
	
	public interface TextChangedListener {
		public abstract void textChanged(TextChangedEvent event);
		
	}
	
	public interface RemoveTagListener {
		public abstract void removeTag(RemoveTagEvent event);
	}
	
	public static class TextChangedEvent extends EventObject {
		private final String newText;
		private final Tag oldTag;
		private TextChangedEvent(Object source, Tag oldTag, String newText) {
			super(source);
			this.oldTag = oldTag;
			this.newText = newText;
		}
		
		public Tag getOldTag() {
			return this.oldTag;
		}
		
		public String getNewText() {
			return this.newText;
		}
	}
	
	public static class RemoveTagEvent extends EventObject {
		private final Tag tag;
		
		public RemoveTagEvent(Object source, Tag tag) {
			super(source);
			this.tag = tag;
			
		}
		
		public Tag getTag() {
			return this.tag;
		}
	}
}
