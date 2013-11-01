package net.steelbreeze.behaviour;

public class Transition<TMessage> implements ITransition {
	IGuardT<TMessage> guard;
	Path path;
	Element target;
	
	public Transition( Element source, Element target, IGuardT<TMessage> guard ) {
		this.guard = guard;
		this.target = target;
		this.path = new Path( source, target );
	}
}
