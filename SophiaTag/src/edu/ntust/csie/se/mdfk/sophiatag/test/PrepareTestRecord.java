/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.test;

import java.util.Random;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger;
import edu.ntust.csie.se.mdfk.sophiatag.data.Tag;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialList;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

/**
 * @author maeglin89273
 *
 */
public class PrepareTestRecord {
	
	private static final String TEST_PATH = "/home/maeglin89273/Desktop/Android App";
	
	private static final int TAGGING_TIMES = 50;
	private static final int TAG_ID_RANGE = 6;
	private static final float RANGE = 0.1f;
	
	private final SophiaTagServices services;
	private final Random rand;
	private MaterialList list;
	
	/**
	 * @param args
	 */
	
	private PrepareTestRecord() {
		this.services = new SophiaTagServices(); 
		this.rand = new Random();
		
	}
	
	public static void main(String[] args) {
		new PrepareTestRecord().start();
	}
	
	private void start() {
		
		this.services.newScan(TEST_PATH);
		
		this.list = this.services.getMaterialList();
		
		for (int i = 0; i < TAGGING_TIMES; i++) {
			MaterialTagger.getInstance().attachTagToMaterial(genTag(), genMat());
		}
		
		this.services.saveRecord();
	}
	
	private Tag genTag() {
		String text = "Tag_" + rand.nextInt(TAG_ID_RANGE);
		return services.getMaterialSearcher().getTagDatabase().getTag(text);
	}
	
	private Material genMat() {
		return this.list.get(rand.nextInt((int)(RANGE * list.size())));
	}
}
