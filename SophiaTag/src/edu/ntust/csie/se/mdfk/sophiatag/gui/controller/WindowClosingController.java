/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.controller;

import java.awt.event.WindowEvent;

import edu.ntust.csie.se.mdfk.sophiatag.gui.view.MainView;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

/**
 * @author maeglin89273
 *
 */
public class WindowClosingController implements	EventController<WindowEvent, MainView> {

	
	@Override
	public void handle(WindowEvent event, SophiaTagServices services, MainView view) {
		services.saveRecord();
	}

}
