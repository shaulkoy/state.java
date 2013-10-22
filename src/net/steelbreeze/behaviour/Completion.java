package net.steelbreeze.behaviour;

public class Completion {
	IGuard guard;
	IElement target;
	
	public Completion( IElement source, IElement target, IGuard guard ){
		this.guard = guard;
		this.target = target;
	}
}
