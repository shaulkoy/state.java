package net.steelbreeze.behaviour;

public class Transition<TMessage> implements ITransition {
	IElement target;
	IGuardT<TMessage> guard;
	
	public Transition( IElement source, IElement target, IGuardT<TMessage> guard ) {
		this.guard = guard;
		this.target = target;
	}
}
