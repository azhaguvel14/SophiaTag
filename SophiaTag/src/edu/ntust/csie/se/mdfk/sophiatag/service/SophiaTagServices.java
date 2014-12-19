/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.service;

import java.awt.event.ActionListener;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.gui.custom.SelectDirectoryDialog;

/**
 * @author maeglin89273
 *
 */
public class SophiaTagServices {
	
	
	// service objects
	private MaterialSearcher searcher;
	private MaterialScanner scanner;
	private MaterialList list;
	private RecordStorage storage;
	
	private boolean ready = false;
	
	public SophiaTagServices() {
		
		this.storage = new RecordStorage();
		
		if (storage.hasSavedRecord()) {
			initializeRecordedServices(this.storage);
			this.scanner.updateMaterialPool(this.list);
			this.ready = true;
		}
		
		
//		
//		for(Material material: this.pool) {
//			System.out.println(material.getName());
//		}
		
	}
	
	private void initializeNewServices(String path) {
		this.scanner = new MaterialScanner(path);
		this.list = new MaterialList(this.scanner.newScan());
		this.searcher = new MaterialSearcher();
		
		this.saveRecord();
		
		this.ready = true;
	}
	
	private void initializeRecordedServices(RecordStorage storage) {
		//the record is guaranteed available due to the record guard
		if (!storage.hasSavedRecord()) {
			throw new IllegalStateException("The record file should be created on the file system beforehand");
		}
		
		RecordStorage.NecessaryRecord record = storage.loadRecord();
		this.scanner = new MaterialScanner(record.getRootDirectory());
		this.list = record.getMaterialPool();
		this.searcher = new MaterialSearcher(record.getTagDatabase());
					
	}
	
	public MaterialList getMaterialList() {
		return this.list;
	}
	
	public MaterialSearcher getMaterialSearcher() {
		return this.searcher;
	}
	
	public void saveRecord() {
		RecordStorage.NecessaryRecord record = new RecordStorage.NecessaryRecord(this.scanner.getRootDirectory(),
																				 this.getMaterialList(),
																				 this.getMaterialSearcher().getDatabase());
		this.storage.saveRecord(record);
	}
	
	public boolean isServiceReady() {
		return this.ready;
	}
	
	public void newScan(String path) {
		if (!this.isServiceReady()) {
			this.initializeNewServices(path);
			return;
		}
		
		this.getMaterialSearcher().getDatabase().drop();
		this.scanner.setRootDirectory(path);
		this.getMaterialList().setList(this.scanner.newScan());
		
		this.saveRecord();
		
	}
	
	public String getRootDirectory() {
		return this.scanner.getRootDirectory();
	}

}
