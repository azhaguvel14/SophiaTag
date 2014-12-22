/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue;

import java.util.EventObject;

import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.EventController;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.bundle.ControllerBundle;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.View;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

/**
 * @author maeglin89273
 *
 */
public abstract class MVCGlue {
	
	protected final Scope scope;
	
	MVCGlue() {
		this.scope = new Scope();
	}
	
	public static <V extends View> MVCGlue glue(SophiaTagServices services, V view, ControllerBundle<V> bundle) {
		return new MVCGlueImpl<V>(services, view, bundle);
	}
	
	public Scope getScope() {
		return this.scope;
	}
	
	public abstract SophiaTagServices getServices();
	public abstract View getView();
	
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
			
			controller.handle(this.scope, event, this.services, this.view);
			
		}

		@Override
		public SophiaTagServices getServices() {
			return this.services;
		}
		
		@Override
		public View getView() {
			return view;
		}
	}
}
