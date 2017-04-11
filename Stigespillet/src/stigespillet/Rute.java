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
	}
	
	/*public Rute getRute(){
		return this;
	}*/
	
	public int getNummer(){
		return this.nummer;
	}
	
	
	public static void main(String[] args) {
		Rute rute = new Rute();
		System.out.println(rute.pssble_types);
	}
	
}