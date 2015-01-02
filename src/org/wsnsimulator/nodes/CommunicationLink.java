package org.wsnsimulator.nodes;

public class CommunicationLink {

	protected SensorNode sender;
	protected SensorNode receiver;
	
	public CommunicationLink(SensorNode sender, SensorNode receiver) {
		this.sender=sender;
		this.receiver=receiver;
	}
	public SensorNode getSender() {
		return sender;
	}
	public SensorNode getReceiver() {
		return receiver;
	}
}
