package net.steelbreeze.behaviour;

public class CompositeState extends SimpleState {
	PseudoState initial;
	
	public CompositeState(String name, Region owner) {
		super(name, owner);
	}

	public CompositeState(String name, CompositeState owner) {
		super(name, owner);
	}
}
