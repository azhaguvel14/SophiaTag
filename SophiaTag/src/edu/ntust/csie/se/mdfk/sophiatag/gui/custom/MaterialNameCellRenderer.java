/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.custom;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;

/**
 * @author maeglin89273
 *
 */
public class MaterialNameCellRenderer extends DefaultTableCellRenderer {
	private static final String LOST_HINT = "(LOST!) ";
	private static final String NORMAL_FILE_TMP = "<html><body style=\"color:" + ColorSwatch.toNoAlphaString(ColorSwatch.GREEN) + ";font-weight:bold;\">%s</body></html>";
	private static final String LOST_HINT_TMP = "<html><body style=\"color:red;\"><span style=\"white-space:nowrap;font-weight:bold;\">" + LOST_HINT + "</span>%s</body></html>";
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		Material material = (Material)value;
		String materialName = material.getName();
		Component delegate;
		
		if (isSelected) {
			if (material.isLost()) {
				materialName = LOST_HINT + materialName;
			}
			delegate = super.getTableCellRendererComponent(table, materialName, isSelected, hasFocus, row, column);
		} else {
			if (material.isLost()) {
				materialName = String.format(LOST_HINT_TMP, materialName);
			} else if (material.isAttached()) {
				materialName = String.format(NORMAL_FILE_TMP, materialName);
			}
			
			delegate = super.getTableCellRendererComponent(table, materialName, isSelected, hasFocus, row, column);
		}
		return delegate;
		
	}
	
}
