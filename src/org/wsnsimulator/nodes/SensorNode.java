package org.wsnsimulator.nodes;

import java.util.List;

public abstract class SensorNode {

	/**
	 *  Battery capacity of the node in mAh.
	 */
	protected int batteryCapacity;

	/**
	 *  Transmission radius of the node in m.
	 */
	protected double transmissionRange;

	/**
	 * Energy consumption of the node when idle in uAs.
	 */
	protected double idleConsumptionRate;

	/**
	 * Energy consumption of the node when processing tasks in uAs.
	 */
	protected double processingConsumptionRate;

	/**
	 * Memory capacity of the node in KBs.
	 */
	protected int memoryCapacity;

	/**
	 * List of connected nodes in the communication range.
	 */
	protected List<CommunicationLink> neighbours; 

	/**
	 * x, y coordinates of the node. 
	 */
	protected Location location; 

	public boolean isInTransmissionRange(SensorNode s) {

		return (Math.pow(s.location.xCoor-this.location.xCoor,2)+Math.pow(s.location.yCoor-this.location.yCoor,2)
				<=Math.pow(this.transmissionRange,2));
		

	}
	/**
	 * ID number of node.
	 */
	protected int nodeID;

	public void connectNeighbours(List<SensorNode> snsrs) {

		for (SensorNode s: snsrs) {
			if(s!=this && this.isInTransmissionRange(s)) {
				this.neighbours.add(new CommunicationLink(this, s));
			}
		}
	}
}
