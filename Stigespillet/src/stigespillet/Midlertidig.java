package stigespillet;

import java.util.ArrayList;
import java.util.Arrays;

public class Midlertidig {

	
	private int[] l1 = {0,1,2,3,4,5,6,7,8,9};
	private int[] l2 = {19,18,17,16,15,14,13,12,11,10};
	private int[] l3 = {20,21,22,23,24,25,26,27,28,29};
	private int[] l4 = {39,38,37,36,35,34,33,32,31,30};
	private int[] l5 = {40,41,42,43,44,45,46,47,48,49};
	private int[] l6 = {59,58,57,56,55,54,53,52,51,50};
	private int[] l7 = {60,61,62,63,64,64,66,67,68,69};
	private int[] l8 = {79,78,77,76,75,74,73,72,71,70};
	private int[] l9 = {80,81,82,83,84,85,86,87,88,89};
	private int[] l10 = {99,98,97,96,95,94,93,92,91,90};

	
	
	private int[] returXY(int rutenr){
	
		rutenr = nummer - 1;
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
			ener = rutenr - (rutenr%10) + 10 - rutenr;
			tier = (rutenr-(rutenr%10))/10;
		}
		
		int[] xListe = {20,80,140,200,260,320,380,440,500,560};
		int[] yListe = {5,65,125,185,245,305,365,425,485,545};
		ret[0] = xListe[ener];
		ret[1] = yListe[tier];
		
		return ret;
	}
	
}

