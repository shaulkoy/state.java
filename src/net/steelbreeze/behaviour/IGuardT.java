package net.steelbreeze.behaviour;

public interface IGuardT<T> {
	public Boolean evaluate( T message );
}
