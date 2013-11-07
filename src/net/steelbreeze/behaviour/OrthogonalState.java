package net.steelbreeze.behaviour;

import java.util.ArrayList;

public class OrthogonalState extends SimpleState {
	ArrayList< Region > regions = new ArrayList< Region >();

	public OrthogonalState( String name, Region owner ) {
		super( name, owner );
	}

	public OrthogonalState( String name, CompositeState owner ) {
		super( name, owner );
	}

	public Boolean isComplete( IState context ) {
		if( context.getTerminated() )
			return true;

		for( Region region : regions )
			if( region.isComplete( context ) == false )
				return false;

		return true;
	}

	@Override
	void beginExit( IState context ) {
		for( Region region : this.regions ) {
			if( context.getActive( region ) ) {
				region.beginExit( context );
				region.endExit( context );
			}
		}
	}

	@Override
	void endEnter( IState context, Boolean deepHistory ) throws StateMachineException {
		for( Region region : this.regions ) {
			region.beginEnter( context );
			region.endEnter( context, deepHistory );
		}
		
		super.endEnter( context, deepHistory );
	}

	@Override
	public Boolean process( IState context, Object message ) throws StateMachineException {
		Boolean result =  super.process( context, message );
		
		for( Region region : this.regions )
			if( region.process( context, message ) )
				result = true;			
		
		return result;
	}	
}
