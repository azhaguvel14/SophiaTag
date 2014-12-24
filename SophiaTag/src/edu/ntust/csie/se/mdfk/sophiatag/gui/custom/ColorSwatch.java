/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.custom;

import java.awt.Color;

/**
 * @author maeglin89273
 *
 */
public abstract class ColorSwatch {
	public static final Color BLUE = new Color(0x196eef);
	public static final Color GREEN = new Color(0x09a460);
	public static final Color ORANGE = new Color(0xff6519);
	public static final Color RED = new Color(0xde5642);
	
	public static final Color PINK = new Color(0xff006a);
	public static final Color LIGHT_PINK = new Color(0xffcce0);
	public static final Color LIGHT_YELLOW = new Color(0xfff0cc);
	
	public static final String toNoAlphaString(Color color) {
		return "#" + Integer.toHexString(color.getRGB() & 0x00ffffff);
	}
}
