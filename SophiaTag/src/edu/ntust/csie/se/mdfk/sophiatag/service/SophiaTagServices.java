/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.service;

import java.io.IOException;

import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.Scope;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialSearcher.TagDatabase;


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
			try {
				initializeRecordedServices(this.storage);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			this.scanner.updateMaterialPool(this.list);
			this.ready = true;
		}
		
		
	}
	
	private void initializeNewServices(String path) {
		this.scanner = new MaterialScanner(path);
		this.list = new MaterialList(this.scanner.newScan());
		this.searcher = new MaterialSearcher();
		
		this.saveRecord();
		
		this.ready = true;
	}
	
	private void initializeRecordedServices(RecordStorage storage) throws ClassNotFoundException, IOException {
		if (!storage.hasSavedRecord()) {
			throw new IllegalStateException("The record file should be created on the file system beforehand");
		}
		
		RecordStorage.NecessaryRecord record;
		record = storage.loadRecord();
			
		this.scanner = new MaterialScanner(record.getRootDirectory());
		this.list = record.getMaterialList();
		this.list.restoreInit();
		TagDatabase db = record.getTagDatabase();
		db.restoreInit();
		this.searcher = new MaterialSearcher(db);
					
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
																				 this.getMaterialSearcher().getTagDatabase());
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
		
		this.getMaterialSearcher().getTagDatabase().drop();
		this.scanner.setRootDirectory(path);
		this.getMaterialList().setList(this.scanner.newScan());
		
		this.saveRecord();
		
	}
	
	public String getRootDirectory() {
		return this.scanner.getRootDirectory();
	}
}
