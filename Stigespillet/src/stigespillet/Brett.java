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
			this.isGameOver = true;
		}
		
		int newRuteIndex = spiller.getIRute().getNummer() - 1 + diceShows;
		Rute oldRute = spiller.getIRute();
		Rute newRute = brettet.get(newRuteIndex);
		if (!(newRute.isOpptatt())) {
			newRute.setPlayer(spiller);
			oldRute.setPlayer(null);
			spiller.setIRute(newRute);
		}else if (newRute.isOpptatt()) {
			fixOpptatt(spiller, newRute);
		}
	}
		
	private void fixOpptatt(Player sinTur, Rute oldRute){
		Rute newRute = brettet.get(oldRute.getNummer() - 2);
		oldRute.setPlayer(null);
		if (newRute.isOpptatt()) {
			fixOpptatt(newRute.getPlayer(), newRute);
		}
		else{
			newRute.setPlayer(sinTur);
			oldRute.setPlayer(null);
			sinTur.setIRute(newRute);
		}
	}
	
	
	public void executeRute(Rute rute){
		
	}
		
		
	public void playGame(){
		//initialize();
		while (!isGameOver){
			for (Player spiller: this.spillere){
				sinTur = spiller;
				if (sinTur.getPause()){
					sinTur.setPause(false);
					continue;
				}
				else{
				System.out.println("Det er " + spiller + " sin tur.");
				movePlayer(spiller);
				}
			}
		}
			
		
	}
	
	//Rykk ned
	public void rødRute(){
		
	}
	
	//Rykk opp
	public void grønnRute(){
		
	}
	
	//Stå over en runde
	public void oransjeRute(){
		sinTur.setPause(true);
	}
	
	//Velg noen som må stå over
	public void svartRute(){
		
	}
	
	//Enten opp eller ned
	public void blåRute(){
		
	}
}	