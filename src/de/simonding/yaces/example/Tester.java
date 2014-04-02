package de.simonding.yaces.example;

import de.simonding.yaces.EventSource;

public class Tester {

	public static void main(String[] args) {
		System.out.println("Test - Using default EventSource");

		System.out.println("\nInitialize needed objects");
		EventSource source = new EventSource();
		MyEvent event = new MyEvent(source);
		MyEventListener listener = new MyEventListener();

		System.out.println("Adding listener");
		source.addEventListener(listener, MyEvent.class);

		System.out.print("Firing event... ");
		source.fireEvent(event);

		System.out.println("Removing listener");
		source.removeListener(listener, MyEvent.class);

		System.out.println("Firing event... ");
		source.fireEvent(event);

		System.out.println("\nTest Finished");

		/*
		 * Output:
		 * 
		 * Test - Using default EventSource
		 * 
		 * Initialize needed objects Adding listener Firing event... Event
		 * received! Removing listener Firing event...
		 * 
		 * Test Finished
		 */
	}
}