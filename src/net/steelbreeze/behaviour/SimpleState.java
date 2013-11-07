package net.steelbreeze.behaviour;

import java.util.ArrayList;

public class SimpleState extends Element {
	private ArrayList<Completion> completions;
	private ArrayList<ITransition> transitions;
	ArrayList<IBehaviour> exit;
	ArrayList<IBehaviour> entry;
	
	public SimpleState(String name, Region owner) {
		super( name, owner );
	}

	public SimpleState(String name, CompositeState owner) {
		super( name, owner );
	}

	void addCompletion( Completion completion ) {
		if( this.completions == null )
			this.completions = new ArrayList<Completion>();
		
		this.completions.add(completion);
	}

	void addTransition( ITransition transition ) {
		if( this.transitions == null )
			this.transitions = new ArrayList<ITransition>();
		
		this.transitions.add(transition);
	}

	public void addExit(IBehaviour behaviour) {
		if( this.exit == null )
			this.exit = new ArrayList<IBehaviour>();
		
		this.exit.add( behaviour );
	}
	
	public void addEntry(IBehaviour behaviour) {
		if( this.entry == null )
			this.entry = new ArrayList<IBehaviour>();
		
		this.entry.add( behaviour );
	}
	
	public Boolean isComplete( IState context ) {
		return true;
	}
	
	protected void onExit() {
		if( this.exit != null )
			for( IBehaviour behaviour : this.exit )
				behaviour.execute();
	}
	
	protected void onEnter() {
		if( this.entry != null )
			for( IBehaviour behaviour : this.entry )
				behaviour.execute();
	}

	@Override
	void endExit(IState context) {
		this.onExit();
		super.endExit(context);
	}
	
	@Override
	void beginEnter(IState context) {
		super.beginEnter(context);
		
		if( this.getOwner() != null )
			context.setCurrent( this.getOwner(), this );
			
		this.onEnter();
	}

	@Override
	void endEnter(IState state, Boolean deepHistory) throws StateMachineException {
		if( this.completions != null ) {
			if( this.isComplete(state)) {
				ArrayList<Completion> results = new ArrayList<Completion>();
				
				for( Completion completion : this.completions )
					if( completion.guard() )
						results.add( completion );
								
				if( results.size() > 1 )
					throw new StateMachineException( "Malformed state machine; multiple completion transitions evaluated true from " + this );
				
				if( results.size() == 1 )
					results.get( 0 ).traverse(state,  deepHistory );
			}
		}
	}
	
	public Boolean process( IState context, Object message ) throws StateMachineException {
		if( context.getTerminated())
			return false;
		
		if( this.transitions == null )
			return false;
		
		ArrayList<ITransition> results = new ArrayList<ITransition>();
		
		for( ITransition transition : transitions )
			if( transition.guard( message ) )
				results.add( transition );
		
		if( results.size() == 0 )
			return false;

		if( results.size() > 1 )
			throw new StateMachineException( "Malformed state machine; multiple transitions evaluated true from " + this + " for " + message );
		
		results.get( 0 ).traverse( context, message );
		
		return true;
	}
}
