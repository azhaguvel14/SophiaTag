/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.controller.bundle;

import java.awt.event.WindowEvent;

import javax.swing.event.ListSelectionEvent;

import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.MaterialSelectedController;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.WindowClosingController;
import edu.ntust.csie.se.mdfk.sophiatag.gui.tagbutton.TagButton.TextChangedEvent;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.MainView;

/**
 * @author maeglin89273
 *
 */
public class MainBundle extends ControllerBundle<MainView> {

	@Override
	protected void register() {
		this.addController("window_closing", WindowEvent.class, new WindowClosingController());
		
		//replace null with the controller instance that you implement, you can refer to LoginBundle class 
		this.addActionEventController("logout", null); //Tung
		this.addActionEventController("change_root_dir", null); //紀
		this.addActionEventController("search", null); //紀
		this.addActionEventController("open_dir", null); //常
		this.addActionEventController("add_tag", null); //CoZy
		this.addActionEventController("remove_tag", null);//常
		this.addActionEventController("discard_material", null); //CoZy
		
		this.addController("material_selected", ListSelectionEvent.class, new MaterialSelectedController()); //Tung
		this.addController("edit_tag", TextChangedEvent.class, null);//常
	}

}
