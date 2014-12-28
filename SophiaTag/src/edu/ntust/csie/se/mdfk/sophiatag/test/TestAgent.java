package edu.ntust.csie.se.mdfk.sophiatag.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.ntust.csie.se.mdfk.sophiatag.gui.custom.ColorSwatch;

public class TestAgent {
	public static void main(String[] args) {
		TestG<Integer> test = new TestG<Integer>(1);
		Object o = test;
		TestG<String> test2 = (TestG<String>)o;
		System.out.println(test2.get());
		
	}
	
	private static class TestG<T> {
		private T field;
		TestG(T value) {
			this.field = value;
		}
		
		T get() {
			return field;
		}
	}
}

