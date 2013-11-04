package net.steelbreeze.behaviour;

import java.util.ArrayList;

class Path {
	Element beginExit;
	ArrayList<Element> endExit = new ArrayList<Element>();
	Element[] beginEnter;
	Element endEnter;
	
	Path( Element source, Element target ) {
		ArrayList<Element> sourceAncestors = source.getAncestors();
		ArrayList<Element> targetAncestors = target.getAncestors();
		int uncommonAncestor = uncommon( sourceAncestors, targetAncestors, 0 );
				
		this.beginExit = source;
		
		for( int e = sourceAncestors.size(); e > uncommonAncestor; --e )
			this.endExit.add( sourceAncestors.get( e - 1 ) );

		this.beginEnter = new Element[ targetAncestors.size() - uncommonAncestor ];
		
		for( int e = uncommonAncestor; e < targetAncestors.size(); e++ )
			this.beginEnter[ e - uncommonAncestor ] = targetAncestors.get( e );
		
		this.endEnter = target;
 	}
	
	void exit( IState context ) {
		this.beginExit.beginExit( context );
		
		for( Element element : this.endExit ) {
			element.endExit( context );
		}
	}
	
	void enter( IState context, Boolean deepHistory ) {		
		for( Element element : this.beginEnter ) {
			element.beginEnter( context );
			
			this.endEnter.endEnter( context, deepHistory );
		}
	}
	
	private static int uncommon( ArrayList<Element> sourceAncestors, ArrayList<Element> targetAncestors, int index )
	{
		return sourceAncestors.get( index ).equals( targetAncestors.get( index ) ) ? uncommon( sourceAncestors, targetAncestors, ++index ) : index;
	}
}
