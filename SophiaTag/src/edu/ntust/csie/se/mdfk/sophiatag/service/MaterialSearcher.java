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
	
	public static final int WRAP_WORD = Highlighter.WRAPPING_MODE;
	public static final int START_WITH = Highlighter.LEADING_MODE;
	public static final String SEPARATOR = "&&";
	private final TagDatabase database;
	private final Highlighter highlighter;
	private int searchConfig;
	private final SearchFunction[] searchFunctions;
	
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
		this.searchFunctions = new SearchFunction[2];
		this.initSearchFunctions();
	}
	
	private void initSearchFunctions() {
		
		this.searchFunctions[WRAP_WORD] = new SearchFunction() {

			@Override
			public Collection<Tag> search(String keyword) {
				return MaterialSearcher.this.database.getTagsIgnoreCase(keyword);
			}
			
		};
		
		this.searchFunctions[START_WITH] = new SearchFunction() {

			@Override
			public Collection<Tag> search(String keyword) {
				return MaterialSearcher.this.database.getPrefixedTags(keyword);
			}
			
		};
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
		this.highlighter.setHighlightingMode(this.getSearchConfig());
		Set<Collection<Material>> tags = this.queryTags(keywords, this.searchFunctions[this.getSearchConfig()]);
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
		String[] tokens = text.split(SEPARATOR);
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
	
	private Set<Collection<Material>> queryTags(Set<String> keywords, SearchFunction  function) {
		Set<Collection<Material>> tagSet = new HashSet<Collection<Material>>();
		Set<Material> union;
		for (String keyword: keywords) {
			
			union = new HashSet<Material>();
			this.highlighter.addKeyword(keyword);
			for (Tag tag: function.search(keyword)) {
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
	
	private interface SearchFunction {
		public Collection<Tag> search(String keyword);
	}
	
	public static class TagDatabase implements Serializable, TagTextChangedListener, MaterialTaggedListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = -749559691139964422L;
		private Map<String, Map<String, Tag>> tagMap;
		private NavigableMap<String, Map<String, Tag>> sortedTagMap;
		
		public TagDatabase() {
			this.tagMap = new HashMap<String, Map<String, Tag>>();
			this.sortedTagMap = new TreeMap<String, Map<String, Tag>>();
			this.restoreInit();
		}
		
		public Collection<Tag> getTagsIgnoreCase(String tagLiteral) {
			Map<String, Tag> submap = tagMap.get(tagLiteral.toLowerCase());
			
			return submap == null? new LinkedList<Tag>(): Collections.unmodifiableCollection(submap.values());
		}
		
		public Tag getTagIfExist(String tagLiteral) {
			Map<String, Tag> submap = tagMap.get(tagLiteral.toLowerCase());
			return submap == null? null: submap.get(tagLiteral);
		}
		
		public Tag getTag(String tagLiteral) {
			Tag tag = this.getTagIfExist(tagLiteral);
			return tag == null? new Tag(tagLiteral): tag;
		}
		
		public Collection<Tag> getPrefixedTags(String prefix) {
			prefix = prefix.toLowerCase();
			Collection<Tag> result = new LinkedList<Tag>();
			Set<Entry<String, Map<String, Tag>>> potentialTags = this.sortedTagMap.tailMap(prefix).entrySet();
			
			for (Entry<String, Map<String, Tag>> pair : potentialTags) {
				if (!pair.getKey().startsWith(prefix)) {
					break;
				}
				result.addAll(pair.getValue().values());
			}
			
			return result;
		}
		
		public void drop() {
			this.tagMap.clear();
			this.sortedTagMap.clear();
		}
		
		public void onTextChanged(String oldText, Tag newTag) {
			
			this.remove(oldText);
			this.put(newTag);
		}
		
		private void remove(String text) {
			String index = text.toLowerCase();
			Map<String, Tag> sharedSubmap = this.tagMap.get(index);
			if (sharedSubmap != null) {
				sharedSubmap.remove(text);
				
				if (sharedSubmap.isEmpty()) {
					this.tagMap.remove(index);
					this.sortedTagMap.remove(index);
				}
			}
		}
		
		private void put(Tag tag) {
			String tagIndex = tag.getText().toLowerCase();
			Map<String, Tag> sharedSubmap = this.tagMap.get(tagIndex);
			if (sharedSubmap == null) {
				sharedSubmap = new HashMap<String, Tag>();
				this.tagMap.put(tagIndex, sharedSubmap);
				this.sortedTagMap.put(tagIndex, sharedSubmap);
			}
			
			sharedSubmap.put(tag.getText(), tag);
		}
		
		public void onTag(Tag tag, Material material) {
			this.put(tag);
		}
	
		public void onDetag(Tag tag, Material material) {
			if (!tag.isAttached()) {
				this.remove(tag.getText());
			}
		}

		void restoreInit() {
			MaterialTagger.getInstance().addMaterialTaggedListenerAsService(this);
			MaterialTagger.getInstance().addTagTextChangedListenerAsService(this);
		}
	}
}

