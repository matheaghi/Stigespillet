package stigespillet;

import java.awt.Graphics;

public class GameState extends State{

	private Player player;
	private Player player2;
	
	public GameState(Game game){
		super(game);
		player = new Player("Mathea", 1, 100, 100, game);
		player2 = new Player("Randi", 2, 50, 100, game);
	}
	
	@Override
	public void tick() {
		player.tick();
		player2.tick();
	}

	@Override
	public void render(Graphics g) {
		player.render(g);
		player2.render(g);
	}

}
