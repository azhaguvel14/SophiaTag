/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.data;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author maeglin89273
 *
 */
public abstract class Attachable<T> implements Serializable{
	
	private Set<T> targets;
	
	public Attachable() {
		this.targets = new HashSet<T>();
	}
	
	protected final boolean attachedTo(T target) {
		return targets.add(target);
	}
	
	protected final boolean detachedFrom(T target) {
		return targets.remove(target);
	}
	
	public final boolean isAttached() {
		return !targets.isEmpty();
	}
	
	public final Set<T> getTargetsView() {
		return Collections.unmodifiableSet(targets);
	}
	
	public final boolean isAttachedTo(T target) {
		return targets.contains(target);
	}
	
	protected final void clearTargets() {
		targets.clear();
	}
	
}
