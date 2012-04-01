package impar.realWorld;

import static org.junit.Assert.*;
import impar.pointMap.Point;

import org.junit.Test;

public class ControlTest {
	
	
	
	///When the piontFront <20 , we test the Class Control as follow:

	@Test
	public void testBlockSpeed() {
		
		World world = new World();
		Car car = new Car(world);
		Control control = new Control(car);		
		car.update(2);
		Point pointFront = new Point(0, 0, 19, 0);
		Point pointLeft = new Point(0, 0, 0, 0);
		Point pointRight = new Point(0, 0, 0, 0);
		Point pointRight2 = new Point(0, 0, 19, 0);
		Point pointLeft2 = new Point(0, 0, 19, 0);
		
		control.exploreMap2(pointFront, pointLeft, pointRight, pointRight2, pointLeft2);
		
		assertEquals(car.speed, 0, 0);
		
		
	}
	
	@Test
	public void testBlockTurn() {
		
		World world = new World();
		Car car = new Car(world);
		Control control = new Control(car);		
		car.update(2);
		Point pointFront = new Point(0, 0, 19, 0);
		Point pointLeft = new Point(0, 0, 0, 0);
		Point pointRight = new Point(0, 0, 0, 0);
		Point pointRight2 = new Point(0, 0,19, 0);
		Point pointLeft2 = new Point(0, 0, 19, 0);
		
		control.exploreMap2(pointFront, pointLeft, pointRight, pointRight2, pointLeft2);
		System.out.println(world.car.turn);
		assertEquals(car.turn, Car.maxTurn, 0.000001);
		
		
	}
	
	@Test
	public void testBlockInTurn() {
		
		World world = new World();
		Car car = new Car(world);
		Control control = new Control(car);		
		car.update(2);
		Point pointFront = new Point(0, 0, 19, 0);
		Point pointLeft = new Point(0, 0, 0, 0);
		Point pointRight = new Point(0, 0, 0, 0);
		Point pointRight2 = new Point(0, 0, 19, 0);
		Point pointLeft2 = new Point(0, 0, 19, 0);
		
		control.exploreMap2(pointFront, pointLeft, pointRight, pointRight2, pointLeft2);
		
		assertEquals(car.inTurn, true);
		
		
	}
	
	@Test
	public void testBlockAndgleToTurn() {
		
		World world = new World();
		Car car = new Car(world);
		Control control = new Control(car);		
		car.update(2);
		Point pointFront = new Point(0, 0, 19, 0);
		Point pointLeft = new Point(0, 0, 0, 0);
		Point pointRight = new Point(0, 0, 0, 0);
		Point pointRight2 = new Point(0, 0, 19, 0);
		Point pointLeft2 = new Point(0, 0, 19, 0);
		
		control.exploreMap2(pointFront, pointLeft, pointRight, pointRight2, pointLeft2);
		
		assertEquals(car.angleToTurn, Math.PI, 0.00001);
		
		
	}
	
	
	
	
	//When the pointFront >20

	@Test
	public void testMovingSpeed() {
		
		World world = new World();
		Car car = new Car(world);
		Control control = new Control(car);		
		car.update(2);
		Point pointFront = new Point(0, 0, 21, 0);
		Point pointLeft = new Point(0, 0, 31, 0);
		Point pointRight = new Point(0, 0, 31, 0);
		Point pointRight2 = new Point(0, 0, 21, 0);
		Point pointLeft2 = new Point(0, 0, 21, 0);
		
		control.exploreMap2(pointFront, pointLeft, pointRight, pointRight2, pointLeft2);
		
		assertEquals(car.speed==car.maxSpeed, true);
		
		
	}
	
	@Test
	public void testMovingTurn() {
		
		World world = new World();
		Car car = new Car(world);
		Control control = new Control(car);		
		car.update(2);
		Point pointFront = new Point(0, 0, 21, 0);
		Point pointLeft = new Point(0, 0, 31, 0);
		Point pointRight = new Point(0, 0, 31, 0);
		Point pointRight2 = new Point(0, 0, 21, 0);
		Point pointLeft2 = new Point(0, 0, 21, 0);
		
		control.exploreMap2(pointFront, pointLeft, pointRight, pointRight2, pointLeft2);
		
		assertEquals(car.turn==0, true);
		
		
	}
	
