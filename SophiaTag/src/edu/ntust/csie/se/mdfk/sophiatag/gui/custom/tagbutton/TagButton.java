/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.custom.tagbutton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
	
	private Tag tag;
	private final boolean editable;
	
	
	
	public TagButton() {
		this(new Tag("New Tag"), true);
	}
	
	public TagButton(Tag tag, boolean editable) {
		this.tag = tag;
		this.editable = editable;
		this.setEnabled(false);
		this.setOpaque(false);
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
	
	public void setTag(Tag newTag) {
		this.tag = newTag;
		this.renameBtn.setText(this.tag.getText());
		this.renameBox.setText(this.tag.getText());
	}
	
	public void editTag() {
		renameBtn.setVisible(false);
		renameBox.setVisible(true);
		renameBox.requestFocusInWindow();
		renameBox.selectAll();
	}
	
	private void prepareViewAndLayout() {
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
						TagButton.this.firePropertyChange(TEXT_PROPERTY, TagButton.this.getTag(), changedText);
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
	
	public void addTextChangedListener(TextChangedListener listener) {
		if (this.isEditable()) {
			this.addPropertyChangeListener(TEXT_PROPERTY, listener);
		}
	}
	
	public void removeTextChangedListener(TextChangedListener listener) {
		if (this.isEditable()) {
			this.removePropertyChangeListener(TEXT_PROPERTY, listener);
		}
	}
	
	
	public void addRemoveListener(ActionListener listener) {
		if (this.isEditable()) {
			this.removeBtn.addActionListener(listener);
		}
	}
	
	public void removeRemoveListener(ActionListener listener) {
		if (this.isEditable()) {
			this.removeBtn.removeActionListener(listener);
		}
	}

	
	public boolean isEditable() {
		return editable;
	}
	
	public static abstract class TextChangedListener implements PropertyChangeListener {
		@Override
		public void propertyChange(PropertyChangeEvent event) {
			this.textChanged(new TextChangedEvent(event.getSource(), (Tag)event.getOldValue(), (String)event.getNewValue()));
		}
		
		
		public abstract void textChanged(TextChangedEvent event);
		
	}

	public static class TextChangedEvent extends PropertyChangeEvent {
	
		private TextChangedEvent(Object source, Tag oldTag, String newText) {
			super(source, TagButton.TEXT_PROPERTY, oldTag, newText);
		}
		
		public Tag getOldTag() {
			return (Tag)this.getOldValue();
		}
		
		public String getNewText() {
			return (String)this.getNewValue();
		}
	}
	
}
