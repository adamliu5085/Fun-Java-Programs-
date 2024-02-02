package a9;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ActorDisplay extends JPanel {
	/** Contains all plants and zombies in this game. */
	private ArrayList<Actor> actors = new ArrayList<>();
	private BufferedImage background;

	/**
	 * Creates a canvas upon which all actors will live.
	 * 
	 * @param colPixels the number of pixels that this panel is wide
	 * @param rowPixels the number of pixels that this panel is high
	 */
	public ActorDisplay(int colPixels, int rowPixels) {
		setPreferredSize(new Dimension(colPixels, rowPixels));
		// Load the background image for the actorDisplay panel.
		try {
			background = ImageIO.read(new File("src/a9/background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Adds an actor to the master list of actors ONLY IF the provided actor is not
	 * colliding with any of the existing actors.
	 * 
	 * @param actor the object to add
	 * @return false if something prevents the actor from being added, true
	 *         otherwise
	 */
	public boolean addActor(Actor actor) {
		if (actor.isCollidingAny(actors)) {
			return false;
		}
		actors.add(actor);
		return true;
	}

	/**
	 * This overrided method draws the details of this particular panel, including
	 * all actors that are contained within.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Adds our background to the actorDisplay panel.
		g.drawImage(background, 0, 0, this);
		for (Actor actor : actors) {
			actor.draw(g);
		}
	}

	/**
	 * Executes all of the actor logic that happens in one turn, including moving
	 * actors, checking for collisions, managing attacks, and more.
	 */
	public void step() {
		// Increment actor cooldowns.
		for (Actor actor : actors) {
			actor.update();
		}
		// Allow all actors to interact with all other actors.
		// This is where attacks, healing, etc happen.
		for (Actor actor : actors) {
			for (Actor other : actors) {
				actor.actOn(other);
			}
		}
		// Remove plants and zombies with low health
		ArrayList<Actor> nextTurnActors = new ArrayList<>();
		for (Actor actor : actors) {
			if (actor.isAlive())
				nextTurnActors.add(actor);
			else
				actor.removeAction(actors); // Execute any special effects for dead actors
		}
		actors = nextTurnActors;
		// Move the (alive) actors that are not colliding.
		for (Actor actor : actors) {
			if (!actor.isCollidingAny(actors)) {
				actor.move();
			}
		}
		// Redraw the scene.
		repaint();
	}
}