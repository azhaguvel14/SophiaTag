/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.controller;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableModel;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.Scope;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.MainView;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialList;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

/**
 * @author maeglin89273
 *
 */
public class MaterialSelectedController implements MainViewEventController<ListSelectionEvent> {
	@Override
	public void handle(Scope scope, ListSelectionEvent event, SophiaTagServices services, MainView view) {
		
		ListSelectionModel model = (ListSelectionModel)event.getSource();
		int selectedIndex = model.getLeadSelectionIndex();
		MaterialList listModel = scope.get("listModel");
		
		if (!model.isSelectionEmpty()) {
			// add the selected material to Scope
			scope.set("selectedMaterial", listModel.get(selectedIndex));
		} else {
			// remove the selected material from Scope
			scope.remove("selectedMaterial");			
		}
		
	}

	
	
}
