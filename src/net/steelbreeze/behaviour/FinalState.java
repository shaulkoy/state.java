package net.steelbreeze.behaviour;

public final class FinalState extends SimpleState {
	public FinalState(String name, Region owner) {
		super(name, owner);
	}

	public FinalState(String name, CompositeState owner) {
		super(name, owner);
	}
}
