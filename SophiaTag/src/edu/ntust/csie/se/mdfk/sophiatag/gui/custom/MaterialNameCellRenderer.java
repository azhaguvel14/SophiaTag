/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.custom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;

/**
 * @author maeglin89273
 *
 */
public class MaterialNameCellRenderer extends DefaultTableCellRenderer {
	
	private static final Color NEW_FILE_COLOR = new Color(0, 155, 88);
	private static final Color LOST_FILE_COLOR = Color.RED;
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Material material = (Material)value;
		String materialName = material.getName();
		Component delegate;
		
		
		if (material.isLost()) {
			materialName += " (Lost)";
			delegate = super.getTableCellRendererComponent(table, materialName, isSelected, hasFocus, row, column);
			delegate.setForeground(LOST_FILE_COLOR);
			delegate.setFont(this.italicThisFont(delegate.getFont()));
		} else {
			
			delegate = super.getTableCellRendererComponent(table, materialName, isSelected, hasFocus, row, column);
			if (!material.isAttached()) {
				delegate.setFont(this.boldThisFont(delegate.getFont()));
				delegate.setForeground(NEW_FILE_COLOR);
			} 
		}
		
		return delegate;
		
	}
	
	//font problem
	private Font italicThisFont(Font original) {
		return original.deriveFont(Font.ITALIC);
	}
	
	private Font boldThisFont(Font original) {
		return original.deriveFont(Font.BOLD);
	}
	
	
}
