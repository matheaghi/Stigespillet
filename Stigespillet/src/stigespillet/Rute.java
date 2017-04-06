package stigespillet;
import java.security.spec.PSSParameterSpec;
import java.util.*;

public class Rute {
	//mathea jobber her nå
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
	
	public void setOpptatt() {
		if (player != null) {
			opptatt = true;
		}
		else {
			opptatt = false;
		}
	}
	
	public void setPlayer(Player player, Rute forrige_rute){
		if(this.player != null){
			this.player.
		}
	}
	
	
	public static void main(String[] args) {
		Rute rute = new Rute();
		System.out.println(rute.pssble_types);
	}
	
}