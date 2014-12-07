package edu.ntust.csie.se.mdfk.sophiatag.service;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger.TagTextChangedListener;

import java.util.List;

import edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger.MaterialTaggedListener;
import edu.ntust.csie.se.mdfk.sophiatag.data.Tag;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class MaterialSearcher {
	
	
	private final TagDatabase database;
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public MaterialSearcher(){
		this.database = new TagDatabase();
	}
	
	public MaterialSearcher(TagDatabase database){
		this.database = database;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Set<Material> query(String text) {
		
		Set<Tag> tags = this.queryTags(parseQueryText(text));
		List<Set<Material>> materialList = this.getMaterialList(tags);
		return this.findIntersection(materialList);
		
	}
	
	public TagDatabase getDatabase() {
		return this.database;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Set<String> parseQueryText(String text) {
		Set<String> result = new HashSet<String>();
		String[] tokens = text.split("&&");
		for (String token : tokens) {
			result.add(token.trim());
		}
		
		return result;	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Set<Material> findIntersection(List<Set<Material>> setList) {
		if (setList.isEmpty()) {
			return new HashSet<Material>();
		}
		
		Set<Material> result = new HashSet<Material>(setList.remove(0));
		
		for (Set<Material> materialSet: setList) {
			result.retainAll(materialSet);
		}
		
		return result;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Set<Tag> queryTags(Set<String> tagLiterals) {
		Tag tag;
		Set<Tag> tagSet = new HashSet<Tag>();
		for (String literal: tagLiterals) {
			tag = this.database.getTag(literal);
			if (tag == null) {
				tagSet.clear();
				return tagSet;
			}
			
			tagSet.add(tag);
			
		}
		
		return tagSet;
	}
	
	private List<Set<Material>> getMaterialList(Set<Tag> tags) {
		List<Set<Material>> materialList = new LinkedList<Set<Material>>();
		for (Tag tag: tags) {
			materialList.add(tag.getTargetsView());
		}
		
		return materialList;	
	}
	
	public static class TagDatabase implements Serializable, TagTextChangedListener, MaterialTaggedListener {
		private Map<String, Tag> tagMap;
		
		public TagDatabase() {
			this.tagMap = new HashMap<String, Tag>();
		}
		
		public Tag getTag(String tagLiteral) {
			return tagMap.get(tagLiteral);
		}
		
		public void drop() {
			this.tagMap.clear();
		}
		
		public void onTextChanged(String oldText, Tag newTag) {
			this.tagMap.remove(oldText);
			this.tagMap.put(newTag.getText(), newTag);
		}
		
		public void onTag(Tag tag, Material material) {
			if (!this.tagMap.containsKey(tag.getText())) {
				this.tagMap.put(tag.getText(), tag);
			}
		}
	
		public void onDetag(Tag tag, Material material) {
			if (!tag.isAttached()) {
				this.tagMap.remove(tag.getText());
			}
		}
	}
}

