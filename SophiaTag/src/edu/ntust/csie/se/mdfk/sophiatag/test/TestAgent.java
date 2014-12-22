package edu.ntust.csie.se.mdfk.sophiatag.test;

import java.util.ArrayList;
import java.util.List;

import edu.ntust.csie.se.mdfk.sophiatag.gui.custom.ColorSwatch;

public class TestAgent {
	public static void main(String[] args) {
		System.out.println(ColorSwatch.BLUE.toString());
		System.out.println("#" + Integer.toHexString(ColorSwatch.BLUE.getRGB() & 0x00ffffff));
	}
}
