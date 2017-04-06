package stigespillet;

public class Player {

	private String name;
	private int playerNr;
	private Rute iRute;
	private boolean pause = false;
	
	
	public Player(String name, int playerNr, Rute iRute){
		this.name = name;
		this.playerNr = playerNr;
		this.iRute = iRute;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getPlayerNr(){
		return this.playerNr;
	}
	
	public Rute getIRute(){
		return this.iRute;
	}
	public boolean getPause(){
		return this.pause;
	}
}
