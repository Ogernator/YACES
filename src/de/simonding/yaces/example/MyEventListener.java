package de.simonding.yaces.example;

import de.simonding.yaces.EventListener;

public class MyEventListener implements EventListener<MyEvent> {
	
	@Override
	public void receiveEvent(MyEvent event) {
		System.out.println("Event received!");
	}

}
