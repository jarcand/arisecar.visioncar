package real.simulation;

import real.realWorld.World;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Game {	
	
	public enum KeyType{
		Pressed, Released;
	}
	
	private World world;
	
	public Game(){
		world = new World();
	}
	
	public void draw(Graphics2D g) {
		//The map is drawing the car too
		world.pointMap.draw(g);
	}
	
	public void update(int deltaTime){
		world.pointMap.update(deltaTime);
	}
	
	public void keyEvent(KeyEvent e, KeyType type){
		//Nothing here yet.
	}

}
