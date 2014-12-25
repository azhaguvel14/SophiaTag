package edu.ntust.csie.se.mdfk.sophiatag.service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger;
import edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger.MaterialDiscardedListener;
import edu.ntust.csie.se.mdfk.sophiatag.data.UniqueList;


public class MaterialList implements MaterialDiscardedListener, Iterable<Material>, Serializable {
	
	private List<Material> materials;
	private transient MaterialList selection = null;
	private transient int latestRemovedIndex = -1;
	
	MaterialList() {
		this(new UniqueList<Material>());
	}
	
	MaterialList(Collection<Material> materials) {
		this.materials = new UniqueList<Material>(materials);
		restoreInit();
	}
	
	void restoreInit() {
		MaterialTagger.getInstance().addMaterialDiscardedListenerAsService(this);
	}
	
	public void addMaterial(Material material) {
		this.materials.add(material);
	}
	
	public void addMaterials(Collection<Material> materials) {
		this.materials.addAll(materials);
	}
	
	public boolean hasMaterials() {
		return !this.materials.isEmpty();	
	}
	
	public Material get(int index) {
		return this.materials.get(index);
	}
	
	public int indexOf(Material material) {
		return this.materials.indexOf(material);
	}
	
	public int size() {
		return this.materials.size();
	}
	
	public void setList(Collection<Material> newMaterials) {
		this.materials.clear();
		this.addMaterials(newMaterials);
	}
	
	public int getLatestRemovedIndex() {
		return this.latestRemovedIndex;
	}
	
	public void onDiscarded(Material material) {
		
		this.latestRemovedIndex  = materials.indexOf(material);
		materials.remove(material);
		
	}
	
	public MaterialList select(Collection<Material> selections) {
		if (this.selection == null) {
			this.selection = new Selection(selections, materials);
		} else {
			this.selection.setList(selections);
		}
		
		return this.selection;
		
	}
	
	@Override
	public Iterator<Material> iterator() {
		return this.materials.iterator();
	}
	
	private class Selection extends MaterialList {
		
		private Selection(Collection<Material> selections, List<Material> parent) {
			selections.retainAll(parent);
			this.addMaterials(selections);
		}
		
		@Override
		public void addMaterial(Material material) {
			MaterialList.this.addMaterial(material);
			super.addMaterial(material);
		}
		
		@Override
		public void addMaterials(Collection<Material> materials) {
			MaterialList.this.addMaterials(materials);
			super.addMaterials(materials);
		}
		
	}
	
}

