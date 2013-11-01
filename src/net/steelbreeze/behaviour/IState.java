package net.steelbreeze.behaviour;

public interface IState {
	public void SetActive( Element element, Boolean value );
	
	public Boolean GetActive( Element element );
}
