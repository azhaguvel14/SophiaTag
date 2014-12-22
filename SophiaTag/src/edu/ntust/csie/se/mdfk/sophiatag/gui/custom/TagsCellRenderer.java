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
	private static final String HIGHTLIGHT_COLOR_STRING = "#ffff7f";// + ColorSwatch.toNoAlphaString(ColorSwatch.YELLOW);
	private static final String KEYWORD_COLOR_STRING = "#3c3c3c";
	
	private static final String HIGHLIGHT_WRAPPER_START = "<span style=\"background:" + HIGHTLIGHT_COLOR_STRING + "; color:" + KEYWORD_COLOR_STRING + ";\">";
	private static final String HIGHLIGHT_WRAPPER_END = "</span>";
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		Collection<Tag> highlights = ((MaterialListModel) table.getModel()).getHighlightedTags();
		@SuppressWarnings("unchecked")
		Collection<Tag> tags = (Collection<Tag>)value;
		String shownValue = highlights != null ? toHighlightedTagString(tags, highlights): toTagString(tags);
		return super.getTableCellRendererComponent(table, shownValue, isSelected, hasFocus, row, column);
	}
	
	private static StringBuffer highlightIt(String keyword) {
		StringBuffer buffer = new StringBuffer(HIGHLIGHT_WRAPPER_START);
		buffer.append(keyword);
		buffer.append(HIGHLIGHT_WRAPPER_END);
		
		return buffer;
		
	}
	
	private static String toHighlightedTagString(Collection<Tag> tags, Collection<Tag> highlights) {
		StringBuffer value = new StringBuffer("<html>");
		Tag tag;
		for (Iterator<Tag> iter = tags.iterator(); iter.hasNext();) {
			tag = iter.next();
			if (highlights.contains(tag)) {
				value.append(highlightIt(tag.getText()));
			} else {
				value.append(tag.getText());
			}
			
			if (iter.hasNext()) {
				value.append(", ");
			}
		}
		
		value.append("</html>");
		return value.toString();
	}
	
	private static String toTagString(Collection<Tag> tags) {
		StringBuffer value = new StringBuffer();
		for (Iterator<Tag> iter = tags.iterator(); iter.hasNext();) {
			value.append(iter.next().getText());
			if (iter.hasNext()) {
				value.append(", ");
			}
		}
		
		return value.toString();
	}
}
