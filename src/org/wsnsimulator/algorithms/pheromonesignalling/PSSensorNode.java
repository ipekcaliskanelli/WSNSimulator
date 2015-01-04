package org.wsnsimulator.algorithms.pheromonesignalling;

import java.util.List;

import org.wsnsimulator.nodes.SensorNode;

public class PSSensorNode extends SensorNode{

	/**
	 * Pheromone level of the node.
	 */
	protected int pheromoneLevel;
	
	public List<Pheromone> getPheromones() {
		return pheromones;
	}

	/**
	 * List of received pheromones.
	 */
	List <Pheromone> pheromones;
	
	protected boolean queen;

	public boolean isQueen() {
		return queen;
	}

	public void setQueen(boolean queen) {
		this.queen = queen;
	}
	public int getPheromoneLevel() {
		return pheromoneLevel;
	}
}
