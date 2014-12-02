package edu.ntust.csie.se.mdfk.sophiatag.gui;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;


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
	
	private Map<UILimitation.EditableComponent, Boolean> editableFlagMap;
	private final String userTitle;
	private static final Map<UILimitation.EditableComponent, SubUIBuilder> SUB_BUIDLER_MAP = new HashMap<UILimitation.EditableComponent, SubUIBuilder>();
	
	static {
		initSubBuilders();
	}
	

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public MainUIBuilder(String userTitle, UILimitation limitation) {
		this.userTitle = userTitle;
		this.editableFlagMap = new HashMap<UILimitation.EditableComponent, Boolean>();
		this.setupComponentMap(Arrays.asList(UILimitation.EditableComponent.values()), true);
		this.setupComponentMap(limitation, false);
		
	}
	
	private void setupComponentMap(Iterable<UILimitation.EditableComponent> iterable, boolean editable) {
		for (UILimitation.EditableComponent component: iterable) {
			this.editableFlagMap.put(component, editable);
		}
	}
	
	private static void initSubBuilders() {
		SUB_BUIDLER_MAP.put(UILimitation.EditableComponent.ROOT_DIRECTORY, new SubUIBuilder() {

			@Override
			public JComponent buildComponent(boolean editable) {
				return null;
			}
			
		});
		
		SUB_BUIDLER_MAP.put(UILimitation.EditableComponent.SEARCH_BAR, new SubUIBuilder() {

			@Override
			public JComponent buildComponent(boolean editable) {
				return null;
			}
			
		});
		
		SUB_BUIDLER_MAP.put(UILimitation.EditableComponent.MATERIAL_PROFILE, new SubUIBuilder() {

			@Override
			public JComponent buildComponent(boolean editable) {
				return null;
			}
			
		});
		
		SUB_BUIDLER_MAP.put(UILimitation.EditableComponent.MATERIAL_TABLE, new SubUIBuilder() {

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
		for (UILimitation.EditableComponent component: UILimitation.EditableComponent.values()) {
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
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	
	public static class UILimitation implements Iterable<UILimitation.EditableComponent> {
		
		public enum EditableComponent {
			ROOT_DIRECTORY, SEARCH_BAR, MATERIAL_PROFILE, MATERIAL_TABLE
		}
		
		private final List<EditableComponent> limitedComponents; 
		/**
		 * <!-- begin-user-doc -->
		 * <!--  end-user-doc  -->
		 * @generated
		 */
		public UILimitation(EditableComponent... components) {
			this.limitedComponents = Arrays.asList(components);
			
		}
	
		public void limit(EditableComponent component) {
			this.limitedComponents.add(component);
		}

		@Override
		public Iterator<EditableComponent> iterator() {
			return this.limitedComponents.iterator();
		}

	}
}

