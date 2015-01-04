package org.wsnsimulator.algorithms.pheromonesignalling;

public class Pheromone {
	
	protected PSSensorNode sender;
	protected PSSensorNode receiver;
	protected int pheromoneAmount;
	protected int hopCount;

	public Pheromone(PSSensorNode sender, PSSensorNode receiver, int pheromone, int hopCount) {
		this.sender = sender;
		this.receiver = receiver;
		this.pheromoneAmount = pheromone;
		this.hopCount = hopCount;
	}
	

	public PSSensorNode getSender() {
		return sender;
	}

	public PSSensorNode getReceiver() {
		return receiver;
	}

	public int getPheromoneAmount() {
		return pheromoneAmount;
	}
	public int getPheromoneHopCount() {
		return hopCount;
	}

}
