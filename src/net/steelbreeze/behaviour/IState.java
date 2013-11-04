package net.steelbreeze.behaviour;

public interface IState {
	public void setTerminated();
	
	public boolean getTerminated();
	
	public void setActive( Element element, Boolean value );
	
	public boolean getActive( Element element );
	
	public void setCurrent( Element element, SimpleState value );
	
	public SimpleState getCurrent( Element element );
}
