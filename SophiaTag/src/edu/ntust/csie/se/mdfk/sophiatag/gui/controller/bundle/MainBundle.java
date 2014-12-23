/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.controller.bundle;

import java.awt.event.WindowEvent;

import javax.swing.event.ListSelectionEvent;

import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.LogoutController;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.MainViewInitController;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.MaterialSelectedController;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.OpenDirectoryController;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.SearchController;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.WindowClosingController;
import edu.ntust.csie.se.mdfk.sophiatag.gui.custom.tagbutton.TagButton.TextChangedEvent;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.MainView;

/**
 * @author maeglin89273
 *
 */
public class MainBundle extends ControllerBundle<MainView> {

	@Override
	protected void register() {
		this.addController("init", WindowEvent.class, new MainViewInitController());
		this.addController("window_closing", WindowEvent.class, new WindowClosingController());
		
		//replace null with the controller instance that you implement, you can refer to LoginBundle class 
		this.addActionEventController("logout", new LogoutController());
		this.addActionEventController("change_root_dir", null); //蝝�
		this.addActionEventController("search", new SearchController()); //蝝�
		this.addActionEventController("open_dir", new OpenDirectoryController()); //CoZy
		this.addActionEventController("add_tag", null); //撣�
		this.addActionEventController("remove_tag", null);//撣�
		this.addActionEventController("discard_material", null); //CoZy
		
		this.addController("material_selected", ListSelectionEvent.class, new MaterialSelectedController());
		this.addController("edit_tag", TextChangedEvent.class, null);//撣�
	}

}
