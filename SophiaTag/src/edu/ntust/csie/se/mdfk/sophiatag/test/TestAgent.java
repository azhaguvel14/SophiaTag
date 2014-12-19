package edu.ntust.csie.se.mdfk.sophiatag.test;

import java.util.ArrayList;
import java.util.List;

public class TestAgent {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		
		List<Integer> sub = list.subList(1, 5);
		sub.remove(2);
		System.out.println(sub.size());
	}
}
