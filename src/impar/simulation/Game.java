package impar.simulation;

import impar.realWorld.Car;
import impar.realWorld.World;

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
		world.map.draw(g);
		world.pointMap.draw(g);
	}
	
	public void update(int deltaTime){
		world.pointMap.update(deltaTime);
		world.car.update(deltaTime);
	}
	
	public void keyEvent(KeyEvent e, KeyType type){
		if(type == KeyType.Pressed){
			switch(e.getKeyCode()){
			case(KeyEvent.VK_W):
				world.car.setSpeed(Car.maxSpeed);
				
			break;
			case(KeyEvent.VK_A):
				world.car.setTurn(-Car.maxTurn);
			break;
			case(KeyEvent.VK_D):
				world.car.setTurn(Car.maxTurn);
			break;
			case (KeyEvent.VK_SPACE):
			{
				world.car.setSpeed(0);
				world.car.setTurn(0);
				world.car.setExplore(!world.car.getExplore());
			}
			}
		}else if(type == KeyType.Released){
			switch(e.getKeyCode()){
			case(KeyEvent.VK_W):
				world.car.setSpeed(0);
				world.car.setInTurn(false);
			break;
			case(KeyEvent.VK_A):
				world.car.setTurn(0);
				world.car.setInTurn(false);
			break;
			case(KeyEvent.VK_D):
				world.car.setTurn(0);
				world.car.setInTurn(false);
			break;
			}
		}
	}

}
