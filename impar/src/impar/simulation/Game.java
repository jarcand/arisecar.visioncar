package impar.simulation;

import impar.pointMap.Sonde;
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
	
	public synchronized void draw(Graphics2D g) {
		//The map is drawing the car too
		world.map.draw(g);
		for(Car car : world.carList){
			car.pointMap.draw(g);
		}
	}
	
	public synchronized void update(int deltaTime){
		for(Car car : world.carList){
			car.pointMap.update(deltaTime);
		}
		for(Car car : world.carList){
			car.update(deltaTime);
		}
	}
	
	public void setPrecision(int prc)
	{
		Sonde.setPrecision(prc);
	}
	
	public synchronized void keyEvent(KeyEvent e, KeyType type){
		if(e.getKeyCode() == KeyEvent.VK_ENTER && type == KeyType.Released){
			world.carList.add(new Car(world));
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE && type == KeyType.Released){
			for(Car car : world.carList){
				car.setSpeed(0);
				car.setTurn(0);
				car.setInTurn(false);
			}
			Car.setExplore(!Car.getExplore());
		}
		
		for(Car car : world.carList){
			if(type == KeyType.Pressed){
				switch(e.getKeyCode()){
				case(KeyEvent.VK_W):
					car.setSpeed(Car.maxSpeed);

				break;
				case(KeyEvent.VK_A):
					car.setTurn(-Car.maxTurn);
				break;
				case(KeyEvent.VK_D):
					car.setTurn(Car.maxTurn);
				break;
				
				
					
				}
			}else if(type == KeyType.Released){
				switch(e.getKeyCode()){
				case(KeyEvent.VK_W):
					car.setSpeed(0);
					car.setInTurn(false);
				break;
				case(KeyEvent.VK_A):
					car.setTurn(0);
					car.setInTurn(false);
				break;
				case(KeyEvent.VK_D):
					car.setTurn(0);
					car.setInTurn(false);
				break;
				
				
				}
			}
		}
	}

}
