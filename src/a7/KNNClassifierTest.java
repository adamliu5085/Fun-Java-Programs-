package a7;

// CS1410 A7
// Written By: ADAM LIU

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class KNNClassifierTest {
	
	Sample2D sampleA = new Sample2D(0, 0, "a");
	Sample2D sampleB = new Sample2D(1, 0, "b");
	Sample2D sampleC = new Sample2D(2, 0, "c");
	Sample2D sampleD = new Sample2D(3, 0, "d");
	Sample2D sampleE = new Sample2D(4, 0, "e");
	Sample2D sampleF = new Sample2D(5, 0, "f");
	Sample2D sampleG = new Sample2D(5, 0, "f");

	@Test
	void testFindkClosest() {
		ArrayList<Sample2D> samples = new ArrayList<Sample2D>();
		samples.add(sampleA);
		samples.add(sampleB);
		samples.add(sampleC);
		samples.add(sampleD);
		samples.add(sampleE);
		samples.add(sampleF);
		
		KNNClassifier classifyTest1 = new KNNClassifier(6, samples);
		ArrayList<Neighbor> neighborsParam = classifyTest1.computeNeighbors(sampleF);
		ArrayList<Neighbor> kClosestToSampleF = classifyTest1.findKClosest(neighborsParam);
		assertEquals(sampleE, kClosestToSampleF.get(1).getSample(), "Test Failed.");
		assertEquals(sampleD, kClosestToSampleF.get(2).getSample(), "Test Failed.");
		assertEquals(sampleA, kClosestToSampleF.get(5).getSample(), "Test Failed.");
		
		KNNClassifier classifyTest2 = new KNNClassifier(6, samples);
		ArrayList<Neighbor> neighborsParam2 = classifyTest2.computeNeighbors(sampleA);
		ArrayList<Neighbor> kClosestToSampleA = classifyTest2.findKClosest(neighborsParam2);
		assertEquals(sampleB, kClosestToSampleA.get(1).getSample(), "Test Failed.");
		assertEquals(sampleF, kClosestToSampleA.get(5).getSample(), "Test Failed.");
		assertEquals(sampleC, kClosestToSampleA.get(2).getSample(), "Test Failed.");
		
	}
	
	@Test
	void voteOnClassification() {
		ArrayList<Sample2D> samples = new ArrayList<Sample2D>();
		samples.add(sampleA);
		samples.add(sampleB);
		samples.add(sampleC);
		samples.add(sampleD);
		samples.add(sampleE);
		samples.add(sampleF);
		samples.add(sampleG);
		samples.add(sampleB);
		samples.add(sampleB);
		samples.add(sampleD);
		
		KNNClassifier classifyTest1 = new KNNClassifier(10, samples);
		ArrayList<Neighbor> neighborsParam = classifyTest1.computeNeighbors(sampleA);
		ArrayList<Neighbor> kClosestToSampleA = classifyTest1.findKClosest(neighborsParam);
		HashMap<String, Integer> mapOfVotes = classifyTest1.voteOnClassification(kClosestToSampleA);
		System.out.println(mapOfVotes);
		
		assertEquals(1, mapOfVotes.get("a"), "Test Failed");
		assertEquals(2, mapOfVotes.get("f"), "Test Failed");
		assertEquals(3, mapOfVotes.get("b"), "Test Failed");
		assertEquals(2, mapOfVotes.get("d"), "Test Failed");
		assertEquals(1, mapOfVotes.get("c"), "Test Failed");
		assertEquals(1, mapOfVotes.get("e"), "Test Failed");

	}
	
	@Test
	void testGetHighestVotedClassification() {
		ArrayList<Sample2D> samples = new ArrayList<Sample2D>();
		samples.add(sampleA);
		samples.add(sampleB);
		samples.add(sampleC);
		samples.add(sampleD);
		samples.add(sampleE);
		samples.add(sampleF);
		samples.add(sampleG);
		samples.add(sampleB);
		samples.add(sampleB);
		samples.add(sampleD);
		
		KNNClassifier classifyTest1 = new KNNClassifier(10, samples);
		ArrayList<Neighbor> neighborsParam = classifyTest1.computeNeighbors(sampleA);
		ArrayList<Neighbor> kClosestToSampleA = classifyTest1.findKClosest(neighborsParam);
		HashMap<String, Integer> mapOfVotes = classifyTest1.voteOnClassification(kClosestToSampleA);
		System.out.println(mapOfVotes);
		String highestVote = classifyTest1.getHighestVotedClassification(mapOfVotes);
		System.out.println(highestVote);
		
		assertEquals("b", highestVote, "Test Failed"); // <---- This one needs to be equal
		assertNotSame("a", highestVote, "Test Failed");  // <----- Want this to not be the same
		assertNotSame("c", highestVote, "Test Failed");
		assertNotSame("d", highestVote, "Test Failed");
		assertNotSame("e", highestVote, "Test Failed");
		assertNotSame("f", highestVote, "Test Failed");
	}
	
	@Test 
	void testCompareTo() {
		ArrayList<Sample2D> samples = new ArrayList<Sample2D>();
		samples.add(sampleA);
		samples.add(sampleB);
		samples.add(sampleC);
		samples.add(sampleD);
		samples.add(sampleE);
		samples.add(sampleF);
		samples.add(sampleG);
		samples.add(sampleB);
		samples.add(sampleB);
		samples.add(sampleD);
		
		KNNClassifier classifyTest1 = new KNNClassifier(10, samples);
		ArrayList<Neighbor> neighborsParam = classifyTest1.computeNeighbors(sampleA);
		ArrayList<Neighbor> kClosestToSampleA = classifyTest1.findKClosest(neighborsParam);
		int compared = kClosestToSampleA.get(0).compareTo(kClosestToSampleA.get(1));
		System.out.println(compared);
		assertEquals(-1, compared, "Test Failed");
		
		int compared2 = kClosestToSampleA.get(1).compareTo(kClosestToSampleA.get(0));
		System.out.println(compared2);
		assertEquals(1, compared2, "Test Failed");
		
		int compared3 = kClosestToSampleA.get(0).compareTo(kClosestToSampleA.get(0));
		System.out.println(compared3);
		assertEquals(0, compared3, "Test Failed");
	}
}