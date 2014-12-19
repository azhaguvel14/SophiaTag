/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.custom;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.data.Tag;
import edu.ntust.csie.se.mdfk.sophiatag.data.UniqueList;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialList;

/**
 * @author maeglin89273
 *
 */
public class MaterialTable extends JTable {

	/**
	 * 
	 */
	public MaterialTable() {
		super(new MaterialListModel());
		this.setSelectionRestrictions();
		
		this.getColumnModel().getColumn(0).setPreferredWidth(50);
		this.getColumnModel().getColumn(1).setPreferredWidth(50);
		
		this.getTableHeader().setReorderingAllowed(false);
		this.getColumnModel().getColumn(0).setCellRenderer(new MaterialNameCellRenderer());
		
	}
	
	private void setSelectionRestrictions() {
		this.setCellSelectionEnabled(false);
		this.setColumnSelectionAllowed(false);
		this.setRowSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}
	
	public static class MaterialListModel extends AbstractTableModel {
		
		private List<String> columnList;
		private GetFieldFunction[] getFieldFunctions;
		private MaterialList content;
		
		public MaterialListModel() {
			initializeColumnList();
			initializeFieldFunctions();
		}
		
		private void initializeColumnList() {
			this.columnList = new UniqueList<String>();
			this.columnList.add("Name");
			this.columnList.add("Directory");
			this.columnList.add("Tags");
		}
		
		private void initializeFieldFunctions() {
			this.getFieldFunctions = new GetFieldFunction[3];
			
			this.getFieldFunctions[0] = new GetFieldFunction() {
				@Override
				public Object getSpecificField(Material material) {
					return material;
				}
			};
			
			this.getFieldFunctions[1] = new GetFieldFunction() {
				@Override
				public Object getSpecificField(Material material) {
					return material.getDirectoryPath();
				}
			};
			
			this.getFieldFunctions[2] = new GetFieldFunction() {
				@Override
				public Object getSpecificField(Material material) {
					return this.toTagsString(material.getTargetsView());
				}
				
				private String toTagsString(Set<Tag> tags) {
					StringBuffer value = new StringBuffer();
					for (Iterator<Tag> iter = tags.iterator(); iter.hasNext();) {
						value.append(iter.next().getText());
						if (iter.hasNext()) {
							value.append(", ");
						}
					}
					
					return value.toString();
				}
			};
			
		}
		
		public String getColumnName(int column) {
	        return this.columnList.get(column);
	    }

	   
	    public int findColumn(String columnName) {    
	        return this.columnList.indexOf(columnName);
	    }

	    
	    public Class<?> getColumnClass(int columnIndex) {
	        return columnIndex == 0 ? Material.class: String.class;
	    }
		
		@Override
		public int getRowCount() {
			return this.content.size();
		}

		@Override
		public int getColumnCount() {
			return this.columnList.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return this.getFieldFunctions[columnIndex].getSpecificField(this.content.get(rowIndex));
		}
		
		public void setList(MaterialList list) {
			this.content = list;
			this.fireTableDataChanged();
		}
		
		private interface GetFieldFunction {
			public abstract Object getSpecificField(Material material);
		}
	}
	
	
}
