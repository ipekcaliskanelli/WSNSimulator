package org.wsnsimulator.events.tasks;

import java.util.Random;

import org.wsnsimulator.events.tasks.DAGTaskSetGenerator.TaskSetBodySize;

public class main {
	
	public static void main(String[] args) {
		
		Random x=new Random(2);
		DAGTaskSetGenerator dag=new DAGTaskSetGenerator(TaskSetBodySize.LARGE,x);
		TaskSet ts = dag.generate();
		for(int i=0; i<ts.communications.size(); i++) {
			System.out.println("task " + ts.communications.get(i).getSender().id + "between " + ts.communications.get(i).getReceiver().id);
		}
		
	}
	
}
