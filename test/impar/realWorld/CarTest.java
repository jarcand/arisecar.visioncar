package impar.realWorld;

import static org.junit.Assert.*;
import org.junit.Test;

public class CarTest {

	@Test
	public void testHasMovedWhenTurn() {
		World world = new World();
		world.car.hasMoved = false;
		world.car.turn = 1;
		world.car.speed = 0;
		world.car.update(2);
		assertEquals(world.car.hasMoved, true);
	}
	
	@Test
	public void testhasMovedWhenSpeed() {
		World world = new World();
		world.car.hasMoved = false;
		world.car.turn = 0;
		world.car.speed = 1;
		world.car.update(2);
		assertEquals(world.car.hasMoved, true);
	}
	
	@Test
	public void testDirectionWhenNoTurn(){
		World world = new World();
		world.car.direction = 0;
		world.car.turn = 0;
		world.car.update(2);
		assertEquals(0, world.car.direction, 0);
	}
	
	@Test
	public void testDirectionWhenTurn(){
		World world = new World();
		world.car.direction = 0;
		world.car.turn = 1;
		world.car.update(2);
		double expDirection = world.car.turn*2;
		assertEquals(expDirection, world.car.direction, 0);
	}

	
}
