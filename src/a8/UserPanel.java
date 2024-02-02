package a8;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class UserPanel extends JPanel implements ActionListener {
    private Battle parent;
    private JButton rock = new JButton("Rock");
    private JButton paper = new JButton("Paper");
    private JButton scissors = new JButton("Scissors"); 

    // This panel should have three buttons for 
    // rock, paper, or scissors. If a button is pushed,
    // then this choice should be send to parent.playGame()
    // where the winner is chosen and the results panel updated.
    public UserPanel(Battle parent) {
        super();
        // Store the Battle object so we can call playGame().
        this.parent = parent;
        rock.addActionListener(this);
        paper.addActionListener(this);
        scissors.addActionListener(this);
//		rock.setPreferredSize(new Dimension(2, 2));
//		paper.setPreferredSize(new Dimension(2, 2));
//		scissors.setPreferredSize(new Dimension(2, 2));
        add(rock);
        add(paper);
        add(scissors);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
	if (e.getSource() == rock) {
		parent.playGame(RPS.ROCK);
	}
	if (e.getSource() == paper) {
		parent.playGame(RPS.PAPER);
	}
    if (e.getSource() == scissors){
        parent.playGame(RPS.SCISSORS);
    }
}
}