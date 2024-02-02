package a5;

/**
 * A DynamicArray behaves like an array of strings, except that it can grow and
 * shrink. It is indexed beginning with zero. When a DynamicArray is created, it
 * is empty. Methods are provided to add, get, set, and remove elements.
 * 
 * DO NOT MODIFY THIS FILE
 */
public class DynamicArray {

	private String[] data; // the backing array

	/**
	 * Creates an empty dynamic array.
	 */
	public DynamicArray() {
		data = new String[0];
	}

	/**
	 * Adds a string s to the dynamic array at index i. Prior
	 * elements are shifted up.
	 * 
	 * @param i the index
	 * @param s the String to insert at i
	 */
	public void add(int i, String s) {
		// Check for a legal index value
		if(i < 0 || i > data.length)
			throw new IndexOutOfBoundsException("Index " + i +
			        "is invalid for adding to this array with length " + data.length);

		// Adding a new value requires more space, so make a new array.
		String[] newData = new String[data.length + 1];

		// Copy the values up to the ith position
		for(int j = 0; j < i; j++) {
			newData[j] = data[j];
		}
		
		// Add in the new value
		newData[i] = s;

		// Copy and shift the values above the new value.
		for(int j = i + 1; j < newData.length; j++) {
			newData[j] = data[j - 1];
		}

		data = newData;
	}

	/**
	 * Adds string s to end of the dynamic array.
	 * 
	 * @param s a String to add to the end of the array
	 */
	public void add(String s) {
		add(data.length, s);
	}

	/**
	 * Returns the string stored at index i in the dynamic array.
	 * Throws an exception if index out of bounds.
	 * 
	 * @param i the index
	 * @return the string stored at index i
	 */
	public String get(int i) {
		if(i < 0 || i >= data.length)
			throw new IndexOutOfBoundsException("Index " + i +
			        "is invalid for this array with length " + data.length);
		return data[i];
	}

	/**
	 * Returns the number of elements in the dynamic array.
	 * 
	 * @return the number of elements
	 */
	public int size() {
		return data.length;
	}

	/**
	 * Changes the value of the string stored at index i in the dynamic array to
	 * s.
	 * Throws an exception if index out of bounds.
	 * 
	 * @param i the index
	 * @param s the String to put at location i
	 */
	public void set(int i, String s) {
		if(i < 0 || i >= data.length)
			throw new IndexOutOfBoundsException("Index " + i +
			        "is invalid for this array with length " + data.length);

		data[i] = s;
	}

	/**
	 * Removes the string at index i in the dynamic array.
	 * Throws an exception if index out of bounds. 
	 * @param i the index
	 */
	public void remove(int i) {
		if(i < 0 || i >= data.length)
			throw new IndexOutOfBoundsException("Index " + i +
			        "is invalid for this array with length " + data.length);

		// When a value is removed, less space is needed
		String[] newData = new String[data.length - 1];

		for(int j = 0; j < i; j++) {
			newData[j] = data[j];
		}

		for(int j = i; j < newData.length; j++) {
			newData[j] = data[j + 1];
		}

		data = newData;
	}

	/**
	 * Returns the dynamic array as a formatted string.
	 * 
	 * @return the formatted string
	 */
	public String toString() {
		String result = "[";
		if(size() > 0) {
			result += get(0);
		}
		for(int i = 1; i < size(); i++) {
			result += ", " + get(i);
		}
		return result + "]";
	}
	
	public static void main(String[] args) {
		DynamicArray d = new DynamicArray();
		d.add("David");
		d.add("Joe");
		System.out.println(d);
		System.out.println(d.get(0));
		d.set(1, "Mary");
		System.out.println(d.get(1));	
	}
}
