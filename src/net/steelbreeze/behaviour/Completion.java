package net.steelbreeze.behaviour;

import java.util.HashSet;


public class Completion {
	IGuard guard;
	Path path;
	Element target;
	
	public Completion( Element source, Element target, IGuard guard ){
		this.guard = guard;
		this.target = target;
		this.path = new Path( source, target );	
	}
}
