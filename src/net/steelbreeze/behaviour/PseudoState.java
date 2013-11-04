package net.steelbreeze.behaviour;

import java.util.HashSet;

public class PseudoState extends Element {
	private PseudoStateKind kind;
	private HashSet<Completion> completions;
	
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
			this.completions = new HashSet<Completion>();
		
		this.completions.add(completion);
	}
	
	@Override
	void beginEnter(IState context) {
		super.beginEnter( context );
		
		if( this.kind == PseudoStateKind.Terminate )
			context.setTerminated();
	}

	@Override
	void endEnter(IState state, Boolean deepHistory) {
		this.kind.completion( completions ).traverse( state, deepHistory );
	}	
}
