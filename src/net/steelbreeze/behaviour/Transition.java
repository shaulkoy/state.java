package net.steelbreeze.behaviour;

import java.util.ArrayList;

public class Transition<TMessage> implements ITransition {
	Path path;
	IGuardT<TMessage> guard;
	ArrayList<IBehaviourT<TMessage>> effect;
	
	public Transition( SimpleState source, PseudoState target, IGuardT<TMessage> guard ) {
		this.guard = guard;
		this.path = new Path( source, target );
		
		source.addTransition( this );
	}
	
	public Transition( SimpleState source, SimpleState target, IGuardT<TMessage> guard ) {
		this.guard = guard;
		this.path = new Path( source, target );
		
		source.addTransition( this );
	}
	
	public Transition( SimpleState source, IGuardT<TMessage> guard ) {
		this.guard = guard;
		
		source.addTransition( this );
	}
	
	public void AddEffect( IBehaviourT<TMessage> behaviour ) {
		if( this.effect == null )
			this.effect = new ArrayList<IBehaviourT<TMessage>>();
		
		this.effect.add( behaviour );
	}
	
	@SuppressWarnings("unchecked") // unsafe cast is safe as this won't get called unless Guard evaluates true and that performs a type check
	protected void OnEffect( Object message ) {		
		if( this.effect != null )
			for( IBehaviourT<TMessage> behaviour : effect )
				behaviour.execute( ( TMessage )message );
	}
	
	@SuppressWarnings("unchecked") // TODO: find a better way to perform the type check
	Boolean Guard( Object message ) {
		try {
			return this.guard == null || this.guard.evaluate( (TMessage )message  );
		}
		catch( ClassCastException e ) {
			return false;
		}
	}
	
	void Traverse( IState context, Object message ) {
		if( this.path != null )
			this.path.exit( context );
		
		this.OnEffect( message );
		
		if( this.path != null )
			this.path.enter( context, false );
	}
}
