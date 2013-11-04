package net.steelbreeze.behaviour;

import java.util.HashSet;

public class SimpleState extends Element {
	private HashSet<Completion> completions;
	private HashSet<ITransition> transitions;
	HashSet<IBehaviour> exit;
	HashSet<IBehaviour> entry;
	
	public SimpleState(String name, Region owner) {
		super( name, owner );
	}

	public SimpleState(String name, CompositeState owner) {
		super( name, owner );
	}

	void addCompletion( Completion completion ) {
		if( this.completions == null )
			this.completions = new HashSet<Completion>();
		
		this.completions.add(completion);
	}

	void addTransition( ITransition transition ) {
		if( this.transitions == null )
			this.transitions = new HashSet<ITransition>();
		
		this.transitions.add(transition);
	}

	public void addExit(IBehaviour behaviour) {
		if( this.exit == null )
			this.exit = new HashSet<IBehaviour>();
		
		this.entry.add( behaviour );
	}
	
	public void addEntry(IBehaviour behaviour) {
		if( this.entry == null )
			this.entry = new HashSet<IBehaviour>();
		
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
	void endEnter(IState state, Boolean deepHistory) {
		if( this.completions != null ) {
			if( this.isComplete(state)) {
				Completion completion = Enumerable.singleOrDefault( completions );
				
				if( completion != null )
					completion.traverse(state,  deepHistory );
			}
		}
	}		
}
