package a5;

//ADAM LIU CS 1410 A5
 
public class StringSet {
	/**
	 * A backing array to store all the data in this set. If you need a set
	 * that can add items very very quickly, consider using a DoublingDynamicArray
	 * instead.
	 */
    private DynamicArray data;
	
    /**
     * This constructor creates a new empty set.
     */
    public StringSet() {
        data = new DynamicArray();
    }
    
    /**
     * Inserts the provided element in this set. If the provided element
     * is already in the set, inserting has no effect. Otherwise, this
     * adds the element to the group of elements already contained in this set.
     * 
     * @param e a non-null element to insert
     * @return true if this set contains e, otherwise false
     * @throws IllegalArgumentException if e is null
     */
    public void insert(String e) {
        if (e == null)
            throw new IllegalArgumentException("Cannot insert a null element");
        if (!contains(e)) {
            data.add(e);
        }
    }
    
    /**
     * Tests whether the provided element is contained in this set
     * 
     * @param e a non-null element to check for containment
     * @return true if this set contains e, otherwise false
     * @throws IllegalArgumentException if e is null
     */
    public boolean contains(String e) {
        if (e == null)
            throw new IllegalArgumentException("Cannot call contains with a null element");
        
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(e)) {
                return true;
            }
        }
        return false;
    }
    
    /**Returns the size of the string set object.
     * @return the size of the string set.
     */
    public int size() {
        return this.data.size();
    }  
    

    /** Remove the string provided in the parameter from the string set.
     * @param e A string value to be removed from the set.
     */
    public void remove(String e) {
    	// FILL IN
        if (e == null)
            throw new IllegalArgumentException("Cannot call contains with a null element");
    if (this.contains(e)) {
    	for (int i = 0; i < data.size(); i++) {
    		if (data.get(i).equals(e)) {
    			data.remove(i);
    			}
    		}
    	}
    }
    
    
    /** Return a new string set of all string with all strings only added once into the new returned. 
     * @param other A string set to be the union on to the evoked object. 
     * @return A new string with all the strings contained in the evoked object and parameter with no duplicates.
     */
    public StringSet union(StringSet other) {
    	StringSet returnSet = new StringSet();	
    	for (int i = 0; i < this.size(); i++) {
    		returnSet.insert(this.data.get(i));
    	}
    	for (int i = 0; i < other.size(); i++) {
    		returnSet.insert(other.data.get(i));
    	}
    	return returnSet;
    }

  
    /** Return a new list that contains the strings that can be found in both the evoked object and the parameter.
     * @param other A string set type that holds strings to check and add the similar ones.
     * @return A new string set with all strings found in both evoked object and parameter string set. 
     */
    public StringSet intersection(StringSet other) {
    	StringSet returnSet = new StringSet();	
    	for (int i = 0; i < other.size(); i++) {
    		if ((this.contains(other.data.get(i)))) {
    			returnSet.insert(other.data.get(i)); 
    		}
    	}
    	return returnSet;    	
    }

    
    /**
     * Returns a formatted version of this set.
     * 
     * For example, a set containing "a" and "b" returns
     * the string "{a, b}". An empty set returns "{}"
     * 
     * @return the formatted string
     */
    public String toString() {
        String setContents = "";
        for (int i = 0; i < data.size(); i++) {
            setContents += data.get(i);
            if (i != data.size() - 1) {
                setContents += ", ";
            }
        }
        return "{" + setContents + "}";
    }
    
    public static void main(String[] Args) {
        StringSet testSet = new StringSet();
        testSet.insert("hello");
        testSet.insert("goodbye");
        testSet.insert("balls");
        testSet.insert("jaws");
        System.out.println(testSet.toString());
        StringSet testSet2 = new StringSet();
        testSet2.insert("hello");
        testSet2.insert("news");
        testSet2.insert("hello");
        testSet2.insert("brisket");
        System.out.println(testSet2.toString());
        System.out.println(testSet.union(testSet2));
        testSet2.remove("hello");
        System.out.println(testSet2.toString());

    }

}