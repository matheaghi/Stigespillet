package stigespillet;

import java.awt.Graphics;

public class GameState extends State{

	private Player player;
	
	public GameState(Game game){
		super(game);
		player = new Player("Mathea", 1, 100, 100, game);
	}
	
	@Override
	public void tick() {
		player.tick();
	}

	@Override
	public void render(Graphics g) {
		player.render(g);
	}

}
