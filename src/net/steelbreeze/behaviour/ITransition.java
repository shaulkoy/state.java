package net.steelbreeze.behaviour;

interface ITransition {
	Boolean guard( Object message );
	
	void traverse( IState context, Object message ) throws StateMachineException;
}
