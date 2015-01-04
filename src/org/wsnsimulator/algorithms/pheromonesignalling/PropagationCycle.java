package org.wsnsimulator.algorithms.pheromonesignalling;

import org.wsnsimulator.SensorNetwork;
import org.wsnsimulator.nodes.SensorNode;

public class PropagationCycle {

	protected SensorNetwork network;

	public PropagationCycle(SensorNetwork s) {
		this.network = s;
	}

	public void Propagate() {

		for(SensorNode s:network.getSensors()) {
			if(s instanceof PSSensorNode) {
				PSSensorNode node = (PSSensorNode)s;
				if(node.isQueen()) {
					// create pheromone packages and send.
				}
			}
		}


	}
}