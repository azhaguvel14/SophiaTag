/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.custom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;

/**
 * @author maeglin89273
 *
 */
public class MaterialNameCellRenderer extends DefaultTableCellRenderer {
	private static final Color NORMAL_FILE_COLOR = ColorSwatch.GREEN;
	private static final Color NEW_FILE_COLOR = new Color(60, 60, 60);//ColorSwatch.GREEN;
	private static final Color LOST_FILE_COLOR = ColorSwatch.RED;
	private static final Color SELECTED_COLOR = Color.WHITE;
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		Material material = (Material)value;
		String materialName = material.getName();
		Component delegate;
		
		if (isSelected) {
			if (material.isLost()) {
				materialName += " (Lost)";
			}
			delegate = super.getTableCellRendererComponent(table, materialName, isSelected, hasFocus, row, column);
			delegate.setForeground(SELECTED_COLOR);
		} else {
			if (material.isLost()) {
		
				materialName += " (Lost)";
				delegate = super.getTableCellRendererComponent(table, materialName, isSelected, hasFocus, row, column);
				delegate.setForeground(LOST_FILE_COLOR);
				delegate.setFont(this.italicThisFont(delegate.getFont()));
			} else {
				
				delegate = super.getTableCellRendererComponent(table, materialName, isSelected, hasFocus, row, column);
				if (!material.isAttached()) {
					
					delegate.setForeground(NEW_FILE_COLOR);
				} else {
					delegate.setFont(this.boldThisFont(delegate.getFont()));
					delegate.setForeground(NORMAL_FILE_COLOR);
				}
			}
		}
		return delegate;
		
	}
	
	private Font italicThisFont(Font original) {
		return original.deriveFont(Font.ITALIC);
	}
	
	private Font boldThisFont(Font original) {
		return original.deriveFont(Font.BOLD);
	}
	
	
}
