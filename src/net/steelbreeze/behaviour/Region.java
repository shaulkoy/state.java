package net.steelbreeze.behaviour;

public class Region extends Element {
	PseudoState initial;
	
	public Region(String name, OrthogonalState owner) {
		super( name, owner );
		
		if( owner != null )
			owner.regions.add( this );
	}
}
