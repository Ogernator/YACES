package de.simonding.yaces;

import java.util.EventObject;

public abstract class Event extends EventObject {

	private static final long serialVersionUID = 1L;

	public Event(EventSource source) {
		super(source);
	}

}