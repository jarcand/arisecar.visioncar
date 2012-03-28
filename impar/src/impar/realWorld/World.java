package impar.realWorld;

import impar.pointMap.FogMap;
import impar.pointMap.PointMap;
import impar.pointMap.VisionCar;

/**
 * A practical design I came up with while coding game. It's very useful for 
 * developing quick changing code like prototype, simulation, game, etc.
 * 
 * Basically, this class create a link between every other class. So at any time,
 * one class can access another class in just one step. Every object, in the world
 * should have a reference to the World. They might not need it at the time you
 * create it but they might need it at a later point.
 * 
 * The other class still maintain their object encapsulation so they decide what 
 * they want to show to the rest of the world and what to hide.
 * 
 * Anyway, that is just something I use for fixing some problem of creating a big
 * network of interdepent class that is hard to deal with. Such network is not scalable
 * and not refactorable. At least in my experience.
 * 
 * On a side note, this design work well as long as you are the only one working on
 * your code. If other people are working in a project with you, you should probably
 * hide away the world class under some interface so they can't do a mess with using
 * some part of your code that should only be accessible to the thing you define 
 * have an access to the world yourself.
 * 
 * @author Gudradain
 *
 */
public class World {
	
	public final Map map;
	public final Car car;
	public final Car car2;
	public final FogMap fogMap;
	
	public World(){
		map = new Map(this);
		car = new Car(this);
		car2 = new Car(this);
		fogMap = new FogMap(this);
	}

}
