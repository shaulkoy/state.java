package net.steelbreeze.behaviour;

import java.util.*;

class Path {
	private List<Element> exit;
	private List<Element> enter;
	
	Path( Element source, Element target ) {
		List<Element> sourceAncestors = source.getAncestors();
		List<Element> targetAncestors = target.getAncestors();
		int uncommonAncestor = source.getOwner().equals( target.getOwner() ) ? sourceAncestors.size() - 1 :  uncommon( sourceAncestors, targetAncestors, 0 );
					
		this.exit =  sourceAncestors.subList( uncommonAncestor, sourceAncestors.size() );
		this.enter = targetAncestors.subList( uncommonAncestor, targetAncestors.size() );
		
		Collections.reverse( this.exit );
 	}
	
	void exit( IState context ) {
		this.exit.get( 0 ).beginExit( context );	
		
		for( Element element : this.exit )
			element.endExit( context );
	}
	
	void enter( IState context, Boolean deepHistory ) throws StateMachineException {		
		for( Element element : this.enter )
			element.beginEnter( context );

		this.enter.get( this.enter.size() - 1 ).endEnter( context, deepHistory );
	}
	
	private static int uncommon( List<Element> sourceAncestors, List<Element> targetAncestors, int index )
	{
		return sourceAncestors.get( index ).equals( targetAncestors.get( index ) ) ? uncommon( sourceAncestors, targetAncestors, ++index ) : index;
	}
}
