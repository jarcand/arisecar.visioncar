package impar.pointMap;

import static org.junit.Assert.*;
import impar.pointMap.PointMapData.Cell;

import org.junit.Test;

public class PointMapDataTest {

	@Test
	public void testAdd() {
		
		PointMapData pmd = new PointMapData();
		pmd.add( new Point(0, 0, 0, 0));
		pmd.getCell(0, 0);
		
		assertEquals(pmd.getCellList().size()==1,true);
	}

	@Test
	public void testGetCollisionList() {

		PointMapData pmd = new PointMapData();
		pmd.add( new Point(0, 1, 0, 1));
		pmd.getCell(0, 1);
		
		assertEquals(pmd.getCollisionList().size()==1,true);
	}

	@Test
	public void testGetCellList() {

		PointMapData pmd = new PointMapData();
		pmd.add( new Point(0, 0, 0, 0));
		pmd.getCell(0, 0);
		assertEquals(pmd.getCellList().size()==1,true);
	}

	@Test
	public void testGetCell() {

		PointMapData pmd = new PointMapData();
		pmd.add( new Point(0, 0, 0, 0));
		pmd.getCell(1, 1);
		assertEquals(pmd.getCellList().size()==2,true);
	}

}
