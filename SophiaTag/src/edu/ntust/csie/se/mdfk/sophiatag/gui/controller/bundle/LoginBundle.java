package edu.ntust.csie.se.mdfk.sophiatag.gui.controller.bundle;

import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.LoginController;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.LoginView;

public class LoginBundle extends ControllerBundle<LoginView> {

	@Override
	protected void register() {
		this.addActionEventController("login", new LoginController());
	}

}
