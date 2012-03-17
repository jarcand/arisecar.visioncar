package real.realWorld;

import ca.ariselab.devices.serial.LocoArduino;
import ca.ariselab.lib.serialdevices.SerialDeviceID;
import ca.ariselab.lib.serialdevices.SerialDeviceInitException;
import ca.ariselab.lib.serialdevices.SerialPortMgmt;
import real.pointMap.FogMap;
import real.pointMap.Point;
import real.pointMap.PointMap;
import real.pointMap.VisionCar;

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
	
	public final PointMap pointMap;
	public final VisionCar visionCar;
	public final FogMap fogMap;
	
	public LocoArduino locoArduino;
	
	public World(){
		pointMap = new PointMap(this);
		visionCar = new VisionCar(this);
		fogMap = new FogMap(this);
		
		SerialPortMgmt.listSerialPorts();
		
		try {
			locoArduino = new LocoArduino(new SerialDeviceID(0x70)) {
				public void inputsUpdated() {
					double pointFront = getRangeFinder2();
					double pointLeft = getRangeFinder3();
					double pointRight = getRangeFinder1();
					
					double angle = visionCar.getAngle();
					
					Point front = new Point(0, 0, pointFront, angle+0);
					Point left = new Point(0, 0, pointLeft, angle+Math.PI/6);
					Point right = new Point(0, 0, pointRight, angle-Math.PI/6);
					
					    
					pointMap.addPoint(front);
					pointMap.addPoint(left);
					pointMap.addPoint(right);
					
					
				}
			};
		} catch (SerialDeviceInitException e) {
			e.printStackTrace();
		}
	}

}
