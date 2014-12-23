package edu.ntust.csie.se.mdfk.sophiatag.gui.controller;

import java.awt.event.ActionEvent;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.data.MaterialTagger;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.Scope;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.MainView;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

public class MaterialDiscardController implements MainViewActionEventController {

	@Override
	public void handle(Scope scope, ActionEvent event,
			SophiaTagServices services, MainView view) {
		// TODO Auto-generated method stub
		Material material = scope.get("selectedMaterial");
		if(material.isLost()== true ){
			MaterialTagger.getInstance().discardMaterial(material);
		}
	}

}
