package org.wsnsimulator.time;

public abstract class AbstractClockEvent {

	/**
	 * Event occurrence time.
	 */
	protected long time;

	public long getTime() {
		return time;
	}
}
