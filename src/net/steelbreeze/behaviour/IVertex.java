package net.steelbreeze.behaviour;

public interface IVertex extends IElement {
	void Complete( IState context, Boolean deepHistory);
}
