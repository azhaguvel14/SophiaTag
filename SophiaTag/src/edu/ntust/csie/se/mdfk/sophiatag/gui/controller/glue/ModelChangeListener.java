package edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public abstract class ModelChangeListener<T> implements PropertyChangeListener {
	
	@Override
	@SuppressWarnings("unchecked")
	public void propertyChange(PropertyChangeEvent evt) {
		this.modelChange((ModelChangeEvent<T>) evt);
	}
	
	public abstract void modelChange(ModelChangeEvent<T> evt);
}
