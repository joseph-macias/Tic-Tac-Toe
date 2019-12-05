import java.awt.Color;
import java.awt.Image;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GameModel extends Observable {
	private int turns;
	private boolean playersTurn;
	private static final int WIN = 1;
	private static final int LOSE = 0;
	private static final int TIE = 2;
	private static final int GAME_IN_PROGRESS = 3;

	public GameModel(int t, boolean p) {
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

	public boolean addTurn() {
		turns = turns + 1;
		if (turns % 2 == 0) {
			playersTurn = true;
		} else {
			playersTurn = false;
		}
		setChanged();
		notifyObservers();

		return playersTurn;
	}

	public boolean checkForWin(GameViews view) {
		// CHECK HORIZONTAL
		if (checkNext(0, 0, 0, 1, view) && checkNext(0, 1, 0, 2, view))
			return true;
		else if (checkNext(1, 0, 1, 1, view) && checkNext(1, 1, 1, 2, view))
			return true;
		else if (checkNext(2, 0, 2, 1, view) && checkNext(2, 1, 2, 2, view))
			return true;

		// CHECK VERTICAL
		else if (checkNext(0, 0, 1, 0, view) && checkNext(1, 0, 2, 0, view))
			return true;
		else if (checkNext(0, 1, 1, 1, view) && checkNext(1, 1, 2, 1, view))
			return true;
		else if (checkNext(0, 2, 1, 2, view) && checkNext(1, 2, 2, 2, view))
			return true;

		// CHECK DIAGONAL
		else if (checkNext(0, 0, 1, 1, view) && checkNext(1, 1, 2, 2, view))
			return true;
		else if (checkNext(0, 2, 1, 1, view) && checkNext(1, 1, 2, 0, view))
			return true;
		else
			return false;
	}

	public boolean checkNext(int a, int b, int c, int d, GameViews view) {
		// CHECK IF THE CORRESPONDING BUTTONS HAVE THE SAME VALUES
		if (view.squares[a][b].getText().equals(view.squares[c][d].getText())
				&& !view.squares[a][b].getText().equals(""))
			return true;
		else
			return false;
	}

	public boolean checkForTie(int turns, boolean playerWins, boolean computerWins, GameViews view) {
		boolean isTie = false;
		// CHECK IF ALL TURNS HAVE BEEN MADE & NO ONE HAS WON
		if (turns == 10 && playerWins == false && computerWins == false) {
			isTie = true;
			// RESET THE TEXT TO SHOW A TIE
			view.text.setText("It's a tie.");
			view.text.setBackground(Color.YELLOW);
		}
		return isTie;
	}

	public JButton getRandomButton(GameViews view) {
		// GENERATE RANDOM BUTTON
		int i = (int) (Math.random() * 3);
		int j = (int) (Math.random() * 3);
		JButton button = view.squares[i][j];
		// MAKE SURE THAT BUTTON HAS NOT BEEN CLICKED
		while (button.getText() == "X" || button.getText() == "O") {
			i = (int) (Math.random() * 3);
			j = (int) (Math.random() * 3);
			button = view.squares[i][j];
		}
		return button;
	}

	public boolean playPlayersTurn(GameViews view, JButton button) {
		// SET IMAGE ICON
		ImageIcon imageIcon = new ImageIcon("images\\o.png");
		imageIcon = view.resizeImage(imageIcon, 200, 200);
		button.setIcon(imageIcon);
		// SET TEXT USED TO CHECK FOR WIN
		button.setText("O");
		// DISABLE BUTTON
		button.setEnabled(false);
		button.setBackground(Color.blue);
		// CHECK FOR WIN
		boolean playerWins = checkForWin(view);
		if (playerWins == true) {
			view.text.setBackground(Color.GREEN);
			view.text.setText("YOU WIN");
		}
		return playerWins;
	}

	public boolean playComputersTurn(GameViews view) {
		// GET RANDOM BUTTON
		JButton button = getRandomButton(view);
		// SET IMAGE ICON
		ImageIcon imageIcon = new ImageIcon("images\\x.png");
		imageIcon = view.resizeImage(imageIcon, 120, 120);
		button.setIcon(imageIcon);
		// SET TEXT USED TO CHECK FOR WIN
		button.setText("X");
		button.setEnabled(false);
		button.setBackground(Color.red);

		// CHECK FOR WIN
		boolean computerWins = checkForWin(view);
		if (computerWins == true) {
			view.text.setBackground(Color.RED);
			view.text.setText("You really lost to the computer...");
		}
		return computerWins;
	}

}
