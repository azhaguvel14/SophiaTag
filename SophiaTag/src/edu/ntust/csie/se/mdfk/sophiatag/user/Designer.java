package edu.ntust.csie.se.mdfk.sophiatag.user;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Designer extends User {
	private static final String DESIGNER_TITLE = "Designer";
	private static final String DESIGNER_ACCOUNT = "designer";
	private static final String DESIGNER_HASHED_PASSWORD = "02bcb6e15372321c57ef39ece6a87c8e889d83b8";
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Designer(){
		super(DESIGNER_TITLE, DESIGNER_ACCOUNT, DESIGNER_HASHED_PASSWORD);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public FuntionalLimitation getFunctionalLimitation() {
		return new FuntionalLimitation(FuntionalLimitation.LimitableFunction.CHANGE_ROOT_DIRECTORY,
									   FuntionalLimitation.LimitableFunction.CHANGE_TAG_ON_MATERIAL,
									   FuntionalLimitation.LimitableFunction.EDIT_MATERIAL_TABLE);	
	}
	
}

