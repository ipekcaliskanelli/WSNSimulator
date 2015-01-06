package org.wsnsimulator.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.wsnsimulator.SensorNetwork;
import org.wsnsimulator.events.AbstractEvent;
import org.wsnsimulator.events.tasks.Task;

public class Route {

	protected SensorNetwork network;
	protected AbstractEvent event;
	private Set<SensorNode> settledNodes;
	private Set<SensorNode> unSettledNodes;
	private Map<SensorNode, SensorNode> predecessors;
	private Map<SensorNode, Integer> distance;

	public Route(SensorNetwork s, AbstractEvent event) {
		this.network = s;
		this.event = event;
	}

	public void execute(SensorNode source)
	{
		settledNodes = new HashSet<SensorNode>();
		unSettledNodes = new HashSet<SensorNode>();
		distance = new HashMap<SensorNode, Integer>();
		predecessors = new HashMap<SensorNode, SensorNode>();
		distance.put(source, 0);
		unSettledNodes.add(source);
		while (unSettledNodes.size() > 0) {
			SensorNode node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);


		}
	}

	private void findMinimalDistances(SensorNode node) {
		List<SensorNode> adjacentNodes = getNeighbors(node);
		for (SensorNode target : adjacentNodes) {
			if (getShortestDistance(target) > getShortestDistance(node)) {
				distance.put(target, getShortestDistance(node));
				predecessors.put(target, node);
				unSettledNodes.add(target);
			}
		}

	}

	private List<SensorNode> getNeighbors(SensorNode node) {
		List<SensorNode> neighbors = new ArrayList<SensorNode>();
		for (CommunicationLink<SensorNode> links : network.getLinks()) {
			if (links.getSender().equals(node)
					&& !isSettled(links.getReceiver())) {
				neighbors.add(links.getReceiver());
			}
		}
		return neighbors;
	}

	/**
	 * Returns the shortest path of the available roots
	 * 
	 * @param SensorNodes
	 * @return
	 */
	private SensorNode getMinimum(Set<SensorNode> SensorNodes) {
		SensorNode minimum = null;
		for (SensorNode sn : SensorNodes) {
			if (minimum == null) {
				minimum = sn;
			} else {
				if ((getShortestDistance(sn) < getShortestDistance(minimum))&& ((sn.getBatteryCapacity()>(sn.getCommunicationalConsumptionRate())))){
					// (sn.getBatteryCapacity()>(sn.getCommunicationalConsumptionRate())) should be changed to send event id in bytes times consumption rate
					if (getShortestDistance(sn) < getShortestDistance(minimum)){	
						minimum = sn;
					}
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(SensorNode node) {

		return settledNodes.contains(node);
	}

	private int getShortestDistance(SensorNode destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	/*
	 * This method returns the path from the source to the selected target and
	 * NULL if no path exists
	 */
	public LinkedList<SensorNode> getPath(SensorNode target) {
		LinkedList<SensorNode> path = new LinkedList<SensorNode>();
		SensorNode step = target;
		// Check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		return path;
	}


}
