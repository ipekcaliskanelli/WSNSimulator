package org.wsnsimulator;

import java.util.ArrayList;
import java.util.LinkedList;

import org.wsnsimulator.algorithms.pheromonesignalling.DecayCycle;
import org.wsnsimulator.algorithms.pheromonesignalling.DifferentiationCycle;
import org.wsnsimulator.algorithms.pheromonesignalling.PSSensorNode;
import org.wsnsimulator.events.BirdCallEvent;
import org.wsnsimulator.nodes.CommunicationLink;
import org.wsnsimulator.nodes.Location;
import org.wsnsimulator.nodes.Route;
import org.wsnsimulator.nodes.SensorNode;

public class main {
	
	static int nodeid=0;
	
	public static void main(String[] args){
		
		SensorNetwork sn= new SensorNetwork();
		
		sn.sensors = new ArrayList<SensorNode>();
		int numberOfSensors=4;
		int sizeOfMesh=4;
		int meshx=0;
		int meshy=0;

		for(int i=0; i<numberOfSensors; i++)
		{
			for(int j=0; j<numberOfSensors; j++)
			{
				PSSensorNode ip = new PSSensorNode() { };
				ip.setTransmissionRange(1);
				ip.setNeighbours(new ArrayList<CommunicationLink>());
				ip.setLocation(new Location(meshx+i, meshy+j));
				ip.setNodeID(nodeid++);
				sn.getSensors().add(ip);
			}
		}
		
		for(int i=0; i<sn.sensors.size(); i++) {
			sn.sensors.get(i).connectNeighbours(sn.sensors);
			for(int j=0; j<sn.sensors.get(i).getNeighbours().size(); j++) {
				System.out.println("Sender is " + sn.sensors.get(i).getNeighbours().get(j).getNodeId((SensorNode) sn.sensors.get(i).getNeighbours().get(j).getSender())
						+ "and receiver is " + sn.sensors.get(i).getNeighbours().get(j).getNodeId((SensorNode) sn.sensors.get(i).getNeighbours().get(j).getReceiver()));
			}
		}
		BirdCallEvent e = new BirdCallEvent(new Location (1,1), 1) {
		};
		Route dijkstra = new Route(sn, e);
		SensorNode source = sn.sensors.get(0);
		SensorNode destination = sn.sensors.get(11);
		dijkstra.execute(source);
		LinkedList<SensorNode> path = dijkstra.getPath(destination);
		System.out.println("source is " + source.getNodeID()+ " and destination is " + destination.getNodeID());

		if(path.size() > 0);

		for (SensorNode sns : path) {
			System.out.println(sns.getNodeID());
		}
		DifferentiationCycle df = new DifferentiationCycle(sn);
		df.initialQueenNodeSelection();
		
		df.differentiate();
		DecayCycle dc = new DecayCycle(sn);
		dc.decay();
		
		
	}
}