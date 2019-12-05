import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameViews {

	// CREATE GUI ELEMENTS
	public JPanel panel;
	public JPanel panel1;
	public JFrame window;
	public JButton[][] squares;
	public JButton reset;
	public JTextField text;
	public ImageIcon imageIcon;
	public Image image;

	// SETTERS AND GETTERS

	public GameViews() {

		// CREATE THE PANEL
		panel = new JPanel(new GridLayout(4, 3, 5, 5));
		panel.setPreferredSize(new Dimension(600, 600));
		panel.setBackground(Color.BLACK);

		// INSTANTIATE THE SQUARES AND CLICKED
		squares = new JButton[3][3];

		// ADD BUTTONS TO THE PANEL AND SET CLICKED TO FALSE
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares.length; j++) {
				squares[i][j] = new JButton();
				squares[i][j].setBackground(Color.WHITE);
				panel.add(squares[i][j]);
			}
		}

		// CREATE THE RESET BUTTON AND ADD TO THE PANNEL
		reset = new JButton("RESET");
		reset.setBackground(Color.WHITE);
		panel.add(reset);

		// CREATE TEXT FIELD
		text = new JTextField("It is your turn, you are O's");
		text.setHorizontalAlignment(0);
		panel.add(text);

		// ADD THE PANEL TO A WINDOW AND MAKE IT VISIBLE
		window = new JFrame("Tic-Tac-Toe");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(panel);
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public ImageIcon resizeImage(ImageIcon imageIcon, int width, int height) {
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		return imageIcon;
	}

}
