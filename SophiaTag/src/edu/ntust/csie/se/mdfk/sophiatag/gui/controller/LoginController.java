package edu.ntust.csie.se.mdfk.sophiatag.gui.controller;

import java.awt.event.ActionEvent;

import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.bundle.MainBundle;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.MVCGlue;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.Scope;
import edu.ntust.csie.se.mdfk.sophiatag.gui.custom.SelectDirectoryDialog;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.LoginView;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.MainView;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.View;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;
import edu.ntust.csie.se.mdfk.sophiatag.user.Administrator;
import edu.ntust.csie.se.mdfk.sophiatag.user.Designer;
import edu.ntust.csie.se.mdfk.sophiatag.user.IdentityAuthorizer;
import edu.ntust.csie.se.mdfk.sophiatag.user.User;

public class LoginController implements EventController<ActionEvent, LoginView> {


	@Override
	public void handle(Scope scope, ActionEvent event, SophiaTagServices services, LoginView view) {
		User user = createUser(event.getActionCommand());
		
		String account = view.getAccount();
		String rawPassword = new String(view.getPassword());
		
		if (IdentityAuthorizer.authorize(account, rawPassword, user)) {
			view.clearError();
			try {
				makeServicesReady(services, view);
			} catch (IllegalStateException exception) {
				view.setError("there is an error when scanning directories");
				return;
			}
			
			setupMainView(services, user).getFrame().setVisible(true);
			view.getFrame().dispose();
		} else {
			view.setError("wrong account or password");
			
		}
	}
	
	
	
	private User createUser(String actionCommand) {
		User user = null;
		
		switch (actionCommand) {
			case LoginView.ADMIN_COMMAND:
				user = new Administrator();
				break;
				
			case LoginView.DESIGNER_COMMAND:
				user = new Designer();
		}

		return user;
	}
	
	private void makeServicesReady(SophiaTagServices services, View view) throws IllegalStateException {
		if (services.isServiceReady()) {
			return;
		}
		
		String path = SelectDirectoryDialog.showSelectDirectoryDialog(view.getFrame(), "Choose the root directory of the materials:");
		if (path == null) {
			throw new IllegalStateException("The scanning path is not set");
		}
		
		services.newScan(path);
	}
	
	private MainView setupMainView(SophiaTagServices services, User user) {
		MainView view = new MainView(user);
		MVCGlue.glue(services, view, new MainBundle());
		return view;
			
	}
	
	
}