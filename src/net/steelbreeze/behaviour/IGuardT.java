package net.steelbreeze.behaviour;

public interface IGuardT<TMessage> {
	public Boolean evaluate( TMessage message );
}
