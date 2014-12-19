package edu.ntust.csie.se.mdfk.sophiatag.service;
import java.io.Serializable;
import java.util.LinkedList;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger;
import edu.ntust.csie.se.mdfk.sophiatag.data.UniqueList;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.util.HashSet;

import edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger.MaterialDiscardedListener;


public class MaterialList implements MaterialDiscardedListener, Iterable<Material>, Serializable {
	
	private List<Material> materials;
	private MaterialList selection = null;
	
	MaterialList() {
		this(new UniqueList<Material>());
	}
	
	public MaterialList(Collection<Material> materials) {
			this.materials = new UniqueList<Material>(materials);
			MaterialTagger.getInstance().addMaterialDiscardedListener(this);
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
	
	public int size() {
		return this.materials.size();
	}
	
	public void setList(Collection<Material> newMaterials) {
		this.materials.clear();
		this.addMaterials(newMaterials);
	}
	
	public void onDiscarded(Material material) {
		materials.remove(material);
	}
	
	public MaterialList select(Set<Material> selections) {
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
		private List<Material> parent;
		
		private Selection(Set<Material> selections, List<Material> parent) {
			selections.retainAll(parent);
			this.addMaterials(selections);
		}
		
		@Override
		public void addMaterial(Material material) {
			this.parent.add(material);
			super.addMaterial(material);
		}
		
		@Override
		public void addMaterials(Collection<Material> materials) {
			this.parent.addAll(materials);
			super.addMaterials(materials);
		}
		
	}
	
}

