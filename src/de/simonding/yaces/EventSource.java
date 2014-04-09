package de.simonding.yaces;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.omg.Dynamic.Parameter;


public class EventSource {
	private HashMap<String, List<EventListener<Event>>> listenerLists = new HashMap<>();
	private HashMap<String, List<EventCanceler<Event>>> cancelerLists = new HashMap<>();
	
	@SuppressWarnings("unchecked")
	public synchronized <T extends Event> void addEventListener(EventListener<T> listener, Class<T> eventClass) {
		
		String key = eventClass.getName();
		
		//Create List if not done already
		if(!listenerLists.containsKey(key)){
			List<EventListener<Event>> value = new Vector<>();
			listenerLists.put(key, value);
		}
		
		//Add listener to List
		listenerLists.get(key).add((EventListener<Event>) listener);
	}
	
	public synchronized <T extends Event> void removeListener(EventListener<T> listener, Class<T> eventClass) {
		String key = eventClass.getName();
		
		//Remove Listener from List
		listenerLists.get(key).remove(listener);
		
		//Remove List if empty
		if(listenerLists.get(key).isEmpty()){
			listenerLists.remove(key);
		}
	}
	
	@SuppressWarnings("unchecked")
	public synchronized <T extends Event> void addEventCanceler(EventCanceler<T> listener, Class<T> eventClass) {
		
		String key = eventClass.getName();
		
		//Create List if not done already
		if(!cancelerLists.containsKey(key)){
			List<EventCanceler<Event>> value = new Vector<>();
			cancelerLists.put(key, value);
		}
		
		//Add listener to List
		cancelerLists.get(key).add((EventCanceler<Event>) listener);
	}
	
	public synchronized <T extends Event> void removeCanceler(EventCanceler<T> listener, Class<T> eventClass) {
		String key = eventClass.getName();
		
		//Remove Listener from List
		cancelerLists.get(key).remove(listener);
		
		//Remove List if empty
		if(cancelerLists.get(key).isEmpty()){
			cancelerLists.remove(key);
		}
	}
	
	public synchronized <T extends Event> void fireEvent(T event){
		String key = event.getClass().getName();
		
		if(event.isCancelable()){
			Iterator<EventCanceler<Event>> i = cancelerLists.get(key).iterator();
			while(i.hasNext()){
				if(i.next().isToBeCanceled(event)){
					return;
				}
			}
		}
		
		if(listenerLists.containsKey(key)){
			Iterator<EventListener<Event>> i = listenerLists.get(key).iterator();
			while(i.hasNext()){
				
				i.next().receiveEvent(event); //Compiler Error
			}
		}
	}
	
}
