package net.steelbreeze.behaviour;

public interface IBehaviourT<TMessage> {
	public void execute( TMessage message );
}
