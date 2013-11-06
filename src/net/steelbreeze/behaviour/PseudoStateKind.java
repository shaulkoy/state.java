package net.steelbreeze.behaviour;

import java.util.ArrayList;

public enum PseudoStateKind {
	
	Choice,
	DeepHistory,
	Initial,
	Junction,
	ShallowHistory,
	Terminate;

	public boolean isHistory() {
		switch( this ) {
		case DeepHistory:
		case ShallowHistory:
			return true;
		
		default:
			return false;
		}
	}

	public boolean isInitial() {
		switch( this ) {
		case DeepHistory:
		case Initial:
		case ShallowHistory:
			return true;
		
		default:
			return false;
		}
	}
	
	public Completion completion( ArrayList<Completion> completions ) throws StateMachineException {
		switch( this ) {
		
		// TODO: Choice
		
		// TODO: Junction
		
		case Terminate:
			return null;
		
		default:
			if( completions.size() != 1 )
				throw new StateMachineException();
			
			return completions.get( 0 );
		}
	}
}
