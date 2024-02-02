//CS1410 Assignment 1: CS Biography Maker written by ADAM LIU"

public class A1 {
	
	//This method will take in the current year and past year, outputting the difference between them. 
	public static int calculateYearsAgo(int currentYear, int pastYear)
	{
		// Subtract the current year from the past year to find its difference. 
		int yearDifference = currentYear - pastYear;
		return yearDifference;
	}
	
	// This method will take in parameters being a persons name, their birth year, the current year, and their achievement.
	//It will then concatenate the information to return a short biography. 
	public static String biographyMaker(String name, int currentYear, int birthYear, String famousAchievement)
	{
		// Created a variable names biography to concatenate all the parameter information with some static text that I chose, this is so I can piece together the biography line. 
		//Then returning the biography variable as a string. 
		String fullBiography = name + " was born " + calculateYearsAgo(currentYear, birthYear) + " years ago. " + name + " is famous for " + famousAchievement + "."; 
		return fullBiography;
		
	}

	// Main method that will call the BiographyMaker methods to output the two individuals biography. 
	public static void main(String[] args) 
	{
		// Testing the calculateYearsAgo method below
		//System.out.println(calculateYearsAgo(2000, 1950));
		
		//Now calling the biographyMaker methods below. 	
		// https://thebestschools.org/magazine/most-influential-computer-scientists/
		System.out.println(biographyMaker("Vinton G. Cerf", 2022, 1943, "his contributions to creating the internet, while working at DARPA"));
		// https://president.yale.edu/biography-grace-murray-hopper
		System.out.println(biographyMaker("Grace Hopper", 2022, 1906, "her contributions twoard creating the compiler, and her ironic story of debugging"));
		System.out.println();
		System.out.println("Hello World!");
	}

}