package net.steelbreeze.behaviour;

public interface IGuardT<T> {
	public Boolean Evaluate( T message );
}
