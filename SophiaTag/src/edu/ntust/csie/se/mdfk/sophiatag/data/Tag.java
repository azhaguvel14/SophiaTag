package edu.ntust.csie.se.mdfk.sophiatag.data;
import java.util.Random;


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
	
	private static final String HIGHTLIGHT_COLOR_STRING = "#ffff7f";
	private static final String KEYWORD_COLOR_STRING = "#3c3c3c";
	
	private static final String HIGHLIGHT_WRAPPER_START = "<span style=\"background:" + HIGHTLIGHT_COLOR_STRING + "; color:" + KEYWORD_COLOR_STRING + ";\">";
	private static final String HIGHLIGHT_WRAPPER_END = "</span>";
	
	private String text;
	
	private transient String highlightedText;
	
	
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
	
	void setText(String text) {
		this.text = text;
		this.highlightedText = text;
		
	}
	
	public void highlight(String part) {
		int prefixStart = this.text.indexOf(part);
		int posfixStart = prefixStart + part.length();
		
		StringBuffer buffer = new StringBuffer(this.text.substring(0, prefixStart));
		buffer.append(HIGHLIGHT_WRAPPER_START);
		buffer.append(part);
		buffer.append(HIGHLIGHT_WRAPPER_END);
		buffer.append(this.text.substring(posfixStart));
		
		this.highlightedText = buffer.toString();
	}
	
	public String getHighlightText() {
//		return this.text;
		return this.highlightedText == null? this.text: this.highlightedText;
	}
	
	@Override
	public String toString() {
		return this.getText();
	}

	public void clearHighlight() {
		this.highlightedText = null;
	}
	
}

