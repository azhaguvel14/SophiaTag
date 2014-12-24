/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.service;

import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger;
import edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger.MaterialTaggedListener;
import edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger.TagTextChangedListener;
import edu.ntust.csie.se.mdfk.sophiatag.data.Tag;
import edu.ntust.csie.se.mdfk.sophiatag.gui.custom.ColorSwatch;

/**
 * @author maeglin89273
 *
 */
public class Highlighter implements TagTextChangedListener,	MaterialTaggedListener {
	
	private static final String HIGHTLIGHT_COLOR_STRING = ColorSwatch.toNoAlphaString(ColorSwatch.LIGHT_YELLOW);
	private static final String KEYWORD_COLOR_STRING = ColorSwatch.toNoAlphaString(ColorSwatch.ORANGE);
	
	private static final String HIGHLIGHT_WRAPPER_START = "<span style=\"font-weight:bold;background:" + HIGHTLIGHT_COLOR_STRING + "; color:" + KEYWORD_COLOR_STRING + ";\">";
	private static final String HIGHLIGHT_WRAPPER_END = "</span>";
	
	private NavigableSet<String> keywords;
	private Set<Tag> highLightedTags;
	private String latestKeywordCache;
	
	Highlighter() {
		this.keywords = new TreeSet<String>();
		this.highLightedTags = new HashSet<Tag>();
		MaterialTagger.getInstance().addMaterialTaggedListenerAsService(this);
		MaterialTagger.getInstance().addTagTextChangedListenerAsService(this);
	}
	
	public void addKeyword(String keyword) {
		this.latestKeywordCache = keyword;
		this.keywords.add(keyword);
	}
	
	public void highlightByLatestKeyword(Tag tag) {
		if (this.latestKeywordCache == null) {
			return;
		}
		this.markAndAddTag(tag);
		
	}
	
	public void highlight(Tag tag) {
		this.latestKeywordCache = this.keywords.floor(tag.getText());
		this.markAndAddTag(tag);
	}
	
	private boolean markHighlightOnTag(String keyword, Tag tag) {
		if (keyword == null) {
			return false;
		}
		int prefixStart = tag.getText().indexOf(keyword);
		
		if (prefixStart < 0) {
			return false;
		}
		
		int posfixStart = prefixStart + keyword.length();
		
		StringBuffer buffer = new StringBuffer(tag.getText().substring(0, prefixStart));
		buffer.append(HIGHLIGHT_WRAPPER_START);
		buffer.append(keyword);
		buffer.append(HIGHLIGHT_WRAPPER_END);
		buffer.append(tag.getText().substring(posfixStart));
		
		tag.setHighlightHTML(buffer.toString());
		return true;
	}
	
	private void markAndAddTag(Tag tag) {
		if(this.markHighlightOnTag(this.latestKeywordCache, tag)) {
			this.highLightedTags.add(tag);
		}
	}
	
	public void clearKeywords() {
		this.keywords.clear();
		for (Tag tag: this.highLightedTags) {
			tag.clearHighlightHTML();
		}
		
		highLightedTags.clear();
	}
	/* (non-Javadoc)
	 * @see edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger.MaterialTaggedListener#onDetag(edu.ntust.csie.se.mdfk.sophiatag.data.Tag, edu.ntust.csie.se.mdfk.sophiatag.data.Material)
	 */
	@Override
	public void onDetag(Tag tag, Material material) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger.MaterialTaggedListener#onTag(edu.ntust.csie.se.mdfk.sophiatag.data.Tag, edu.ntust.csie.se.mdfk.sophiatag.data.Material)
	 */
	@Override
	public void onTag(Tag tag, Material material) {
		this.highlight(tag);
	}

	/* (non-Javadoc)
	 * @see edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger.TagTextChangedListener#onTextChanged(java.lang.String, edu.ntust.csie.se.mdfk.sophiatag.data.Tag)
	 */
	@Override
	public void onTextChanged(String oldText, Tag newTag) {
		this.highlight(newTag);
	}

}
