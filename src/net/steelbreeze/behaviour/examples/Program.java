package net.steelbreeze.behaviour.examples;

import net.steelbreeze.behaviour.*;

public class Program {

	public static void main(String[] args) {
		
		// create the state machine root
		Region player = new Region( "player", null );
		
		// create some states
		PseudoState initial = new PseudoState( "initial", PseudoStateKind.Initial, player);
		CompositeState operational = new CompositeState( "operational", player);
		SimpleState flipped = new SimpleState( "flipped", player );
		FinalState end = new FinalState("final", player);
		
		PseudoState history = new PseudoState("history", PseudoStateKind.DeepHistory, operational);
		SimpleState stopped = new SimpleState("stopped", operational);
		CompositeState active = new CompositeState("active", operational);
		
		SimpleState running = new SimpleState("running", active);
		SimpleState paused = new SimpleState("paused", active);
		
		// add some behaviour
		active.AddEntry( new IBehaviour() { public void Execute() { EngageHead(); } } );
		active.AddExit( new IBehaviour() { public void Execute() { DisengageHead(); } } );

		running.AddEntry( new IBehaviour() { public void Execute() { StartMotor(); } } );
		running.AddExit( new IBehaviour() { public void Execute() { StopMotor(); } } );
		
		// create the transitions between states
		new Completion( initial, operational, null );
		new Completion( history, stopped, null );
		new Transition<String>( stopped, running, new IGuardT<String>() { public Boolean Evaluate( String message ) { return message == "play"; } } );
		new Transition<String>( active, stopped, new IGuardT<String>() { public Boolean Evaluate( String message ) { return message == "stop"; } } );
		new Transition<String>( running, paused, new IGuardT<String>() { public Boolean Evaluate( String message ) { return message == "pause"; } } );
		new Transition<String>( paused, running, new IGuardT<String>() { public Boolean Evaluate( String message ) { return message == "play"; } } );
		new Transition<String>( operational, flipped, new IGuardT<String>() { public Boolean Evaluate( String message ) { return message == "flip"; } } );
		new Transition<String>( flipped, operational, new IGuardT<String>() { public Boolean Evaluate( String message ) { return message == "flip"; } } );
		new Transition<String>( operational, end, new IGuardT<String>() { public Boolean Evaluate( String message ) { return message == "off"; } } );
				
		System.out.println(paused);
		System.out.println(history);
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
