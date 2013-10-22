package net.steelbreeze.behaviour;

import java.util.HashSet;

public class OrthogonalState extends SimpleState {
	HashSet<Region> regions = new HashSet<Region>();
	
	public OrthogonalState(String name, IRegion owner) {
		super(name, owner);
	}
}
