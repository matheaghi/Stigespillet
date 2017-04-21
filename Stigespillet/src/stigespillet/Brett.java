package stigespillet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Brett {
	
	private Player sinTur;
	private ArrayList<Rute> brettet;
	private List<Player> spillere = new ArrayList<Player>();
	private List<String> playerNames = new ArrayList<String>();
	private boolean isGameOver = false;
	
	private String melding;
	private Dice dice = new Dice();
	
	public Brett(List<Player> spillere){
		for (Player player : spillere) {
			spillere.add(player);
			player.setIRute(brettet.get(0));
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
	
	public void playGame(){
		//initialize();
		while (!isGameOver){
			for (Player spiller: this.spillere){
				sinTur = spiller;
				if (sinTur.getPause()){
					sinTur.setPause(false);
					continue;
				}
				movePlayer(spiller);
				if(isGameOver){
					break;
				}
			}
		}
		setMelding("3");		
	}
	
	//movePlayer
	public void movePlayer(Player spiller) {
		//gjor at spiller maa trykke enter for aa kaste terning
		setMelding("Det er " + spiller.getName() + "sin tur. Press ENTER for aa kaste terning.");
		promptEnterKey();
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
		String feedBack = diceFeedback(diceShows);
		setMelding(feedBack);
		setMelding("Klikk ENTER for � flytte brikken din");
		promptEnterKey();
		
		//Sjekker og fikser hvis ruten er opptatt
		if (!newRute.isOpptatt() && oldRute.getNummer() == 1) {
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
			setMelding("Den ruten var visst opptatt, haha, motspilleren maa trekke bakover!");
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
		if (newRute.isOpptatt() && newRute.getNummer() != 1) {
			fixOpptatt(staarHerFraFor, newRute);
		}
		

		//Hvis staarHerFraFor ender p� en spesiel rute etter den er blitt flyttet bakover
		newRute = executeRute(newRute, staarHerFraFor);
		newRute.setPlayer(staarHerFraFor);		

		
		//Hvis staarHerFraFor ender p� en spesiel rute etter den er blitt flyttet bakover
		if (newRute.getNummer() == 1){
			newRute.setPlayers(staarHerFraFor, true);			
		}else{
			newRute = executeRute(newRute, staarHerFraFor);
			newRute.setPlayer(staarHerFraFor);
		}
		

		oldRute.setPlayer(sinTur);
		sinTur.setIRute(oldRute);
		staarHerFraFor.setIRute(newRute);
		
		
	}
	
	public String diceFeedback(int dicethrow){
		if(dicethrow == 1){
			return "Du fikk desverre bare en 1'er... Men det kan jo vaere bra det og";
		}else if(dicethrow == 2){
			return "Du fikk en 2'er p� terningen, hva kan vi gjoere med det?";
		}else if(dicethrow == 3){
			return "Du fikk en midt p� treet 3'er. Det kunne vaert vaerre";
		}else if(dicethrow == 4){
			return "Ikke verst, du fikk en 4'er.";
		}else if(dicethrow == 5){
			return "Yeah, en 5'er kommer man langt med. Hei som det gaar";
		}else{
			return "Hurra, 6'er! Ikke lenge igjen til maal :D";
		}
		
	}
	
	public Rute executeRute(Rute rute, Player spiller){
		if(rute.getType() == 'g'){
			rute = gronnRute(rute, spiller);
			setMelding("Hurra, en stige! Tjohei :)");
			
		}else if(rute.getType() == 'r'){
			rute = rodRute(rute);
			setMelding("Buhuu, en slange. Du m� tilbake igjen");
			
		}else if(rute.getType() == 'b'){
			setMelding("Du kom paa en blaa rute og maa kaste terning. Faar du 1-3 rykker du ned, faar du 4-6 farer du oppover (Press ENTER)");
			promptEnterKey();
			int diceThrow = this.dice.throwDice();
			if(diceThrow > 3){
				setMelding("Bra jobba, du kan ta stigen!");
			}else{
				setMelding("Saa trist, slagen tok deg...");
			}
			rute = blaRute(diceThrow, spiller, rute);
			
		}else if(rute.getType() == 's'){
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
	public void promptEnterKey(){
		Scanner scanner;
		scanner = new Scanner(System.in);
		scanner.nextLine();
		scanner.close();
		}
		
	
		
		

	/*public void playGame(){
		//initialize();
		while (!isGameOver){
			for (Player spiller: this.spillere){
				if(isGameOver){
					break;
				}
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
		System.out.println("Spillet er over! Spiller" + sinTur + "vant :)");
			
		
}*/
	
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
		while (true){
			setMelding(sinTur.getName() + "Skriv inn (trykk keys) hvilken spiller du onsker skal staa over en runde");
			Scanner reader = new Scanner(System.in);  // Reading from System.in
			String n = reader.nextLine();
			reader.close();
			if(!(Arrays.asList(playerNames).contains(n))){
				setMelding("Det er ikke en av spillerene. Skriv igjen");
			}else{
				for (Player p : spillere) {
					if(p.getName().equals(n)){
						p.setPause(true);
					}
				}
				break;
			}
		}
	}
	
	//Enten opp eller ned
	public Rute blaRute(int terningkast, Player spiller, Rute foerStige){
		promptEnterKey();
		Rute etterStige;
		if (terningkast <= 3){
			etterStige = rodRute(foerStige);
		}else{
			etterStige = gronnRute(foerStige, spiller);
		}
		return etterStige;
	}
		
	public void setMelding(String mld){
		this.melding = mld + "/n" + this.melding;
		/*if(mld == 1){
			this.melding = this.sinTur.getName() + " det er din tur. Trykk enter for � kaste terningen";
		}else if(mld == 2){
			this.melding = "Trykk ENTER " + sinTur.getName() + " for � flytte brikken";
		}else if(mld == 3){
			this.melding = "Spillet er ferdig og " + sinTur.getName() + "vant!! ^_^";
		}*/
	}
		
	public String getMelding(){
		return this.melding;
	}
	
}