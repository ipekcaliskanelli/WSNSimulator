package org.wsnsimulator.algorithms.pheromonesignalling;

import org.wsnsimulator.SensorNetwork;
import org.wsnsimulator.nodes.CommunicationLink;
import org.wsnsimulator.nodes.SensorNode;

public class PropagationCycle {

	protected SensorNetwork network;
	// replace these values with the config.prop values. 
	protected int pheromoneAmountToPass =60;
	protected int hopThreshold =2;
	
	public PropagationCycle(SensorNetwork s) {
		this.network = s;
	}

	public void propagate() {

		for(SensorNode s:network.getSensors()) {
			if(s instanceof PSSensorNode) {
				PSSensorNode node = (PSSensorNode)s;
				if(node.isQueen()) {
					for(CommunicationLink<PSSensorNode> cl: node.getNeighbours()) {

						if(cl.getSender()==node) {
							Pheromone p = new Pheromone(node, cl.getReceiver(), pheromoneAmountToPass, 1);
							cl.getReceiver().addPheromones(p);
							passPheromoneToNeighbours(p, cl.getReceiver());
						}
					}
				}
			}
		}


	}
	
	public void passPheromoneToNeighbours(Pheromone p, PSSensorNode node) {
		while(p.getPheromoneHopCount()<=hopThreshold) {
			for(CommunicationLink<PSSensorNode> cl: node.getNeighbours()) {

				if(cl.getSender()==node) {
					Pheromone np = new Pheromone(node, cl.getReceiver(), pheromoneAmountToPass/2, 2);
					cl.getReceiver().addPheromones(p);
					passPheromoneToNeighbours(p, cl.getReceiver());
				}
			}
			
		}
	}


}