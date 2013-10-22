package net.steelbreeze.behaviour;

public class PseudoState implements IVertex {
	String name;
	PseudoStateKind kind;
	IRegion owner;
	
	public PseudoState(String name, PseudoStateKind kind, IRegion owner) {
		this.name = name;
		this.kind = kind;
		this.owner = owner;
		
		if( this.kind.IsInitial() )
			this.owner.SetInitial( this );
	}
	
	@Override
	public IElement Owner() {
		return this.owner;
	}
	
	@Override
	public void Exit(IState context) {		
	}

	@Override
	public void Enter(IState context) {		
	}

	@Override
	public void Complete(IState context, Boolean deepHistory) {		
	}

	@Override
	public String toString() {
		return this.owner != null ? this.owner + "." + this.name : this.name;
	}
}
