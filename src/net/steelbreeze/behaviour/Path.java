package net.steelbreeze.behaviour;

import java.util.ArrayList;

class Path {
	ArrayList<Element> exit = new ArrayList<Element>();
	ArrayList<Element> beginEnter = new ArrayList<Element>();
	Element endEnter;
	
	Path( Element source, Element target ) {
		ArrayList<Element> sourceAncestors = source.GetAncestors();
		ArrayList<Element> targetAncestors = target.GetAncestors();
 	}
}
