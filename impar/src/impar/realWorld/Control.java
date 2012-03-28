package impar.realWorld;

import impar.pointMap.Point;

public class Control {
	
	/**
	 * The car to which this control module is attach.
	 */
	private Car car;
	
	public Control(Car car){
		this.car = car;
	}
	
	private boolean testPoint (Point point, double dist){
		return (point!= null && point.dist < dist);
	}
	
	public void contact(){
			car.setTurn(Car.maxTurn);
			car.setInTurn(true);
			car.setAngleToTurn(Math.PI/3);	
	}
		
	
	
	public String exploreMap2(Point pointFront, Point pointLeft, Point pointRight, Point pointRight2, Point pointLeft2){
		if (testPoint (pointFront,20))
		{
			if (testPoint (pointRight2, 20) && testPoint(pointLeft2, 20))
			{	
				car.setSpeed(0);
				car.setTurn(Car.maxTurn);
				car.setInTurn(true);
				car.setAngleToTurn(Math.PI);
			}
			else if (!testPoint (pointRight2, 20) && testPoint(pointLeft2, 20))
			{
				car.setSpeed(0);
				car.setTurn(-Car.maxTurn);
				car.setInTurn(true);
				car.setAngleToTurn(Math.PI/2);
			}
			else if (!testPoint (pointLeft2, 20) && testPoint(pointRight2, 20))
			{
				car.setSpeed(0);
				car.setTurn(Car.maxTurn);
				car.setInTurn(true);
				car.setAngleToTurn(Math.PI/2);
			}
			else if (testPoint (pointLeft2, 20) && testPoint(pointRight2, 100))
			{
				car.setSpeed(0);
				car.setTurn(-Car.maxTurn);
				car.setInTurn(true);
				car.setAngleToTurn(Math.PI/2);
			}
			else if (testPoint (pointRight2, 20) && testPoint(pointLeft2, 100))
			{
				car.setSpeed(0);
				car.setTurn(Car.maxTurn);
				car.setInTurn(true);
				car.setAngleToTurn(Math.PI/2);
			}

			else
			{

				System.out.println (pointFront.dist);
				if (pointLeft2 != null){
					car.setSpeed(0);
					car.setTurn(-Car.maxTurn);
					car.setInTurn(true);
					car.setAngleToTurn(Math.PI/2);
					System.out.print (" " + pointLeft2.dist);}

				if (pointRight2 != null){
					car.setSpeed(0);
					car.setTurn(Car.maxTurn);
					car.setInTurn(true);
					car.setAngleToTurn(Math.PI/2);
					System.out.print(" " +pointRight2.dist);}
				else if (pointRight2 == null && pointLeft2 == null)
				{
					car.setSpeed(0);
					car.setTurn(-Car.maxTurn);
					car.setInTurn(true);
					car.setAngleToTurn(Math.PI/2);
				}
			}

		}
	
	
		else if (!(testPoint(pointFront, 20)))
		{
			if (testPoint (pointRight2, 12))
			{
				car.setTurn(Car.maxTurn);
			}
			else if (testPoint(pointLeft2 , 12))
			{
				car.setTurn(-Car.maxTurn);
			}
			else if (testPoint (pointRight, 30))
			{
				car.setTurn(Car.maxTurn);
			}
			else if (testPoint (pointLeft, 30))
			{
				car.setTurn(-Car.maxTurn);
			}
			else{
				car.setTurn(0);
				car.setSpeed(Car.maxSpeed);
				if (pointFront != null)
					System.out.println("clear " + pointFront.dist);
			}
		}
		return "";
	}

	/* private String exploreMap(Point pointFront, Point pointLeft, Point pointRight, Point pointRight2, Point pointLeft2){	
		if (!testPoint (pointFront, 30) && testPoint (pointLeft2, 30) && testPoint(pointRight2, 99)){
			speed=maxSpeed;
			return ("left, right?");
		}
		
		else if ( !testPoint (pointFront,  30) && testPoint (pointRight2,  30) && testPoint(pointLeft2, 99)){
			speed=maxSpeed;
			return ("right, left?");
		}
		
		else if(!testPoint(pointFront, 30) && !testPoint(pointRight2, 30) && !testPoint(pointLeft2, 30) ){
			if(pointFront != null){
				System.out.print(pointFront.dist);
			}
			System.out.println("clear " );
			turn = 0;
			speed=maxSpeed;
			
			/*if (testPoint (pointLeft2, 30) || testPoint (pointLeft, 40))
			{
				turn = maxTurn;
			}
			else if (testPoint (pointRight2, 30) || testPoint (pointLeft,40))
			{
				turn = -maxTurn;
			}* /
			return ("clear");
		}
		
		else if (testPoint(pointFront,  30) && testPoint(pointRight2,  30) &&  !testPoint (pointLeft2, 30)){	
			System.out.println("right,front");
			speed = 0;
			turn = -maxTurn;
			inTurn = true;
			angleToTurn = Math.PI/2;
			return("right,front");
		}

		else if (testPoint (pointFront,  30)&& testPoint (pointLeft2,  30) && !testPoint (pointRight2, 30)){
			System.out.println("left,front" );
			speed = 0;
			turn = maxTurn;
			inTurn = true;
			angleToTurn = Math.PI/2;
			return("left,front" );
		}
		
/*		else if ( testPoint (pointFront,  30) && testPoint (pointLeft2,  30) && testPoint(pointRight2, 99)){
			System.out.println ("left,front, right?");
			speed = 0;
			turn = maxTurn;
			inTurn = true;
			angleToTurn = Math.PI/2;
			return ("left,front, right?");
		}*/

/*		else if (testPoint(pointFront, 30) && testPoint(pointRight2,  30) && testPoint (pointLeft2, 99)){	
			System.out.println("right,front, left?" );
			speed = 0;
			turn = -maxTurn;
			inTurn = true;
			angleToTurn = Math.PI/2;
			return("right,front, left?" );
		}
		* /
		else if(!testPoint(pointFront,  30) && testPoint(pointRight2,  30) && testPoint(pointLeft2,  30)){
			System.out.println("corridor");
			turn = 0;
			speed=maxSpeed;
	/*		if (testPoint (pointLeft2, 30) || testPoint (pointLeft, 40))
			{
				turn = maxTurn;
			}
			else if (testPoint (pointRight2, 30) || testPoint (pointLeft,40))
			{
				turn = -maxTurn;
			}* /
			return("corridor");
		}

		else if ( testPoint (pointFront ,  30) && testPoint (pointLeft2,  30) && testPoint (pointRight2,  30))
		{
			System.out.println("dead end");
			speed = 0;
			turn = -maxTurn;
			inTurn = true;
			angleToTurn = Math.PI;
			return("dead end");
		}

		else if (testPoint (pointFront,  30))
		{ 	System.out.println("front");
			speed= 0;
			turn = -maxTurn;
			inTurn = true;
			angleToTurn = Math.PI/2;
			return("front");
		}

		else
		{
			System.out.println("unknown");
			System.out.println(testPoint(pointLeft2, 30) + " " + testPoint(pointFront, 30) + " " + testPoint(pointRight, 30));
			turn = 0;
			speed= maxSpeed;
			return ("unknown");
		}
	}*/
	
	

}
