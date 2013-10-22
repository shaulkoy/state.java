package net.steelbreeze.behaviour;

public enum PseudoStateKind {
	Choice,
	DeepHistory,
	Initial,
	Junction,
	ShallowHistory,
	Terminate;

	public boolean IsHistory() {
		switch( this ) {
		case DeepHistory:
		case ShallowHistory:
			return true;
		
		default:
			return false;
		}
	}

	public boolean IsInitial() {
		switch( this ) {
		case DeepHistory:
		case Initial:
		case ShallowHistory:
			return true;
		
		default:
			return false;
		}
	}
}
