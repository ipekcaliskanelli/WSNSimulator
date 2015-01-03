package org.wsnsimulator.events.tasks;

import java.util.Random;

import org.wsnsimulator.events.BirdCallEvent;
import org.wsnsimulator.nodes.Location;

public class BirdCallEventGenerator extends AbstractEventGenerator<BirdCallEvent> {

	protected final int width, height;
	protected final double maxRadius, minRadius;

	public BirdCallEventGenerator(int width, int height, double maxRadius, double minRadius) {
		this.width=width;
		this.height=height;
		this.maxRadius=maxRadius;
		this.minRadius=minRadius;
	}

	public BirdCallEvent generate() {

		Random random = new Random();

		/**
		 * Origin of where event occurs.
		 */
		double x=(double)random.nextInt(width)+random.nextDouble();
		double y=(double)random.nextInt(height)+random.nextDouble();
		Location origin = new Location(x, y);

		/**
		 * Radius of the event.
		 */
		double radius = minRadius+(maxRadius-minRadius)*random.nextDouble();
		
		return new BirdCallEvent(origin, radius);

	}


}
