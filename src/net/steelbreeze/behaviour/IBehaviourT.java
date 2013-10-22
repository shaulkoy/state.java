package net.steelbreeze.behaviour;

public interface IBehaviourT<TMessage> {
	public void Execute( TMessage message );
}
