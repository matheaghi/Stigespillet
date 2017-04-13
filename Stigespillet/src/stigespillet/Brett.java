package stigespillet;

import java.util.ArrayList;
import java.util.List;


public class Brett {
	
	
	private Player sinTur;
	private ArrayList<Rute> brettet;
	private List<Player> spillere = new ArrayList<Player>();
	private boolean isGameOver = false;
	
	
	Dice dice = new Dice();
	
	
	//movePlayer
	public void movePlayer(Player spiller) {
		int diceShows = this.dice.throwDice();
		
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
			oldRute.setPlayer(null);
			oldRute.setOpptatt();
		}
	}
		//Sykt bra skreve. Det eneste jeg tenker paa er at naa er det spilleren som kaster som maa 
		//rykke bakover hvis det er opptatt, men var ikke tanken at spilleren som stod der fra for
		//skal gå en bakover?
	private void fixOpptatt(Player sinTur, Rute oldRute){
		Rute newRute = brettet.get(oldRute.getNummer() - 2);
		Player staarHerFraFor = oldRute.getPlayer();
		oldRute.setPlayer(sinTur);
		if (newRute.isOpptatt()) {
			fixOpptatt(staarHerFraFor, newRute);
		}
		newRute.setPlayer(staarHerFraFor);
		oldRute.setPlayer(sinTur);
		sinTur.setIRute(oldRute);
		staarHerFraFor.setIRute(newRute);
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
					System.out.println(spiller.getName() + " må stå over denne runden. ");
					sinTur.setPause(false);
					continue;
				}
				System.out.println("Det er " + spiller + " sin tur.");
				movePlayer(spiller);
			}
		}
			
		
	}
	
	//Rykk ned
	public void rodRute(){
		
	}
	
	//Rykk opp
	public void gronnRute(){
		
	}
	
	//StÃ¥ over en runde
	public void oransjeRute(){
		sinTur.setPause(true);
	}
	
	//Velg noen som mÃ¥ stÃ¥ over
	public void svartRute(Player maaStaaOver){
		maaStaaOver.setPause(true);
	}
	
	//Enten opp eller ned
	public void bloRute(){
		
	}
}	