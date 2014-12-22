/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.custom;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 * @author maeglin89273
 *
 */
public class MaterialTable extends JTable {

	
	public MaterialTable() {
		super(new MaterialListModel());
		this.setSelectionRestrictions();
		
		this.getTableHeader().setReorderingAllowed(false);
		this.getColumnModel().getColumn(0).setCellRenderer(new MaterialNameCellRenderer());
		this.getColumnModel().getColumn(2).setCellRenderer(new TagsCellRenderer());
		
	}
	
	private void setSelectionRestrictions() {
		this.setCellSelectionEnabled(false);
		this.setColumnSelectionAllowed(false);
		this.setRowSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}
	
	
}
