package stigespillet;
import java.security.spec.PSSParameterSpec;
import java.util.*;

public class Rute {
	
	
	private int nummer;
	private char type;
	private boolean opptatt;
	private Player player;
	
	//Players er spillere i rute nummmer 1
	private List<Player> players = new ArrayList<Player>();
	private List<Character> pssble_types = new ArrayList<Character>(Arrays.asList(new Character[]{'r', 'g', 'o', 's', 'b'}));
	
	
	public Rute(int index){
		this.nummer = index+1;
	}
	
	public void setType(char type) {
		if (pssble_types.contains(type)){
			this.type = type;
		}
	}
	
	public boolean isOpptatt(){
		if (opptatt){
			return true;
		}
		else{
			return false;
		}
	}

	
	public void setPlayer(Player player){
		this.player = player;
		if (player == null){
			this.opptatt = false;
		}else{
			this.opptatt = true;
		}
	}
	
	public void setPlayers(Player player, boolean leggTil){
		if (leggTil && !(this.players.contains(player)){
			this.players.add(player);
		}else if(!leggTil && players.contains(player))
			this.players.remove(player);
		}
		this.opptatt = false;
		for (Player p : players){
			if (player != null){
				this.opptatt = true;
		}
		
	}
	
	public int getNummer(){
		return this.nummer;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public ArrayList<Player> getPlayers(){
		return this.players;
	}
	
	public char getType(){
		return this.type;
	}
}