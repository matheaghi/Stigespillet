package stigespillet;

public class Player {

	private String name;
	private int playerNr;
	private Rute iRute;
	private boolean pause = false;
	
	
	public Player(){
		
		
		
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
