package edu.ntust.csie.se.mdfk.sophiatag.gui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;

import edu.ntust.csie.se.mdfk.sophiatag.user.FuntionalLimitation;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class MainUIBuilder {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Map<FuntionalLimitation.LimitableFunction, Boolean> editableFlagMap;
	private final String userTitle;
	private static final Map<FuntionalLimitation.LimitableFunction, SubUIBuilder> SUB_BUIDLER_MAP = new HashMap<FuntionalLimitation.LimitableFunction, SubUIBuilder>();
	
	static {
		initSubBuilders();
	}
	

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public MainUIBuilder(String userTitle, FuntionalLimitation limitation) {
		this.userTitle = userTitle;
		this.editableFlagMap = new HashMap<FuntionalLimitation.LimitableFunction, Boolean>();
		this.setupComponentMap(Arrays.asList(FuntionalLimitation.LimitableFunction.values()), true);
		this.setupComponentMap(limitation, false);
		
	}
	
	private void setupComponentMap(Iterable<FuntionalLimitation.LimitableFunction> iterable, boolean editable) {
		for (FuntionalLimitation.LimitableFunction component: iterable) {
			this.editableFlagMap.put(component, editable);
		}
	}
	
	private static void initSubBuilders() {
		SUB_BUIDLER_MAP.put(FuntionalLimitation.LimitableFunction.CHANGE_ROOT_DIRECTORY, new SubUIBuilder() {

			@Override
			public JComponent buildComponent(boolean editable) {
				return null;
			}
			
		});
		
		SUB_BUIDLER_MAP.put(FuntionalLimitation.LimitableFunction.SEARCH_BAR, new SubUIBuilder() {

			@Override
			public JComponent buildComponent(boolean editable) {
				return null;
			}
			
		});
		
		SUB_BUIDLER_MAP.put(FuntionalLimitation.LimitableFunction.CHANGE_TAG_ON_MATERIAL, new SubUIBuilder() {

			@Override
			public JComponent buildComponent(boolean editable) {
				return null;
			}
			
		});
		
		SUB_BUIDLER_MAP.put(FuntionalLimitation.LimitableFunction.EDIT_MATERIAL_TABLE, new SubUIBuilder() {

			@Override
			public JComponent buildComponent(boolean editable) {
				return null;
			}
			
		});
		
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public JPanel build(JPanel panel) {
		panel.add(this.buildUserStatus());
		for (FuntionalLimitation.LimitableFunction component: FuntionalLimitation.LimitableFunction.values()) {
			panel.add(SUB_BUIDLER_MAP.get(component).buildComponent(editableFlagMap.get(component)));
		}
		
		return panel;
	}
	
	private JComponent buildUserStatus() {
		return null;
	}
	
	private interface SubUIBuilder {
		public JComponent buildComponent(boolean editable);
	}
}

