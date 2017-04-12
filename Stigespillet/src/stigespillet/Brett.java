package stigespillet;

import java.util.ArrayList;
import java.util.List;


public class Brett {
	
	// endret sinTur til Player
	private Player sinTur;
	private ArrayList<Rute> brettet;
	private List<Player> spillere = new ArrayList<Player>();
	private boolean isGameOver = false;
	private int diceShows;
	
	Dice dice = new Dice();
	
	
	//movePlayer
	public void movePlayer(Player spiller) {
		diceShows = this.dice.throw_dice();
		
		//Hvis terningkastet forer til at spillerens plasering gar over 100 er spillet ferdig. 
		if ((spiller.getIRute().getNummer() + diceShows) >= 100){
			this.isGameOver = ture;
		}
		
		int newRuteIndex = spiller.getIRute().getNummer() - 1 + diceShows;
		Rute oldRute = spiller.getIRute();
		Rute newRute = brettet.get(newRuteIndex);
		if (!(newRute.isOpptatt())) {
			newRute.setPlayer(spiller);
			oldRute.setPlayer(null);
			spiller.setIRute(newRute);
		}else if (newRute.isOpptatt()) {
			
	}	
		
		
	public void playGame(){
		//initialize();
		while (!isGameOver){
			for (Player spiller: this.spillere){
				movePlayer(spiller);
			}
		}
			
		
	}
	
	
	
}	