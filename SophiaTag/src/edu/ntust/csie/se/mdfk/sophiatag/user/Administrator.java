package edu.ntust.csie.se.mdfk.sophiatag.user;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Administrator extends User {
	private static final String ADMINISTRATOR_TITLE = "Administrator";
	private static final String ADMINISTRATOR_ACCOUNT = "admin";
	private static final String ADMINISTRATOR_HASHED_PASSWORD = "1c46c18d116e5ff76b026510b54e603660c6954b";
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Administrator(){
		super(ADMINISTRATOR_TITLE, ADMINISTRATOR_ACCOUNT, ADMINISTRATOR_ACCOUNT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public FuntionalLimitation makeUIRequest() {
		return new FuntionalLimitation();	
	}
	
}

