package org.wsnsimulator.nodes;

public class Sink extends SensorNode{

	/*
	 * Unique id for sink only. Different than nodeID
	 */
	protected int id;

	protected boolean sink;

	public int getId() {
		return id;
	}

	public boolean isSink() {
		return sink;
	}
	public Sink() {
		this.sink=true;
	}

}
