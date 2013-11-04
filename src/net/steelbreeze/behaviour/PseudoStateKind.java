package net.steelbreeze.behaviour;

import java.util.HashSet;

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
	
	public Completion completion( HashSet<Completion> completions ) {
		switch( this ) {
		
		// TODO: Choice
		
		// TODO: Junction
		
		case Terminate:
			return null;
		
		default:
			return Enumerable.singleOrDefault(completions);
		}
	}
}
