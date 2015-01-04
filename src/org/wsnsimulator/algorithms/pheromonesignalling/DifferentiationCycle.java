package org.wsnsimulator.algorithms.pheromonesignalling;

import org.wsnsimulator.SensorNetwork;
import org.wsnsimulator.nodes.SensorNode;

public class DifferentiationCycle {

	protected SensorNetwork network;

	public DifferentiationCycle(SensorNetwork s) {
		this.network = s;
	}

	public void differentiate() {

		for(SensorNode s:network.getSensors()) {
			if(s instanceof PSSensorNode) {
				PSSensorNode node = (PSSensorNode)s;
				if(node.getPheromoneLevel()<Configuration.threshold) {//assign the config.properties values.

					node.setQueen(true);
				}
			}
		}
		PropagationCycle pc = new PropagationCycle(network);
		pc.propagate();
	}
}
