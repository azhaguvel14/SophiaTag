/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.controller;

import java.awt.event.ActionEvent;

import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.Scope;
import edu.ntust.csie.se.mdfk.sophiatag.gui.custom.SelectDirectoryDialog;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.MainView;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialList;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

/**
 * @author maeglin89273
 *
 */
public class ChangeDirectoryController implements MainViewActionEventController {
	private static String DIALOG_MESSAGE = "<html><div style=\"font-weight:bold; font-size:20px;\">Be careful!</div>Change the path will delete all the records of tags in the system</br></html>";
	/* (non-Javadoc)
	 * @see edu.ntust.csie.se.mdfk.sophiatag.gui.controller.EventController#handle(edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.Scope, java.util.EventObject, edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices, edu.ntust.csie.se.mdfk.sophiatag.gui.view.View)
	 */
	@Override
	public void handle(Scope scope, ActionEvent event, SophiaTagServices services, MainView view) {
		String path = SelectDirectoryDialog.showSelectDirectoryDialog(view.getFrame(), DIALOG_MESSAGE);
		if (path == null) {
			return;
		}
		
		services.newScan(path);
		scope.set("rootDir", path);
		
		if(!scope.set("listModel", services.getMaterialList())) {
			scope.<MaterialList>notifyInternalValueChanged("listModel");
		}
	}

}
