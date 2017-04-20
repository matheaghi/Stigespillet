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
		int[] rode_ruter = {20, 46, 71, 82, 97};
		int[] gronne_ruter = {5, 11, 22, 52, 60, 87, };
		int[] oransje_ruter = {12, 41, 73, 95, 98};
		for (int i : rode_ruter) {
			brettet.get(i).setType('r');
		}
		for (int j : gronne_ruter) {
			brettet.get(j).setType('g');
		}
		for (int k : oransje_ruter) {
			brettet.get(k).setType('o');
		}
		brettet.get(28).setType('s');
		brettet.get(65).setType('s');
		brettet.get(56).setType('b');
		
		playGame();
	}
	
	//movePlayer
	public void movePlayer(Player spiller) {
		//gjor at spiller maa trykke enter for aa kaste terning
		promptEnterKey(1);
		int diceShows = this.dice.throwDice();
		
		//Hvis terningkastet forer til at spillerens plasering gar over 100 er spillet ferdig. 
		if ((spiller.getIRute().getNummer() + diceShows) >= 100){
			this.isGameOver = true;
		}
		
		//Lager navn til tidligere rute og ny rute
		int newRuteIndex = spiller.getIRute().getNummer() - 1 + diceShows;
		Rute oldRute = spiller.getIRute();
		Rute newRute = brettet.get(newRuteIndex);
		
		//Spiller maa presse enter for aa flytte brikken
		promptEnterKey(2);
		
		//Sjekker og fikser hvis ruten er opptatt
		if (!newRute.isOpptatt() && oldRute.getNummer == 1) {
			newRute.setPlayer(spiller);
			oldRute.setPlayers(spiller, false);
			spiller.setIRute(newRute);
		}else if (!newRute.isOpptatt()) {
			newRute.setPlayer(spiller);
			oldRute.setPlayer(null);
			spiller.setIRute(newRute);
		}else{
			fixOpptatt(spiller, newRute);
			oldRute.setPlayer(null);
		}
		
		//Sjekker og fikser hvis spilleren ender paa en spesiell rute
		newRute = executeRute(newRute, sinTur);
				
		//Gjoer at spilleren kan kaste paa nytt hvis den faar 6
		if(diceShows == 6){
			movePlayer(spiller);
		}
	}

	private void fixOpptatt(Player sinTur, Rute oldRute){
		Rute newRute = brettet.get(oldRute.getNummer() - 2);
		Player staarHerFraFor = oldRute.getPlayer();
		if (newRute.isOpptatt() && newRute.getNummer != 1) {
			fixOpptatt(staarHerFraFor, newRute);
		}
		
		
		//Hvis staarHerFraFor ender p� en spesiel rute etter den er blitt flyttet bakover
		if (newRute.getNummer == 1){
			newRute.setPlayers(staarHerFraFor, true);			
		}else{
			newRute = executeRute(newRute, staarHerFraFor);
			newRute.setPlayer(staarHerFraFor);
		}
		
		oldRute.setPlayer(sinTur);
		sinTur.setIRute(oldRute);
		staarHerFraFor.setIRute(newRute);
		
		
	}
	
	
	public Rute executeRute(Rute rute, Player spiller){
		if(rute.getType() == 'g'){
			rute = gronnRute(rute, spiller);
			
		}else if(rute.getType() == 'r'){
			rute = rodRute(rute);
			
		}else if(rute.getType() == 'b'){
			int diceThrow = this.dice.throwDice();
			rute = blaRute(diceThrow, spiller, rute);
			
		}else if(rute.getType() == 's'){
			//Flyttet lesninen av input inn i svartRute()
			svartRute();
		}
		else if(rute.getType() == 'o'){
			oransjeRute(spiller);
		}	
		
		return rute;
	}
	
	//promptEnterKey skal gjoere at en spiller maa trykke enter for aa fortsette, og den
	//tar inn et tall som indikerer hvorfor man maa trykke enter
	//1 = spiller skal kaste terning
	//2 = spiller skal flytte brikken sin
	public void promptEnterKey(int nummer){
		Scanner scanner;
		if (nummer == 1){
			System.out.println("Press \"ENTER\" to throw the dice...");
			scanner = new Scanner(System.in);
			scanner.nextLine();
		}else if(nummer == 2){
			System.out.println("Press \"ENTER\" to move your faboulus face/piece/chip...");
			scanner = new Scanner(System.in);
			scanner.nextLine();
		}
		
	}
		
		
	public void playGame(){
		//initialize();
		while (!isGameOver){
			for (Player spiller: this.spillere){
				sinTur = spiller;
				if (sinTur.getPause()){
					System.out.println(spiller.getName() + " maa staa over denne runden. ");
					sinTur.setPause(false);
					continue;
				}
				System.out.println("Det er " + spiller + " sin tur.");
				movePlayer(spiller);
			}
		}
		System.out.println("Spillet er over! Spiller" + + "vant :)");
			
		
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
	public void svartRute(){
		Player maaStaaOver = null;
		while (true){
			Scanner reader = new Scanner(System.in);  // Reading from System.in
			System.out.println("Which Player must wait? ");
			String n = reader.nextLine();
			reader.close();
			if(!(Arrays.asList(playerNames).contains(n))){
				System.out.println("That is not the name of a player!");
			}else{
				for (Player p : spillere) {
					if(p.getName().equals(n)){
						maaStaaOver = p;
					}
				}
				break;
			}
		}
		maaStaaOver.setPause(true);
	}
	
	//Enten opp eller ned
	public Rute blaRute(int terningkast, Player spiller, Rute foerStige){
		promptEnterKey(1);
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