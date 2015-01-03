package org.wsnsimulator.events.tasks;
import org.wsnsimulator.nodes.CommunicationLink;
import java.util.Random;

/**
 * 
 * @author ipek: DAG's written in this class is designed according to the Figure 3.4 on http://etheses.whiterose.ac.uk/7030/1/thesis_IpekCaliskanelli.pdf
 *
 */

public class DAGTaskSetGenerator {

	/**
	 * The number of tasks placed in the body of the DAG.
	 */
	protected TaskSetBodySize bodySize;
	protected Random random;
	protected static int idNumber=1;

	enum TaskSetBodySize {
		SMALL(2), MEDIUM(4), LARGE(8);

		private final int value;

		private TaskSetBodySize(int value){
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	public DAGTaskSetGenerator(TaskSetBodySize bodySize, Random rnd) {
		this.bodySize = bodySize;
		random = rnd;
	}

	protected TaskSet generate() {

		TaskSet ts = new TaskSet(null);
		

		for(int i=0; i<2; i++) {

			Task t = new Task();
			t.id=idNumber++;
			t.arrivalTime=System.currentTimeMillis();
			t.cpuExecutionTime=5+(random.nextInt(45));
			ts.taskqueue.add(t);

		}

		CommunicationLink<Task> o = new CommunicationLink<Task>(ts.taskqueue.get(0),ts.taskqueue.get(1));
		ts.communications.add(o);

		switch(bodySize) {
		case SMALL:
			for(int i=0; i<2; i++) {
				Task t = new Task();
				t.id=idNumber++;
				t.arrivalTime=System.currentTimeMillis();
				t.cpuExecutionTime=0.05;
				ts.taskqueue.add(t);
			}
			break;
		case MEDIUM:
			for(int i=0; i<4; i++) {
				Task t = new Task();
				t.id=idNumber++;
				t.arrivalTime=System.currentTimeMillis();
				t.cpuExecutionTime=0.1;
				ts.taskqueue.add(t);
			}
			break;
		case LARGE:
			for(int i=0; i<8; i++) {
				Task t = new Task();
				t.id=idNumber++;
				t.arrivalTime=System.currentTimeMillis();
				t.cpuExecutionTime=0.2;
				ts.taskqueue.add(t);
			}
			break;
		}

		for (int i=2; i <2+bodySize.getValue(); i++) {
			CommunicationLink<Task> o1 = new CommunicationLink<Task>(ts.taskqueue.get(1),ts.taskqueue.get(i));
			ts.communications.add(o1);

		}

		for(int i=0; i<4; i++) {
			Task t = new Task();
			t.id=idNumber++;
			t.arrivalTime=System.currentTimeMillis();
			t.cpuExecutionTime=5+(random.nextInt(45));
			ts.taskqueue.add(t);
		}

		CommunicationLink<Task> o3 = new CommunicationLink<Task>(ts.taskqueue.get(ts.taskqueue.size()-2),ts.taskqueue.get(ts.taskqueue.size()-1));
		ts.communications.add(o3);		//last 2 tasks and going backwards
		CommunicationLink<Task> o4 = new CommunicationLink<Task>(ts.taskqueue.get(ts.taskqueue.size()-3),ts.taskqueue.get(ts.taskqueue.size()-2));
		ts.communications.add(o4);	
		CommunicationLink<Task> o5 = new CommunicationLink<Task>(ts.taskqueue.get(1),ts.taskqueue.get(2+bodySize.getValue()+1));
		ts.communications.add(o5);	
		CommunicationLink<Task> o6 = new CommunicationLink<Task>(ts.taskqueue.get(2+bodySize.getValue()),ts.taskqueue.get(2+bodySize.getValue()+2));
		ts.communications.add(o6);	
		
		for(int i=bodySize.getValue(); i<2; i++) {

			CommunicationLink<Task> o7 = new CommunicationLink<Task>(ts.taskqueue.get(1+i),ts.taskqueue.get(ts.taskqueue.size()-4));
			ts.communications.add(o7);	

		}
		return ts;
	}
}
