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
		
		
		//Sjekker og fikser hvis spilleren ender paa en spesiell rute
		if(newRute.getType() == 'g'){
			newRute = gronnRute(newRute, spiller);
			
		}else if(newRute.getType() == 'r'){
			newRute = rodRute(newRute);
			
		}else if(newRute.getType() == 'b'){
			int diceThrow = this.dice.throwDice();
			newRute = blaRute(diceThrow, spiller, newRute);
			
		}else if(newRute.getType() == 's'){
			svartRute(input fra bruker: hvilken spiller skal stå over?);
			
		}else if(newRute.getType() == 'o'){
			oransjeRute(spiller);
		}
			
		//Sjekker og fikser hvis ruten er opptatt
		if (!(newRute.isOpptatt())) {
			newRute.setPlayer(spiller);
			oldRute.setPlayer(null);
			spiller.setIRute(newRute);
		}else {
			fixOpptatt(spiller, newRute);
			oldRute.setPlayer(null);
		}
		
		//Gjoer at spilleren kan kaste på nytt hvis den faar 6
		if(diceShows == 6){
			movePlayer(spiller);
		}
	}
		//Sykt bra skreve. Æ gjor litt forandring, men det var kult med rekursivt kall :)
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
	public Rute rodRute(Rute foerStige){
		Rute etterStige;
		if (foerStige.getNummer == 21){
			etterStige = brett.get(1);
		}else if(foerStige.getNummer == 47){
			etterStige = brett.get(25);
		}else if(foerStige.getNummer == 72){
			etterStige = brett.get(48);
		}else if(foerStige.getNummer == 83){
			etterStige = brett.get(25);
		}else if(foerStige.getNummer == 98){
			etterStige = brett.get(80);
		}else if (foerStige.getNummer == 57)
			etterStige = brett.get(16);
		}	
		return etterStige;
	}
	
	//Rykk opp
	public Rute gronnRute(Rute foerStige, Player spiller){
		Rute etterStige;
		if (foerStige.getNummer == 6){
			etterStige = brett.get(15);
		}else if(foerStige.getNummer == 12){
			etterStige = brett.get(30);
		}else if(foerStige.getNummer == 23){
			etterStige = brett.get(75);
		}else if(foerStige.getNummer == 53){
			etterStige = brett.get(73);
			oransjeRute(spiller);
		}else if(foerStige.getNummer == 61){
			etterStige = brett.get(79);
		}else if(foerStige.getNummer == 88){
			etterStige = brett.get(92);
		}else if (foerStige.getNummer == 57)
			etterStige = brett.get(81);
		}	
		return etterStige;
	}
	
	
	//StÃ¥ over en runde
	public void oransjeRute(Player spiller){
		spiller.setPause(true);
	}
	
	//Velg noen som mÃ¥ stÃ¥ over
	public void svartRute(Player maaStaaOver){
		maaStaaOver.setPause(true);
	}
	
	//Enten opp eller ned
	public Rute blaRute(int terningkast, Player spiller, Rute foerStige){
		Rute etterStige;
		if (terningkast =< 3){
			etterStige = rodRute(foerStige);
		}else{
			etterStige = gronnRute(foerStige, spiller);
		return etterStige;
	}
}	