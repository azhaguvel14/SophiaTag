package edu.ntust.csie.se.mdfk.sophiatag.gui.custom;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger;
import edu.ntust.csie.se.mdfk.sophiatag.data.Tag;
import edu.ntust.csie.se.mdfk.sophiatag.data.UniqueList;
import edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger.MaterialTaggedListener;
import edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger.TagTextChangedListener;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialList;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialList.MaterialRemovedListener;

public class MaterialListModel extends AbstractTableModel implements MaterialRemovedListener, MaterialTaggedListener, TagTextChangedListener {
	
	private List<String> columnList;
	private MaterialListModel.GetFieldFunction[] getFieldFunctions;
	
	private MaterialList content = null;
	private Collection<Tag> highlights;
	private Path rootDir;
	
	private static final Class<?>[] classes = {Material.class, String.class, Collection.class};
	
	public MaterialListModel() {
		initializeColumnList();
		initializeFieldFunctions();
		
		MaterialTagger.getInstance().addMaterialTaggedListener(this);
		MaterialTagger.getInstance().addTagTextChangedListener(this);
	}
	
	private void initializeColumnList() {
		this.columnList = new UniqueList<String>();
		this.columnList.add("Name");
		this.columnList.add("Directory");
		this.columnList.add("Tags");
	}
	
	private void initializeFieldFunctions() {
		this.getFieldFunctions = new MaterialListModel.GetFieldFunction[3];
		
		this.getFieldFunctions[0] = new GetFieldFunction() {
			@Override
			public Object getSpecificField(Material material) {
				return material;
			}
		};
		
		this.getFieldFunctions[1] = new GetFieldFunction() {
			@Override
			public Object getSpecificField(Material material) {
				return rootDir.relativize(dirPath(material)).toString();
			}
			
			private Path dirPath(Material material) {
				return material.getUnderlyingFile().toPath().getParent();
			}
		};
		
		this.getFieldFunctions[2] = new GetFieldFunction() {
			@Override
			public Object getSpecificField(Material material) {
				return material.getTargetsView();
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
        return classes[columnIndex];
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
	
	public Material getMaterialAt(int index) {
		return this.content.get(index);
	}
	
	
	public void setList(MaterialList list) {
		if (this.content == null) {
			list.addMaterialRemovedListener(this);
		}
		this.content = list;
		this.fireTableDataChanged();
		
	}
	
	public void unsubscribeUpdates() {
		if (this.content != null) {
			this.content.removeMaterialRemovedListener(this);
		}
		
		MaterialTagger.getInstance().removeMaterialTaggedListener(this);
		MaterialTagger.getInstance().removeTagTextChangedListener(this);
	}
	

	public void setHighlightedTags(Collection<Tag> tags) {
		this.highlights = tags;
	}
	
	public Collection<Tag> getHighlightedTags() {
		return this.highlights;
	}
	
	public void setRootDirectory(String rootDir) {
		this.rootDir = Paths.get(rootDir);
	}
	
	@Override
	public void onRemoved(Material material, int indexInList) {
		this.fireTableRowsDeleted(indexInList, indexInList);
	}
	
	@Override
	public void onTextChanged(String oldText, Tag newTag) {
		for (Material material: newTag.getTargetsView()) {
			this.updateMaterialRow(material);
		}
	}

	@Override
	public void onDetag(Tag tag, Material material) {
		this.updateMaterialRow(material);
	}

	@Override
	public void onTag(Tag tag, Material material) {
		this.updateMaterialRow(material);
	}
	
	private void updateMaterialRow(Material material) {
		int row = this.content.indexOf(material);
		this.fireTableRowsUpdated(row, row);
	}
	
	private interface GetFieldFunction {
		public abstract Object getSpecificField(Material material);
	}

}