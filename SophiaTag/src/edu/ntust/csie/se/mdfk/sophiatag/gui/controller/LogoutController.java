/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.bundle.LoginBundle;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.MVCGlue;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.Scope;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.LoginView;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.MainView;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

/**
 * @author cy
 *
 */
public class LogoutController implements MainViewActionEventController {

	@Override
	public void handle(Scope scope, ActionEvent event, SophiaTagServices services, MainView view) {
		int userDecision =
		JOptionPane.showConfirmDialog(view.getFrame(), "Are you sure to logout?", "Logout Confirm", JOptionPane.YES_NO_OPTION);
		
		switch(userDecision)
		{
			case JOptionPane.YES_OPTION:
				services.saveRecord();
				services.getHighLighter().clearKeywords();
				view.getFrame().dispose();
				LoginView loginForm = new LoginView(services.isServiceReady());
				MVCGlue.glue(services, loginForm, new LoginBundle());
				loginForm.getFrame().setVisible(true);
				break;
			case JOptionPane.NO_OPTION:				
		}
	}

}
