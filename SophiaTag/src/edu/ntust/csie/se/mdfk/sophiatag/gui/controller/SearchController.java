package edu.ntust.csie.se.mdfk.sophiatag.gui.controller;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Set;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.data.Tag;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.Scope;
import edu.ntust.csie.se.mdfk.sophiatag.gui.custom.MaterialListModel;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.MainView;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialList;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialSearcher;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialSearcher.SearchResult;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

public class SearchController implements MainViewActionEventController {

	@Override
	public void handle(Scope scope, ActionEvent event, SophiaTagServices services, MainView view) {
		String queryText = view.getQueryText();
//		if(queryText.isEmpty()) {
//			scope.set("listModel", services.getMaterialList());
//			return;
//		}
		
		SearchResult searchResult = services.getMaterialSearcher().query(queryText);
		MaterialList shownList;
		if (searchResult.hasResult()) {
			shownList = services.getMaterialList().select(searchResult.getResult());
		} else {
			shownList = services.getMaterialList();
		}
		
		scope.set("listModel", shownList);
		
	}

}
