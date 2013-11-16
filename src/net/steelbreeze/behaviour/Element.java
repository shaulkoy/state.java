package net.steelbreeze.behaviour;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstraction of any node in a state machine hierarchy
 * @author Mesmo
 */
public abstract class Element {
	private String name;
	private Element owner;

	/**
	 * Creates a new instance of the Element class
	 * @param name The name of the new Element
	 * @param owner The owning (parent) Element of the new element
	 */
	Element( String name, Element owner ) {
		this.name = name;
		this.owner = owner;
	}

	/**
	 * Returns the name of the element
	 * @return The name of the element
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the fully qualified name of the element
	 * @return The fully qualified name of the element
	 */
	public String getQualifiedName() {
		return this.owner != null ? this.owner.getQualifiedName() + "." + this.name : this.name;
	}

	/**
	 * Returns the owning (parent) element of the element
	 * @return The owning (parent) element of the element
	 */
	public Element getOwner() {
		return this.owner;
	}

	/**
	 * Returns the ancestors of the element (including the element)
	 * @return The ancestors of the element (including the element)
	 */
	List< Element > getAncestors() {
		List< Element > ancestors = this.owner != null ? this.owner.getAncestors() : new ArrayList< Element >();

		ancestors.add( this );

		return ancestors;
	}

	/**
	 * Initiates the exit operation of an element (cascades to children as appropriate)
	 * @param context The state machine state
	 */
	void beginExit( IState context ) {}

	/**
	 * Completes the exit operation of an element
	 * @param context The state machine state
	 */
	void endExit( IState context ) {
		System.out.println( "Leave: " + this.getQualifiedName() );

		context.setActive( this, false );
	}

	/**
	 * Initiates the entry operation of an element
	 * @param context The state machine state
	 */
	void beginEnter( IState context ) {
		if( context.getActive( this ) ) {
			this.beginExit( context );
			this.endExit( context );
		}

		System.out.println( "Enter: " + this.getQualifiedName() );

		context.setActive( this, true );
	}

	/**
	 * Completes the entry operation of an element (cascades to children as appropriate)
	 * @param state The state machine state
	 * @param deepHistory Flag indicating of deep history was set in an ancestor
	 * @throws StateMachineException
	 */
	void endEnter( IState state, Boolean deepHistory ) throws StateMachineException {}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getQualifiedName();
	}
}
