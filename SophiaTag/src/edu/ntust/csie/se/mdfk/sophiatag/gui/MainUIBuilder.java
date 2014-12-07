package edu.ntust.csie.se.mdfk.sophiatag.gui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
	
	private Map<FuntionalLimitation.LimitableFunction, Boolean> limitedFlagMap;
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
		this.limitedFlagMap = new HashMap<FuntionalLimitation.LimitableFunction, Boolean>();
		this.setupComponentMap(Arrays.asList(FuntionalLimitation.LimitableFunction.values()), true);
		this.setupComponentMap(limitation, false);
		
	}
	
	private void setupComponentMap(Iterable<FuntionalLimitation.LimitableFunction> iterable, boolean limited) {
		for (FuntionalLimitation.LimitableFunction component: iterable) {
			this.limitedFlagMap.put(component, limited);
		}
	}
	
	private static void initSubBuilders() {
		SUB_BUIDLER_MAP.put(FuntionalLimitation.LimitableFunction.CHANGE_ROOT_DIRECTORY, new SubUIBuilder() {

			@Override
			public JComponent buildComponent(MainUIController boundController, boolean limited) {
				return null;
			}
			
		});
		
		SUB_BUIDLER_MAP.put(FuntionalLimitation.LimitableFunction.SEARCH_BAR, new SubUIBuilder() {

			@Override
			public JComponent buildComponent(MainUIController boundController, boolean limited) {
				return null;
			}
			
		});
		
		SUB_BUIDLER_MAP.put(FuntionalLimitation.LimitableFunction.CHANGE_TAG_ON_MATERIAL, new SubUIBuilder() {

			@Override
			public JComponent buildComponent(MainUIController boundController, boolean limited) {
				return null;
			}
			
		});
		
		SUB_BUIDLER_MAP.put(FuntionalLimitation.LimitableFunction.EDIT_MATERIAL_TABLE, new SubUIBuilder() {

			@Override
			public JComponent buildComponent(MainUIController boundController, boolean limited) {
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
	
	public JFrame build(MainUIController boundController) {
		JFrame mainFrame = getEmptyMainFrame();
		
		JPanel mainUIPanel = new JPanel();
		mainUIPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
//		
//		mainUIPanel.add(this.buildUserStatus());
//		for (FuntionalLimitation.LimitableFunction limitableFunction: FuntionalLimitation.LimitableFunction.values()) {
//			mainUIPanel.add(SUB_BUIDLER_MAP.get(limitableFunction).buildComponent(boundController, limitedFlagMap.get(limitableFunction)));
//		}
		
		
		mainFrame.getContentPane().add(mainUIPanel);
		return mainFrame;
	}
	
	private JFrame getEmptyMainFrame() {
		JFrame mainFrame = new JFrame();
		mainFrame = new JFrame();
		mainFrame.setTitle("SophiaTag");
		mainFrame.setBounds(100, 100, 600, 500);
//		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		return mainFrame;
	}
	
	private JComponent buildUserStatus() {
		return null;
	}
	
	private interface SubUIBuilder {
		public JComponent buildComponent(MainUIController boundController, boolean limited);
	}
}

