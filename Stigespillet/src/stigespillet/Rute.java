package stigespillet;
import java.security.spec.PSSParameterSpec;
import java.util.*;

public class Rute {
	
	
	private int nummer;
	private char type;
	private boolean opptatt;
	private Player player;
	private Player player_2;
	private List<Character> pssble_types = new ArrayList<Character>(Arrays.asList(new Character[]{'r', 'g', 'o', 's', 'b'}));
	//public Rute(int index, char type){
		
	//}
	
	public void setNummer(int nummer) {
		if (nummer > 0) {
			this.nummer = nummer;
		}
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
	
	/*public Rute getRute(){
		return this;
	}*/
	
	public int getNummer(){
		return this.nummer;
	}
	
	public Player getPlayer(){
		return this.player;
	}

	
	
	public static void main(String[] args) {
		Rute rute = new Rute();
		System.out.println(rute.pssble_types);
	}
	
}