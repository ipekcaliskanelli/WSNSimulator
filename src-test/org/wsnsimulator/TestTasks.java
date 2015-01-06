package org.wsnsimulator;
import static org.junit.Assert.*;

import org.junit.Test;
import org.wsnsimulator.events.tasks.RandomTaskSetGenerator;
import org.wsnsimulator.events.tasks.TaskSet;


public class TestTasks {

	@Test
	public void test() {

		RandomTaskSetGenerator tg = new RandomTaskSetGenerator(10, 9);
		TaskSet ts = new TaskSet(null);
		ts=	tg.generate();
		assertEquals(10,ts.getTaskqueue().size());
		assertEquals(9, ts.getCommunications().size());
	}

}
