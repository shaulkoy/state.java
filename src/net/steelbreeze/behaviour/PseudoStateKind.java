package net.steelbreeze.behaviour;

import java.util.ArrayList;
import java.util.Random;

public enum PseudoStateKind {

	Choice, DeepHistory, Initial, Junction, ShallowHistory, Terminate;

	public boolean isHistory() {
		switch( this ) {
			case DeepHistory:
			case ShallowHistory:
				return true;

			default:
				return false;
		}
	}

	public boolean isInitial() {
		switch( this ) {
			case DeepHistory:
			case Initial:
			case ShallowHistory:
				return true;

			default:
				return false;
		}
	}

	private static Random random = new Random();

	public Completion completion( ArrayList< Completion > completions ) throws StateMachineException {
		switch( this ) {
			case Choice: {
				ArrayList< Completion > results = new ArrayList< Completion >();

				for( Completion completion : completions )
					if( !completion.isElse() )
						if( completion.guard() )
							results.add( completion );

				if( results.size() > 0 )
					return results.get( random.nextInt( results.size() ) );

				for( Completion completion : completions )
					if( completion.isElse() )
						results.add( completion );

				if( results.size() > 1 )
					throw new StateMachineException( "Malformed state machine; multiple else transitions " + this );

				if( results.size() == 0 )
					throw new StateMachineException( "Malformed state machine; no completion transitions evaluated true from " + this );

				return results.get( 0 );
			}

			case Junction: {
				Completion result = null;
				
				for( Completion completion : completions ) {
					if( !completion.isElse() ) {
						if( completion.guard() ) {
							if( result != null )
								throw new StateMachineException( "Malformed state machine; multiple completion transitions evaluated true from " + this );

							result = completion;
						}
					}
				}

				if( result != null )
					return result;

				for( Completion completion : completions ) {
					if( completion.isElse() ) {
						if( result != null )
							throw new StateMachineException( "Malformed state machine; multiple else completion transitions from " + this );

						result = completion;
					}
				}

				if( result == null )
					throw new StateMachineException( "Malformed state machine; no completion transitions evaluated true from " + this );
								
				return result;
			}

			case Terminate:
				return null;

			default: {
				if( completions.size() != 1 )
					throw new StateMachineException( "Malformed state machine; initial pseuso states must have a single transitions (" + this + ")" );

				return completions.get( 0 );
			}
		}
	}
}
