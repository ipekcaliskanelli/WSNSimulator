package org.wsnsimulator.algorithms.pheromonesignalling;

import org.wsnsimulator.SensorNetwork;
import org.wsnsimulator.nodes.SensorNode;

public class DecayCycle {

	protected SensorNetwork network;
	protected int decayFactor=2;

	public DecayCycle(SensorNetwork s) {
		this.network = s;
	}

	public void decay() {

		for(SensorNode s:network.getSensors()) {
			if(s instanceof PSSensorNode) {
				PSSensorNode node = (PSSensorNode)s;
				node.setPheromoneLevel(node.getPheromoneLevel()/decayFactor);
			}
		}
	}
}

