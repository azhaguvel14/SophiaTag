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
	
	private static final int TAGGING_TIMES = 500;
	private static final int TAG_ID_LENGTH = 1;
	private static final float RANGE = 0.17f;
	
	private final SophiaTagServices services;
	private final Random rand;
	private MaterialList list;
	
	private static char[] ID_CHARS = new char[] {
//		'1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
	};
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
		return services.getMaterialSearcher().getTagDatabase().getTag(genId());
	}
	
	private String genId() {
		StringBuffer buffer = new StringBuffer(TAG_ID_LENGTH);
		for (int i = 0; i < buffer.capacity(); i++) {
			buffer.append(ID_CHARS[rand.nextInt(ID_CHARS.length)]);
		}
		
		return buffer.toString();
	}
	private Material genMat() {
		return this.list.get(rand.nextInt((int)(RANGE * list.size())));
	}
}
