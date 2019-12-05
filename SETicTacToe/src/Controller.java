import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Controller {

	private static int turns = 0;

	public static void main(String[] args) {
		buildGameViews();
	}

	public static void buildGameViews() {
		// CREATE GAME VIEW
		GameViews view = new GameViews();

		// CREATE GAME MODEL
		GameModel gameModel = new GameModel(turns, true);

		// CREATE ACTION LISTENERS FOR THE BUTTONS
		for (int i = 0; i < view.squares.length; i++) {
			for (int j = 0; j < view.squares.length; j++) {
				JButton button = view.squares[i][j];
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == button) {
							// DETERMINE WHO'S TURN
							boolean computersTurn = buildGameModel(turns, gameModel);
							// INCREMENT TURNS
							turns++;

							// CREATE BOOLEAN TO CHECK WHO WINS
							boolean playerWins = false;
							boolean computerWins = false;
							boolean isTie = false;

							// PLAY PLAYERS TURN
							if (computersTurn == false) {
								playerWins = gameModel.playPlayersTurn(view, button);
							}
							// INCREMENT TURNS
							turns++;

							// CHECK FOR TIE
							isTie = gameModel.checkForTie(turns, playerWins, computerWins, view);

							// MAKE IT COMPUTERS TURN
							computersTurn = buildGameModel(turns, gameModel);

							// PLAY COMPUTERS TURN AS LONG AS THE PLAYER HASN'T
							// WON
							if (computersTurn == true && playerWins == false && isTie == false) {
								computerWins = gameModel.playComputersTurn(view);
							}
							// CHECK IF THEIR IS A WINNER AND END GAME
							if (playerWins == true || computerWins == true || isTie == true) {
								endGame(view);
							}
						}
					}
				});
			}
		}

		// RESET GAME
		resetGame(view, gameModel);

	}

	public static boolean buildGameModel(int turns, GameModel gameModel) {
		// CREATE PLAYER AND COMPUTER
		Player player = new Player(turns, true);
		Computer computer = new Computer(turns, false);
		// ADD THE PLAYER
		gameModel.addObserver(player);
		gameModel.addObserver(computer);

		// ADD THE TURN
		gameModel.addTurn();

		return player.isPlayersTurn();

	}

	public static void endGame(GameViews view) {
		// DISABLE ALL BUTTONS
		for (int i = 0; i < view.squares.length; i++) {
			for (int j = 0; j < view.squares.length; j++) {
				view.squares[i][j].setEnabled(false);
			}
		}
	}

	public static void resetGame(GameViews view, GameModel gameModel) {
		// CREATE ACTION LISTENER FOR RESET BUTTON
		view.reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// CLEAR THE GRID AND MAKE EVERY BUTTON ENABLED
				for (int i = 0; i < view.squares.length; i++) {
					for (int j = 0; j < view.squares.length; j++) {
						view.squares[i][j].setIcon(null);
						view.squares[i][j].setText("");
						view.squares[i][j].setBackground(Color.WHITE);
						view.squares[i][j].setEnabled(true);
					}
				}
				// RESET THE TURNS SO THE PLAYER GOES FIRST
				turns = 0;

				// RESET TEXT FIELD
				view.text.setText("It is your turn, you are O's");
				view.text.setBackground(Color.WHITE);
			}
		});
	}

}
