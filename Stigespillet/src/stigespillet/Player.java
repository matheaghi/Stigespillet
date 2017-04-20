package stigespillet;

import java.awt.Graphics;
import java.awt.Image;

public class Player extends Entity{

	private String name;
	private int playerNr;
	private Rute iRute /* index, ikke nummeret på brettet*/;
	private boolean pause = false;
	private Image icon = Assets.grin;
	
	private Game game;
	
	public Player(String name, int playerNr, float x, float y, Game game){
		super(x, y);
		this.name = name;
		this.playerNr = playerNr;
		this.game = game;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getPlayerNr(){
		return this.playerNr;
	}
	
	public Rute getIRute(){
		return this.iRute;
	}
	
	public boolean getPause(){
		return this.pause;
	}
	
	public void setIRute(Rute rute){
		this.iRute = rute;
	}
	
	public void setPause(boolean some){
		pause = some;
	}

	@Override
	public void tick() {
		if(game.getKeyManager().up)
			y -= 3;
		if(game.getKeyManager().down)
			y += 3;
		if(game.getKeyManager().left)
			x -= 3;
		if(game.getKeyManager().right)
			x += 3;
	}

	@Override
	public void render(Graphics g) { 
		g.drawImage(icon, (int)x, (int)y, null);
	}
	
}
