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
	
	private static final TextGetter HTML_GETTER = new TextGetter() {
		@Override
		public String getText(Tag tag) {
			return tag.getHighlightHTML();
		}
	};
	
	private static final TextGetter NORMAL_GETTER = new TextGetter() {
		@Override
		public String getText(Tag tag) {
			return tag.getText();
		}
	};
	
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		@SuppressWarnings("unchecked")
		Collection<Tag> tags = (Collection<Tag>)value;
		
		if (isSelected) {
			return super.getTableCellRendererComponent(table, toTagString(tags, NORMAL_GETTER), isSelected, hasFocus, row, column);
		}
		return super.getTableCellRendererComponent(table, toTagString(tags, HTML_GETTER), isSelected, hasFocus, row, column);
	}
	
	private static String toTagString(Collection<Tag> tags, TextGetter getter) {
		StringBuffer value = new StringBuffer("<html><body style=\"white-space:nowrap\">");
		for (Iterator<Tag> iter = tags.iterator(); iter.hasNext();) {
			value.append(getter.getText(iter.next()));
			if (iter.hasNext()) {
				value.append(", ");
			}
		}
		value.append("</body></html>");
		return value.toString();
	}
	
	private interface TextGetter {
		public String getText(Tag tag);
	}
}
