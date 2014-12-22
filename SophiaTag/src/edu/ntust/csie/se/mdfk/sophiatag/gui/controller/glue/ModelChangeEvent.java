/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue;

import java.beans.PropertyChangeEvent;

/**
 * @author maeglin89273
 *
 */
public class ModelChangeEvent<T> extends PropertyChangeEvent {

	ModelChangeEvent(Scope scope, String propertyName, T oldValue, T newValue) {
		super(scope, propertyName, oldValue, newValue);
	}
	
	@SuppressWarnings("unchecked")
	public T getModel() {
		return (T) this.getNewValue();
	}
}
