package de.simonding.yaces;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;


public class EventSource {
	HashMap<String, List<EventListener>> events = new HashMap<>();
	
	public synchronized <E> void addEventListener(EventListener<E> listener, Class<E> eventClass) throws InvalidParameterException{
		chekIfValid(eventClass);
		
		String key = eventClass.getName();
		
		//Create List if not done already
		if(!events.containsKey(key)){
			List<EventListener> value = new Vector<>();
			events.put(key, value);
		}
		
		//Add listener to List
		events.get(key).add(listener);
	}
	
	public synchronized <E> void removeListener(EventListener<E> listener, Class<E> eventClass) throws InvalidParameterException {
		chekIfValid(eventClass);
		
		String key = eventClass.getName();
		
		//Remove Listener from List
		events.get(key).remove(listener);
		
		//Remove List if empty
		if(events.get(key).isEmpty()){
			events.remove(key);
		}
	}
	
	/**
	 * Checks if the type variable of the listener is a subclass of de.simonding.yaces.Event.
	 * This can and has to be done by using the eventClass parameter because type variables seem not to be accessible during runtime.
	 */
	private <E> void chekIfValid(Class<E> eventClass) throws InvalidParameterException {
		if(!Event.class.isAssignableFrom(eventClass)){
			throw new InvalidParameterException("The type variable of the listener must be a subclass of de.simonding.yaces.Event");
		}
	}
	
	public synchronized void fireEvent(Event e){
		String key = e.getClass().getName();
		if(events.containsKey(key)){
			Iterator<EventListener> i = events.get(key).iterator();
			while(i.hasNext()){
				EventListener<Event> el = i.next();
				el.receiveEvent(e);
			}
		}
	}
	
}
