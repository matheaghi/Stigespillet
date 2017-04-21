package stigespillet;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
	
	private Display display;
	public int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private Brett spill;
	
	private SpriteSheet sheet;
	
	int x = 20;
	
	//States
	private State gameState;
	
	
	
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
		display.setVisibility(true);
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Board.png"));
		Assets.init();
		spill = new Brett(4, this);
		System.out.println(spill.getMelding());
		
		
		gameState = new GameState(this, spill);
		State.setState(gameState);

	}
	
	private void tick(){
		keyManager.tick();
		
		if(State.getState() != null){
			State.getState().tick();
		}
		
		spill.playGame();
		

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
		if(spill.getIsGameOver()){
			g.drawString("Spillet er over, og " + spill.getsinTur().getName() + " vant! Grattis!", 650,200 );
		}
		else{
			if (spill.getMelding().size() > 10){
				for (int i = 0; i < 10; i++) {
				if(!(spill.getMelding().get(i) == null)){
				g.drawString(spill.getMelding().get(i), 650,200 + (i*20));
				}
			else{
				for (int j = 0; j < spill.getMelding().size(); j++) {
					g.drawString(spill.getMelding().get(j), 650,200 + (j*20));
				}
				}
			}
			
			}
		}
		
		
		g.drawImage(sheet.crop(0, 0, 600, 600), 20, 5, null);
		
		
		for (int i = 0; i < spill.getSpillere().size(); i++) {
			g.drawString(spill.getSpillere().get(i).getName(), 700, 50 + (i*40));
			g.drawImage(spill.getSpillere().get(i).getIcon(), 650, 50 + (i*40) - 40, null);
		}

		
		

		
		
		//Fargelegg ruter:
		
		
		
		//g.drawImage(emoticons.crop(60, 0, 60, 60), x, 5, null);
		
		
		//g.drawImage(Assets.angry, 20, 545, null);
		
		
		if(State.getState() != null){
			State.getState().render(g);
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
	
	
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_1){
			System.out.println("Skrev 1");
		}
		else if(key == KeyEvent.VK_2){
			
		}
		else if(key == KeyEvent.VK_3){
			
		}
		
		else if(key == KeyEvent.VK_4){
			
		}
	}
	
	public void keyReleased(KeyEvent e){
		
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