	@Test
	public void testMovingInTurn() {
		
		World world = new World();
		Car car = new Car(world);
		Control control = new Control(car);		
		car.update(2);
		Point pointFront = new Point(0, 0, 21, 0);
		Point pointLeft = new Point(0, 0, 31, 0);
		Point pointRight = new Point(0, 0, 31, 0);
		Point pointRight2 = new Point(0, 0, 21, 0);
		Point pointLeft2 = new Point(0, 0, 21, 0);
		
		control.exploreMap2(pointFront, pointLeft, pointRight, pointRight2, pointLeft2);
		
		assertEquals(car.inTurn, false);
		
		
	}
	
	@Test
	public void testMovingAndgleToTurn() {
		
		World world = new World();
		Car car = new Car(world);
		Control control = new Control(car);		
		car.update(2);
		Point pointFront = new Point(0, 0, 21, 0);
		Point pointLeft = new Point(0, 0, 15, 0);
		Point pointRight = new Point(0, 0,15, 0);
		Point pointRight2 = new Point(0, 0, 21, 0);
		Point pointLeft2 = new Point(0, 0, 21, 0);
		
		control.exploreMap2(pointFront, pointLeft, pointRight, pointRight2, pointLeft2);
		
		assertEquals(car.turn==car.maxTurn, true);
		
		
	}
	
	
	@Test
	public void testMovingSpeed2() {
		
		World world = new World();
		Car car = new Car(world);
		Control control = new Control(car);		
		car.update(2);
		Point pointFront = new Point(0, 0, 21, 0);
		Point pointLeft = new Point(0, 0, 19, 0);
		Point pointRight = new Point(0, 0, 19, 0);
		Point pointRight2 = new Point(0, 0, 21, 0);
		Point pointLeft2 = new Point(0, 0, 21, 0);
		
		control.exploreMap2(pointFront, pointLeft, pointRight, pointRight2, pointLeft2);
		
		assertEquals(car.speed==0, true);
		
		
	}
	
	@Test
	public void testMovingTurn2() {
		
		World world = new World();
		Car car = new Car(world);
		Control control = new Control(car);		
		car.update(2);
		Point pointFront = new Point(0, 0, 21, 0);
		Point pointLeft = new Point(0, 0, 19, 0);
		Point pointRight = new Point(0, 0, 19, 0);
		Point pointRight2 = new Point(0, 0, 21, 0);
		Point pointLeft2 = new Point(0, 0, 21, 0);
		
		control.exploreMap2(pointFront, pointLeft, pointRight, pointRight2, pointLeft2);
		
		assertEquals(car.turn==car.maxTurn, true);
		
		
	}
	
	@Test
	public void testMovingInTurn2() {
		
		World world = new World();
		Car car = new Car(world);
		Control control = new Control(car);		
		car.update(2);
		Point pointFront = new Point(0, 0, 21, 0);
		Point pointLeft = new Point(0, 0, 19, 0);
		Point pointRight = new Point(0, 0, 19, 0);
		Point pointRight2 = new Point(0, 0, 21, 0);
		Point pointLeft2 = new Point(0, 0, 21, 0);
		
		control.exploreMap2(pointFront, pointLeft, pointRight, pointRight2, pointLeft2);
		
		assertEquals(car.inTurn, false);
		
		
	}
	
	@Test
	public void testMovingAndgleToTurn2() {
		
		World world = new World();
		Car car = new Car(world);
		Control control = new Control(car);		
		car.update(2);
		Point pointFront = new Point(0, 0, 21, 0);
		Point pointLeft = new Point(0, 0, 15, 0);
		Point pointRight = new Point(0, 0,15, 0);
		Point pointRight2 = new Point(0, 0, 21, 0);
		Point pointLeft2 = new Point(0, 0, 21, 0);
		
		control.exploreMap2(pointFront, pointLeft, pointRight, pointRight2, pointLeft2);
		
		assertEquals(car.turn==car.maxTurn, true);
		
		
	}
	
	

}
