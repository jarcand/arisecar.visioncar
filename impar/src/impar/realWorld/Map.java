package impar.realWorld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * 
 * This class represent the real world. It is currently
 * a grid map with empty square and full square. The robot (alias)
 * the car is able to move into empty square and is block by full
 * square. 
 * 
 * The robot gather data it receive from its sensors when one sensor
 * detect a new obstacle.
 * 
 * The function of this class is to generate the world and hold
 * the data for it. It also draws the map and the car.
 * 
 * @author Marc-Andre
 *
 */
public class Map {

	
	/**
	 * A practical way to get access to everything that exist.
	 */
	private World world;
	
	/** 
	 * Represent the different value a square can take
	 */
	public enum TypeEnum{
		wall, empty;
	}
	
	/**
	 * The size of 1 square.
	 */
	public static final int size = 40;
	/** 
	 * The number of square 
	 */
	public static final int number = 16;
	
	/**
	 * That's the map of all our square
	 */
	private TypeEnum [][] map = new TypeEnum[number][number];
	/**
	 * This list is used to do detection. It is built into the
	 * constructor.
	 */
	private ArrayList<Rectangle> rectList = new ArrayList<Rectangle>();
	/**
	 * It construct the maps
	 * 
	 * @param world
	 */
	
	/**
	 * directory and file name to access static maps
	 */
	private String directory = "\\impar\\MapFile\\";
	private String filename = "mapfile.txt";
	File file = new File ("mapfile.txt");
	
	public Map(World world) {
		directory = file.getParent() + directory;
		this.world = world;
		try{
/*			 FileOutputStream fos = new FileOutputStream("mapfile.txt");
			 OutputStreamWriter out = new OutputStreamWriter(fos, "UTF-8");
*/			 
			FileInputStream fstream = new FileInputStream(directory + filename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader (new InputStreamReader(in));
			//BufferedReader br = new BufferedReader(new FileReader("mapfile.txt"));
			String strLine;
			
			//int row=0;
			for (int row = 0; row<number; row++) {
				strLine = br.readLine();
				char []mapLine = strLine.toCharArray();
				for (int col=0; col< number; col++)
				{
					
					if (mapLine[col]=='#'){
//						System.out.println(row + " " + col);
						map[col][row] = TypeEnum.wall;
						Rectangle rect = new Rectangle(col*size, row*size, size, size);
						rectList.add(rect);
					}
					else if (mapLine[col] == '.'){
//						System.out.println(row + " " + col);
						map[col][row] = TypeEnum.empty;
					}
					else{
						map [col][row]=TypeEnum.empty;
					}
				
				}
			}
			fstream.close();
			br.close();
			//Close the input stream
			in.close();

		}catch (Exception e){//Catch exception if any
			System.err.println("No preloaded map " + e.getMessage());
			e.printStackTrace();
			for(int i=0; i<number; i++){
				for(int j=0; j<number; j++){
					if(Math.random() < 0.30){
						map[i][j] = TypeEnum.wall;
						Rectangle rect = new Rectangle(i*size, j*size, size, size);
						rectList.add(rect);
					}else{
						map[i][j] = TypeEnum.empty;
					}
				}
			}
			
			try {
				FileOutputStream fos = new FileOutputStream("mapfile.txt");
				OutputStreamWriter outStream = new OutputStreamWriter(fos, "UTF-8");
				BufferedWriter out = new BufferedWriter(outStream);
				for (int i = 0; i < number; i++)
				{
					for (int j = 0; j< number; j++)
					{
						if (map[i][j] == TypeEnum.wall){
							out.append('#');
						}
						else
						{
							out.append('.');
						}
						
					}
					out.newLine();
				}
				out.close(); fos.close(); outStream.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}

	}
	/**
	 * Return the value at that position in the map.
	 * This is used by the car to determine where to be placed
	 * at the begin of the simulation.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public TypeEnum get(int x, int y){
		return map[x][y];
	}
	
	/**
	 * Return the list of all the rectangle representing walls.
	 * This is used by the Sondes to determine where there is a wall.
	 * 
	 * @return
	 */
	public ArrayList<Rectangle> getRectList(){
		return rectList;
	}
	
	/**
	 * It draws the map and it draws the car after it draw
	 * the map.
	 * 
	 * @param g
	 */
	public void draw(Graphics2D g){
		double scale = 0.3;
		double trans = 600;
		g.scale(scale, scale);
		g.translate(trans/scale, 10);
		g.setColor(Color.gray);
		//Draw the edge of the map
		g.fillRect(-10, -10, number*size+20, number*size+20);
		
		//Draw all the square
		for(int i=0; i<number; i++){
			for(int j=0; j<number; j++){
				if(map[i][j] == TypeEnum.wall){
					g.setColor(Color.black);
				}else{
					g.setColor(Color.white);
				}
				g.fillRect(i*size, j*size, size, size);
			}
		}
		
		for(Car car : world.carList){
			car.draw(g);
		}
		g.translate(-trans/scale, -10);
		g.scale(1/scale, 1/scale);
		
	}

}
