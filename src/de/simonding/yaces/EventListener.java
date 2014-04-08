package de.simonding.yaces;

public interface EventListener<T extends Event> {
	public void receiveEvent(T event);
}
