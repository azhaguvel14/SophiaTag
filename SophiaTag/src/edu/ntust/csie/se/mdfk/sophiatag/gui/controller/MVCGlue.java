/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.controller;

import java.util.EventObject;

import edu.ntust.csie.se.mdfk.sophiatag.gui.View;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.bundle.ControllerBundle;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

/**
 * @author maeglin89273
 *
 */
public abstract class MVCGlue {
	
	public static <V extends View> MVCGlue glue(SophiaTagServices services, V view, ControllerBundle<V> bundle) {
		return new MVCGlueImpl<V>(services, view, bundle);
	}
	
	public abstract void handleEvent(String eventName, EventObject event);
	
	//enclose any generics information here
	private static class MVCGlueImpl<V extends View> extends MVCGlue {
		
		private final SophiaTagServices services;
		private final V view;
		private final ControllerBundle<V> bundle;
		
		public MVCGlueImpl(SophiaTagServices services, V view, ControllerBundle<V> bundle) {
			this.services = services;
			this.view = view;
			this.bundle = bundle;
			
			this.view.bindEvent(this);
		}

		@Override
		public void handleEvent(String eventName, EventObject event) {
			EventController<EventObject, V> controller = this.bundle.get(eventName);
			if (controller == null) {
				throw new IllegalArgumentException("There is no controllers can handle this event");
			}
			
			controller.handle(event, this.services, this.view);
			
		}
	}
}
