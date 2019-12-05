import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;

public class Computer implements Observer {
	private int turns;
	private boolean computersTurn;

	public Computer(int t, boolean c) {
		turns = t;
		computersTurn = c;
	}

	public int getTurns() {
		return turns;
	}

	public void setTurns(int turns) {
		this.turns = turns;
	}

	public boolean isPlayersTurn() {
		return computersTurn;
	}

	public void setPlayersTurn(boolean computersTurn) {
		this.computersTurn = computersTurn;
	}

	public void update(Observable o, Object arg) {
		// UPDATE COMPUTER
		turns = ((GameModel) o).getTurns();
		computersTurn = !((GameModel) o).isPlayersTurn();
	}

}
