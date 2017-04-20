package stigespillet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable{
	
	private Display display;
	public int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private Image testImage;
	private SpriteSheet sheet;
	private SpriteSheet emoticons;
	private SpriteSheet tileColors;
	
	int x = 20;
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
	 }
	
	
	private void init(){
		display = new Display(title, width, height);
		testImage = ImageLoader.loadImage("/textures/kitten.png");
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Grid6003.png"));
		emoticons = new SpriteSheet(ImageLoader.loadImage("/textures/emoticons2.png"));
		tileColors = new SpriteSheet(ImageLoader.loadImage("/textures/tileColors4.png"));
		
	}
	
	private void tick(){
		x += 1;
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Draw Here!
		
		g.drawImage(sheet.crop(0, 0, 600, 600), 20, 5, null);
		//Fargelegg ruter:
		
		
		g.drawImage(tileColors.crop(0, 0, 60, 60), 440, 65, null);
		g.drawImage(tileColors.crop(0, 0, 60, 60), 440, 245, null);
		g.drawImage(tileColors.crop(0, 0, 60, 60), 500, 485, null);
		g.drawImage(tileColors.crop(0, 0, 60, 60), 320, 545, null);
		g.drawImage(tileColors.crop(0, 0, 60, 60), 140, 425, null);
		g.drawImage(tileColors.crop(0, 0, 60, 60), 20, 185, null);
		g.drawImage(tileColors.crop(60, 0, 60, 60), 20, 425, null);
		g.drawImage(tileColors.crop(60, 0, 60, 60), 380, 305, null);
		g.drawImage(tileColors.crop(60, 0, 60, 60), 500, 125, null);
		g.drawImage(tileColors.crop(60, 0, 60, 60), 140, 65, null);
		g.drawImage(tileColors.crop(60, 0, 60, 60), 140, 5, null);
		g.drawImage(tileColors.crop(120, 0, 60, 60), 440, 485, null);
		g.drawImage(tileColors.crop(120, 0, 60, 60), 80, 305, null);
		g.drawImage(tileColors.crop(120, 0, 60, 60), 380, 125, null);
		g.drawImage(tileColors.crop(120, 0, 60, 60), 260, 5, null);
		g.drawImage(tileColors.crop(120, 0, 60, 60), 80, 5, null);
		g.drawImage(tileColors.crop(180, 0, 60, 60), 200, 245, null);
		g.drawImage(tileColors.crop(240, 0, 60, 60), 320, 185, null);
		g.drawImage(tileColors.crop(240, 0, 60, 60), 500, 425, null);
		g.drawImage(tileColors.crop(300, 0, 60, 60), 20, 5, null);
		
		g.drawImage(emoticons.crop(60, 0, 60, 60), x, 5, null);
		
		
		g.drawImage(emoticons.crop(0, 0, 60, 60), 20, 545, null);
		
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
