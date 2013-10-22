package net.steelbreeze.behaviour;

public class Region implements IRegion {
	String name;
	OrthogonalState owner;
	PseudoState initial;
	
	public Region(String name, OrthogonalState owner) {
		this.name = name;
		this.owner = owner;	
		
		if( this.owner != null )
			this.owner.regions.add( this );
	}

	@Override
	public IElement Owner() {
		return this.owner;
	}

	@Override
	public void SetInitial( PseudoState initial ) {
		this.initial = initial;
	}
	
	@Override
	public void Exit(IState context) {		
	}

	@Override
	public void Enter(IState context) {		
	}

	@Override
	public String toString() {
		return this.owner != null ? this.owner + "." + this.name : this.name;
	}	
}
