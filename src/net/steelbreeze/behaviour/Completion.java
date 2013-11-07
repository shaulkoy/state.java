package net.steelbreeze.behaviour;

import java.util.ArrayList;

public class Completion {
	Path path;
	IGuard guardCondition;
	ArrayList< IBehaviour > effect;

	public Completion( PseudoState source, PseudoState target, IGuard guardCondition ) {
		this.guardCondition = guardCondition;
		this.path = new Path( source, target );

		source.addCompletion( this );
	}

	public Completion( PseudoState source, SimpleState target, IGuard guard ) {
		this.guardCondition = guard;
		this.path = new Path( source, target );

		source.addCompletion( this );
	}

	public Completion( SimpleState source, PseudoState target, IGuard guard ) {
		this.guardCondition = guard;
		this.path = new Path( source, target );

		source.addCompletion( this );
	}

	public Completion( SimpleState source, SimpleState target, IGuard guard ) {
		this.guardCondition = guard;
		this.path = new Path( source, target );

		source.addCompletion( this );
	}

	Boolean isElse() {
		return false;
	}
	
	public void addEffect( IBehaviour behaviour ) {
		if( this.effect == null )
			this.effect = new ArrayList< IBehaviour >();

		this.effect.add( behaviour );
	}

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
	
	public class Else extends Completion {
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
