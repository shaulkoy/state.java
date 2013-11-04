package net.steelbreeze.behaviour;

import java.util.HashSet;

class Enumerable {
	public static Completion singleOrDefault(HashSet<Completion> completions) {
		return completions.size() == 1 ? completions.iterator().next() : null;
	}

}
