package stigespillet;

import java.awt.Graphics;

public class GameState extends State{
	private Brett spill;
	
	
	public GameState(Game game, Brett spill){
		super(game);
		this.spill = spill;
		for (Player spiller : spill.getSpillere() ) {
			
		}
	}
	
	@Override
	public void tick() {
	for (Player spiller : spill.getSpillere() ) {
				spiller.tick();
		}
	}

	@Override
	public void render(Graphics g) {
		for (Player spiller : spill.getSpillere() ) {
			spiller.render(g);
		}
	}

}
