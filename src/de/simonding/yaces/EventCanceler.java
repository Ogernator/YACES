package de.simonding.yaces;

public interface EventCanceler<T extends Event> {
	public boolean isToBeCanceled(T event);
}
