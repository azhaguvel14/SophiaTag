package edu.ntust.csie.se.mdfk.sophiatag.data;

import edu.ntust.csie.se.mdfk.sophiatag.gui.custom.ColorSwatch;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Tag extends Attachable<Material>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6577249205844764790L;
	
	private String text;
	
	private transient String highlightedHTML;
	
	
	/**
	 * constructor
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Tag(String text) {
		this.setText(text);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public String getText() {
		return this.text;	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	boolean setText(String text) {
		if (this.text == null || !this.text.equals(text)) {
			this.text = text;
			this.clearHighlightHTML();
			return true;
		}
		
		return false;
	}
	
	public void setHighlightHTML(String html) {
		this.highlightedHTML = html;
	}
	
	public String getHighlightHTML() {
		return this.highlightedHTML == null? this.text: this.highlightedHTML;
	}
	
	@Override
	public String toString() {
		return this.getText();
	}

	public void clearHighlightHTML() {
		this.highlightedHTML = null;
	}
	
}

