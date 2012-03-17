package real.pointMap;

import real.realWorld.World;

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
	
	private double angle = 0;
	private double radius = 25;
	private double posX = 0;
	private double posY = 0;
	
	public VisionCar(World world){
		this.world = world;
	}
	
	public double getPosX(){
		return posX;
	}
	
	public double getPosY(){
		return posY;
	}
	
	public double getRadius(){
		return radius;
	}
	
	public double getAngle(){
		return angle;
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
		int dirX = (int) (getRadius()*Math.cos(getAngle()));
		int dirY = (int) (getRadius()*Math.sin(getAngle()));
		g.drawLine(x, y, x+dirX, y+dirY);
	}
	
	public void update(int deltaTime){
		double m1v = (world.locoArduino.getMotor1SetPoint() - 1500) / 450.0 * 92 / 60 * 102.8;
		double m2v = (world.locoArduino.getMotor2SetPoint() - 1500) / 450.0 * 97 / 60 * 102.6;
		double dist = (m1v + m2v) / 2.0 / 1000 * deltaTime; 
		double rot = (m1v - m2v) / 2.0 * (2 * Math.PI) / 171.2 / 1000 * deltaTime;
		
		angle += rot;
		posX += Math.cos(angle) * dist;
		posY += Math.sin(angle) * dist;
		
		world.pointMap.addPos(new Point((int)posX, (int)posY, 0, 0));
		
//		System.out.println("dist:" + dist + "; rot: " + rot);
	}

}
