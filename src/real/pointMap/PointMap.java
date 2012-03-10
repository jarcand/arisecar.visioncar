package real.pointMap;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import real.pointMap.PointMapData.Cell;
import real.realWorld.World;

/**
 * 
 * This class hold all the data the robot know about it's surrounding world. It
 * receives all the data from the sensor, analyze them and construct a point map
 * where every point indicate an obstacle. When many point are close together the
 * robot know that there is a wall there.
 * 
 * I decided to move much of the logic about whether to keep a point or not into
 * the helper class PointMapData
 * 
 * @author Gudradain
 *
 */
public class PointMap {
	
	/**
	 * A practical way to get access to everything that exist.
	 */
	private World world;
	
	private ArrayList<Point> posList = new ArrayList<Point>();
	
	private ArrayList<Point> tempPointList = new ArrayList<Point>();
	
	private PointMapData pointData = new PointMapData();
	
	public PointMap(World world){
		this.world = world;
	}
	
	public void draw(Graphics2D g){
		
		//Move the point map to the side of the ''real'' map.
		//Since the car initially think he is at position 0, we want to begin the drawing in the middle.
		g.translate(660*0.5, 660*0.5);
		
		//Draw fog of war
		world.fogMap.draw(g);
		
		//Draw wall
		g.setStroke(new BasicStroke(3));
		for(Cell cell : pointData.getCellList()){
			for(Point point : cell.getList()){
				if(point.validity < 10){
					g.setColor(Color.gray);
					g.drawLine(point.x(), point.y(), point.x()+1, point.y());
				}
			}
		}
		
		for(Cell cell : pointData.getCellList()){
			for(Point point : cell.getList()){
				if(point.validity >= 10){
					g.setColor(Color.green);
					g.drawLine(point.x(), point.y(), point.x()+1, point.y());
				}
			}
		}
		
		for(Point point : pointData.getCollisionList()){
			if(point.validity < 10 && pointData.time-point.time < PointMapData.MinimumCollision){
				g.setColor(Color.red);
				g.drawLine(point.x(), point.y(), point.x()+1, point.y());
			}
		}
		
		//Draw path
		g.setColor(Color.orange);
		for(Point point : posList){
			//g.drawLine(point.x, point.y, point.x+1, point.y);
		}
		
		//Draw car
		world.visionCar.draw(g);
	}
	
	/**
	 * Add a point to this world. When you call this method it does not garantee
	 * that the point that you add will be add to the map. The PointMap decide
	 * whether or not it is keeping a point.
	 * 
	 * @param point
	 */
	public void addPoint(Point point){
		tempPointList.add(point);
	}
	
	/**
	 * Add a position. Currently all position are kept.
	 * 
	 * @param point
	 */
	public synchronized void addPos(Point point){
		posList.add(point);
	}
	
	/**
	 * Update the point map.
	 * 
	 * Certain point are only kept a certain time before disappearing. Also, the
	 * validity of a point decrease over time. If a point does not reach a good
	 * enough validity in the certain time it will be remove because we can't 
	 * construct a map with innacurate point.
	 * 
	 * @param deltaTime
	 */
	public synchronized void update(int deltaTime){
		while(tempPointList.size() > 0){
			Point point = tempPointList.remove(0);
			pointData.add(point);
		}
		pointData.update(deltaTime);
	}

}
