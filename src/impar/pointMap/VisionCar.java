package impar.pointMap;

import impar.realWorld.World;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Represent the vision the car has of itself. This class describe where the car
 * think he is, what angle he think he is facing, etc.
 * 
 * The function of this class are not complete for now but they might become at
 * some point depending how far we get this simulation to.
 * 
 * Currently this class is mostly used to draw the representation of the car on
 * the PointMap.
 * 
 * @author Gudradain
 *
 */
public class VisionCar {
	
	/**
	 * A practical way to get access to everything that exist.
	 */
	private World world;
	
	public VisionCar(World world){
		this.world = world;
	}
	
	public double getPosX(){
		return world.car.getPosX()-world.car.getStartX();
	}
	
	public double getPosY(){
		return world.car.getPosY()-world.car.getStartY();
	}
	
	public double getRadius(){
		return world.car.getRadius();
	}
	
	public void draw(Graphics2D g){
		g.setStroke(new BasicStroke(1));
		
		g.setColor(Color.blue);
		int x = (int) (getPosX()-getRadius());
		int y= (int) (getPosY()-getRadius());
		int width = (int) (getRadius()*2);
		int height = (int) (getRadius()*2);
		g.fillOval(x, y, width, height);
		
		g.setColor(Color.pink);
		x = (int) (getPosX());
		y = (int) (getPosY());
		int dirX = (int) (getRadius()*Math.cos(world.car.getAngle()));
		int dirY = (int) (getRadius()*Math.sin(world.car.getAngle()));
		g.drawLine(x, y, x+dirX, y+dirY);
	}

}
