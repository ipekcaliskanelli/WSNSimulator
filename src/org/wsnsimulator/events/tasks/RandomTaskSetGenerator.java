package org.wsnsimulator.events.tasks;

import java.util.Random;

import org.wsnsimulator.nodes.CommunicationLink;

public class RandomTaskSetGenerator extends AbstractTaskSetGenerator{

	protected int taskSetSize;
	protected Random random;

	public RandomTaskSetGenerator(int numberOfTasks, int randomSeed) {
		this.taskSetSize = numberOfTasks;
		this.random = new Random(randomSeed);
	}

	@Override
	public TaskSet generate() {

		TaskSet ts = new TaskSet();

		for(int i=0; i<taskSetSize; i++) {

			Task t = new Task();
			t.arrivalTime=System.currentTimeMillis();
			t.cpuExecutionTime=5+(random.nextInt(45));
			ts.taskqueue.add(t);
		}
		for(int i=0; i<ts.taskqueue.size()-1; i++) {
			CommunicationLink <Task> c = new CommunicationLink<>(ts.taskqueue.get(i), ts.taskqueue.get(i++));
			ts.communications.add(c);
		}

		return ts;
	}


}
