/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.controller.bundle;

import edu.ntust.csie.se.mdfk.sophiatag.gui.MainView;

/**
 * @author maeglin89273
 *
 */
public class MainBundle extends ControllerBundle<MainView> {

	@Override
	protected void register() {
		//replace null with the controller instance that you implement, you can refer to LoginBundle class 
		this.addActionEventController("logout", null);
		this.addActionEventController("change_root_dir", null);
		this.addActionEventController("search", null);
		this.addActionEventController("open_dir", null);
		this.addActionEventController("add_tag", null);
		this.addActionEventController("discard_material", null);
	}

}
