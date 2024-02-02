// A3 Wordle.java Code Written by ADAM LIU

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Wordle {

	/** Return an Array with all the lines in a file as separate indexes of the Array.
	 * 
	 * 
	 * @param fileName A string that is the name of a text file.
	 * @return An array of file lines as separate indexes. 
	 * @throws FileNotFoundException Throws this exception when the file string name cannot be found. 
	 */
	public static String[] wordsFromFile(String fileName) throws FileNotFoundException {
		File wordsFile = new File(fileName);
		Scanner fileScanner = new Scanner(wordsFile);
		String[] wordsArray = new String[fileScanner.nextInt()];
		int iterationCount = 0;
		while (fileScanner.hasNextLine()) {
			String tempWordLine = fileScanner.next();
			wordsArray[iterationCount] = tempWordLine;
			iterationCount++;
			}
 		fileScanner.close();
		return wordsArray;
	}
	
	
	/** Return A random string word from an array. 
	 * 
	 * 
	 * @param arrayOfStrings An array of string words. 
	 * @return A random string value from the parameter array of string words
	 */
	public static String pickRandomWord(String[] arrayOfStrings) {
		Random randomGenerator = new Random();
		int randomIndex = randomGenerator.nextInt(arrayOfStrings.length);
		String randomWord = arrayOfStrings[randomIndex];
		return randomWord;
	}
	
	
	/** Return a boolean value of true or false if a string word can be found in an array of Strings. 
	 * 
	 * 
	 * @param wordToFind The string value that is the word to search for in an array of strings.
	 * @param wordArray The array of strings from the text file. 
	 * @return True If the word can be found, false if the word cannot be found in the array parameter. 
	 */
	public static boolean wordInArray(String wordToFind, String[] wordArray) {
		for (String word : wordArray) {
			if (word.equals(wordToFind)) {
				return true;
			}
		}
		return false; 
	}
	
	/** Return a word that the User will input as a String if it meets the conditions of 1. Being a word from the text file, 2. Being 5 characters long. 
	 * 
	 * 
	 * @param stringArray  An array of strings to compare the user guess input word with.
	 * @return The users input as a string if it meets the conditions listed above. 
	 */
	public static String getUserGuess(String[] stringArray) {
		boolean status = true;
		String returnWord = "";
		Scanner userInput = new Scanner(System.in);
		while (status) {
			System.out.println("Please enter a 5 letter word from the word bank.");
			String tokenToCheck = userInput.next();
			boolean condition1 = (tokenToCheck.length() == 5);
			boolean condition2 = wordInArray(tokenToCheck, stringArray);
			if (condition1 && condition2) {
				returnWord = tokenToCheck;
				break;
			}
			else {
				System.out.println("Condition was not met.");
				System.out.println();
			}
		}
		return returnWord;
	}
	
	/** Return true if the character to search for can be found in the word parameter, if not return false.
	 * 
	 * 
	 * @param character any single character.
	 * @param anyWord and string word. 
	 * @return A boolean value true if the word contains the character, false if the word does not contain the character. 
	 */
	public static boolean letterInWord(char character, String anyWord) {
		for (int index = 0; index < anyWord.length(); index++) {
			if (anyWord.charAt(index) == character){
				return true;
			}
		}
		return false;
	}
	
	/** Print a word if the parameters are matching, if the letters do not match, replace with dashes, if the letters are in the wrong order, replace with lower case letters. 
	 * 
	 * 
	 * @param guessWord A word input by a use that meets all the parameters described previously.
	 * @param secretWord A randomly generated word from the text file in an array as described previously.
	 */
	public static void displayMatching(String guessWord, String secretWord) {
		String returnWordPieces = secretWord.toUpperCase();
		String displayBuildUp = "";
		if (guessWord.equals(secretWord)) {
			System.out.println(guessWord.toUpperCase());
		}
		else {
			
			for (int count = 0; count < 5; count ++) {
				if (letterInWord(guessWord.charAt(count), secretWord) && (guessWord.charAt(count) == secretWord.charAt(count))) {
					displayBuildUp = displayBuildUp + returnWordPieces.charAt(count);
				}
				else if (letterInWord(guessWord.charAt(count), secretWord) && !(guessWord.charAt(count) == secretWord.charAt(count))) {
					displayBuildUp = displayBuildUp + guessWord.charAt(count);
				}
				else
					displayBuildUp = displayBuildUp + '-';
		}
				System.out.println(displayBuildUp);
		}
	}
	
	/** Main function will call each function. First it will get a word bank from the existing file, if no file is read throw FileNotFoundExceptiom
	 * Then pick a random word to be the guessing word. Next set the guessing turns to 6 tries, then get the users input word and compare it to the randomly selected word.
	 * If the word is not found, then you may keep trying while you still have guesses left, if the word is found then print the word and a winning statement.
	 * If you run out of turns and did not sole the whole word, then you Fail! The right answer will then be revealed to you. 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			int turns = 0;
			String[] wordBank = wordsFromFile("words (1).txt");
			String randomWordToGuess = pickRandomWord(wordBank);
			while(turns < 6) {
				String usersGuess = getUserGuess(wordBank);
//				System.out.println(randomWordToGuess);
//				^^ To test the correct answer without actually trying to find it.
				displayMatching(usersGuess, randomWordToGuess);
				if (usersGuess.equals(randomWordToGuess)) {
					System.out.println("You Win!");
					break;
			}
				turns++;
			}
			if (turns > 5) {
				System.out.println("You ran out of guesses!");
				System.out.println("Your word was " + randomWordToGuess);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
		} catch (Exception e) {
			System.out.println("SOmething not right");
		}
	}
}