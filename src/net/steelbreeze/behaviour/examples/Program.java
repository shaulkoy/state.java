package net.steelbreeze.behaviour.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.steelbreeze.behaviour.*;

public class Program {

	public static void main(String[] args) throws IOException, StateMachineException {
		
		// create the state machine root
		Region player = new Region( "player", null );
		
		// create some states
		PseudoState initial = new PseudoState( "initial", PseudoStateKind.Initial, player );
		CompositeState operational = new CompositeState( "operational", player );
		SimpleState flipped = new SimpleState( "flipped", player );
		FinalState end = new FinalState("final", player );
		
		PseudoState history = new PseudoState("history", PseudoStateKind.DeepHistory, operational );
		SimpleState stopped = new SimpleState("stopped", operational);
		CompositeState active = new CompositeState("active", operational);
		
		SimpleState running = new SimpleState("running", active);
		SimpleState paused = new SimpleState("paused", active);
		
		// add some behaviour
		active.addEntry( new IBehaviour() { public void execute() { EngageHead(); } } );
		active.addExit( new IBehaviour() { public void execute() { DisengageHead(); } } );

		running.addEntry( new IBehaviour() { public void execute() { StartMotor(); } } );
		running.addExit( new IBehaviour() { public void execute() { StopMotor(); } } );
		
		// create the transitions between states
		Completion t0 = new Completion( initial, operational, null );
		new Completion( history, stopped, null );
		new Transition<String>( stopped, running, new IGuardT<String>() { public Boolean evaluate( String message ) { return message.equals( "play" ); } } );
		new Transition<String>( active, stopped, new IGuardT<String>() { public Boolean evaluate( String message ) { return message.equals( "stop" ); } } );
		new Transition<String>( running, paused, new IGuardT<String>() { public Boolean evaluate( String message ) { return message.equals( "pause" ); } } );
		new Transition<String>( paused, running, new IGuardT<String>() { public Boolean evaluate( String message ) { return message.equals( "play" ); } } );
		new Transition<String>( operational, flipped, new IGuardT<String>() { public Boolean evaluate( String message ) { return message.equals( "flip" ); } } );
		new Transition<String>( flipped, operational, new IGuardT<String>() { public Boolean evaluate( String message ) { return message.equals( "flip" ); } } );
		new Transition<String>( operational, end, new IGuardT<String>() { public Boolean evaluate( String message ) { return message.equals( "off" ); } } );
		Transition<String> help = new Transition<String>( operational, operational, new IGuardT<String>() { public Boolean evaluate( String message ) { return message.equals( "help" ); } } );
				
		t0.addEffect( new IBehaviour() { public void execute() { DisengageHead(); } } );
		t0.addEffect( new IBehaviour() { public void execute() { StopMotor(); } } );
		help.addEffect( new IBehaviourT<String>() { public void execute( String command ) { System.out.println( "help yourself"); } } );
		
		State state = new State();
		
		player.initialise( state );
		
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );

		while( player.isComplete(state) == false  ) {
			
			System.out.print( "> " );
			
			if( player.process(state, br.readLine() ) == false )
					System.out.println( "unknown command" );
		}
	}
	
	public static void EngageHead() {
		System.out.println( "- engaging head" );
	}
	
	public static void DisengageHead() {
		System.out.println( "- disengaging head" );
	}
	
	public static void StartMotor() {
		System.out.println( "- starting motor" );
	}
	
	public static void StopMotor() {
		System.out.println( "- stopping motor" );
	}
}
