package edu.ntust.csie.se.mdfk.sophiatag.data;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Material extends Attachable<Tag>{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private boolean lost = false;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private final File path;
	

	/**
	 * constructor
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Material(File path) {
		this.path = path.getAbsoluteFile();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public String getName() {
		return path.getName();	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public String getDirectoryPath() {
		return this.path.getParent();
	}
	
	public File getUnderlyingFile() {
		return this.path;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public boolean isLost() {
		return this.lost;	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void setLost(boolean lost) {
		this.lost = lost;
	}
	
	/**
	 * check if it's lost before discard it<div><br></div>
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	Set<Tag> discard() {
		Set<Tag> tags = new HashSet<Tag>(this.getTargetsView());
		this.clearTargets();
		return tags;	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public int hashCode() {
		return path.hashCode();	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public boolean equals(Object object) {
		if (!(object instanceof Material)) {
			return false;
		}
		
		Material material = (Material)object;
		
		return this.path.equals(material.path) &&
			   this.isLost() == material.isLost() &&
			   this.getTargetsView().equals(material.getTargetsView());
	}
	
	
	
}

