package net.steelbreeze.behaviour;

import java.util.HashSet;

public class OrthogonalState extends SimpleState {
	HashSet<Region> regions = new HashSet<Region>();
	
	public OrthogonalState(String name, Region owner) {
		super(name, owner);
	}
	
	public OrthogonalState(String name, CompositeState owner) {
		super(name, owner);
	}
}
