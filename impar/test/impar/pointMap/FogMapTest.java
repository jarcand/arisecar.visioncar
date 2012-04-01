package impar.pointMap;

import static org.junit.Assert.*;
import impar.realWorld.World;
import impar.realWorld.Map.TypeEnum;

import org.junit.Test;

public class FogMapTest {

	
	
	//The four test cases below is for setKnow(int x, int y)
	@Test
	public void testMaxLeft() {
		World world = new World();
		FogMap fogmap = new FogMap(world);
		fogmap.setKnown(0, 0);
		
		assertEquals(fogmap.maxLeft==-2,true);
	}
	
	@Test
	public void testMaxTop() {
		World world = new World();
		FogMap fogmap = new FogMap(world);
		fogmap.setKnown(26, 0);
		
		assertEquals(fogmap.maxTop==-2,true);
	}

	@Test
	public void testMaxRight() {
		World world = new World();
		FogMap fogmap = new FogMap(world);
		fogmap.setKnown(20, 0);
		
		//System.out.println(fogmap.maxRight);
		assertEquals(fogmap.maxRight==25,true);
	}

	@Test
	public void testMaxBottom() {
		World world = new World();
		FogMap fogmap = new FogMap(world);
		fogmap.setKnown(40, 0);
		//System.out.println(fogmap.maxBottom);
		assertEquals(fogmap.maxBottom==25,true);
	}

	
}
