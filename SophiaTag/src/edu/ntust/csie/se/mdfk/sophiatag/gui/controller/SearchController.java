package edu.ntust.csie.se.mdfk.sophiatag.gui.controller;

import java.awt.event.KeyEvent;

import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.Scope;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.MainView;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialList;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialSearcher.SearchResult;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

public class SearchController implements MainViewEventController<KeyEvent> {

	@Override
	public void handle(Scope scope, KeyEvent event, SophiaTagServices services, MainView view) {
		String queryText = view.getQueryText();
		
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
