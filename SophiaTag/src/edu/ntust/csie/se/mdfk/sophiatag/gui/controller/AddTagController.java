package edu.ntust.csie.se.mdfk.sophiatag.gui.controller;

import java.awt.event.ActionEvent;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger;
import edu.ntust.csie.se.mdfk.sophiatag.data.Tag;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.Scope;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.MainView;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialSearcher.TagDatabase;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

public class AddTagController implements MainViewActionEventController {

	private static final String DEFAULT_TAG_TEXT = "New Tag";

	@Override
	public void handle(Scope scope, ActionEvent event, SophiaTagServices services, MainView view) {
		Material material = scope.get("selectedMaterial");
		Tag tag = new Tag(generateNewDefaultText(services.getMaterialSearcher().getTagDatabase()));
		MaterialTagger.getInstance().attachTagToMaterial(tag, material);
		view.getLastTagButton().editTag();
	}
	
	private static String generateNewDefaultText(TagDatabase database) {
		String defaultText = DEFAULT_TAG_TEXT;
		int posfix = 1;
		while (database.getTagIfExist(defaultText) != null) {
			defaultText = DEFAULT_TAG_TEXT + String.format("(%d)", posfix++);
		}
		return defaultText;
	}
}
