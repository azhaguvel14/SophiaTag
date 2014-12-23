package edu.ntust.csie.se.mdfk.sophiatag.gui.controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.IOException;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.Scope;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.MainView;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

public class OpenDirectoryController implements MainViewActionEventController {
	@Override
	public void handle(Scope scope, ActionEvent event,
			SophiaTagServices services, MainView view) {		
		try {
			Material material = scope.get("selectedMaterial");
			Desktop.getDesktop().open(material.getUnderlyingFile().getParentFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
