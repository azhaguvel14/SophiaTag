package edu.ntust.csie.se.mdfk.sophiatag.user;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class FuntionalLimitation implements Iterable<FuntionalLimitation.LimitableFunction> {
	
	public enum LimitableFunction {
		CHANGE_ROOT_DIRECTORY, SEARCH_BAR, CHANGE_TAG_ON_MATERIAL, EDIT_MATERIAL_TABLE
	}
	
	private final List<FuntionalLimitation.LimitableFunction> limitedFunctions; 
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public FuntionalLimitation(FuntionalLimitation.LimitableFunction... components) {
		this.limitedFunctions = Arrays.asList(components);
		
	}

	public void limits(FuntionalLimitation.LimitableFunction component) {
		this.limitedFunctions.add(component);
	}

	@Override
	public Iterator<FuntionalLimitation.LimitableFunction> iterator() {
		return this.limitedFunctions.iterator();
	}

}