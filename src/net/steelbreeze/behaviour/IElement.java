package net.steelbreeze.behaviour;

public interface IElement {
	IElement Owner();
	
	void Exit( IState context );
	void Enter( IState context );
}
