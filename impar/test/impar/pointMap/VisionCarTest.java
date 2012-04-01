package impar.pointMap;

import static org.junit.Assert.*;
import impar.realWorld.Car;
import impar.realWorld.World;

import org.junit.Test;

public class VisionCarTest {

	@Test
	public void testGetPosX() {
		
		World world = new World();
		Car car = new Car(world);
		VisionCar vCar = new VisionCar(car);
		
		assertEquals(vCar.getPosX()==0,true);
		
	}

	@Test
	public void testGetPosY() {
		
		World world = new World();
		Car car = new Car(world);
		VisionCar vCar = new VisionCar(car);
		
		assertEquals(vCar.getPosY()==0,true);
	}

	@Test
	public void testGetRadius() {
		
		World world = new World();
		Car car = new Car(world);
		VisionCar vCar = new VisionCar(car);
		assertEquals(vCar.getRadius()==10,true);
	}

}
