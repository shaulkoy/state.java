package net.steelbreeze.behaviour.examples;

import java.util.HashMap;
import net.steelbreeze.behaviour.*;

public class State implements IState {
	Boolean terminated = false;
	HashMap<Element, Boolean> active = new HashMap<Element, Boolean>();
	HashMap<Element, SimpleState> current = new HashMap<Element, SimpleState>();
	
	@Override
	public void setTerminated() {
		this.terminated = true;
	}

	@Override
	public boolean getTerminated() {
		return this.terminated;
	}

	@Override
	public void setActive(Element element, Boolean value) {
		if( this.active.containsKey(element))
			this.active.remove(element); // TODO: make much cleaner (update in place)

		this.active.put(element, value);
	}

	@Override
	public boolean getActive(Element element) {
		return this.active.containsKey(element) ? this.active.get(element) : false; // TODO: single hit on collection
	}

	@Override
	public void setCurrent(Element element, SimpleState value) {
		if( this.current.containsKey(element))
			this.current.remove(element); // TODO: make much cleaner (update in place)

		this.current.put(element, value);
	}

	@Override
	public SimpleState getCurrent(Element element) {
		return this.current.containsKey(element) ? this.current.get(element) : null; // TODO: single hit on collection
	}
}
