package stigespillet;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Brett {
	
	private Player sinTur;
	private ArrayList<Rute> brettet = new ArrayList<Rute>();
	private List<Player> spillere = new ArrayList<Player>();
	private boolean isGameOver = false;
	private List<String> meld = new ArrayList<>();
	private List<BufferedImage> emojis = new ArrayList<BufferedImage>();
	private Dice dice = new Dice();
	
	
	private void makelist(){
		emojis.add(Assets.smile);
		emojis.add(Assets.wink);
		emojis.add(Assets.loveeyes);
		emojis.add(Assets.grin);
		emojis.add(Assets.angry);
		emojis.add(Assets.tear);
		emojis.add(Assets.oops);
		emojis.add(Assets.laugh);
		emojis.add(Assets.heart);
		emojis.add(Assets.like);
	}
	
	
	
	public Brett(int tall, Game game){
		makelist();
	
		for (int i = 0; i < tall; i++) {
			spillere.add(new Player("Player "+ (i+1), i+1, 20,545, game, this));
		}
		
		for (int i = 0; i < 100; i++) {
			Rute rute = new Rute(i);
			brettet.add(rute);
		}
		for (int i = 0; i < spillere.size(); i++) {
			int nummer = (int)(Math.random() * (10-i));
			BufferedImage emoji = emojis.get(nummer);
			emojis.remove(emoji);
			spillere.get(i).setIcon(emoji);
		}
		
		for (Player player : spillere) {
			int teller = 0;
			for (Player player2 : spillere) {
				if(teller == 1){
					player.setNextPlayer(player2);
					teller = 0;
					continue;}
				if(player.equals(player2)){
					teller += 1;
				}
			}
			for(Player player3 : spillere){
				if(player3.getNextPlayer() == null){
					player3.setNextPlayer(spillere.get(0));
				}
			}
			player.setIRute(brettet.get(0));
		}
		
		int[] rode_ruter = {20, 46, 71, 82, 97};
		int[] gronne_ruter = {5, 11, 22, 52, 60, 87};
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
		
		sinTur = spillere.get(0);
	}
	
	public List<Player> getSpillere(){
		return this.spillere;
	}
	
	public void playGame(){
		if(isGameOver) {
			setMelding("Spillet er slutt " + sinTur.getName() + " har vunnet spillet!!!");
		}
		else{
			Player nextPlayer = sinTur.getNextPlayer();
			this.sinTur = nextPlayer;
			if(sinTur.getPause()){
				sinTur.setPause(false);
			}
			else{
			movePlayer(sinTur);
			}
			
		}
	}
	
	//movePlayer
	public void movePlayer(Player spiller) {
		//gjor at spiller maa trykke enter for aa kaste terning
		setMelding("Det er " + spiller.getName() + "sin tur. Press ENTER for aa kaste terning.");
		//promptEnterKey();
		
		int diceShows = this.dice.throwDice();
		
		spiller.setTerningkast(diceShows);
		spiller.setForrigeRute(spiller.getIRute());
		spiller.setTokStige(false);
		//Hvis terningkastet forer til at spillerens plasering gar over 100 er spillet ferdig. 
		if ((spiller.getIRute().getNummer() + diceShows) >= 100){
			this.isGameOver = true;
			spiller.setIRute(brettet.get(99));
			System.out.println("Spillet er ferdig");
		}else{
		//Lager navn til tidligere rute og ny rute
		int newRuteIndex = spiller.getIRute().getNummer() - 1 + diceShows;
		Rute oldRute = spiller.getIRute();
		Rute newRute = brettet.get(newRuteIndex);
		
		//Spiller maa presse enter for aa flytte brikken
		String feedBack = diceFeedback(diceShows);
		setMelding(feedBack);
		setMelding("Klikk ENTER for aa flytte brikken din");
		
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
		newRute = executeRute(sinTur.getIRute(), sinTur);
		spiller.getIRute().setPlayer(null);
		spiller.setIRute(newRute);
		newRute.setPlayer(spiller);
		
		
		System.out.println(spiller.getName() + " fikk en " + diceShows + " og står nå i " + spiller.getIRute().getNummer());
		//Gjoer at spilleren kan kaste paa nytt hvis den faar 6
		if(diceShows == 6){
			movePlayer(spiller);
		}
		}
	}

	private void fixOpptatt(Player sinTur, Rute oldRute){
		Rute newRute = brettet.get(oldRute.getNummer() - 2);
		Player staarHerFraFor = oldRute.getPlayer();
		if (newRute.isOpptatt() && newRute.getNummer() != 1) {
			fixOpptatt(staarHerFraFor, newRute);
		}
		
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
	
	public Player getsinTur(){
		return sinTur;
	}
	
	public String diceFeedback(int dicethrow){
		if(dicethrow == 1){
			return "Du fikk desverre bare en 1'er... Men det kan jo vaere bra det og";
		}else if(dicethrow == 2){
			return "Du fikk en 2'er paa terningen, hva kan vi gjoere med det?";
		}else if(dicethrow == 3){
			return "Du fikk en midt paa treet 3'er. Det kunne vaert vaerre";
		}else if(dicethrow == 4){
			return "Ikke verst, du fikk en 4'er.";
		}else if(dicethrow == 5){
			return "Yeah, en 5'er kommer man langt med. Hei som det gaar";
		}else{
			return "Hurra, 6'er! Da kan du kaste igjen ^_^";
		}
	}
	
	public Rute executeRute(Rute rute, Player spiller){
		if(rute.getType() == 'g'){
			rute = gronnRute(rute, spiller);
			setMelding("Hurra, en stige! Tjohei :)");
			
		}else if(rute.getType() == 'r'){
			rute = rodRute(rute, spiller);
			setMelding("Buhuu, en slange. Du maa tilbake igjen");
			
		}else if(rute.getType() == 'b'){
			setMelding("Du kom paa en blaa rute og maa kaste terning. Faar du 1-3 rykker du ned, faar du 4-6 farer du oppover (Press ENTER)");
			//promptEnterKey();
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
	
	//Rykk ned
	public Rute rodRute(Rute foerStige, Player spiller){
		System.out.println("rød");
		Rute etterStige = null;
		spiller.setTokStige(true);
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
		System.out.println("grønn");
		Rute etterStige = null;
		spiller.setTokStige(true);
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
		System.out.println("oransje");
		spiller.setPause(true);
	}
	
	//Velg noen som må stå over
	public void svartRute(){
		System.out.println("svart");
		sinTur.getNextPlayer().setPause(true);
	}
	
	//Enten opp eller ned
	public Rute blaRute(int terningkast, Player spiller, Rute foerStige){
		System.out.println("blå");
		Rute etterStige;
		spiller.setTokStige(true);
		if (terningkast <= 3){
			etterStige = rodRute(foerStige);
		}else{
			etterStige = gronnRute(foerStige, spiller);
		}
		return etterStige;
	}
		
	public void setMelding(String mld){
		this.meld.add(0, mld);
	}
	public boolean getIsGameOver(){
		return this.isGameOver;
	}
		
	public List<String> getMelding(){
		return this.meld;
	}

}