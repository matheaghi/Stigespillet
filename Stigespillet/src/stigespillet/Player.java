package stigespillet;

public class Player {

	private String name;
	private int playerNr;
	private Rute iRute /* index, ikke nummeret på brettet*/;
	private boolean pause = false;
	
	
	public Player(String name, int playerNr){
		this.name = name;
		this.playerNr = playerNr;
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
	
	public void setIRute(Rute rute){
		this.iRute = rute;
	}
	
	public void setPause(boolean some){
		pause = some;
	}
	
}
