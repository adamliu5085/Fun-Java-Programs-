package a7;

//CS1410 A7
//Code Filled by ADAM LIU

/**
 * This class represents a neighbor to an unknown sample. It stores a Sample and
 * a distance to the Sample.
 *
 */
public class Neighbor implements Comparable<Neighbor> {

	double distance;
	Sample2D sample;

	/**
	 * Construct a Neighbor from a sample and an unknown sample
	 * 
	 * @param sample
	 * @param unknown
	 */
	public Neighbor(Sample2D sample, Sample2D unknown) {
		this.sample = sample;
		this.distance = sample.calculateDistance(unknown);
	}

	/**
	 * @return the known sample
	 */
	public Sample2D getSample() {
		return sample;
	}

	/**
	 * Compare one sample with another based on the stored distance. A Neighbor with
	 * a smaller distance is less than an other Neighbor with a larger distance.
	 * Match the expected behavior of a compareTo method.
	 */
	@Override
	public int compareTo(Neighbor other) { // Basic compareTo method.
		if (this.distance > other.distance)
			return 1; // Positive int returned if the distance is greater. 
		if (this.distance < other.distance)
			return -1; // Negative int returned if the distance is less. 
		return 0; // return 0 otherwise. 
	}
}