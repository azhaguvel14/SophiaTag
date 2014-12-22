/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.controller;

import java.awt.event.WindowEvent;

import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.Scope;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.MainView;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

/**
 * @author maeglin89273
 *
 */
public class MainViewInitController implements MainViewEventController<WindowEvent> {

	@Override
	public void handle(Scope scope, WindowEvent event, SophiaTagServices services, MainView view) {
		scope.set("rootDir", services.getRootDirectory());
		scope.set("listModel", services.getMaterialList());
	}

}
