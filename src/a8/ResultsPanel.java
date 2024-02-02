package a8;

import java.awt.GridLayout;

import javax.swing.*;

import a8.RPS;
import a8.ResultsPanel;

@SuppressWarnings("serial")
public class ResultsPanel extends JPanel {
	
	private JLabel humanChoice = new JLabel("Human Choice: ");
	private JLabel computerChoice = new JLabel("Computer Choice: ");
	private JLabel winner = new JLabel("Winner: ");
 
	// The results panel clearly communicate the choices made by the 
	// player and the computer and show who won. You will likely need
	// some instance variables so the updateResults method can access
	// parts of the panel to update them. A layout will help arrange
	// these parts of the panel.
    public ResultsPanel() {
    	super();
    	add(humanChoice);
    	add(computerChoice);
    	add(winner);
    	this.setLayout(new GridLayout(3, 1, 10, 10));
    	// This needs to be implemented
    }
    
    public void updateResults(RPS human, RPS computer, String winner) {
    	if (human == RPS.ROCK)
    		humanChoice.setText("Human Choice: Rock");
    	if (human == RPS.PAPER)
        	humanChoice.setText("Human Choice: Paper");
    	if (human == RPS.SCISSORS)
        	humanChoice.setText("Human Choice: Scissors");
    	if (computer == RPS.ROCK)
        	computerChoice.setText("Computer Choice: Rock");
    	if (computer == RPS.PAPER)
        	computerChoice.setText("Computer Choice: Paper");
    	if (computer == RPS.SCISSORS)
        	computerChoice.setText("Computer Choice: Scissors");
    	this.winner.setText("Winner: " + winner);
    }
    
    // Example test code to test this frame
    public static void main(String[] args) {
        JFrame testFrame = new JFrame("test results");
        
        // Code similar to the following can be used to test
        // this panel on its own. You can create a panel
        // and call methods on it without worrying
        // how the other panels interact with it.
        ResultsPanel testPanel = new ResultsPanel();
        testPanel.updateResults(RPS.ROCK, RPS.PAPER, "computer");
        
        testFrame.pack();
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setVisible(true);
    }
    
}
