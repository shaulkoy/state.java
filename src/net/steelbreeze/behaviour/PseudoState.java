package net.steelbreeze.behaviour;

public class PseudoState extends Element {
	private PseudoStateKind kind;
	
	public PseudoState(String name, PseudoStateKind kind, Region owner) {
		super( name, owner);
		
		this.kind = kind;
		
		if( this.kind.IsInitial() )
			owner.initial = this;
	}
	
	public PseudoState(String name, PseudoStateKind kind, CompositeState owner) {
		super( name, owner);
		
		this.kind = kind;
		
		if( this.kind.IsInitial() )
			owner.initial = this;
	}
}
