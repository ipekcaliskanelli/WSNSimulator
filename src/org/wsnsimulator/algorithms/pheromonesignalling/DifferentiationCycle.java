package org.wsnsimulator.algorithms.pheromonesignalling;

import java.util.Random;

import org.wsnsimulator.SensorNetwork;
import org.wsnsimulator.nodes.SensorNode;

public class DifferentiationCycle {

	protected SensorNetwork network;
	protected int threshold=5;
	protected int initialNumberQN=5;
	protected Random rnd= new Random(5);

	public DifferentiationCycle(SensorNetwork s) {
		this.network = s;
	}

	public void differentiate() {

		for(SensorNode s:network.getSensors()) {
			if(s instanceof PSSensorNode) {
				PSSensorNode node = (PSSensorNode)s;
				if(node.getPheromoneLevel()<threshold) {//assign the config.properties values.

					node.setQueen(true);
				}
			}
		}
		for(SensorNode s:network.getSensors()) {
			if(s instanceof PSSensorNode && ((PSSensorNode) s).isQueen()) {
				System.out.println("node " + s.getNodeID() +" is a QN");
			}
		}
		PropagationCycle pc = new PropagationCycle(network);
		pc.propagate();
	}

	
	//TODO: not sure if initialQueenNodeSelection should be here
	/**
	 * Randromly selects queen nodes to start initial pheromone propagation.
	 * 
	 */
	public void initialQueenNodeSelection() {
		for (int i=0; i<initialNumberQN; i++) {
			((PSSensorNode) network.getSensors().get(rnd.nextInt(network.getSensors().size()-1))).setQueen(true);
		}
		PropagationCycle pc = new PropagationCycle(network);
		pc.propagate();
	}
}
