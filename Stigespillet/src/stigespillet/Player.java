package stigespillet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity{

	private String name;
	private int playerNr;
	private Rute iRute;/* index, ikke nummeret på brettet*/;
	private boolean pause = false;
	private BufferedImage icon = Assets.grin;
	private Player nextPlayer;

	
	
	
	public void setIcon(BufferedImage icon){
		this.icon = icon;
	}
	
	public Player(String name, int playerNr, float x, float y, Game game){
		super(x,y);
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
	
	public void setNextPlayer(Player player){
		this.nextPlayer = player;
	}
	
	public Player getNextPlayer(){
		return nextPlayer;
		
	
	}
	
	public BufferedImage getIcon(){
		return this.icon;
	}
	
	@Override
	public void tick() {
		x = getXY(iRute.getNummer()-1)[0];
		y = getXY(iRute.getNummer()-1)[1];
	}

	@Override
	public void render(Graphics g) { 
		g.drawImage(icon, (int)x, (int)y, null);
	}

	
	private int[] getXY(int rutenr){
		
		
		int[] ltr = {0,1,2,3,4,5,6,7,8,9,20,21,22,23,24,25,26,27,28,29,40,41,42,43,44,45,46,47,48,49,60,61,62,63,64,64,66,67,68,69,80,81,82,83,84,85,86,87,88,89};
		int[] rtl = {19,18,17,16,15,14,13,12,11,10,39,38,37,36,35,34,33,32,31,30,59,58,57,56,55,54,53,52,51,50,79,78,77,76,75,74,73,72,71,70,99,98,97,96,95,94,93,92,91,90};
		
		int[] ret = new int[2];
		String liste = "";
		int ener;
		int tier;
		for (int i : ltr) {
			if(i == rutenr){
				liste = "ltr";
			}
		}
		for (int i : rtl) {
			if(i == rutenr){
				liste = "rtl";
			}
		}
		if(liste.equals("ltr")){
			ener = rutenr%10;
			tier = (rutenr-ener)/10;
		}
		
		else{
			ener = 9 - (rutenr%10);
			tier = (rutenr-(rutenr%10))/10;
		}
	
		
		int[] xListe = {20,80,140,200,260,320,380,440,500,560};
		//int[] yListe = {5,65,125,185,245,305,365,425,485,545};
		int[] yListe = {545,485,425,365,305,245,285,125,65,5};
	
		ret[0] = xListe[ener];
		ret[1] = yListe[tier];
		
		return ret;
	
	}
	
}
