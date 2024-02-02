//A2-String-Scanner_Arrays Written by: ADAM LIU

import java.util.Arrays;
import java.util.Scanner;

public class A2 {
	
	// This method takes a string word and determines if it is a palindrome returning true if it is, and false if it is not.  
	public static boolean isPalindrome(String anyWord) {
		int wordLength = anyWord.length();
		boolean whileStatus = true;
		boolean result = true;
		int count = 0;
		int backwardsCount = anyWord.length() -1;
		while (whileStatus) {
			if (anyWord.length() == 0) {
				return result;
			}
			if (anyWord.charAt(count) == anyWord.charAt(backwardsCount)) {
				count++;
				backwardsCount--;
				}
			else {
				whileStatus = false;
				result = false;
			}
			if (count > backwardsCount) {
				whileStatus = false;
			}
		}
		return result;
	}
	
	// This method takes in a string phrase and will count up how many tokens there are in in phrase, returning the number of tokens that was counted. 
	public static int countTokens(String phrase) {
		int count = 0;
		boolean status = true;
		Scanner scanner = new Scanner(phrase);
		while (status)
			if (scanner.hasNext()) {
				scanner.next();
				count += 1;
			}
			else 
				status = false;
		return count;
	}

	// This method takes in a string of words and or numbers, it counts up all the numbers and return true if there are more even numbers than odd, if not, false is returned. 
	public static boolean moreEvenThanOdd(String scannerString) {
		int evenCount = 0;
		int oddCount = 0;
		Scanner scanner = new Scanner(scannerString);
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
            	if (scanner.nextInt() % 2 == 0) {
            		evenCount++;       
            }
            	else
            		oddCount++;
            }
            else
            	scanner.next();
            }     
        if (evenCount > oddCount)
        	return true;
        else
        	return false;
	}
	
	
	//This method takes in a string of lowercase words as parameters, string returning the words combined with each first letter uppercase, in correct camelCase form. 
	public static String camelCase(String lowerWords) {
		String camelCase = "";
		Scanner scanner = new Scanner(lowerWords);
		while (scanner.hasNext()) {
			String tempString = scanner.next();
			camelCase = camelCase + (Character.toUpperCase(tempString.charAt(0))  + tempString.substring(1, tempString.length()));
		}
		String finalCase = Character.toLowerCase(camelCase.charAt(0)) + camelCase.substring(1, camelCase.length());
		return finalCase;
		}
	
	// This method takes in an array of numbers and takes the average of them all, returning the average value. 
	public static double average(int[] arrayOfNums) {
		double sumOfNums = 0;
		double numCount = 0;
		for (int number : arrayOfNums) {
			numCount++;
			sumOfNums += number;
		}
		double result = sumOfNums / numCount;
		return result;
	}
	
	// This method takes in an array of numbers from 0-9 (inclusive), and returns the number of times each number shows up at the index of an array.
	public static int[] frequencyCount(int[] arrayOfNums) {
		int[] appearancesIndex = new int[10];
		for (int count = 0; count < 10; count++) {
			int tempNumCount = 0;
			for (int number : arrayOfNums) {
				if (number == count) {
					tempNumCount++;
				}
			}
			appearancesIndex[count] = tempNumCount;
		}
		return appearancesIndex;
	}
	
	public static void main(String[] args) {
		System.out.println("Now testing isParlindrome method.");
		System.out.println("Testing with the word (abba) String parameter. Expecting True. Computed: " + isPalindrome("abba"));
		System.out.println("Testing with the word (parlindrome) String parameter. Expecting False. Computed: " + isPalindrome("parlindrome"));
		System.out.println("Testing with (empty string). Expecting True. Computed: " + isPalindrome(""));
		System.out.println();
		System.out.println("Now testing countTokens method.");
		System.out.println("Testing with the phrase (this is a test) String Parameter. Expecting 4. Computed: " + countTokens("this is a test"));
		System.out.println("Testing with the phrase (empty string) String Parameter. Expecting 0. Computed: " + countTokens(""));
		System.out.println();
		System.out.println("Now testing moreEvenThanOdd method.");
		System.out.println("Testing with (1 2 3) String parameter. Expecting False. Computed: " + moreEvenThanOdd("1 2 3"));
		System.out.println("Testing with (4 5 22 dog cat mouse 4 2). Expecting True. Computed: " + moreEvenThanOdd("4 5 22 dog cat mouse 4 2"));
		System.out.println();
		System.out.println("Now testing camelCase method.");
		System.out.println("Testing with (first letter in these words) String Parameter. Expecting: firstLetterInTheseWords. Computed: " + camelCase("first letter in these words"));
		System.out.println("Testing with (smash these words and make them uppercase) String Parameter. Expecting: smashTheseWordsAndMakeThemUppercase. Computed: " + camelCase("smash these words and make them uppercase"));
		System.out.println();
		System.out.println("Now testing average method.");
		System.out.println("Tesitng with {1, 2, 3} array parameter. Expecting 2.0. Computed: " + average(new int[]{1, 2, 3}));
		System.out.println("Tesitng with {50, 60 , 4, -1} array parameter. Expecting 28.25. Computed: " + average(new int[]{50, 60, 4, -1}));
		System.out.println("Tesitng with {32} array parameter. Expecting 32.0. Computed: " + average(new int[]{32}));
		System.out.println();
		System.out.println("Now testing frequencyCount method.");
		System.out.println("Testing with {4, 2, 3, 5, 4, 4, 3, 2, 9, 1, 3, 4, 5, 2, 1, 5 } array parameter. Expecting [0,2,3,3,4,3,0,0,0,1]. Computed: " + Arrays.toString(frequencyCount(new int[]{4, 2, 3, 5, 4, 4, 3, 2, 9, 1, 3, 4, 5, 2, 1, 5 })));
		System.out.println("Testing with {0, 1, 2, 3, 4, 5, 6, 7, 8, 9 } array parameter. Expecting [1, 1, 1, 1, 1, 1, 1, 1, 1, 1]. Computed: " + Arrays.toString(frequencyCount(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9 })));
	}

}
