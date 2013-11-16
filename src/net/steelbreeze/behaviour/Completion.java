package net.steelbreeze.behaviour;

import java.util.ArrayList;

/**
 * A completion transition for transitions that automatically transition upon entry of pseudo states 
 * @author David
 */
public class Completion {
	Path path;
	IGuard guardCondition;
	ArrayList< IBehaviour > effect;

	/**
	 * Creates a new instance of the Completion class
	 * @param source The PseudoState to transition away from
	 * @param target The PseudoState to transition to
	 * @param guardCondition An optional guard condition that must be satisfied in order to pass
	 */
	public Completion( PseudoState source, PseudoState target, IGuard guardCondition ) {
		this.guardCondition = guardCondition;
		this.path = new Path( source, target );

		source.addCompletion( this );
	}

	/**
	 * Creates a new instance of the Completion class
	 * @param source The PseudoState to transition away from
	 * @param target The SimpleState to transition to
	 * @param guardCondition An optional guard condition that must be satisfied in order to pass
	 */
	public Completion( PseudoState source, SimpleState target, IGuard guard ) {
		this.guardCondition = guard;
		this.path = new Path( source, target );

		source.addCompletion( this );
	}

	/**
	 * Creates a new instance of the Completion class
	 * @param source The SimpleState to transition away from
	 * @param target The PseudoState to transition to
	 * @param guardCondition An optional guard condition that must be satisfied in order to pass
	 */
	public Completion( SimpleState source, PseudoState target, IGuard guard ) {
		this.guardCondition = guard;
		this.path = new Path( source, target );

		source.addCompletion( this );
	}

	/**
	 * Creates a new instance of the Completion class
	 * @param source The SimpleState to transition away from
	 * @param target The SimpleState to transition to
	 * @param guardCondition An optional guard condition that must be satisfied in order to pass
	 */
	public Completion( SimpleState source, SimpleState target, IGuard guard ) {
		this.guardCondition = guard;
		this.path = new Path( source, target );

		source.addCompletion( this );
	}

	Boolean isElse() {
		return false;
	}
	
	/**
	 * Adds a transition effect
	 * @param behaviour
	 */
	public void addEffect( IBehaviour behaviour ) {
		if( this.effect == null )
			this.effect = new ArrayList< IBehaviour >();

		this.effect.add( behaviour );
	}

	/**
	 * Calls the transtion effects; override to change desired behaviour
	 */
	protected void onEffect() {
		if( this.effect != null )
			for( IBehaviour behaviour : this.effect )
				behaviour.execute();
	}

	Boolean guard() {
		return this.guardCondition == null || this.guardCondition.evaluate();
	}

	void traverse( IState context, Boolean deepHistory ) throws StateMachineException {
		path.exit( context );

		this.onEffect();

		path.enter( context, deepHistory );
	}
	
	public final class Else extends Completion {
		public Else( PseudoState source, PseudoState target ) {
			super( source, target, new IGuard() { public Boolean evaluate() { return false; } } );
		}

		public Else( PseudoState source, SimpleState target ) {
			super( source, target, new IGuard() { public Boolean evaluate() { return false; } } );
		}

		@Override
		Boolean isElse() {
			return true;
		}		
	}
}
