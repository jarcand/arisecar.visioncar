package impar.realWorld;

import static org.junit.Assert.*;
import impar.realWorld.Map.TypeEnum;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MapTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getTest() {
		World world = new World();
		Map map = new Map(world);

		System.out.println(map.get(0, 0));
		assertEquals(map.get(0, 0) == TypeEnum.wall, true);

	}

}
