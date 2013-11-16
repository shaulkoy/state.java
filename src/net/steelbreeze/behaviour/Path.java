package net.steelbreeze.behaviour;

import java.util.Collections;
import java.util.List;

/**
 * The path to traverse between a pair of Elements within a state machine hierarchy
 * @author David
 */
final class Path {
	private List<Element> exit;
	private List<Element> enter;

	/**
	 * Creates a new path between elements
	 * @param source The source element
	 * @param target The target element
	 */
	Path( Element source, Element target ) {
		// get the ancestors of both the source and target elements
		List<Element> sourceAncestors = source.getAncestors();
		List<Element> targetAncestors = target.getAncestors();
		
		// find the index of the first uncommon ancestor
		int uncommonAncestor = source.getOwner().equals( target.getOwner() ) ? sourceAncestors.size() - 1 :  uncommon( sourceAncestors, targetAncestors, 0 );

		// record the elements that need to be exited and entered during a transition
		this.exit =  sourceAncestors.subList( uncommonAncestor, sourceAncestors.size() );
		this.enter = targetAncestors.subList( uncommonAncestor, targetAncestors.size() );

		// reverse the order of those that need to be exited
		Collections.reverse( this.exit );
 	}
	
	void exit( IState context ) {
		Boolean first = true;
		
		for( Element element : this.exit ) {
			if( first ) {
				element.beginExit( context );
				first = false;
			}
			
			element.endExit( context );
		}
	}
	
	void enter( IState context, Boolean deepHistory ) throws StateMachineException {
		Element last = null;
		
		for( Element element : this.enter ) {
			element.beginEnter( context );
			last = element;
		}
		
		last.endEnter( context, deepHistory );
	}
	
	private static int uncommon( List<Element> sourceAncestors, List<Element> targetAncestors, int index )
	{
		return sourceAncestors.get( index ).equals( targetAncestors.get( index ) ) ? uncommon( sourceAncestors, targetAncestors, ++index ) : index;
	}
}
