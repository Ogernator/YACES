package de.simonding.yaces;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;


public class EventSource {
	private HashMap<String, List<EventListener<? extends Event>>> events = new HashMap<>();
	
	public synchronized <T extends Event> void addEventListener(EventListener<T> listener, Class<T> eventClass) {
		
		String key = eventClass.getName();
		
		//Create List if not done already
		if(!events.containsKey(key)){
			List<EventListener<? extends Event>> value = new Vector<>();
			events.put(key, value);
		}
		
		//Add listener to List
		events.get(key).add(listener);
	}
	
	public synchronized <T extends Event> void removeListener(EventListener<T> listener, Class<T> eventClass) {
		String key = eventClass.getName();
		
		//Remove Listener from List
		events.get(key).remove(listener);
		
		//Remove List if empty
		if(events.get(key).isEmpty()){
			events.remove(key);
		}
	}
	
	public synchronized <T extends Event> void fireEvent(T event){
		String key = event.getClass().getName();
		if(events.containsKey(key)){
			Iterator<EventListener<? extends Event>> i = events.get(key).iterator();
			while(i.hasNext()){
				
				i.next().receiveEvent(event); //Compiler Error
			}
		}
	}
	
}
