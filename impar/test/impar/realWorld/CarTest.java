package impar.realWorld;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
		world.car.setSpeed(1);
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
	
	//The speed is negative
	@Test
	public void testNegativeSpeed(){
		World world = new World();
		world.car.hasMoved = false;
		world.car.turn = 0;
		world.car.setSpeed(-1);
		world.car.update(2);
		assertEquals(world.car.hasMoved, true);
	}
	
	
	//The speed is bigger than the MaxSpeed
		@Test
		public void testMaxSpeed(){
			World world = new World();
			world.car.hasMoved = false;
			world.car.turn = 0;
			world.car.setSpeed(Car.maxSpeed+2);
			world.car.update(2);
			assertEquals(world.car.speed<=Car.maxSpeed, true);
		}
	
	@Test
	public void testAngle(){
		World world = new World();
		world.car.hasMoved = false;
		world.car.setSpeed(0);
		world.car.setTurn(-1);
		world.car.update(2);
		assertEquals(world.car.hasMoved, true);
		
	
	}
	
	@Test
	public void testAngleTOTurn(){
		World world = new World();
		world.car.hasMoved = false;
		world.car.setSpeed(-1);
		world.car.setTurn(0);
		world.car.setAngleToTurn(0);
		world.car.update(2);
		assertEquals(world.car.hasMoved, true);
	}
	
	
	
	
	
	
	
    @BeforeClass
    public static void oneTimeSetUp() {
        // one-time initialization code   
    	System.out.println("@BeforeClass - oneTimeSetUp");
    }
    
    
    @AfterClass
    public static void oneTimeTearDown() {
        // one-time cleanup code
    	System.out.println("@AfterClass - oneTimeTearDown");
    }
 
    @Before
    public void setUp() {
        
        System.out.println("@Before - setUp");
    }
 
    @After
    public void tearDown() {
   
        System.out.println("@After - tearDown");
    }
 

	
}
