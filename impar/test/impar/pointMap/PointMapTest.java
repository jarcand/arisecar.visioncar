package impar.pointMap;

import static org.junit.Assert.*;
import impar.realWorld.Car;
import impar.realWorld.World;

import org.junit.Test;

public class PointMapTest {

	@Test
	public void testAddPoint() {
		
		PointMap pointMap = new PointMap(new Car(new World()));
		Point point = new Point(0,10,0,10);
		pointMap.addPoint(point);

	
		assertEquals(pointMap.pointData.getCellList().size()==1,true);
	}

	@Test
	public void testAddPos() {

		PointMap pointMap = new PointMap(new Car(new World()));
		Point point = new Point(1,0,0,2);
		pointMap.addPos(point);

		assertEquals(pointMap.posList.size()==1,true);
	}

	@Test
	public void testUpdate() {
		PointMap pointMap = new PointMap(new Car(new World()));
		Point point = new Point(1,0,0,2);
		pointMap.addPos(point);
		pointMap.update(0);
		
		assertEquals(pointMap.pointData.getCollisionList().size()==0,true);
	}

}
