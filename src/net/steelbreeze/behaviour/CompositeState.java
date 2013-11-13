package net.steelbreeze.behaviour;

public class CompositeState extends SimpleState {
	PseudoState initial;

	public CompositeState( String name, Region owner ) {
		super( name, owner );
	}

	public CompositeState( String name, CompositeState owner ) {
		super( name, owner );
	}

	@Override
	public Boolean isComplete( IState context ) {
		return context.getTerminated() || context.getCurrent( this ) instanceof FinalState;
	}

	@Override
	void beginExit( IState context ) {
		SimpleState current = context.getCurrent( this );

		if( current != null ) {
			current.beginExit( context );
			current.endExit( context );
		}
	}

	@Override
	void endEnter( IState context, Boolean deepHistory ) throws StateMachineException {
		Element current = deepHistory || this.initial.getKind().isHistory() ? context.getCurrent( this ) : this.initial;

		if( current == null )
			current = this.initial;

		current.beginEnter( context );
		current.endEnter( context, deepHistory || this.initial.getKind() == PseudoStateKind.DeepHistory );

		super.endEnter( context, deepHistory );
	}

	@Override
	public Boolean process( IState context, Object message ) throws StateMachineException {
		if( context.getTerminated() )
			return false;

		return super.process( context, message ) || context.getCurrent( this ).process( context, message );
	}
}
