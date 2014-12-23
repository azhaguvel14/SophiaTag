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
	
	private static final String HIGHTLIGHT_COLOR_STRING = ColorSwatch.toNoAlphaString(ColorSwatch.LIGHT_YELLOW);
	private static final String KEYWORD_COLOR_STRING = ColorSwatch.toNoAlphaString(ColorSwatch.ORANGE);
	
	private static final String HIGHLIGHT_WRAPPER_START = "<span style=\"font-weight:bold;background:" + HIGHTLIGHT_COLOR_STRING + "; color:" + KEYWORD_COLOR_STRING + ";\">";
	private static final String HIGHLIGHT_WRAPPER_END = "</span>";
	
	private String text;
	
	private transient String highlightedHTML;
	private transient String highlightedPart;
	
	
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
			if (this.highlightedPart != null) { //has been highlighed
				this.highlight(highlightedPart);
			}
			return true;
		}
		
		return false;
	}
	
	public void highlight(String part) {
		// the highlight condition is restricted by prefixing part, it can be modified this restriction in the future
		if (!this.text.startsWith(part)) {
			this.clearHighlight();
			return;
		}
		
		this.highlightedPart = part;
		
		int prefixStart = 0;
		int posfixStart = prefixStart + part.length();
		
		StringBuffer buffer = new StringBuffer(this.text.substring(0, prefixStart));
		buffer.append(HIGHLIGHT_WRAPPER_START);
		buffer.append(part);
		buffer.append(HIGHLIGHT_WRAPPER_END);
		buffer.append(this.text.substring(posfixStart));
		
		this.highlightedHTML = buffer.toString();
	}
	
	public String getHighlightHTML() {
		return this.highlightedPart == null? this.text: this.highlightedHTML;
	}
	
	@Override
	public String toString() {
		return this.getText();
	}

	public void clearHighlight() {
		this.highlightedPart = null;
		this.highlightedHTML = null;
	}
	
}

