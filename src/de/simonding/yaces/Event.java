package de.simonding.yaces;

import java.util.EventObject;

import lombok.Getter;

public abstract class Event extends EventObject {
	
	private static final long serialVersionUID = 1L;
	@Getter private final boolean cancelable;

	public Event(Object source, boolean isCancelable) {
		super(source);
		this.cancelable = isCancelable;
	}

}