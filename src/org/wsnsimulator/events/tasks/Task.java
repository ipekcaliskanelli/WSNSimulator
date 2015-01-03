package org.wsnsimulator.events.tasks;

public class Task {

	/**
	 * Time it takes to be executed on the node in ms.
	 */
	protected double cpuExecutionTime;
	
	/**
	 * Time the task was generated.
	 */
	protected long arrivalTime;
	
	/**
	 * Flag to mark whether the task was executed or not.
	 */
	protected boolean executed;
	
	/**
	 * Tasks id number.
	 */
	protected int id;
	
	
	
}
