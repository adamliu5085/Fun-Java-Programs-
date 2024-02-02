package a6;

// CS 1410 A6. ADAM LIU. 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Chatter {	
	private String greeting;
	private String goodbye;
	private String responsesPath;
	
    /**
     * The constructor builds a Chatter object, with 
     * set greeting, goodbye, and potential response phrases.
     * @param greeting a fixed String greeting
     * @param goodbye a fixed String goodbye
     * @param responsesPath the filename for a file with possible chatbot responses
     */
    public Chatter(String greeting, String goodbye, String responsesPath) {
    	this.greeting = greeting;
    	this.goodbye = goodbye;
    	this.responsesPath = responsesPath;
    }
    
    /**
     * Loads lines from a file into a chatbot response map. 
     * The lines have the format of a word followed by
     * a response for the word. The word should be all lowercase and 
     * the response can be any String.
     *           
     * If the file is not found, prints an error and returns an 
     * empty HashMap. Do not use a throws to send the error somewhere
     * else. Deal with it in this method.
     * 
     * @param filePath the String path to the file
     * @return a HashMap with each word as a key and response as a value
     */
    private static HashMap<String, String> loadResponsesFromFile(String filePath) {
    	HashMap<String, String> responseMap = new HashMap<>();
    	try {
    		File responsesFile = new File(filePath);
    		Scanner fileScanner = new Scanner(responsesFile); // Create Scanner to scan through the lines.
    		while (fileScanner.hasNextLine()) {
    			String singleLine = fileScanner.nextLine();
    			Scanner lineScanner = new Scanner(singleLine); // Creates a new scanner to scan through the words in the line.
    			while(lineScanner.hasNext()) {
	    			String tempKey = ""; // Key to add from each line
	    			String tempValue = ""; //Value to add from each line
	    			tempKey = lineScanner.next();
	    			while (lineScanner.hasNext()) {
	    				tempValue += lineScanner.next() + " "; //Builds up a response
	    			}
	    			tempValue = tempValue.substring(0, tempValue.length()- 1); //Remove the unnecessary whitespace at the end. 
	    			responseMap.put(tempKey, tempValue); //Adds the key and value to the map.
    			}
    			lineScanner.close();
    		}
    		fileScanner.close();
    	} catch (FileNotFoundException e) { // Catches an exception if the file is not found.
    		System.out.println("File not Found");
    	}
    	return responseMap; // Returning the built up map.
    }
    
    /**
     * Gives the single, set greeting phrase.
     * 
     * @return the greeting String.
     */
    public String sayHello() {
    	return greeting; // greeting getter
    }
    
    /**
     * Gives the single, set goodbye phrase.
     * 
     * @return the goodbye String.
     */
    public String sayGoodbye() { // Goodbye getter.
    	return goodbye;
    }
     
    /**
     * Picks a response phrase based on userPhrase.
     * This method compares every word in userPhrase with 
     * the keys in the map of words and responses. If the map
     * has the key, than add that response to a list of possible
     * responses. After examining all the words in the userPhrase,
     * choose one of responses randomly. You can use the Random class
     * nextInt(upperbound) here, but the form nextInt(lower, upper) is
     * not supported by the autograder.
     * 
     * Provides a default response if no match is found. The default
     * response should be "Please tell me more about " followed by the 
     * last word in the userPhrase. Assume there is at least one word in the userPhrase.
     * 
     * @param userPhrase a sentence of lower-case words and no punctuation. Assume there is at least one word.
     * @return a response that matches one of the words in userPhrase or a default response.
     */
    public String respondToPhrase(String userPhrase) {
    	// Get the words from the userPhrase
    	ArrayList<String> similarWords = new ArrayList<>(); //Creates a new array list to store the similar words found in the map and the user response. 
    	Scanner phraseScan = new Scanner(userPhrase); //Scanner to scan through the user words.
    	String checkWord = ""; // Check and store each word here.
    	while (phraseScan.hasNext()) {
    		checkWord = phraseScan.next();
    		if (loadResponsesFromFile(responsesPath).containsKey(checkWord)) { //Calls the private method to see if the contents contain each word. 
    			similarWords.add(checkWord); // If true add to the array list.
    		}
    	}
    	phraseScan.close();    	
    	if (similarWords.size() == 0) {
    		return "Please tell me more about " + checkWord; //Default response to no similar words. 
    	}
    	else {
    		Random generator = new Random(); // Generates the random number to choose a response in the map. 
    		int randomIndex = generator.nextInt(similarWords.size());
    		String randomWordFromSimilarList = similarWords.get(randomIndex);
    		return loadResponsesFromFile(responsesPath).get(randomWordFromSimilarList); // return the chatbot response.
    	}  		
    }
    
    public static void main(String[] args) { // To test the methods as I code.
    	System.out.println(loadResponsesFromFile("src/a6/responses.txt"));
    }
    
}