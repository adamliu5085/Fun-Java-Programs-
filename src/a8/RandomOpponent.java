package a8;

import java.util.Random;

// Choose one enum from RPS randomly. See the lecture on enums 
// for an example of choosing a random item from an enum.
// RPS is defined in RPS.java.
public class RandomOpponent implements Opponent {
	
	@Override
	public RPS getResponse(RPS humanMove) {
		Random generator = new Random();
		int randomChoice = generator.nextInt(3);
		return RPS.values()[randomChoice];
	}
}
