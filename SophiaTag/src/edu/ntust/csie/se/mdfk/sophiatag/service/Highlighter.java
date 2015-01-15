/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.service;

import java.awt.Color;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
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
	
	public static final int LEADING_MODE = 0;
	public static final int WRAPPING_MODE = 1;
	
	private static final String HIGHLIGHT_OUTTER_WRAPPER_START_TMPLATE = "<span style=\"background:%s;\">";
	private static final String HIGHLIGHT_INNER_WRAPPER_START_TMPLATE = "<span style=\"font-weight:bold; color:%s;\">";
	private static final String HIGHLIGHT_WRAPPER_END = "</span>";
	
	private NavigableMap<String, KeywordColorBundle> keywords;
	private Set<Tag> highLightedTags;
	private Entry<String, KeywordColorBundle> latestKeywordCache;
	
	private Random hueGenerator;
	private static final long RAND_SEED = 77 * 68 * 70 * 75 * ((18 * 27 * 49 * 54 * 55) << 3);
	
	private int mode = LEADING_MODE;
	
	Highlighter() {
		this.hueGenerator = new Random(RAND_SEED);
		this.keywords = new TreeMap<String, KeywordColorBundle>();
		this.highLightedTags = new HashSet<Tag>();
		
		MaterialTagger.getInstance().addMaterialTaggedListenerAsService(this);
		MaterialTagger.getInstance().addTagTextChangedListenerAsService(this);
	}
	
	public void addKeyword(String keyword) {
		keyword = keyword.toLowerCase();
		this.keywords.put(keyword, new KeywordColorBundle(this.hueGenerator.nextFloat()));
		this.latestKeywordCache = this.keywords.floorEntry(keyword);
	}
	
	public void highlightByLatestKeyword(Tag tag) {
		if (this.latestKeywordCache == null) {
			return;
		}
		this.highlightAndAddTag(this.latestKeywordCache, tag);
		
	}
	
	public void highlight(Tag tag) {
		Entry<String, KeywordColorBundle> entry = this.keywords.floorEntry(tag.getText().toLowerCase());
		if (entry == null) {
			return;
		}
		this.latestKeywordCache = entry;
		this.highlightAndAddTag(entry, tag);
	}
	
	private boolean markHighlightOnTag(Entry<String, KeywordColorBundle> entry, Tag tag) {
		
		String keyword = entry.getKey();
		String originalText = tag.getText();
		String lowerText = originalText.toLowerCase();
		int prefixStart = lowerText.indexOf(keyword);
		
		
		if (prefixStart != 0) {
			return false;
		}
		
		if (this.mode == WRAPPING_MODE && !lowerText.equals(keyword) ) {
			return false;
		}
		
		int posfixStart = prefixStart + keyword.length();
		
		KeywordColorBundle colors = entry.getValue();
		StringBuffer buffer = new StringBuffer(String.format(HIGHLIGHT_OUTTER_WRAPPER_START_TMPLATE, colors.backColor));
		buffer.append(originalText.substring(0, prefixStart));
		buffer.append(String.format(HIGHLIGHT_INNER_WRAPPER_START_TMPLATE, colors.foreColor));
		buffer.append(originalText.substring(prefixStart, posfixStart));
		buffer.append(HIGHLIGHT_WRAPPER_END);
		buffer.append(originalText.substring(posfixStart));
		buffer.append(HIGHLIGHT_WRAPPER_END);
		
		tag.setHighlightHTML(buffer.toString());
		return true;
	}
	
	private void highlightAndAddTag(Entry<String, KeywordColorBundle> entry, Tag tag) {
		if(this.markHighlightOnTag(entry, tag)) {
			this.highLightedTags.add(tag);
		}
	}
	
	public void clearKeywords() {
		this.keywords.clear();
		for (Tag tag: this.highLightedTags) {
			tag.clearHighlightHTML();
		}
		
		highLightedTags.clear();
		this.hueGenerator.setSeed(RAND_SEED);
	}
	
	public void setHighlightingMode(int mode) {
		this.mode = mode;
	}
	
	public int getHighlightingMode() {
		return this.mode;
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
	
	private class KeywordColorBundle {
		private String foreColor;
		private String backColor;
		
		private KeywordColorBundle(float hue) {
			this.foreColor = ColorSwatch.toNoAlphaString(Color.getHSBColor(hue, 0.95f, 0.9f));
			this.backColor = ColorSwatch.toNoAlphaString(Color.getHSBColor(hue, 0.1f, 1f));
		}
		
		
	}
}
