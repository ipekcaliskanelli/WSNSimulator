package org.wsnsimulator.events.tasks;

import org.wsnsimulator.events.AbstractEvent;

public abstract class AbstractEventGenerator<T extends AbstractEvent> {

	abstract public T generate();
}
