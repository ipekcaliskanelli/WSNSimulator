package org.wsnsimulator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.wsnsimulator.nodes.CommunicationLink;
import org.wsnsimulator.nodes.Location;
import org.wsnsimulator.nodes.SensorNode;

public class TestSensorNetwork {

	@Test
	public void test() {

		SensorNetwork ipy = new SensorNetwork();
		int numberOfSensors=5;

		for(int i=0; i<numberOfSensors; i++)
		{
			SensorNode ip = new SensorNode() { };
			ipy.sensors.add(ip);
		}
		assertEquals(numberOfSensors,ipy.sensors.size());
	}
	
	public void testConnectionNetwork() {

		SensorNetwork ipy = new SensorNetwork();
		ipy.sensors = new ArrayList<SensorNode>();
		int numberOfSensors=2;
		int sizeOfMesh=4;
		int meshx=1;
		int meshy=1;

		for(int i=0; i<numberOfSensors; i++)
		{
			for(int j=0; j<numberOfSensors; j++)
			{
				SensorNode ip = new SensorNode() { };
				ip.transmissionRange=1;
				ip.neighbours=new ArrayList<CommunicationLink>();
				ip.setLocation(new Location(meshx+i, meshy+j));
				
				ipy.sensors.add(ip);
			}
		}
		assertEquals(sizeOfMesh,ipy.sensors.size());
		
		for(int i=0; i<ipy.sensors.size(); i++) {
			ipy.sensors.get(i).connectNeighbours(ipy.sensors);
			for(int j=0; j<ipy.sensors.get(i).neighbours.size(); j++) {
				//System.out.println("Sender is " + ipy.sensors.get(i).neighbours.get(j).getSender() + "and receiver is " + 
					//	ipy.sensors.get(i).neighbours.get(j).getReceiver());
			}
		}

	}
	
	@Test
	public void testNeigh() {
		SensorNode ip1 = new SensorNode() { };
		SensorNode ip2 = new SensorNode() { };
		ip1.transmissionRange=1;
		ip1.setLocation(new Location(0,0));
		ip2.transmissionRange=2;
		ip2.setLocation(new Location(0,2));
	
		ip1.neighbours=new ArrayList<CommunicationLink>();
		ip2.neighbours=new ArrayList<CommunicationLink>();
		
		
		ArrayList<SensorNode> list = new ArrayList<SensorNode>();
		list.add(ip1);
		list.add(ip2);
		
		ip1.connectNeighbours(list);
		ip2.connectNeighbours(list);
		
		assertEquals(ip1.neighbours.size(),0);
		assertEquals(ip2.neighbours.size(),1);
	}
	
}