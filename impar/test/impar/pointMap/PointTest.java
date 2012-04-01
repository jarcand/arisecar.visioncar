package impar.pointMap;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

	@Test
	public void testX() {
		
		Point point = new Point(1,0,10,0);
		assertEquals(point.x()==11,true);
	}

	@Test
	public void testY() {
		
		Point point = new Point(1,10,10,Math.PI/2);
		//System.out.println(point.y());
		assertEquals(point.y()==20,true);
	}

}
