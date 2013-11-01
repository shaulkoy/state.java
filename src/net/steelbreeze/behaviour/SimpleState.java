package net.steelbreeze.behaviour;

import java.util.HashSet;

public class SimpleState extends Element {
	HashSet<IBehaviour> exit;
	HashSet<IBehaviour> entry;
	
	public SimpleState(String name, Region owner) {
		super( name, owner );
	}

	public SimpleState(String name, CompositeState owner) {
		super( name, owner );
	}

	public void AddExit(IBehaviour behaviour) {
		if( this.exit == null )
			this.exit = new HashSet<IBehaviour>();
		
		this.entry.add( behaviour );
	}
	
	public void AddEntry(IBehaviour behaviour) {
		if( this.entry == null )
			this.entry = new HashSet<IBehaviour>();
		
		this.entry.add( behaviour );
	}
}
