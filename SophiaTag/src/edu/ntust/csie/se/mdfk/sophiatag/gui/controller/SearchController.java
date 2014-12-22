package edu.ntust.csie.se.mdfk.sophiatag.gui.controller;

import java.awt.event.ActionEvent;
import java.util.Set;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.MainView;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

public class SearchController implements MainViewActionEventController {

	@Override
	public void handle(ActionEvent event, SophiaTagServices services,
			MainView view) {
		
		Set<Material> SearchReturn = services.getMaterialSearcher().query(view.getQueryText());
		
		if (SearchReturn.isEmpty()) {
			view.getTableModel().setList(services.getMaterialList());
		} else {
			view.getTableModel().setList(services.getMaterialList().select(SearchReturn));
		}

	}

}
