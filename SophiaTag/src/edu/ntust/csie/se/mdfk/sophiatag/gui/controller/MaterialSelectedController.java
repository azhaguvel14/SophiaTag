/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.controller;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableModel;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.MainView;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

/**
 * @author maeglin89273
 *
 */
public class MaterialSelectedController implements MainViewEventController<ListSelectionEvent> {

	@Override
	public void handle(ListSelectionEvent event, SophiaTagServices services, MainView view) {
		ListSelectionModel model = (ListSelectionModel)event.getSource();
		int selectedIndex = model.getAnchorSelectionIndex();
		
		//remember to enable or disable the discard button
		if (isSelectionInList(selectedIndex, view.getTableModel())) {
			// add the selected material to Scope,and then set to the Material Profile
			
		} else {
			// remove the selected material from Scope,and then set the Material Profile to null
			
		}
		
		
	}

	private boolean isSelectionInList(int selectedIndex, TableModel model) {
		return selectedIndex >= 0 && selectedIndex < model.getRowCount();
	}
	
	
}
