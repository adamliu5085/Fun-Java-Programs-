package a5;

//ADAM LIU CS 1410 A5

import java.util.Arrays;
   
public class DoublingDynamicArray {

	private String[] data;   // the backing array
	private int virtualArrayLength; // the number of elements in the dynamic array
	
	/**
	 * Creates an empty dynamic array with room to grow.
	 * DO NOT MODIFY THIS METHOD
	 */
	public DoublingDynamicArray() {
		// Start with a few available spaces. Do not change this.
		data = new String[8];
		// But the virtual array is empty.
		virtualArrayLength = 0;
	}
	
	/**
	 * Returns the number of elements in the dynamic array.
	 * @return the number of elements
	 */
	public int size() {
		return virtualArrayLength;
	}
	

	/**Appends the string s to the end of the dynamic array.
	 * @param s a string to append on the end of the dynamic array. 
	 */
	public void add(String s) {
		add(this.size(), s);
		// FILL IN
		// HINT: Call the other add method.
	}
	
	/**Throw an exception if the index to add is not valid. If the array is already full,
	 * double its size from a new array from the backing. Then shift all existing values up
	 * until the index is reached then add the new string at the corresponding index.
	 * @param i the index of the new input 
	 * @param s a string which is the new input
	 */
	public void add(int i, String s) {
		// FILL IN
		if(i < 0 || i > this.size()) {
			throw new IndexOutOfBoundsException("Index " + i + "is invalid for this array with length " + data.length);
		}
		if (this.size() == data.length) {
			String[] doubledArray = new String[data.length * 2];
			for (int index = 0; index < this.size(); index++) {
				doubledArray[index] = data[index];
			}
			data = doubledArray;
		}
		for (int index = this.size(); index > i; index--) {
			data[index] = data[index - 1];
		} 
		data[i] = s;
		virtualArrayLength++;
	}
	
	/** When called, remove the indexed value at the index of the parameter 
	 * by shifting all values down by one. 
	 * @param i The index of the value to remove
	 */
	public void remove(int i) {	
		// FILL IN
		if(i < 0 || i >= this.size()) {
			throw new IndexOutOfBoundsException("Index " + i + "is invalid for this array with length " + data.length);
		}
		for (int index = i; index < this.size(); index++) {
			data[index] = data[index + 1];
		}
		virtualArrayLength--;
	}
	

	/** Return the value at the index in the parameter from the dynamic array.
	 * @param i the index to return from the dynamic array.
	 * @return The indexed value of the dynamic array.
	 */
	public String get(int i) {
		// FILL IN
		if(i < 0 || i >= this.size()) {
			throw new IndexOutOfBoundsException("Index " + i + "is invalid for this array with length " + data.length);
		}
		return data[i];
	}
	
	
	/** Replace the element of the index with the string from the parameter. 
	 * @param index The index to set in the dynamic array
	 * @param string The string value to add into the dynamic array.
	 */
	public void set(int index, String string) {
		// FILL IN
		if(index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException("Index " + index + "is invalid for this array with length " + data.length);
		}
		else {
			data[index] = string;			
		}
	}
	
	/**
	 * Returns a formatted string version of this dynamic array.
	 * 
	 * @return the formatted string
	 */
	public String toString() {
		String result = "[";
		if(this.size() > 0) 
			result += get(0);
		
		for(int i = 1; i < size(); i++) 
			result += ", " + get(i);
		return result + "] backing size: " + data.length;
	}
	
	public static void main(String[] args) {
		DoublingDynamicArray array = new DoublingDynamicArray();
		array.add("num1");
		System.out.println(array.toString());
		String[] largeArray = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	    DoublingDynamicArray array2 = new DoublingDynamicArray();
	    for (String elem : largeArray) {
	         array2.add(elem);
	         System.out.println(array2.toString());
	         System.out.println(array2.size());
	    }
	    array2.remove(0);
	    System.out.println(array2.toString());	        
	}
}