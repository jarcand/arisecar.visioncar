package impar.realWorld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import impar.pointMap.Point;
import impar.pointMap.PointMap;
import impar.pointMap.Sonde;
import impar.pointMap.VisionCar;
import impar.pointMap.Sonde.SondePos;
import impar.realWorld.Map.TypeEnum;

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
	
	/** The maximum speed at which we can move (in pixel). */
	public static final double maxSpeed = 0.1;
	/** The maximum speed at which we can turn (in radiant).  */
	public static final double maxTurn = 0.005;

	/**
	 * A practical way to get access to everything that exist.
	 */
	private World world;
	
	/**
	 * The class that make the robot move on its own.
	 */
	private Control control;
	
	/**
	 * The vision the car has of itself
	 */
	public final VisionCar visionCar;
	
	/**
	 * The information the car has about it's environnement
	 */
	public final PointMap pointMap;
	
	/**
	 * The sonde gathering the information about the environment for the
	 * robot.
	 */
	private ArrayList<Sonde> sondeList = new ArrayList<Sonde>();
	

	/** The current position of the robot */
	double posX;
	double posY;

	/** The size of the robot */
	double radius = 10;

	/** The direction the robot is facing (between 0 and 2*Math.PI) */
	double direction = 0;

	/** 
	 * The current speed of the robot. The speed change when you press
	 * movement key.
	 */
	double speed = 0;
	/** Indicate in which direction the robot is currently turning.
	 *  =0 = Don't turn
	 *  >0 = Turn left
	 *  <0 = Turn right
	 */
	double turn = 0;

	

	/** The starting position of the robot. */
	double startX;
	double startY;

	boolean hasMoved = true;

	boolean explore = false;

	boolean inTurn = false;
	double angleToTurn = 0;

	public Car(World world) {
		System.out.println("New car");
		this.world = world;
		
		this.visionCar = new VisionCar(this);
		this.pointMap = new PointMap(this);
		
		this.control = new Control(this);

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

		Sonde sonde = new Sonde(world, this, 0, SondePos.Front);
		sondeList.add(sonde);
		sonde = new Sonde(world, this, Math.PI/2, SondePos.Left2);
		sondeList.add(sonde);
		sonde = new Sonde(world, this, -Math.PI/2, SondePos.Right2);
		sondeList.add(sonde);
		sonde = new Sonde(world, this, Math.PI/6, SondePos.Left);
		sondeList.add(sonde);
		sonde = new Sonde(world, this, -Math.PI/6, SondePos.Right);
		sondeList.add(sonde);
		sonde = new Sonde(world, this, Math.PI/3, SondePos.None);
		sondeList.add(sonde);
		sonde = new Sonde(world, this, -Math.PI/3, SondePos.None);
		sondeList.add(sonde);


	}

	public double getPosX(){
		return posX;
	}

	public double getPosY(){
		return posY;
	}

	public double getAngle(){
		return direction;
	}

	public double getStartX(){
		return startX;
	}

	public double getStartY(){
		return startY;
	}

	public double getRadius(){
		return radius;
	}
	
	public void setSpeed(double speed){
		this.speed = speed;
	}
	
	public void setTurn(double turn){
		this.turn = turn;
	}
	
	public boolean getExplore(){return explore;}
	
	public void setExplore(boolean explore){this.explore = explore;}
	public void setInTurn(boolean inTurn){this.inTurn = inTurn;}
	public void setAngleToTurn(double angleToTurn){this.angleToTurn = angleToTurn;}

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
		hasMoved = false;

		if(inTurn){
			angleToTurn -= Math.abs(turn)*deltaTime;
			if(angleToTurn < 0){
				inTurn = false;
			}
		}

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
			control.contact();
		}

		//Check for collision
		boolean noIntersect = true;
		if(!keepInside){
			double a = 0;
			Ellipse2D circle = new Ellipse2D.Double(newPosX-radius-a, newPosY-radius-a, (radius+a)*2, (radius+a)*2);

			for(Rectangle rect : world.map.getRectList()){
				if(circle.intersects(rect)){
					noIntersect = false;
					control.contact();
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

		Point pointFront = null;
		Point pointLeft = null;
		Point pointRight = null;
		Point pointLeft2 = null;
		Point pointRight2 = null;

		double angleImperfection = 0*(2*Math.PI)*(1.0/20);
		//Create point
		//Only create a point if the car has moved since we last recorded a point
		//We adjust every point so it is in the center for the car. When we create the map the car was not at position 0,0
		//but for our algorithm it's easier if we consider he is so we must  deduce the startX and startY for every point
		//It is also essential for the point to draw at the right place.
		if(hasMoved || true){
			for(int i=0; i<3; i++){
				for(Sonde sonde : sondeList){
					Point point = sonde.send();
					if(sonde.getPos() == SondePos.Right){
						pointRight = point;
						/*					if (pointRight != null)
					System.out.println("right point " + pointRight.x + " " + pointRight.y);*/
					}if (sonde.getPos() == SondePos.Right2){
						pointRight2 = point;				
					}if(sonde.getPos() == SondePos.Front){
						pointFront = point;
						/*					if (pointFront != null)
					System.out.println("front point " + pointFront.x + " " + pointFront.y);*/
					}if(sonde.getPos() == SondePos.Left){
						pointLeft = point;
						/*if (pointLeft != null)
					System.out.println("left point " + pointLeft.x + " " + pointLeft.y);*/
					}if (sonde.getPos() == SondePos.Left2){
						pointLeft2 = point;
					}

					if(point != null){
						pointMap.addPoint(new Point(point.x-(int)startX, point.y-(int)startY, point.dist, point.angle+angleImperfection));
					}
				}
			}
		}

		if (explore && !inTurn){
			control.exploreMap2(pointFront, pointLeft, pointRight, pointRight2, pointLeft2);
		}


		//Create pos
		pointMap.addPos(new Point((int)(posX-startX), (int)(posY-startY), 0, 0));
		
	}

	public void setPrecision(int prc) {
		Sonde.setPrecision(prc);
		
	}
	

}



