/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.controller.bundle;

import java.awt.event.ActionEvent;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.EventController;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.Scope;
import edu.ntust.csie.se.mdfk.sophiatag.gui.view.View;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

/**
 * @author maeglin89273
 *
 */
public abstract class ControllerBundle<V extends View> {
	
	private Map<String, EventArgumentAdapter<? extends EventObject>> bundle;
	
	public ControllerBundle() {
		this.bundle = new HashMap<String, EventArgumentAdapter<? extends EventObject>>();
		register();
	}
	
	public EventController<EventObject, V> get(String eventName) {
		return this.bundle.get(eventName);
	}
	
	protected void addActionEventController(String eventName, EventController<ActionEvent, V> controller) {
		this.addController(eventName, ActionEvent.class, controller);
	}
	
	protected <E extends EventObject> void addController(String eventName, Class<E> eventClass, EventController<E, V> controller) {
		this.bundle.put(eventName, new EventArgumentAdapter<E>(eventClass, controller));
	}
	
	protected abstract void register();
	
	//the powerful inner class that convert the general event type to the explicit event type
	private class EventArgumentAdapter<E extends EventObject> implements EventController<EventObject, V> {
		private final Class<E> eventCaster;
		private final EventController<E, V> controller;
		
		public EventArgumentAdapter(Class<E> eventCaster, EventController<E, V> controller) {
			this.eventCaster = eventCaster;
			this.controller = controller;
		}
		
		@Override
		public void handle(Scope scope, EventObject event, SophiaTagServices services, V view) {
			this.controller.handle(scope, this.eventCaster.cast(event), services, view);
		}
		
	}
	
}
