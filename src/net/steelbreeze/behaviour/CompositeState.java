package net.steelbreeze.behaviour;

public class CompositeState extends SimpleState implements IRegion {
	PseudoState initial;
	
	public CompositeState(String name, IRegion owner) {
		super(name, owner);
	}

	@Override
	public void SetInitial( PseudoState initial ) {
		this.initial = initial;
	}	
}
