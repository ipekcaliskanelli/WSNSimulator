package org.wsnsimulator.events.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.wsnsimulator.SensorNetwork;
import org.wsnsimulator.events.AbstractEvent;
import org.wsnsimulator.events.tasks.DAGTaskSetGenerator.TaskSetBodySize;
import org.wsnsimulator.nodes.CommunicationLink;
import org.wsnsimulator.nodes.SensorNode;

public class TaskSetMapper {

	protected SensorNetwork sensorNet;
	protected AbstractEventGenerator eventGenerator;
	protected AbstractTaskSetGenerator taskGenerator;

	public TaskSetMapper(SensorNetwork sensorNet, AbstractEventGenerator eventGenerator, AbstractTaskSetGenerator taskGenerator) {
		this.sensorNet=sensorNet;
		this.eventGenerator=eventGenerator;
		this.taskGenerator=taskGenerator;
	}

	public boolean isInTransmissionRange(SensorNode s, AbstractEvent e) {

		return (Math.pow(s.getLocation().getxCoor()-e.getPointofOrigin().getxCoor(),2)+Math.pow(s.getLocation().getyCoor()-e.getPointofOrigin().getyCoor(),2)
				<=Math.pow(e.getRadius(),2));


	}

	public List<SensorNode> findSensorNodesInTheEventRadius(AbstractEvent e, ArrayList<SensorNode> sensors) {
		List<SensorNode> nodesInRadius = new ArrayList<SensorNode>();

		for (SensorNode s: sensors) {
			if(this.isInTransmissionRange(s, e)) {
				nodesInRadius.add(s);
			}
		}


		return nodesInRadius;
	}

	public void run() {

		/**
		 * Generate event: radius and origin
		 */
		AbstractEvent event = eventGenerator.generate();


		/**
		 * Find how many nodes can detect the event
		 */
		List<SensorNode> nodesInRadius = findSensorNodesInTheEventRadius(event, (ArrayList<SensorNode>) sensorNet.getSensors());


		/**
		 * Create DAGs that will be heard by the nodes in the transmission range and map them onto the sensor nodes. 
		 */

		for (int i=0; i<nodesInRadius.size(); i++) {
			TaskSet t=taskGenerator.generate();
			//TODO: map
		}


	}
}
