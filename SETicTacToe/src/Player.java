import java.util.Observable;
import java.util.Observer;

public class Player implements Observer {
	private int turns;
	private boolean playersTurn;

	public Player(int t, boolean p) {
		turns = t;
		playersTurn = p;
	}

	public int getTurns() {
		return turns;
	}

	public void setTurns(int turns) {
		this.turns = turns;
	}

	public boolean isPlayersTurn() {
		return playersTurn;
	}

	public void setPlayersTurn(boolean playersTurn) {
		this.playersTurn = playersTurn;
	}

	public void update(Observable o, Object arg) {
		// UPDATE PLAYER
		turns = ((GameModel) o).getTurns();
		playersTurn = ((GameModel) o).isPlayersTurn();
	}

}
