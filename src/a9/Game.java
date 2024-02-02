package a9;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;
import javax.swing.JLabel;

/**
 * A top-level panel for playing a game similar to Plants Vs Zombies.
 * 
 * This panel is primarily responsible for coordinating the various aspects of
 * the game, including: - Running the game step-by-step using a timer - Creating
 * and displaying other components that make up the game - Creating new plants
 * and/or zombies, when necessary
 * 
 * @author Travis Martin and David Johnson
 * @author Michael Wadley and Adam Liu
 */
@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener, MouseListener {
	// Our theme is based on the game: Clash Royale, with strong and weak towers
	// acting as our
	// stationary plant objects, and hog riders and rage booster hogs acting as our
	// moving zombie objects.
	private static final int NUM_ROWS = 5;
	private static final int NUM_COLS = 7;
	private static final int GRID_BUFFER_PIXELS = 20;
	private static final int CELL_SIZE = 75;
	private static final int STEP_TIME = 20;
	protected static boolean gameRunning = true;
	private Random generator = new Random(); // Creates a random generator.
	private Timer timer = new Timer(STEP_TIME, this);
	private int ticker = 0;
	private int elixirCount = 80; // Initializes the elixir treasury to a balance of 65 to place the first 5
									// plants and start with a balance of 20.
	private JLabel elixir = new JLabel("Elixir Treasury: $" + elixirCount);
	private JRadioButton weakPlantButton;
	private JRadioButton regularPlantButton;
	private BufferedImage gameOverImage;
	private BufferedImage elixirImage;

	/**
	 * This panel is responsible for displaying plants and zombies, and for managing
	 * their interactions.
	 */
	private ActorDisplay actorDisplay = new ActorDisplay(NUM_COLS * CELL_SIZE + GRID_BUFFER_PIXELS * 2,
			NUM_ROWS * CELL_SIZE + GRID_BUFFER_PIXELS * 2);

	/**
	 * Creates the game panel with an actorDisplay component, and adds starter
	 * plants to the left side of the screen.
	 */
	private Game() {
		navBar();
		add(actorDisplay);

		// This layout causes all elements to be stacked vertically
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// The timer calls the actionPerformed method every STEP_TIME milliseconds
		timer.start();

		// This adds a plant to every row
		for (int i = 0; i < NUM_ROWS; i++) {
			addPlant(0, i);
		}
		addWeakPlant(1, 1);
		addWeakPlant(1, 3);
		// This adds an mouselistener to actorDisplay in order to get the coordinates
		// relative to the actor panel.
		actorDisplay.addMouseListener(this);
	}

	/**
	 * Adds a plant to the official game grid & display panel.
	 */
	private void addPlant(int col, int row) {
		// The magic numbers below define various hardcoded plant properties
		Plant plant = new Plant(gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5,
				"src/a9/Animal-Icons/tower-icon.png", 150, 5, 2);
		// Check if the plant actually placed
		boolean wasPlaced = actorDisplay.addActor(plant);
		// If sprite was placed then remove the cost from the resource treasury and
		// update the resource label.
		if (wasPlaced)
			elixirCount -= Plant.ACTORCOST;
		elixir.setText("Elixir Treasury: $" + elixirCount);

	}

	/**
	 * Adds a WeakPlant to the official game grid & display panel.
	 */
	private void addWeakPlant(int col, int row) {
		// The magic numbers below define various hardcoded WeakPlant properties
		Plant weakPlant = new WeakPlant(gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5,
				"src/a9/Animal-Icons/weak-tower-icon.png", 150, 5, 1); // Calls a new weakPlant with new image and
																		// behavior.
		// Check if the plant actually placed
		boolean wasPlaced = actorDisplay.addActor(weakPlant);
		// If sprite was placed then remove the cost from the resource treasury and
		// update the resource label.
		if (wasPlaced)
			elixirCount -= WeakPlant.ACTORCOST;
		elixir.setText("Elixir Treasury: $" + elixirCount);
	}

	/**
	 * Adds a Zombie to the official game grid & display panel.
	 */
	private void addZombie(int col, int row) {
		// The magic numbers below define various hardcoded zombie properties
		actorDisplay.addActor(new Zombie(gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5,
				"src/a9/Animal-Icons/hog-rider.png", 100, 50, -1, 10));
	}

	/**
	 * Adds a RageBoostZombie to the official game grid & display panel.
	 */
	private void addRageZombie(int col, int row) {
		// The magic numbers below define various hardcoded RageBoostZombie properties
		actorDisplay.addActor(new RageBoostZombie(gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5,
				"src/a9/Animal-Icons/hog-horde-icon.png", 100, 30, -2, 15)); // Calls a new rageZombie with new image
																				// and behavior.
	}

	/**
	 * Create a navigation bar panel that contains a resource count and buttons to
	 * select the plant type.
	 */
	private void navBar() {
		// Create a JPanel and set background color and layout.
		JPanel navBar = new JPanel();
		navBar.setBackground(new Color(237, 223, 170));
		navBar.setLayout(new BorderLayout(10, 10));
		// Creates the resource panel to hold our elixirCount and elixir icon.
		JPanel resourcePanel = new JPanel();
		resourcePanel.setLayout(new FlowLayout(0, 10, 3));
		resourcePanel.setBackground(new Color(237, 223, 170));
		try {
			elixirImage = ImageIO.read(new File("src/a9/Animal-Icons/elixir-icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Adds the elixirIcon and label to the resourcePanel.
		JLabel elixirIcon = new JLabel(new ImageIcon(elixirImage));
		resourcePanel.add(elixirIcon);
		elixir.setForeground(Color.BLACK);
		resourcePanel.add(elixir);
		// Creates a new panel for the character plant buttons.
		JPanel actorSelection = new JPanel();
		actorSelection.setBackground(new Color(237, 223, 170));
		actorSelection.setLayout(new FlowLayout(0, 25, 10));
		// Creates weakPlant object button.
		weakPlantButton = new JRadioButton("Weak Tower:  $" + WeakPlant.ACTORCOST);
		weakPlantButton.setBackground(new Color(237, 223, 170));
		weakPlantButton.setForeground(Color.BLACK);
		// Creates regularPlant object button.
		regularPlantButton = new JRadioButton("Normal Tower:  $" + Plant.ACTORCOST);
		regularPlantButton.setBackground(new Color(237, 223, 170));
		regularPlantButton.setForeground(Color.BLACK);
		// Adds the buttons to a buttonGroup and adds them to a panel.
		regularPlantButton.setSelected(true);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(weakPlantButton);
		buttonGroup.add(regularPlantButton);
		actorSelection.add(regularPlantButton);
		actorSelection.add(weakPlantButton);
		navBar.add(resourcePanel, BorderLayout.WEST);
		navBar.add(actorSelection, BorderLayout.EAST);
		// Adds the navbar Panel to our game panel.
		this.add(navBar);
	}

	/**
	 * Converts a row or column to its exact pixel location in the grid.
	 */
	private int gridToPixel(int rowOrCol) {
		return rowOrCol * CELL_SIZE + GRID_BUFFER_PIXELS;
	}

	/**
	 * The inverse of gridToPixel
	 */
	private int pixelToGrid(int xOrY) {
		return (xOrY - GRID_BUFFER_PIXELS) / CELL_SIZE;
	}

	/**
	 * Returns an empty JLabel that uses the overridden paintComponent method to
	 * draw the background.
	 *
	 */
	private JLabel gameOver() {
		try {
			gameOverImage = ImageIO.read(new File("src/a9/game-over.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel gameOverScreen = new JLabel();
		return gameOverScreen;
	}

	/**
	 * Create, start, and run the game.
	 */
	public static void main(String[] args) {
		JFrame app = new JFrame("Clash Royale - PvZ edition");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.add(new Game());
		app.pack();
		app.setVisible(true);
	}

	/**
	 * Overrides the paint component of game panel in order to paint a background
	 * that appears at the end of the game.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(gameOverImage, 0, 0, this);
	}

	/**
	 * Overrides the mouseClicked component to acquire x and y coordinates, and
	 * translate them to the respected grid territory.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (gameRunning) {
			// Get the x and y coordinate for the mouseClick event.
			int x = e.getX();
			int y = e.getY();
			// Add plant object to the respected grid territory that was clicked on.
			if (regularPlantButton.isSelected() && elixirCount >= Plant.ACTORCOST)
				addPlant(pixelToGrid(x), pixelToGrid(y));
			if (weakPlantButton.isSelected() && elixirCount >= WeakPlant.ACTORCOST)
				addWeakPlant(pixelToGrid(x), pixelToGrid(y));
		}
	}

	/**
	 * Executes game logic every time the timer ticks.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		actorDisplay.step();
		int randRow = generator.nextInt(NUM_ROWS);
		int randCol = generator.nextInt(NUM_COLS);
		// Randomly add a new Zombie at a fast interval.
		if (generator.nextInt(85) == 0)
			addZombie(7, randRow);
		// Randomly add a RageBoostZombie at a slower interval.
		if (generator.nextInt(325) == 0)
			addRageZombie(7, randRow);
		// Add elixir to the treasury and update the labels roughly every second.
		ticker++;
		if (ticker % 30 == 0) {
			elixirCount++;
			elixir.setText("Elixir Treasury: $" + elixirCount);
		}
		// If the game is over, freeze it and display a game over message.
		if (!gameRunning) {
			timer.stop();
			removeAll();
			add(gameOver());
			setBackground(new Color(237, 223, 170));
			repaint();
		}
	}

	// Unused overrides from implementing mouseListener.
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}