package net.steelbreeze.behaviour;

import java.util.HashSet;

public class SimpleState implements IVertex {
	HashSet<IBehaviour> exit;
	HashSet<IBehaviour> entry;
	
	public interface IAction {
		public void Action();
	}
	
	String name;
	IRegion owner;
	
	public SimpleState(String name, IRegion owner) {
		this.name = name;
		this.owner = owner;
	}

	@Override
	public IElement Owner() {
		return this.owner;
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
