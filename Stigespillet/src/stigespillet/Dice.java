package stigespillet;

public class Dice {
	private int nummer;

	public int showNumber() {
		int random = (int )(Math.random() * 6 + 1);
		return random;
	}

	public int throwDice() {
		int random = (int )(Math.random() * 6 + 1);
		this.nummer = random;
		return random;
	}
}