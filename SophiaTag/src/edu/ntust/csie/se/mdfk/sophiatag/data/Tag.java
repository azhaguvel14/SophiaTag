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
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String text;
	/**
	 * constructor
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Tag(String text) {
		this.text = text;
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
	}
	
	@Override
	public String toString() {
		return this.getText();
	}
	
}

