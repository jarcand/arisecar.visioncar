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
	
	/**
	 * Indicate the starting position of the "real" car in the "real" map. Well 
	 * in fact it is the reverse but who care.
	 * 
	 * This data is just used for drawing.
	 */
	private double difPosX;
	private double difPosY;
	
	public VisionCar(World world){
		this.world = world;
		difPosX = -world.car.posX;
		difPosY = -world.car.posY;
	}
	
	public void draw(Graphics2D g){
		g.setStroke(new BasicStroke(1));
		
		g.setColor(Color.blue);
		int x = (int) (world.car.posX+difPosX-world.car.radius);
		int y= (int) (world.car.posY+difPosY-world.car.radius);
		int width = (int) (world.car.radius*2);
		int height = (int) (world.car.radius*2);
		g.fillOval(x, y, width, height);
		
		g.setColor(Color.red);
		x = (int) (world.car.posX+difPosX);
		y = (int) (world.car.posY+difPosY);
		int dirX = (int) (world.car.radius*Math.cos(world.car.direction));
		int dirY = (int) (world.car.radius*Math.sin(world.car.direction));
		g.drawLine(x, y, x+dirX, y+dirY);
	}

}
