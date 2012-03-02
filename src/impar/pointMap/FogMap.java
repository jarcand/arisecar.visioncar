package impar.pointMap;

import impar.realWorld.World;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * The fog map represent all the area that where not explored by the robot. The
 * way it work is that it creates a map of small rectangle that are all grey.
 * When the sensor pass over one of those rectangle, this rectangle become white 
 * so we can see the difference.
 * 
 * Currently the fog map might not be of much help. It's using the perfect sonde
 * to create it so everything look very nice but...
 * 
 * One of our member seems to want the fog map to indicate that an area is within 
 * walls so we can't access it and that would tell the robot to not try to explore
 * that zone.
 * 
 * Unfortunately, that won't work. Since the value from the Sondes (aka the
 * finder) are inaccurate, you will discover the area within the wall anyway.
 * 
 * I got 2 alternative for the FogMap in mind. 
 * 
 * The first one is to reveal as much
 * of the fog map as our range finder can go all around the car. It would create
 * some sort of circle all around the car and we would be sure that any place not
 * revealed by the robot is an unknown area.
 * 
 * The second one is to only reveal the place where the robot walk over. That way,
 * it could move to every unknown cell. The problem with that is to tell the robot 
 * what is behind a wall and what is not...
 * 
 * Another problem of the FogMap is that it assumes perfect location; meaning that
 * the robot know exactly where he is. That is indeed a problem because it's not 
 * case.
 * 
 * All in all, I think that I will put the FogMap on hold for now until we can see
 * what precision we can get with the PointMap.
 * 
 * @author Gudradain
 *
 */
public class FogMap {
	
	/**
	 * A practical way to get access to everything that exist.
	 */
	private World world;
	
	public enum FogEnum{
		known, unknown;
	}
	
	ArrayList<ArrayList<FogEnum>> fogMap = new ArrayList<ArrayList<FogEnum>>();
	
	/** Indicate the size of very rectangle */
	int size = 20;
	/** 
	 * Indicate the number of rectangle we have.
	 * In fact we have number*number rectangle.
	 */
	int number = 60;
	/**
	 * A bunch of stupid variable that I used to create a fog map only the size 
	 * of the point map that is know by the robot. Not sure how much that help.
	 * I should probably remove all those thing.
	 * 
	 * Those variable affect the drawing. For example, when you start to draw you
	 * will begin at the maxLeft rectangle in the list and you will stop at 
	 * number-maxRight rectangle in the list.
	 * 
	 * When you discover new point, the size of those value decrease so we see a 
	 * bigger forg map.
	 */
	int maxTop = 25;
	int maxBottom = 25;
	int maxLeft = 25;
	int maxRight = 25;
	
	public FogMap(World world){
		this.world = world;
		for(int i=0; i<number; i++){
			fogMap.add(new ArrayList<FogEnum>());
			for(int j=0; j<number; j++){
				fogMap.get(i).add(FogEnum.unknown);
			}
		}
	}
	
	public void draw(Graphics2D g){
		for(int i=maxLeft; i<fogMap.size()-maxRight; i++){
			for(int j=maxTop; j<fogMap.get(i).size()-maxBottom; j++){
				if(fogMap.get(i).get(j) == FogEnum.unknown){
					g.setColor(Color.gray);
					//g.fillRect(i*size-number/2*size, j*size-number/2*size, size, size);
				}
			}
		}
	}
	
	/**
	 * Indicate that we explore this square in the fog map.
	 * 
	 * If the square is too close of the border of the fog map it will expand the 
	 * fog map.
	 * 
	 * @param x
	 * @param y
	 */
	public void setKnown(int x, int y){
		fogMap.get(x).set(y, FogEnum.known);
		if(x < maxLeft){
			maxLeft = x-2;
		}
		if(y < maxTop){
			maxTop = y-2;
		}
		while(x > fogMap.size()-maxRight){
			maxRight -= 4;
		}
		while(y >= fogMap.size()-maxBottom){
			maxBottom -= 3;
		}
	}

}
