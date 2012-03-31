package impar.simulation;

import java.applet.Applet;
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import impar.simulation.Game.KeyType;


public class BasicApplet extends Applet implements Runnable, ChangeListener{
	
	private static final long serialVersionUID = 5735138575810621548L;
	
	//Set the size in the parameter when launching the applet
	private final int width = 480*2+330;
	private final int height = 330+330;

	Canvas canvas;
	BufferStrategy bufferStrategy;
	Thread gameloopThread;
	JSlider precision = new JSlider(JSlider.VERTICAL);
	
	
	Game game;
	
	@Override
	public void init(){
		game = new Game();
		
		canvas = new Canvas();
		canvas.setBounds(0, 0, width, height);
		add(canvas);
		canvas.setIgnoreRepaint(true);

		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();

		canvas.addKeyListener(new KeyControl());
		canvas.addMouseListener(new MouseControl());
		canvas.addMouseMotionListener(new MouseControl());
		canvas.requestFocus();
		
		precision.addChangeListener(this);
		add(precision);
		
		
	}
	
	@Override
	public void start(){
		gameloopThread = new Thread(this);
		gameloopThread.start();
	}
	
	@Override
	public void stop(){
		setRunning(false);
		try {
			gameloopThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private class KeyControl extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e){
			game.keyEvent(e, KeyType.Pressed);
		}
		
		@Override
		public void keyReleased(KeyEvent e){
			game.keyEvent(e, KeyType.Released);
		}
		
	}
	
	private class MouseControl extends MouseAdapter{
		
	}
	
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!source.getValueIsAdjusting()) {
			int precision = (int)source.getValue();
			game.setPrecision(precision);
		}
	}
	
	private long desiredFPS = 60;
    private long desiredDeltaLoop = (1000*1000*1000)/desiredFPS;
    
	private boolean running = true;
	
	private synchronized void setRunning(boolean running){
		this.running = running;
	}
	
	private synchronized boolean isRunning(){
		return running;
	}
	
	public void run(){
		
		setRunning(true);
		
		long beginLoopTime;
		long endLoopTime;
		long currentUpdateTime = System.nanoTime();
		long lastUpdateTime;
		long deltaLoop;
		
		while(!isActive()){
			Thread.yield();
		}
		while(isRunning()){
			beginLoopTime = System.nanoTime();
			
			render();
			
			lastUpdateTime = currentUpdateTime;
			currentUpdateTime = System.nanoTime();
			update((int) ((currentUpdateTime - lastUpdateTime)/(1000*1000)));
			
			endLoopTime = System.nanoTime();
			deltaLoop = endLoopTime - beginLoopTime;
	        
	        if(deltaLoop > desiredDeltaLoop){
	            //Do nothing. We are already late.
	        }else{
	            try{
	                Thread.sleep((desiredDeltaLoop - deltaLoop)/(1000*1000));
	            }catch(InterruptedException e){
	                //Do nothing
	            }
	        }
		}
	}

	private void render() {
		try{
			Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
			g.clearRect(0, 0, width, height);
			render(g);
			bufferStrategy.show();
			g.dispose();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Overwrite this method in subclass
	 */
	protected void update(int deltaTime){
		game.update(deltaTime);
	}
	
	/**
	 * Overwrite this method in subclass
	 */
	protected void render(Graphics2D g){
		game.draw(g);
	}

}
