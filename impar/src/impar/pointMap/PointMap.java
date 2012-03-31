package impar.pointMap;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import impar.pointMap.PointMapData.Cell;
import impar.realWorld.Car;
import impar.realWorld.Map;
import impar.realWorld.World;

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
	 * The car to which this map is associated
	 */
	private Car car;
	
	private ArrayList<Point> posList = new ArrayList<Point>();
	
	private PointMapData pointData = new PointMapData();
	
	public PointMap(Car car){
		System.out.println("PointMap");
		this.car = car;
	}
	
	public void draw(Graphics2D g){
		
		//Move the point map to the side of the ''real'' map.
		//Since the car initially think he is at position 0, we want to begin the drawing in the middle.
		double scale = 0.8;
		double trans = Map.size*Map.number;
		g.scale(scale, scale);
		g.translate(trans*0.5, trans*0.5);
		
		//Draw fog of war
		//world.fogMap.draw(g);
		
		//Draw wall
		g.setStroke(new BasicStroke(3));
		for(Cell cell : pointData.getCellList()){
			for(Point point : cell.getList()){
				if(point.validity < PointMapData.MinimumValidity){
					g.setColor(Color.gray);
					g.drawLine(point.x(), point.y(), point.x()+1, point.y());
				}
			}
		}
		
		for(Cell cell : pointData.getCellList()){
			for(Point point : cell.getList()){
				if(point.validity >= PointMapData.MinimumValidity){
					g.setColor(Color.green);
					g.drawLine(point.x(), point.y(), point.x()+1, point.y());
				}
			}
		}
		
		for(Point point : pointData.getCollisionList()){
			if(point.validity < PointMapData.MinimumValidity && pointData.time-point.time < PointMapData.MinimumCollision){
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
		car.visionCar.draw(g);
		
		g.translate(-trans*0.5, -trans*0.5);
		g.scale(1/scale, 1/scale);
	}
	
	/**
	 * Add a point to this world. When you call this method it does not garantee
	 * that the point that you add will be add to the map. The PointMap decide
	 * whether or not it is keeping a point.
	 * 
	 * @param point
	 */
	public void addPoint(Point point){
		pointData.add(point);
	}
	
	/**
	 * Add a position. Currently all position are kept.
	 * 
	 * @param point
	 */
	public void addPos(Point point){
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
	public void update(int deltaTime){
		pointData.update(deltaTime);
	}

}
