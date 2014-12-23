/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.custom;

import java.awt.Component;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import edu.ntust.csie.se.mdfk.sophiatag.data.Tag;

/**
 * @author maeglin89273
 *
 */
public class TagsCellRenderer extends DefaultTableCellRenderer {
	
	
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		@SuppressWarnings("unchecked")
		Collection<Tag> tags = (Collection<Tag>)value;
		return super.getTableCellRendererComponent(table, toTagString(tags), isSelected, hasFocus, row, column);
	}
	
	private static String toTagString(Collection<Tag> tags) {
		StringBuffer value = new StringBuffer("<html><body style=\"white-space:nowrap\">");
		for (Iterator<Tag> iter = tags.iterator(); iter.hasNext();) {
			value.append(iter.next().getHighlightHTML());
			if (iter.hasNext()) {
				value.append(", ");
			}
		}
		value.append("</body></html>");
		return value.toString();
	}
}
