package org.wsnsimulator.nodes;

import java.util.List;

public abstract class SensorNode {

	/**
	 *  Battery capacity of the node in mAh.
	 */
	protected double batteryCapacity;

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
	 * Energy consumption of the node when communicating 1 byte of info in uAs.
	 */
	protected double communicationalConsumptionRate;

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
	private Location location; 

	/**
	 * ID number of node.
	 */
	protected int nodeID;

	public boolean isInTransmissionRange(SensorNode s) {

		return (Math.pow(s.getLocation().getxCoor()-this.getLocation().getxCoor(),2)+Math.pow(s.getLocation().getyCoor()-this.getLocation().getyCoor(),2)
				<=Math.pow(this.transmissionRange,2));


	}
	public List<CommunicationLink> getNeighbours() {
		return neighbours;
	}


	public void setNeighbours(List<CommunicationLink> neighbours) {
		this.neighbours = neighbours;
	}

	public void connectNeighbours(List<SensorNode> snsrs) {

		for (SensorNode s: snsrs) {
			if(s!=this && this.isInTransmissionRange(s)) {
				this.neighbours.add(new CommunicationLink(this, s));
			}
		}
	}

	public Location getLocation() {
		return location;
	}
	public void setLocation(Location l) {
		this.location=l;		
	}
	public double getBatteryCapacity() {
		return batteryCapacity;
	}

	public double getTransmissionRange() {
		return transmissionRange;
	}

	public double getIdleConsumptionRate() {
		return idleConsumptionRate;
	}

	public double getProcessingConsumptionRate() {
		return processingConsumptionRate;
	}

	public double getCommunicationalConsumptionRate() {
		return communicationalConsumptionRate;
	}

	public int getMemoryCapacity() {
		return memoryCapacity;
	}


	public int getNodeID() {
		return nodeID;
	}

	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}
	public void setTransmissionRange(double transmissionRange) {
		this.transmissionRange = transmissionRange;
	}
	public void updateBatteryLevel(double consumtion) {
		this.batteryCapacity = this.getBatteryCapacity() - consumtion;
	}

}
