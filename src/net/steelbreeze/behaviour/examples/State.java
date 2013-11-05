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
		this.active.put(element, value);
	}

	@Override
	public boolean getActive(Element element) {
		Boolean active = this.active.get( element );
		
		return active == null ? false : active;
	}

	@Override
	public void setCurrent(Element element, SimpleState value) {
		this.current.put(element, value);
	}

	@Override
	public SimpleState getCurrent(Element element) {
		return this.current.get( element );
	}
}
