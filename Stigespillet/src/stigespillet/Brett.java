package stigespillet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Brett {
	
	
	private Player sinTur;
	private ArrayList<Rute> brettet;
	private List<Player> spillere = new ArrayList<Player>();
	private boolean isGameOver = false;
	private List<String> playerNames = new ArrayList<String>();
	
	
	private Dice dice = new Dice();
	
	public Brett(List<Player> spillere){
		for (Player player : spillere) {
			playerNames.add(player.getName());
		}
		this.spillere = spillere;
		for (int i = 0; i < 100; i++) {
			brettet.add(new Rute(i));
		}
		int[] røde_ruter = {20, 46, 71, 82, 97};
		int[] grønne_ruter = {5, 11, 22, 52, 60, 87, };
		int[] oransje_ruter = {12, 41, 73, 95, 98};
		for (int i : røde_ruter) {
			brettet.get(i).setType('r');
		}
		for (int j : grønne_ruter) {
			brettet.get(j).setType('g');
		}
		for (int k : oransje_ruter) {
			brettet.get(k).setType('o');
		}
		brettet.get(28).setType('s');
		brettet.get(65).setType('s');
		brettet.get(56).setType('s');
		
		playGame();
	}
	
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
		
		
		//Sjekker og fikser hvis ruten er opptatt
				if (!(newRute.isOpptatt())) {
					newRute.setPlayer(spiller);
					oldRute.setPlayer(null);
					spiller.setIRute(newRute);
				}else {
					fixOpptatt(spiller, newRute);
					oldRute.setPlayer(null);
				}
		
		//Sjekker og fikser hvis spilleren ender paa en spesiell rute
		if(newRute.getType() == 'g'){
			newRute = gronnRute(newRute, spiller);
			
		}else if(newRute.getType() == 'r'){
			newRute = rodRute(newRute);
			
		}else if(newRute.getType() == 'b'){
			int diceThrow = this.dice.throwDice();
			newRute = blaRute(diceThrow, spiller, newRute);
			
		}else if(newRute.getType() == 's'){
			//svartRute(input fra bruker: hvilken spiller skal st� over?);
			Player maaStaaOver = null;
			while (true){
				Scanner reader = new Scanner(System.in);  // Reading from System.in
				System.out.println("Which Player must wait? ");
				String n = reader.nextLine();
				reader.close();
				if(!(Arrays.asList(playerNames).contains(n))){
					System.out.println("That is not the name of a player!");
				}
				else{
					for (Player p : spillere) {
						if(p.getName().equals(n)){
							maaStaaOver = p;
							
						}
					}
					break;
				}
			}
			
			svartRute(maaStaaOver);
		}
		else if(newRute.getType() == 'o'){
			oransjeRute(spiller);
		}
		
		
		//Gjoer at spilleren kan kaste p� nytt hvis den faar 6
		if(diceShows == 6){
			movePlayer(spiller);
		}
	}
		//Sykt bra skreve. � gjor litt forandring, men det var kult med rekursivt kall :)
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
	
	
	
	public void executeRute(Rute rute){
		
	}
		
		
	public void playGame(){
		//initialize();
		while (!isGameOver){
			for (Player spiller: this.spillere){
				sinTur = spiller;
				if (sinTur.getPause()){
					System.out.println(spiller.getName() + " m� st� over denne runden. ");
					sinTur.setPause(false);
					continue;
				}
				System.out.println("Det er " + spiller + " sin tur.");
				movePlayer(spiller);
			}
		}
		System.out.println("Spillet er over!");
			
		
	}
	
	//Rykk ned
	public Rute rodRute(Rute foerStige){
		Rute etterStige = null;
		if (foerStige.getNummer() == 21){
			etterStige = brettet.get(1);
		}else if(foerStige.getNummer() == 47){
			etterStige = brettet.get(25);
		}else if(foerStige.getNummer() == 72){
			etterStige = brettet.get(48);
		}else if(foerStige.getNummer() == 83){
			etterStige = brettet.get(25);
		}else if(foerStige.getNummer() == 98){
			etterStige = brettet.get(80);
		}else if (foerStige.getNummer() == 57){
			etterStige = brettet.get(16);
		}	
		return etterStige;
	}
	
	//Rykk opp
	public Rute gronnRute(Rute foerStige, Player spiller){
		Rute etterStige = null;
		if (foerStige.getNummer() == 6){
			etterStige = brettet.get(15);
		}else if(foerStige.getNummer() == 12){
			etterStige = brettet.get(30);
		}else if(foerStige.getNummer() == 23){
			etterStige = brettet.get(75);
		}else if(foerStige.getNummer() == 53){
			etterStige = brettet.get(73);
			oransjeRute(spiller);
		}else if(foerStige.getNummer() == 61){
			etterStige = brettet.get(79);
		}else if(foerStige.getNummer() == 88){
			etterStige = brettet.get(92);
		}else if (foerStige.getNummer() == 57){
			etterStige = brettet.get(81);	
		}
		return etterStige;
	}
	
	
	
	//Stå over en runde
	public void oransjeRute(Player spiller){
		spiller.setPause(true);
	}
	
	//Velg noen som må stå over
	public void svartRute(Player maaStaaOver){
		maaStaaOver.setPause(true);
	}
	
	//Enten opp eller ned
	public Rute blaRute(int terningkast, Player spiller, Rute foerStige){
		Rute etterStige;
		if (terningkast <= 3){
			etterStige = rodRute(foerStige);
		}else{
			etterStige = gronnRute(foerStige, spiller);
		}
		return etterStige;
	}
	
	public static void main(String[] args) {
		
	}
}