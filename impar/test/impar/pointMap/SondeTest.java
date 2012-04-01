package impar.pointMap;

import static org.junit.Assert.*;
import impar.pointMap.Sonde.SondePos;
import impar.realWorld.Car;
import impar.realWorld.World;

import org.junit.Test;

public class SondeTest {

	@Test
	public void testGetAngle() {
		
		World world = new World();
		Car car = new Car(world);
		SondePos pos = SondePos.Front;
		Sonde sonde = new Sonde(world, car, 10,pos );
		
		assertEquals(sonde.getAngle()==10,true);
		
		
	}

	@Test
	public void testGetPos() {
		
		World world = new World();
		Car car = new Car(world);
		SondePos pos = SondePos.Front;
		Sonde sonde = new Sonde(world, car, 10,pos );
		
		assertEquals(sonde.getPos()==pos.Front,true);
		
	}

	@Test
	public void testSend() {
		
		World world = new World();
		Car car = new Car(world);
		SondePos pos = SondePos.Front;
		Sonde sonde = new Sonde(world, car, 10,pos );
		sonde.send();
		assertEquals(sonde.getPos()==pos.Front,true);
	}

	@Test
	public void testSetPrecision() {
		
		World world = new World();
		Car car = new Car(world);
		SondePos pos = SondePos.Front;
		Sonde sonde = new Sonde(world, car, 10,pos );
		sonde.setPrecision(200);
		assertEquals(sonde.imperfection==2,true);
		
	}

}
