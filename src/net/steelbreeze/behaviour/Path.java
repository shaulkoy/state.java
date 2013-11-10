package net.steelbreeze.behaviour;

import java.util.ArrayList;

class Path {
	private Element[] exit;
	private Element[] enter;
	
	Path( Element source, Element target ) {
		ArrayList<Element> sourceAncestors = source.getAncestors();
		ArrayList<Element> targetAncestors = target.getAncestors();
		int uncommonAncestor = source.getOwner().equals( target.getOwner() ) ? sourceAncestors.size() - 1 :  uncommon( sourceAncestors, targetAncestors, 0 );
				
		this.exit = new Element[ sourceAncestors.size() - uncommonAncestor ];
		this.enter = new Element[ targetAncestors.size() - uncommonAncestor ];
		
		for( int i = uncommonAncestor, s = sourceAncestors.size(); i < s; i++ )
			this.exit[ s - i - 1 ] = sourceAncestors.get( i );
				
		for( int i = uncommonAncestor, s = targetAncestors.size(); i < s; i++ )
			this.enter[ i - uncommonAncestor ] = targetAncestors.get( i );	
 	}
	
	void exit( IState context ) {
		this.exit[ 0 ].beginExit( context );
		
		for( Element element : this.exit )
			element.endExit( context );
	}
	
	void enter( IState context, Boolean deepHistory ) throws StateMachineException {		
		for( Element element : this.enter )
			element.beginEnter( context );

		this.enter[ this.enter.length - 1 ].endEnter( context, deepHistory );
	}
	
	private static int uncommon( ArrayList<Element> sourceAncestors, ArrayList<Element> targetAncestors, int index )
	{
		return sourceAncestors.get( index ).equals( targetAncestors.get( index ) ) ? uncommon( sourceAncestors, targetAncestors, ++index ) : index;
	}
}
