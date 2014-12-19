/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.controller;

import java.util.EventObject;

import edu.ntust.csie.se.mdfk.sophiatag.gui.view.View;
import edu.ntust.csie.se.mdfk.sophiatag.service.SophiaTagServices;

/**
 * @author maeglin89273
 *
 */
public interface EventController<E extends EventObject, V extends View> {
	public abstract void handle(E event, SophiaTagServices services, V view);
}
