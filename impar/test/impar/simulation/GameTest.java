package impar.simulation;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import impar.pointMap.Sonde;
import impar.pointMap.Sonde.SondePos;
import impar.realWorld.Car;
import impar.realWorld.World;
import impar.simulation.Game.KeyType;

import org.junit.Test;

public class GameTest {



	@Test
	public void testSetPrecision() {
		Game game = new Game();

		World world = new World();
		Car car = new Car(world);
		SondePos pos = SondePos.Front;
		Sonde sonde = new Sonde(world, car, 10, pos);
		game.setPrecision(100);
		assertEquals(sonde.imperfection == 1, true);
	}

}
