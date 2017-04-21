package stigespillet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.*;

public class Game implements Runnable{
	
	private Display display;
	//private DisplayPlayerChoice pickPlayer;
	public int width, height;
	public String title;
	private boolean displayGame = true;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private Image testImage;
	private SpriteSheet sheet;
	private SpriteSheet emoticons;
	
	JTextField keyText = new JTextField(20);
	
	
	//Brett spillebrett = new Brett();
	
	
	int x = 20;
	
	//States
	private State gameState;
	//private State menuState;
	//private State newGameState;
	
	
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
	 }
	
	//Input
	private KeyManager keyManager;
	
	
	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		//pickPlayer = new DisplayPlayerChoice("Pick Player", width, height);
		display.setVisibility(true);
		testImage = ImageLoader.loadImage("/textures/kitten.png");
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Board.png"));
		emoticons = new SpriteSheet(ImageLoader.loadImage("/textures/emoticons2.png"));
		Assets.init();
		
		
		gameState = new GameState(this);
		State.setState(gameState);
		/*menuState = new MenuState(this);
		State.setState(menuState);
		newGameState = new NewGameState(this);
		State.setState(newGameState);*/
	}
	
	private void tick(){
		keyManager.tick();
		
		if(State.getState() != null){
			State.getState().tick();
		}
		
		x += 1;
	}
	
	private void render(){
		if(displayGame == false){
		
			
		}
		else{
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		}
		
		g = bs.getDrawGraphics();
		
		if(displayGame == true){
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Draw Here!
		//g.drawString(Brett.class.getMelding(), 1000,50 );
		
		g.drawImage(sheet.crop(0, 0, 600, 600), 20, 5, null);
		//Fargelegg ruter:
		
		
		
		g.drawImage(emoticons.crop(60, 0, 60, 60), x, 5, null);
		
		
		g.drawImage(Assets.angry, 20, 545, null);
		
		
		if(State.getState() != null){
			State.getState().render(g);
		}
		}
		
		else{
			
		}
		
		//End Drawing!
		bs.show();
		g.dispose();
		
	}
	
	public void run(){
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks ++;
				delta --;
			}
			
			if(timer >= 1000000000){
				System.out.println("Ticks and frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
		
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
