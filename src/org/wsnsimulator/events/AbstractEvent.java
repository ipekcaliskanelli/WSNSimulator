package org.wsnsimulator.events;

import org.wsnsimulator.nodes.Location;

public abstract class AbstractEvent {

	/**
	 * Event id number.
	 */
	protected int id;	
	
	/**
	 * Radius of the event in ms.
	 */
	protected double radius;
	

	/**
	 * The point in which the event occurred. Location of event is encapsulated from the Location class under 
	 * the nodes package and cannot be changed after events are generated.
	 */
	protected Location pointofOrigin;
	
	public AbstractEvent(Location origin, double radius) {

		this.pointofOrigin = origin;
		this.radius = radius;
	}

	public int getId() {
		return id;
	}

	public Location getPointofOrigin() {
		return pointofOrigin;
	}
	
	public double getRadius() {
		return radius;
	}
}
