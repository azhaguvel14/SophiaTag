package edu.ntust.csie.se.mdfk.sophiatag.data;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Tag extends Attachable<Material>{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String text;
	private final int hashCode;
	/**
	 * constructor
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Tag(String text) {
		this.text = text;
		this.hashCode = new Random().nextInt();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public String getText() {
		// TODO implement me
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
	public int hashCode() {
		return this.hashCode;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public boolean equals(Object object) {
		if (!(object instanceof Tag)) {
			return false;
		}
		
		Tag tag = (Tag)object;
		return this.getText().equals(tag.getText()) && this.getTargetsView().equals(tag.getTargetsView());
	}

	
	
}

