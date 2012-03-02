package impar.pointMap;

import java.awt.Rectangle;
import java.util.ArrayList;

import impar.realWorld.Map;
import impar.realWorld.World;


public class Sonde {
	
	/**
	 * A practical way to get access to everything that exist.
	 */
	private World world;
	
	/**
	 * The angle at which this sonde is facing relatively to the angle the front
	 * of the car is facing.
	 */
	double relativeAngle;
	/**
	 * The speed at which the sonde is traveling. That is just a simplication used
	 * for simulation. It is easier to detect object by just moving the sonde in 
	 * small step than to create a line and see if things intersect that line and 
	 * in which order they intersect it.
	 */
	double speed = 1;
	
	
	public Sonde(World world, double angle){
		this.world = world;
		this.relativeAngle = angle;
	}
	
	public double getAngle(){
		return relativeAngle;
	}
	
	public Point send(){

		double maxDist = 100;
		double imperfection = 0.50;
		double dist = 0;
		
		ArrayList<Rectangle> rectList = world.map.getRectList();
		FogMap fogMap = world.fogMap;
		
		double x = world.car.getPosX();
		double y = world.car.getPosY();
		double angle = world.car.getAngle() + relativeAngle;
		
		boolean collide = false;
		while(!collide){
			dist += speed;
			double nx = dist*Math.cos(angle) + x;
			double ny = dist*Math.sin(angle) + y;
			
			//Check for wall
			for(Rectangle rect : rectList){
				if(rect.contains(nx, ny)){
					collide = true;
				}
			}
			//Check for edge of the map
			if(nx < 0 || nx > Map.size*Map.number || ny < 0 || ny > Map.size*Map.number){
				collide = true;
			}
			//Check for max dist
			if(dist >= maxDist){
				return null;
			}
			
			//Fog discovery
			if(!collide){
				double fogX = nx-world.car.getStartX();
				double fogY = ny-world.car.getStartY();
				
				
				
				int fogCellX = (int) (fogX/fogMap.size);
				int fogCellY = (int) (fogY/fogMap.size);
				
				if(fogX < 0){
					fogCellX -= 1;
				}
				if(fogY < 0){
					fogCellY -= 1;
				}

				fogCellX += fogMap.number/2;
				fogCellY += fogMap.number/2;
				

				fogMap.setKnown(fogCellX, fogCellY);
			}
		}
		
		double imparValue = (imperfection * Math.random()) + (1-imperfection/2);
		double imparDist = dist*imparValue;
		
		return (new Point((int)x, (int)y, imparDist, angle));
	}

}
