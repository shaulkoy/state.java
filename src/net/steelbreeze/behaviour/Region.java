package net.steelbreeze.behaviour;

public class Region extends Element {
	PseudoState initial;
	
	public Region(String name, OrthogonalState owner) {
		super( name, owner );
		
		if( owner != null )
			owner.regions.add( this );
	}
	
	public boolean isComplete( IState context ) {
		return context.getTerminated() || context.getCurrent( this ) instanceof FinalState;
	}
	
	public void initialise( IState context ) {
		this.beginEnter(context);
		this.endEnter(context, false);
	}

	@Override
	void beginExit(IState context) {
		 SimpleState current = context.getCurrent(this);
		 
		 if( current != null ){
			 current.beginExit(context);
			 current.endExit(context);
		 } 
	}

	@Override
	void endEnter(IState context, Boolean deepHistory) {
		Element current = deepHistory || this.initial.getKind().isHistory() ? context.getCurrent( this ) : this.initial;
		
		if( current == null )
			current = this.initial;

		current.beginEnter( context );
		current.endEnter( context, deepHistory || this.initial.getKind() == PseudoStateKind.DeepHistory );

	}
	
	public Boolean process( IState state, Object message ) {
		if(state.getTerminated() )
			return false;
		
		return state.getActive( this ) && state.getCurrent( this ).process( state, message );
	}
}
