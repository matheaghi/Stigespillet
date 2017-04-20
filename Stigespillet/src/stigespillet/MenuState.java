package stigespillet;

import java.awt.Graphics;

public class MenuState extends State {

	public MenuState(Game game){
		super(game);
		
	}
	
	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.laugh, 20, 20, null);
	}

}
