package net.steelbreeze.behaviour;

/**
 * Interface for state machine state
 * @author Mesmo
 */
public interface IState {
	
	/**
	 * Sets the state machine state to terminated (no further operations will be allowed)
	 */
	public void setTerminated();
	
	/**
	 * Returns the terminated status of the state machine state
	 * @return The terminated status of the state machine state
	 */
	public boolean getTerminated();
	
	/**
	 * Sets the active status for a given element in a state machine
	 * @param element The element to set the active status for
	 * @param value The active status
	 */
	public void setActive( Element element, Boolean value );
	
	/**
	 * Returns the active status of a given element in a state machine
	 * @param element The element to get the active status for
	 * @return The active status of a given element in a state machine
	 */
	public boolean getActive( Element element );
	
	/**
	 * Sets the current child state for a given Region or CompositeState
	 * @param element The element to set the current child for
	 * @param value The child state
	 */
	public void setCurrent( Element element, SimpleState value );
	
	/**
	 * Returns the current child state for a given Region or CompositeState
	 * @param element
	 * @return The current child state for a given Region or CompositeState
	 */
	public SimpleState getCurrent( Element element );
}
