package a8;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This panel contains components & logic related to selecting
 * an opponent to play Rock Paper Scissors against.
 * 
 * This class contains one button for each possible RPS opponent,
 * and when a button is clicked then it switches the currently-
 * selected opponent as appropriate.
 */
@SuppressWarnings("serial")
public class OpponentPanel extends JPanel implements ActionListener {
    /**
     * The computer opponent that the human player should play RPS against.
     */
    private Opponent selectedOpponent = new RandomOpponent();
    private JRadioButton random = new JRadioButton("random opponent");
    private JRadioButton previousMove = new JRadioButton("copy previous move opponent");
    
    public OpponentPanel() {
        // Add this as a listener, so it can receive events
        // when either button is clicked.
        random.addActionListener(this);
        previousMove.addActionListener(this);
        
        // Add the two buttons to this panel, one above the other.
        add(random);
        add(previousMove);
        setLayout(new GridLayout(2, 1));
        
        // A ButtonGroup ensures that only one button is selected
        // at a time.
        ButtonGroup group = new ButtonGroup();
        random.setSelected(true);
        group.add(random);
        group.add(previousMove);
    }
    
    /**
     * Returns the currently selected Opponent that the human player
     * should play RPS against.
     */
    public Opponent getOpponent() {
        return selectedOpponent;
    }

    /**
     * Handles events from the JRadioButtons in order to switch
     * the currently selected opponent as appropriate.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == random) {
            selectedOpponent = new RandomOpponent();
        } else if (e.getSource() == previousMove) {
            selectedOpponent = new CopyPreviousOpponent();
        } else {
            System.out.println("UNKNOWN BUTTON");
        }
    }
}
