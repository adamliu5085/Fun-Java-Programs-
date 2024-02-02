package a8;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This is the main class to create a GUI for playing
 * Rock Paper Scissors (RPS).
 * 
 * This GUI has 3 main functionalities:
 * 1. A panel for toggling the type of computer opponent. The
 *    computer opponent is the person who the user of this GUI
 *    will be playing against, and different opponents may use
 *    different strategies for playing their moves.
 * 2. A panel for displaying the results of each round of RPS.
 *    This panel displays the move that the human made,
 *    the move that the computer made, and this round's winner.
 * 3. A panel allowing the human user to choose a RPS move. When
 *    this move is chosen, the currently selected computer opponent
 *    makes its move, and then the results are updated accordingly.
 */
@SuppressWarnings("serial")
public class Battle extends JFrame {
    OpponentPanel opponentPanel;
    ResultsPanel resultsPanel;
    UserPanel userPanel;   
    

    // This adds the three subpanels to the main game panel.
    public Battle() {
        super("Battle");
        opponentPanel = new OpponentPanel();
        resultsPanel = new ResultsPanel();
        userPanel = new UserPanel(this);
        add(opponentPanel);
        add(resultsPanel);
        add(userPanel);
        setLayout(new GridLayout(1, 3, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }
    
    // return a string saying whether the computer or human won or a tie.
    public static String pickWinner(RPS human, RPS computer) {
        if (human == RPS.PAPER && computer == RPS.PAPER)
        	return "Tie";
        if (human == RPS.ROCK && computer == RPS.ROCK)
        	return "Tie";
        if (human == RPS.SCISSORS && computer == RPS.SCISSORS)
        	return "Tie";
        if (human == RPS.ROCK && computer == RPS.SCISSORS)
        	return "Human";
        if (human == RPS.ROCK && computer == RPS.PAPER)
        	return "Computer";       	
        if (human == RPS.PAPER && computer == RPS.SCISSORS)
        	return "Computer";
        if (human == RPS.PAPER && computer == RPS.ROCK)
        	return "Human";      
        if (human == RPS.SCISSORS && computer == RPS.ROCK)
        	return "Computer";
        if (human == RPS.SCISSORS && computer == RPS.PAPER)
        	return "Human";
    	return "Unknown";
    }

    // Based on a the human choice, get the computer choice, pick a winner
    // and display the choices and result on the results panel.
    public void playGame(RPS humanChoice) {
    	RPS opponent = opponentPanel.getOpponent().getResponse(humanChoice);
    	String winner = pickWinner(humanChoice, opponent);
    	resultsPanel.updateResults(humanChoice, opponent, winner);
    }
 
    
    public static void main(String[] args) {
        Battle b = new Battle();
        b.setVisible(true);
    }
}