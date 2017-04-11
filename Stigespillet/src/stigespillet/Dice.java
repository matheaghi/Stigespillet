package stigespillet;

import java.util.Random;

public class Dice {
private int nummer;
Random random = new Random();

public int showNumber() {
	try {
	    Thread.sleep(100);
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
	return random.nextInt(6) + 1;
}

public int throw_dice() {
	try {
	    Thread.sleep(100);
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
	this.nummer = random.nextInt(5) + 1;
	return nummer;
	}

/*Tanken er at man fra brett må bruke en for-løkke og gjennomføre mange showNumber, deretter én throw_dice som både printer
og lagrer siste tallet. Dette løser et problem med at det ikke går an (?) å lage en metode som returnerer flere tall etter hverandre istedet for samtidig.
Legger også til .1 s delay så tallene blir vist, dette må vi sjekke om er riktig pause.*/



public static void main(String[] args) {
	Dice dice = new Dice();
	for (int i = 0; i < 17; i++) {
		System.out.println(dice.showNumber());
	}
	System.out.println(dice.throw_dice());
	System.out.println("Tall:");
	System.out.println(dice.nummer);
	}
}