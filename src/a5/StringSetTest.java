package a5;

// ADAM LIU CS 1410 A5

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StringSetTest {
    /**
     * Helps to more easily create test StringSets.
     */
    private StringSet createThreeElementSet(String e1, String e2, String e3) {
        StringSet set = new StringSet();
        set.insert(e1);
        set.insert(e2);
        set.insert(e3);
        return set;
    }
    
    /**
     * Checks that all elements of elemsToCheck are contained within set, and fails if not.
     */
    private void checkContainsExactly(StringSet set, String[] elemsToCheck, String failureMessage) {
        for (String elem : elemsToCheck)
            assertTrue(set.contains(elem), failureMessage);
        // We also check the size, to make sure that nothing else is contained
        // This test will fail until you have correctly implemented the size() method
        assertEquals(set.size(), elemsToCheck.length, failureMessage);
    }

    /*********** test constructor ************/
    
    @Test
    public void testConstructorEmpty(){
        StringSet set = new StringSet();
        assertEquals("{}", set.toString(), "Failed constructing empty set -- invalid string representation");
        assertEquals(0, set.size(), "Failed constructing empty set -- invalid size");
    }

    /*********** test insert ************/
    
    @Test
    public void testInsert(){
        StringSet set = createThreeElementSet("hello", "goodbye", "why");
        String[] expectedElements = {"hello", "goodbye", "why"};
        checkContainsExactly(set, expectedElements, "Failed constructing 3 element set");
    }
    
    @Test
    public void testInsertDuplicates(){
        StringSet set = createThreeElementSet("hello", "goodbye", "goodbye");
        String[] expectedElements = {"hello", "goodbye"};
        checkContainsExactly(set, expectedElements, "Failed constructing 2 element set with duplicates");
    }
    
    @Test
    public void testInsertNull(){
        StringSet set = new StringSet();
        try {
            set.insert(null);
            fail("Did not throw exception when calling insert(null)");
        } catch (IllegalArgumentException e) {
            // If we catch an exception, we pass the test
        }
    }

    /*********** test contains ************/
    
    @Test
    public void testContainsTrue(){
        StringSet set = createThreeElementSet("hello", "goodbye", "why");
        assertTrue(set.contains("hello"), "Failed checking set for an element that it contains");
        assertTrue(set.contains("why"), "Failed checking set for an element that it contains");
    }
    
    @Test
    public void testContainsFalse(){
        StringSet set = createThreeElementSet("hello", "goodbye", "why");
        assertFalse(set.contains("he"), "Failed checking set for an element that it does not contain");
        assertFalse(set.contains(""), "Failed checking set for an element that it does not contain");
    }
    
    @Test
    public void testContainsEmptySet(){
        StringSet set = new StringSet();
        assertFalse(set.contains("x"), "Failed checking set for an element that it does not contain");
    }
    
    @Test
    public void testContainsNull(){
        StringSet set = new StringSet();
        try {
            set.contains(null);
            fail("Did not throw exception when checking contains(null)");
        } catch (IllegalArgumentException e) {
            // If we catch an exception, we pass the test
        }
    }

    /*********** test toString ************/
    
    @Test
    public void testToStringOneElement(){
        StringSet set = new StringSet();
        set.insert("cat");
        assertEquals("{cat}", set.toString(), "toString({cat}) failed");
    }
    
    @Test
    public void testToStringTwoElements(){
        StringSet set = new StringSet();
        set.insert("cat");
        set.insert("dog");
        String str = set.toString();
        // Sets are not guaranteed to have a particular ordering, so we must check both orders.
        assertTrue(str.equals("{cat, dog}") || str.equals("{dog, cat}"), "toString({cat, dog}) failed");
    }
    
    /*********** test size ************/
    
    @Test
    public void testSize() {
	    StringSet testSet = new StringSet();
	    testSet.insert("hello");
	    testSet.insert("goodbye");
	    testSet.insert("balls");
	    testSet.insert("jaws");
	    int expected = 4;
	    int result = testSet.size();
	    assertEquals(expected, result, "size did not compute properly.");
	    StringSet testSet2 = new StringSet();
	    int expected2 = 0;
	    int result2 = testSet2.size();
	    assertEquals(expected2, result2, "size did not compute properly");
    }
    
    /*********** test remove ************/
    
    @Test
    public void testRemove() {
	    StringSet testSet = new StringSet();
	    testSet.insert("hello");
	    testSet.insert("goodbye");
	    testSet.insert("balls");
	    testSet.insert("jaws");
	    testSet.remove("balls");
	    
	    String result = testSet.toString();
	    String expected = "{hello, goodbye, jaws}";
	    assertEquals(expected, result, "remove did not compute properly.");
	    
	    testSet.remove("hello");
	    String result2 = testSet.toString();
	    String expected2 = "{goodbye, jaws}";
	    assertEquals(expected2, result2, "remove did not compute properly.");
	    
	    testSet.remove("thisdoesntexist");
	    String result3 = testSet.toString();
	    String expected3 = "{goodbye, jaws}";
	    assertEquals(expected3, result3, "remove did not compute properly.");
    }    
    
    /*********** test union ************/
    @Test
    public void testUnion() {
        StringSet testSet = new StringSet();
        testSet.insert("hello");
        testSet.insert("goodbye");
        testSet.insert("balls");
        testSet.insert("jaws");
        
        StringSet testSet2 = new StringSet();
        testSet2.insert("hello");
        testSet2.insert("news");
        testSet2.insert("hello");
        
        testSet2.insert("brisket");
        String result = testSet.union(testSet2).toString();
        String expected = "{hello, goodbye, balls, jaws, news, brisket}";
        assertEquals(expected, result, "Union did not compute properly.");
        
        testSet.insert("lowerletters");
        testSet2.insert("LOWERLETTERS");
        String result2 = testSet.union(testSet2).toString();
        String expected2 = "{hello, goodbye, balls, jaws, lowerletters, news, brisket, LOWERLETTERS}";
        assertEquals(expected2, result2, "Union did not compute properly.");
        
        testSet.insert("ok");
        testSet2.insert("ok");
        String result3 = testSet.union(testSet2).toString();
        String expected3 = "{hello, goodbye, balls, jaws, lowerletters, ok, news, brisket, LOWERLETTERS}";
        assertEquals(expected3, result3, "Union did not compute properly." );

    }
    
    
    /*********** test intersection ************/
    @Test
	    public void testIntersection() {
	    StringSet testSet = new StringSet();
	    testSet.insert("hello");
	    testSet.insert("goodbye");
	    testSet.insert("falls");
	    testSet.insert("jaws");
	    
	    StringSet testSet2 = new StringSet();
	    testSet2.insert("falls");
	    testSet2.insert("hello");
	    testSet2.insert("goodbye");
	    
	    String expected = "{falls, hello, goodbye}";
	    String result = testSet.intersection(testSet2).toString();
	    assertEquals(expected, result, "Intersection did not compute Properly.");
	    
	    testSet.insert("McDonalds");
	    testSet2.insert("McDonalds");
	    String expected2 = "{falls, hello, goodbye, McDonalds}";
	    String result2 = testSet.intersection(testSet2).toString();
	    assertEquals(expected2, result2, "Intersection did not compute properly.");
	    
	    testSet.insert("BIGLETTERS");
	    testSet2.insert("bigletters");
	    String expected3 = "{falls, hello, goodbye, McDonalds}";
	    String result3 = testSet.intersection(testSet2).toString();
	    assertEquals(expected3, result3, "Intersection did not compute properly.");
    }
    
}
