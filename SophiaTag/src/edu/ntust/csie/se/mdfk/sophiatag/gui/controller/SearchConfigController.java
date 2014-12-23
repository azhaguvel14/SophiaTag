package edu.ntust.csie.se.mdfk.sophiatag.gui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JToggleButton;
import javax.swing.JToggleButton.ToggleButtonModel;

import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.Scope;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.MainView;
import edu.ntust.csie.se.mdfk.sophiatag.service.MaterialSearcher;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

public class SearchConfigController implements MainViewActionEventController {
	@Override
	public void handle(Scope scope, ActionEvent event, SophiaTagServices services, MainView view) {
		ToggleButtonModel model = (ToggleButtonModel) ((JToggleButton)event.getSource()).getModel();
		services.getMaterialSearcher().setSearchConfig(model.isSelected()? MaterialSearcher.WRAP_WORD: MaterialSearcher.START_WITH);
	}

}
