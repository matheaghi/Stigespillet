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
	
/*
	public Brett(int antallSpillere){
		this.sinTur = 1;
		Player sp;
		for (int i = 0; i < antallSpillere; i++){
			sp = new Player(name, playerNr);
			spillere.add(sp);
		}
	}
	*/
	
	
	//movePlayer
	public void movePlayer() {
		diceShows = dice.throw_dice();
		int newRuteIndex = sinTur.getIRute().getNummer() - 1 + diceShows;
		Rute oldRute = sinTur.getIRute();
		Rute newRute = brettet.get(newRuteIndex);
		if (!(newRute.isOpptatt())) {
		newRute.setPlayer(sinTur);
		oldRute.setPlayer(null);
		sinTur.setIRute(newRute);
		}
		else if (newRute.isOpptatt()) {
			
		}
	}	
	
	
	
}	