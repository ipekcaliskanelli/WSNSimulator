package org.wsnsimulator;

import java.util.ArrayList;
import java.util.List;

import org.wsnsimulator.nodes.CommunicationLink;
import org.wsnsimulator.nodes.SensorNode;

public class SensorNetwork {
	
	protected List <SensorNode> sensors;
	protected List <CommunicationLink> links;
	
	public SensorNetwork() {
		sensors = new ArrayList<SensorNode>();
		links = new ArrayList<CommunicationLink>();
	}

	public List<SensorNode> getSensors() {
		return sensors;
	}
	
	public List<CommunicationLink> getLinks() {
		for(SensorNode s: sensors) {
			links.addAll(s.getNeighbours());
		}
		return links;
	}
}
