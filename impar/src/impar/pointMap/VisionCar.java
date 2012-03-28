package impar.pointMap;

import impar.realWorld.Car;
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
	 * The car associated with this virtual representation
	 */
	private final Car car;
	
	public VisionCar(Car car){
		this.car = car;
	}
	
	public double getPosX(){
		return car.getPosX()-car.getStartX();
	}
	
	public double getPosY(){
		return car.getPosY()-car.getStartY();
	}
	
	public double getRadius(){
		return car.getRadius();
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
		int dirX = (int) (getRadius()*Math.cos(car.getAngle()));
		int dirY = (int) (getRadius()*Math.sin(car.getAngle()));
		g.drawLine(x, y, x+dirX, y+dirY);
	}

}
