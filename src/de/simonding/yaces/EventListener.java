package de.simonding.yaces;

public interface EventListener<E> {
	public void receiveEvent(E event);
}
