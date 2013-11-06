package net.steelbreeze.behaviour;

import java.util.ArrayList;

public abstract class Element {
	private String name;
	private Element owner;

	Element( String name, Element owner ) {
		this.name = name;
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public String getQualifiedName() {
		return this.owner != null ? this.owner.getQualifiedName() + "." + this.name : this.name;
	}

	public Element getOwner() {
		return this.owner;
	}

	ArrayList< Element > getAncestors() {
		ArrayList< Element > ancestors = this.owner != null ? this.owner.getAncestors() : new ArrayList< Element >();

		ancestors.add( this );

		return ancestors;
	}

	void beginExit( IState context ) {}

	void endExit( IState context ) {
		System.out.println( "Leave: " + this.getQualifiedName() );

		context.setActive( this, false );
	}

	void beginEnter( IState context ) {
		if( context.getActive( this ) ) {
			this.beginExit( context );
			this.endExit( context );
		}

		System.out.println( "Enter: " + this.getQualifiedName() );

		context.setActive( this, true );
	}

	void endEnter( IState state, Boolean deepHistory ) throws StateMachineException {}

	@Override
	public String toString() {
		return this.getQualifiedName();
	}
}
