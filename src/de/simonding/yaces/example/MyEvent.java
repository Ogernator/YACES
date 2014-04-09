package de.simonding.yaces.example;

import de.simonding.yaces.Event;
import de.simonding.yaces.EventSource;

public class MyEvent extends Event {
	
	private static final long serialVersionUID = 5220698149052196529L;

	public MyEvent(EventSource source) {
		super(source, false);
	}

}
