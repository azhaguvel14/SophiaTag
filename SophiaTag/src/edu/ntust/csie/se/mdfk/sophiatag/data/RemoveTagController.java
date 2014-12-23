/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.data;

import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.MainViewEventController;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.Scope;
import edu.ntust.csie.se.mdfk.sophiatag.gui.custom.tagbutton.TagButton.RemoveTagEvent;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.MainView;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

/**
 * @author maeglin89273
 *
 */
public class RemoveTagController implements MainViewEventController<RemoveTagEvent> {

	/* (non-Javadoc)
	 * @see edu.ntust.csie.se.mdfk.sophiatag.gui.controller.EventController#handle(edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.Scope, java.util.EventObject, edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices, edu.ntust.csie.se.mdfk.sophiatag.gui.view.View)
	 */
	@Override
	public void handle(Scope scope, RemoveTagEvent event, SophiaTagServices services, MainView view) {
		Material material = scope.get("selectedMaterial");
		Tag tag = event.getTag();
		MaterialTagger.getInstance().detachTagFromMaterial(tag, material);
	}

}
