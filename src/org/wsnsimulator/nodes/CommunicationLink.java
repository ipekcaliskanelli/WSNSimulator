package org.wsnsimulator.nodes;

public class CommunicationLink<T> {

	protected T sender;
	protected T receiver;
	
	public CommunicationLink(T sender, T receiver) {
		this.sender=sender;
		this.receiver=receiver;
	}
	public T getSender() {
		return sender;
	}
	public T getReceiver() {
		return receiver;
	}
	public int getNodeId(SensorNode s) {
		return s.getNodeID();
	}
	
}
