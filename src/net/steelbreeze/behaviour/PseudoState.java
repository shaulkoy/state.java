package net.steelbreeze.behaviour;

import java.util.ArrayList;

public class PseudoState extends Element {
	private PseudoStateKind kind;
	private ArrayList<Completion> completions;
	
	public PseudoState(String name, PseudoStateKind kind, Region owner) {
		super( name, owner);
		
		this.kind = kind;
		
		if( this.kind.isInitial() )
			owner.initial = this;
	}
	
	public PseudoState(String name, PseudoStateKind kind, CompositeState owner) {
		super( name, owner);
		
		this.kind = kind;
		
		if( this.kind.isInitial() )
			owner.initial = this;
	}

	public PseudoStateKind getKind() {
		return kind;
	}
	
	void addCompletion( Completion completion ) {
		if( this.completions == null )
			this.completions = new ArrayList<Completion>();
		
		this.completions.add(completion);
	}
	
	@Override
	void beginEnter(IState context) {
		super.beginEnter( context );
		
		if( this.kind == PseudoStateKind.Terminate )
			context.setTerminated();
	}

	@Override
	void endEnter(IState state, Boolean deepHistory) throws StateMachineException {
		this.kind.completion( completions ).traverse( state, deepHistory );
	}	
}
