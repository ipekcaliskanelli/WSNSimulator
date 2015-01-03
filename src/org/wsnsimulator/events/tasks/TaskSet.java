package org.wsnsimulator.events.tasks;

import java.util.ArrayList;
import java.util.List;

import org.wsnsimulator.events.AbstractEvent;
import org.wsnsimulator.nodes.CommunicationLink;

public class TaskSet {

	/**
	 * List of the tasks to be executed by a node. 
	 */
	protected List <Task> taskqueue;
	protected List <CommunicationLink<Task>> communications;
	
	protected AbstractEvent event;
	
	public TaskSet(AbstractEvent e) {
		taskqueue = new ArrayList <Task>();
		communications =  new ArrayList<CommunicationLink<Task>>();
		event=e;
	}
	
	public List<Task> getTaskqueue() {
		return taskqueue;
	}

	public AbstractEvent getEvent() {
		return event;
	}
	public List <CommunicationLink<Task>> getCommunications() {
		return communications;
	}
	
}
