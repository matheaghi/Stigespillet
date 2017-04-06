package stigespillet;

import java.util.ArrayList;


public class Brett {
	
	private int sinTur;
	private ArrayList<Rute> brettet;
	private List<Player> spillere = new ArrayList<>();
	private boolean isGameOver = false;
	
	
	public Brett(int antallSpillere){
		this.sinTur = 1;
		Player sp;
		for (int i = 0; i < antallSpillere; i++){
			sp = new Player(name, playerNr);
			spillere.add(sp);
		}
	}
	
	
	
}	