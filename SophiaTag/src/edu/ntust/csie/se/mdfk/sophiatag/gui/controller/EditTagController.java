/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.controller;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger;
import edu.ntust.csie.se.mdfk.sophiatag.data.Tag;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.Scope;
import edu.ntust.csie.se.mdfk.sophiatag.gui.custom.tagbutton.TagButton.TextChangedEvent;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.MainView;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

/**
 * @author maeglin89273
 *
 */
public class EditTagController implements MainViewEventController<TextChangedEvent> {

	@Override
	public void handle(Scope scope, TextChangedEvent event, SophiaTagServices services, MainView view) {
		Material material = scope.get("selectedMaterial");
		Tag newTag = services.getMaterialSearcher().getTagDatabase().getTagIfExist(event.getNewText());
		Tag oldTag = event.getOldTag();
		MaterialTagger tagger = MaterialTagger.getInstance();
		
		if (newTag == null) {
			tagger.changeTextOfTag(event.getNewText(), oldTag);
		} else {
			tagger.detachTagFromMaterial(oldTag, material);
			tagger.attachTagToMaterial(newTag, material); // if the tag already attached, tagger won't notify the listeners 
		}
		
		
	}

}
