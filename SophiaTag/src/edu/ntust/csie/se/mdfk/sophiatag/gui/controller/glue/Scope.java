/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue;

import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;

/**
 * @author maeglin89273
 *
 */
public class Scope {
	private PropertyChangeSupport eventSupport;
	private Map<String, Object> nameValueMap;
	/**
	 * 
	 */
	Scope() {
		this.eventSupport = new PropertyChangeSupport(this); 
		this.nameValueMap = new HashMap<String, Object>();
	}
	
	@SuppressWarnings("unchecked")
	public <T> void set(String name, T value) {
		if (value == null) {
			this.remove(name);
			return;
		}
		
		T oldValue = (T)this.nameValueMap.get(name);
		if (!value.equals(oldValue)) {
			this.nameValueMap.put(name, value);
			this.eventSupport.firePropertyChange(new ModelChangeEvent<T>(this, name, oldValue, value));
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String name) {
		return (T)this.nameValueMap.get(name);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T remove(String name) {
		T oldValue = (T)this.nameValueMap.remove(name);
		this.eventSupport.firePropertyChange(new ModelChangeEvent<T>(this, name, oldValue, null));
		return oldValue;
	}
	
	public void addModelChangeListener(String name, ModelChangeListener<?> listener) {
        this.eventSupport.addPropertyChangeListener(name, listener);
    }

    public  void removeModelChangeListener(String name, ModelChangeListener<?> listener) {
        this.eventSupport.removePropertyChangeListener(name, listener);
    }
}
