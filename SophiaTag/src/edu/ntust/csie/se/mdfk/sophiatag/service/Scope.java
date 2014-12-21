/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.service;

import java.util.HashMap;
import java.util.Map;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;
import edu.ntust.csie.se.mdfk.sophiatag.data.Tag;

/**
 * @author maeglin89273
 *
 */
public class Scope {
	
	private Map<String, Object> nameValueMap;
	/**
	 * 
	 */
	Scope() {
		this.nameValueMap = new HashMap<String, Object>();
	}
	
	public void set(String name, Object value) {
		this.nameValueMap.put(name, value);
	}
	
	public int getInt(String name) {
		return (Integer)getObject(name);
	}
	
	public String getString(String name) {
		return (String)getObject(name);
	}
	
	public Material getMaterial(String name) {
		return (Material)getObject(name);
	}
	
	public Tag getTag(String name) {
		return (Tag)getObject(name);
	}
	
	public Object getObject(String name) {
		return this.nameValueMap.get(name);
	}
	
	public Object remove(String name) {
		return this.nameValueMap.remove(name);
	}
}
