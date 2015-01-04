package org.wsnsimulator.time;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Clock {

	/**
	 * Simulation time in ms.
	 */
	protected long currentTime;

	/**
	 * List of discrete events.
	 */
	protected List<AbstractClockEvent> pendingEvents; 

	private Clock() {
		pendingEvents = new ArrayList<AbstractClockEvent>();
		currentTime = 0;
	}

	protected static Clock clockInstance;

	public static Clock getClock() {
		if (clockInstance == null) {
			clockInstance = new Clock ();

		}
		return clockInstance;
	}

	public void queueEvent(AbstractClockEvent e) {
		if(currentTime>e.getTime()) throw new RuntimeException("Past events cannot be added into the event queue. " + 
				"Current time: "+	currentTime + "Event time: " + e.getTime() );

		this.pendingEvents.add(e);

		Collections.sort(pendingEvents, new Comparator<AbstractClockEvent>() {

			@Override
			public int compare(AbstractClockEvent o1, AbstractClockEvent o2) {
				if(o1.getTime()<o2.getTime())
					return -1;
				else if(o1.getTime()>o2.getTime())
					return 1;
				return 0;
			}
		});
	} 

	public void next() {

		if(pendingEvents.size()!=0) {
			AbstractClockEvent nextEvent = pendingEvents.remove(0);
			currentTime = nextEvent.getTime();
			nextEvent.execute();
		}

	}
}
