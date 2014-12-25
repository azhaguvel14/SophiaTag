package edu.ntust.csie.se.mdfk.sophiatag.service;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger;
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
	
	public static final int WRAP_WORD = 0;
	public static final int START_WITH = 1;
	private static final String AND_OPERATOR = "&&";
	private final TagDatabase database;
	private final Highlighter highlighter;
	private int searchConfig;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	MaterialSearcher(Highlighter highlighter){
		this(new TagDatabase(), highlighter);
	}
	
	MaterialSearcher(TagDatabase database, Highlighter highlighter){
		this.database = database;
		this.highlighter = highlighter;
		this.setSearchConfig(START_WITH);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	
	public SearchResult query(String text) {
		this.highlighter.clearKeywords();
		if (text.isEmpty()) {
			return new SearchResult(new LinkedList<Material>(), new LinkedList<String>());
		}
		
		Set<String> keywords = parseQueryText(text);
		Set<Collection<Material>> tags = this.queryTags(keywords);
		Set<Material> result = this.findIntersection(tags);
		if (result.isEmpty()) {
			this.highlighter.clearKeywords();
		}
		return new SearchResult(result, keywords);
	}
	
	public TagDatabase getTagDatabase() {
		return this.database;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Set<String> parseQueryText(String text) {
		TreeSet<String> result = new TreeSet<String>();
		String trimed;
		String[] tokens = text.split(AND_OPERATOR);
		for (String token : tokens) {
			trimed = token.trim();
			if (!trimed.isEmpty()) {
				result.add(trimed);
			}
		}
		
		return result.descendingSet();	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Set<Material> findIntersection(Set<Collection<Material>> setList) {
		if (setList.isEmpty()) {
			return new HashSet<Material>();
		}
		Iterator<Collection<Material>> itor = setList.iterator();
		Set<Material> result = new HashSet<Material>(itor.next());
		
		for (;itor.hasNext();) {
			result.retainAll(itor.next());
		}
		
		return result;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Set<Collection<Material>> queryTags(Set<String> tagLiterals) {
		
		switch (this.getSearchConfig()) {
		case WRAP_WORD:
			return wrapWordSearch(tagLiterals);
		case START_WITH:
			return startWithSearch(tagLiterals);
		}
		
		return null;
	}
	
	private Set<Collection<Material>> wrapWordSearch(Set<String> tagLiterals) {
		Tag tag;
		Set<Collection<Material>> tagSet = new HashSet<Collection<Material>>();
		for (String literal: tagLiterals) {
			
			tag = this.database.getTagIfExist(literal);
			
			if (tag == null) {
				tagSet.clear();
				return tagSet;
			}
			this.highlighter.addKeyword(literal);
			this.highlighter.highlightByLatestKeyword(tag);
			
			tagSet.add(tag.getTargetsView());
			
		}
		
		return tagSet;
	}
	
	private Set<Collection<Material>> startWithSearch(Set<String> keywords) {
		Set<Collection<Material>> tagSet = new HashSet<Collection<Material>>();
		Set<Material> union;
		for (String keyword: keywords) {
			
			union = new HashSet<Material>();
			this.highlighter.addKeyword(keyword);
			for (Tag tag: this.database.getPrefixedTags(keyword)) {
				this.highlighter.highlightByLatestKeyword(tag);
				union.addAll(tag.getTargetsView());
			}
			
			if (union.isEmpty()) {
				tagSet.clear();
				return tagSet;
			}
			
			tagSet.add(union);
			
		}
		
		return tagSet;
	}
	
	public int getSearchConfig() {
		return searchConfig;
	}

	public void setSearchConfig(int searchConfig) {
		this.searchConfig = searchConfig;
	}

	public static class SearchResult {
		private final Collection<Material> result;
		private final Collection<String> queryKeywords;
		
		private SearchResult(Collection<Material> result,Collection<String> queryKeywords) {
			this.result = result;
			this.queryKeywords = queryKeywords;
		}

		public Collection<Material> getResult() {
			return result;
		}

		public Collection<String> getQueryKeywords() {
			return queryKeywords;
		}
		
		public boolean hasResult() {
			return !this.result.isEmpty();
		}
		
	}
	
	
	public static class TagDatabase implements Serializable, TagTextChangedListener, MaterialTaggedListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = -749559691139964422L;
		private Map<String, Tag> tagMap;
		private NavigableMap<String, Tag> sortedTagMap;
		
		public TagDatabase() {
			this.tagMap = new HashMap<String, Tag>();
			this.sortedTagMap = new TreeMap<String, Tag>();
			this.restoreInit();
		}
		
		public Tag getTagIfExist(String tagLiteral) {
			return tagMap.get(tagLiteral);
		}
		
		public Tag getTag(String tagLiteral) {
			Tag tag = this.getTagIfExist(tagLiteral);
			return tag == null? new Tag(tagLiteral): tag;
		}
		
		public Collection<Tag> getPrefixedTags(String prefix) {
			Collection<Tag> result = new LinkedList<Tag>();
			Set<Entry<String, Tag>> potentialTags = this.sortedTagMap.tailMap(prefix).entrySet();
			
			for (Entry<String, Tag> pair : potentialTags) {
				if (!pair.getKey().startsWith(prefix)) {
					break;
				}
				result.add(pair.getValue());
			}
			
			return result;
		}
		
		public void drop() {
			this.tagMap.clear();
			this.sortedTagMap.clear();
		}
		
		public void onTextChanged(String oldText, Tag newTag) {
			this.tagMap.remove(oldText);
			this.tagMap.put(newTag.getText(), newTag);
			this.sortedTagMap.remove(oldText);
			this.sortedTagMap.put(newTag.getText(), newTag);
		}
		
		public void onTag(Tag tag, Material material) {
			if (!this.tagMap.containsKey(tag.getText())) {
				this.tagMap.put(tag.getText(), tag);
				this.sortedTagMap.put(tag.getText(), tag);
			}
		}
	
		public void onDetag(Tag tag, Material material) {
			if (!tag.isAttached()) {
				this.tagMap.remove(tag.getText());
				this.sortedTagMap.remove(tag.getText());
			}
			
		}

		void restoreInit() {
			MaterialTagger.getInstance().addMaterialTaggedListenerAsService(this);
			MaterialTagger.getInstance().addTagTextChangedListenerAsService(this);
		}
	}
}

