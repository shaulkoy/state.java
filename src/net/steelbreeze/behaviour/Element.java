package net.steelbreeze.behaviour;

import java.util.ArrayList;

public abstract class Element {
	private String name;
	private Element owner;
	
	Element( String name, Element owner ) {
		this.name = name;
		this.owner = owner;
	}
	
	public String GetName() {
		return name;
	}

	public String GetQualifiedName() {
		return this.owner != null ? this.owner.GetQualifiedName() + "." + this.name : this.name;
	}
	
	public Element GetOwner() { 
		return this.owner;
	}
	
	ArrayList<Element> GetAncestors() {
		ArrayList<Element> ancestors = this.owner != null ? this.owner.GetAncestors() : new ArrayList<Element>();
		
		ancestors.add(this);
		
		return ancestors;
	}

	void BeginExit( IState context ) {
	}
	
	void EndExit( IState context ) {
		System.out.println( "Leave: " + this.GetQualifiedName() );
		
		context.SetActive( this, false );
	}
	
	void BeginEnter( IState context ) {
		if( context.GetActive( this ) ) {
			this.BeginExit( context );
			this.EndExit(context);
		}
		
		System.out.println( "Enter: " + this.GetQualifiedName() );
		
		context.SetActive( this, true );
	}
	
	void EntEnter( IState state ) {		
	}
	
	@Override
	public String toString() {
		return this.GetQualifiedName();
	}
}
