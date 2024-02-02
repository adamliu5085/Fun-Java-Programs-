package a5;

//ADAM LIU CS 1410 A5

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DoublingDynamicArrayTest {
    /**
     * Helps to more easily create Dynamic Arrays
     */
    private DoublingDynamicArray createThreeElementArray(
            String e1, String e2, String e3) {
        DoublingDynamicArray array = new DoublingDynamicArray();
        array.add(e1);
        array.add(e2);
        array.add(e3);
        return array;
    }
   
    /*********** test constructor ************/
    
    @Test
    public void testConstructor() {
        DoublingDynamicArray array = new DoublingDynamicArray();
        assertEquals("[] backing size: 8", array.toString(), "Calling the constructor did not yield the correct array string");
        assertEquals(0, array.size(), "Calling the constructor did not yield an 0 sized array");
    }
    
    @Test
    public void testConstructorRepeated() {
        // Test that creating two different DynamicArrays actually creates two
        // distinct arrays. We can test this by modifying one array and seeing
        // if the other changes
        DoublingDynamicArray array = new DoublingDynamicArray();
        DoublingDynamicArray otherArray = new DoublingDynamicArray();
        otherArray.add("test");
        assertEquals(0, array.size(), "Adding an element to one DoublingDynamicArray changed the size of a different one");
    }

    /*********** test add ************/
    
    @Test
    public void testAddSimple() {
        // This also tests the toString method
        DoublingDynamicArray array = createThreeElementArray("9", "0", "cats");
        String expected = "[9, 0, cats] backing size: 8";
        assertEquals(expected, array.toString(), "Failed adding 3 elements to the end");
        assertEquals(3, array.size(), "Incorrect size after adding 3 elements to array");
    }
    
    @Test
    public void testAddLarge() {
        // Adding >= 8 elements tests the growth of an array
        String[] largeArray = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        DoublingDynamicArray array = new DoublingDynamicArray();
        for (String elem : largeArray) {
            array.add(elem);
        }
        String expected = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9] backing size: 16";
        assertEquals(expected, array.toString(), "Failed adding 10 elements to array");
        assertEquals(10, array.size(), "Incorrect size after adding 10 elements to array");
    }
    
    @Test
    public void testAddFront() {
        DoublingDynamicArray array = createThreeElementArray("9", "0", "cats");
        array.add(0, "front");
        String expected = "[front, 9, 0, cats] backing size: 8";
        assertEquals(expected, array.toString(), "Failed adding an element to the front");
        assertEquals(4, array.size(), "Incorrect size after adding element to the front");
    }
    
    @Test
    public void testAddMiddle() {
        DoublingDynamicArray array = createThreeElementArray("9", "0", "cats");
        array.add(1, "middle");
        String expected = "[9, middle, 0, cats] backing size: 8";
        assertEquals(expected, array.toString(), "Failed adding an element to the middle");
        assertEquals(4, array.size(), "Incorrect size after adding element to the middle");
    }
    
    @Test
    public void testAddEnd() {
        DoublingDynamicArray array = createThreeElementArray("9", "0", "cats");
        array.add(3, "end");
        String expected = "[9, 0, cats, end] backing size: 8";
        assertEquals(expected, array.toString(), "Failed adding an element to the end");
        assertEquals(4, array.size(), "Incorrect size after adding element to the end");
    }
    
    @Test
    public void testAddInvalidLow() {
        DoublingDynamicArray array = createThreeElementArray("9", "0", "cats");
        try {
            array.add(-1, "end");
            fail("Did not throw exception when adding at a too-low index");
        } catch (IndexOutOfBoundsException e) {
            // If we catch an exception, we pass the test
        }
    }
    
    @Test
    public void testAddInvalidHigh() {
        DoublingDynamicArray array = createThreeElementArray("9", "0", "cats");
        try {
            array.add(4, "end");
            fail("Did not throw exception when adding at a too-high index");
        } catch (IndexOutOfBoundsException e) {
            // If we catch an exception, we pass the test
        }
    }

    /*********** test get ************/
    
    @Test
    public void testGet(){
        DoublingDynamicArray array = createThreeElementArray("9", "0", "cats");
        assertEquals("9", array.get(0), "Failed getting from front");
        assertEquals("0", array.get(1), "Failed getting from middle");
        assertEquals("cats", array.get(2), "Failed getting from end");
        assertEquals(3, array.size(), "Calling get changed the size of the array");
    }
    
    @Test
    public void testGetInvalidLow() {
        DoublingDynamicArray array = createThreeElementArray("9", "0", "cats");
        try {
            array.get(-1);
            fail("Did not throw exception when getting at a too-low index");
        } catch (IndexOutOfBoundsException e) {
            // If we catch an exception, we pass the test
        }
    }
    
    @Test
    public void testGetInvalidHigh() {
        DoublingDynamicArray array = createThreeElementArray("9", "0", "cats");
        try {
            array.get(3);
            fail("Did not throw exception when getting at a too-high index");
        } catch (IndexOutOfBoundsException e) {
            // If we catch an exception, we pass the test
        }
    }
    
    /*********** test speed ************/
    
    @Test
    public void testDoublingIsFaster() {
        double doublingDynamicArrayTime = SpeedTest.addNToDoublingDynamic(10000);
        double regularDynamicArrayTime = SpeedTest.addNToDynamic(10000);
        assertTrue(doublingDynamicArrayTime < regularDynamicArrayTime,
                "The time to add 10k items to a doubling dynamic array should be faster, " +
                "but it is not with the current implementation");
    }
    
    /*********** test remove ************/
    
    @Test
    public void testRemove() {
		String[] largeArray = {"0", "1", "2", "baltic", "4", "five", "6", "7", "8", "9"};
	    DoublingDynamicArray array = new DoublingDynamicArray();
	    for (String elem : largeArray) {
	         array.add(elem);
	    }
	    array.remove(0);
	    String expected = "[1, 2, baltic, 4, five, 6, 7, 8, 9] backing size: 16";
	    String arrayResult = array.toString();
	    assertEquals(expected, arrayResult, "Remove method did not properly execute.");
	    
	    array.remove(8);
	    String expected2 = "[1, 2, baltic, 4, five, 6, 7, 8] backing size: 16";
	    String arrayResult2 = array.toString();
	    assertEquals(expected2, arrayResult2, "Remove method did not properly execute.");
	    
	    array.remove(4);
	    String expected3 = "[1, 2, baltic, 4, 6, 7, 8] backing size: 16";
	    String arrayResult3 = array.toString();
	    assertEquals(expected3, arrayResult3, "Remove method did not properly execute.");
	    
	    array.remove(2);
	    String expected4 = "[1, 2, 4, 6, 7, 8] backing size: 16";
	    String arrayResult4 = array.toString();
	    assertEquals(expected4, arrayResult4, "Remove method did not properly execute.");
	    
        try {
            array.remove(-1);
            fail("Did not throw exception when adding at a too-low index");
        } catch (IndexOutOfBoundsException e) {
    }
        try {
            array.remove(10);
            fail("Did not throw exception when adding at a too-low index");
        } catch (IndexOutOfBoundsException e) {
    }
    }
        
    /*********** test set ************/
    
    @Test
    public void testSet() {
		String[] largeArray = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	    DoublingDynamicArray array = new DoublingDynamicArray();
	    for (String elem : largeArray) {
	         array.add(elem);
	    }
	    array.set(0,  "FIRST!");
	    String expected = "[FIRST!, 1, 2, 3, 4, 5, 6, 7, 8, 9] backing size: 16";
	    String arrayResult = array.toString();
	    assertEquals(expected, arrayResult, "Set method did not compute properly.");
	    
	    array.set(9,  "LAST!");
	    String expected2 = "[FIRST!, 1, 2, 3, 4, 5, 6, 7, 8, LAST!] backing size: 16";
	    String arrayResult2 = array.toString();
	    assertEquals(expected2, arrayResult2, "Set method did not compute properly.");
	    
	    array.set(5,  "About Middle");
	    String expected3 = "[FIRST!, 1, 2, 3, 4, About Middle, 6, 7, 8, LAST!] backing size: 16";
	    String arrayResult3 = array.toString();
	    assertEquals(expected3, arrayResult3, "Set method did not compute properly.");
	  
        try {
            array.set(-1, "Bruh");
            fail("Did not throw exception when adding at a too-low index");
        } catch (IndexOutOfBoundsException e) {
       
    }
        try {
            array.set(10, "LOL");
            fail("Did not throw exception when adding at a too-low index");
        } catch (IndexOutOfBoundsException e) {
    }
    }
}