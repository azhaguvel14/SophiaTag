package edu.ntust.csie.se.mdfk.sophiatag.gui.controller;

import java.awt.event.ActionEvent;
import java.util.Set;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.MainView;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialList;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

public class SearchController implements MainViewActionEventController {

	@Override
	public void handle(ActionEvent event, SophiaTagServices services, MainView view) {
		
		Set<Material> searchResult = services.getMaterialSearcher().query(view.getQueryText());
		MaterialList shownList;
		if (searchResult.isEmpty()) {
			shownList = services.getMaterialList();
		} else {
			shownList = services.getMaterialList().select(searchResult);
		}
		view.getTableModel().setList(shownList);

	}

}
