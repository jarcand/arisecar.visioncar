package impar.realWorld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

import impar.pointMap.Point;
import impar.pointMap.PointMap;
import impar.pointMap.Sonde;
import impar.realWorld.Map.TypeEnum;
import impar.simulation.Game.KeyType;

/** 
 * That class represent the car in the real world. It gathers
 * data based on the info that is in the Map class.
 * 
 * The Map class represent the real world.
 * 
 * @author Marc-Andre
 *
 */
public class Car {
	
	/**
	 * A practical way to get access to everything that exist.
	 */
	private World world;
	
	/** The current position of the robot */
	private double posX;
	private double posY;
	
	/** The size of the robot */
	private double radius = 10;
	
	/** The direction the robot is facing (between 0 and 2*Math.PI) */
	private double direction = 0;
	
	/** 
	 * The current speed of the robot. The speed change when you press
	 * movement key.
	 */
	private double speed = 0;
	/** Indicate in which direction the robot is currently turning.
	 *  =0 = Don't turn
	 *  >0 = Turn left
	 *  <0 = Turn right
	 */
	private double turn = 0;
	
	/** The maximum speed at which we can move (in pixel). */
	private double maxSpeed = 0.1;
	/** The maximum speed at which we can turn (in radiant).  */
	private double maxTurn = 0.005;
	
	/** The starting position of the robot. */
	private double startX;
	private double startY;
	
	private boolean hasMoved = true;
	
	public Car(World world) {
		this.world = world;
		
		boolean placed = false;
		for(int i=6; i<16; i++){
			for(int j = 6; j<16; j++){
				TypeEnum type = world.map.get(i, j);
				if(type == TypeEnum.empty && !placed){
					posX = i*Map.size + Map.size/2;
					posY = j*Map.size + Map.size/2;
					startX = posX;
					startY = posY;
					placed = true;
				}
			}
		}
		
		
	}
	
	public double getPosX(){
		return posX;
	}
	
	public double getPosY(){
		return posY;
	}
	
	public double getAngle(){
		return angle;
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.blue);
		int x = (int) (posX-radius);
		int y= (int) (posY-radius);
		int width = (int) (radius*2);
		int height = (int) (radius*2);
		g.fillOval(x, y, width, height);
		
		g.setColor(Color.red);
		x = (int) posX;
		y = (int) posY;
		int dirX = (int) (radius*Math.cos(direction));
		int dirY = (int) (radius*Math.sin(direction));
		g.drawLine(x, y, x+dirX, y+dirY);
	}
	
	public void update(int deltaTime){
		
		//Update position
		if(turn != 0){
			hasMoved = true;
		}
		direction += turn*deltaTime;
		double dist = speed*deltaTime;
		double newPosX = posX + dist * Math.cos(direction);
		double newPosY = posY + dist * Math.sin(direction);
		
		//Keep inside
		boolean keepInside = false;
		if (newPosX - radius < 0 
				|| newPosX + radius > Map.size * Map.number
				|| newPosY - radius < 0
				|| newPosY + radius > Map.size * Map.number) {
			keepInside = true;
		}

		//Check for collision
		boolean noIntersect = true;
		if(!keepInside){
			double a = 0;
			Ellipse2D circle = new Ellipse2D.Double(newPosX-radius-a, newPosY-radius-a, (radius+a)*2, (radius+a)*2);

			for(Rectangle rect : world.map.rectList){
				if(circle.intersects(rect)){
					noIntersect = false;
					break;
				}
			}	
		}
		
		//Validate position
		if(noIntersect && !keepInside && speed != 0){
			posX = newPosX;
			posY = newPosY;
			hasMoved = true;
		}
		
		double angleImperfection = 0*(2*Math.PI)*(1.0/20);
		//Create point
		//Only create a point if the car has moved since we last recorded a point
		//We adjust every point so it is in the center for the car. When we create the map the car was not at position 0,0
		//but for our algorithm it's easier if we consider he is so we must  deduce the startX and startY for every point
		//It is also essential for the point to draw at the right place.
		if(hasMoved || true){
			Sonde front = new Sonde(posX, posY, direction);
			Point point = front.send(world.map.rectList, world.fogMap, visionCar);
			if(point != null){
				pointMap.addPoint(new Point(point.x-(int)startX, point.y-(int)startY, point.dist, point.angle+angleImperfection));
			}
			Sonde left = new Sonde(posX, posY, direction + Math.PI/2);
			point = left.send(map.rectList, pointMap.getFogMap(), visionCar);
			if(point != null){
				pointMap.addPoint(new Point(point.x-(int)startX, point.y-(int)startY, point.dist, point.angle+angleImperfection));
			}
			Sonde right = new Sonde(posX, posY, direction - Math.PI/2);
			point = right.send(map.rectList, pointMap.getFogMap(), visionCar);
			if(point != null){
				pointMap.addPoint(new Point(point.x-(int)startX, point.y-(int)startY, point.dist, point.angle+angleImperfection));
			}
			Sonde left2 = new Sonde(posX, posY, direction + Math.PI*(1.0/6));
			point = left2.send(map.rectList, pointMap.getFogMap(), visionCar);
			if(point != null){
				pointMap.addPoint(new Point(point.x-(int)startX, point.y-(int)startY, point.dist, point.angle+angleImperfection));
			}
			Sonde right2 = new Sonde(posX, posY, direction - Math.PI*(1.0/6));
			point = right2.send(map.rectList, pointMap.getFogMap(), visionCar);
			if(point != null){
				pointMap.addPoint(new Point(point.x-(int)startX, point.y-(int)startY, point.dist, point.angle+angleImperfection));
			}
			Sonde left3 = new Sonde(posX, posY, direction + Math.PI*(2.0/6));
			point = left3.send(map.rectList, pointMap.getFogMap(), visionCar);
			if(point != null){
				pointMap.addPoint(new Point(point.x-(int)startX, point.y-(int)startY, point.dist, point.angle+angleImperfection));
			}
			Sonde right3 = new Sonde(posX, posY, direction - Math.PI*(2.0/6));
			point = right3.send(map.rectList, pointMap.getFogMap(), visionCar);
			if(point != null){
				pointMap.addPoint(new Point(point.x-(int)startX, point.y-(int)startY, point.dist, point.angle+angleImperfection));
			}
			
		}
		
		//Create pos
		pointMap.addPos(new Point((int)(posX-startX), (int)(posY-startY), 0, 0));
		hasMoved = false;
	}
	
	public void keyEvent(KeyEvent e, KeyType type){
		if(type == KeyType.Pressed){
			switch(e.getKeyCode()){
			case(KeyEvent.VK_W):
				speed = maxSpeed;
				break;
			case(KeyEvent.VK_A):
				turn = -maxTurn;
				break;
			case(KeyEvent.VK_D):
				turn = maxTurn;
				break;
			}
		}else if(type == KeyType.Released){
			switch(e.getKeyCode()){
			case(KeyEvent.VK_W):
				speed = 0;
				break;
			case(KeyEvent.VK_A):
				turn = 0;
				break;
			case(KeyEvent.VK_D):
				turn = 0;
				break;
			}
		}
		
	}

}
