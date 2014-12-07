/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui;

import java.awt.event.ActionListener;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialPool;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialScanner;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialSearcher;
import edu.ntust.csie.se.mdfk.sophiatag.service.RecordStorage;

/**
 * @author maeglin89273
 *
 */
public class MainUIController {
	
	
	// service objects
	private MaterialSearcher searcher;
	private MaterialScanner scanner;
	private MaterialPool pool;
	private RecordStorage storage;
	
	/* Impelment every handlers. 
	 * They may call service objects while handling events,
	 * so pass the service objects as constructor arguments.
	 * Service objects should be initialized beforehand.
	 */
	private ActionListener changeRootDirectoryHandler;
	private ActionListener openDirectoryHandler;
	private ActionListener discardHandler;
	private ActionListener searchHandler;
	private ActionListener addTagHandler;
	
	public MainUIController() {
		
		this.storage = new RecordStorage();
		initialzeRecordedService(this.storage); 
		
		this.scanner.updateMaterialPool(this.pool);
		
		for(Material material: this.pool) {
			System.out.println(material.getName());
		}
		
		initializeEventHandlers();
	}
	
	private void initialzeRecordedService(RecordStorage storage) {
		//the record is guaranteed available due to the record guard
		if (!storage.hasSavedRecord()) {
			throw new IllegalStateException("The record file should be created on the file system beforehand");
		}
		
		RecordStorage.NecessaryRecord record = storage.loadRecord();
		this.scanner = new MaterialScanner(record.getRootDirectory());
		this.pool = record.getMaterialPool();
		this.searcher = new MaterialSearcher(record.getTagDatabase());
					
	}
	
	//should initialize corresponding handlers here
	private void initializeEventHandlers() {
		this.changeRootDirectoryHandler = this.openDirectoryHandler = this.discardHandler = this.searchHandler = this.addTagHandler = null;
	}
	
	
	public ActionListener getChangeRootDirectoryHandler() {
		return changeRootDirectoryHandler;
	}
	
	public ActionListener getOpenDirectoryHandler() {
		return openDirectoryHandler;
	}
	
	public ActionListener getDiscardHandler() {
		return discardHandler;
	}
	
	public ActionListener getSearchHandler() {
		return searchHandler;
	}
	
	public ActionListener getAddTagHandler() {
		return addTagHandler;
	}

}
