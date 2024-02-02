// A4 Encapsulation Code written by ADAM LIU. CS1410
package a4;

public class Vector2 { // Instance Variables
	private double[] coordinates;
	
	/** Make a public class object constructor called vector that holds an X and a Y coordinate.
	 * @param inputX a double data type holding an x coordinate for a vector.
	 * @param inputY a double data type holding a y coordinate for a vector. 
	 */
	public Vector2(double inputX, double inputY) { // Object Caller
		coordinates = new double[2];
		coordinates[0] = inputX;
		coordinates[1] = inputY;
	}
	
	/** Make a setter for the X vector coordinate.
	 * @param xVal a double value for the X vector coordinate.
	 */
	public void setX(double xVal) { // X Setter
		coordinates[0] = xVal;
	}
	
	/** Make a setter for the Y vector coordinate.
	 * @param yVal a double value for the X vector coordinate.
	 */
	public void setY(double yVal) { // Y Setter
		coordinates[1] = yVal;
	}
	
	/** Make a getter for the X vector coordinate.
	 * @return the X value of the vector.
	 */
	public double getX() { // X Getter
		return coordinates[0];
	}
	
	/** Make a getter for the Y vector coordinate.
	 * @return the Y value of the vector. 
	 */
	public double getY() { // Y Getter
		return coordinates[1]; 
	}
	
	/** Make a magnitude method that will return the magnitude of a vector. 
	 * @return The magnitude of the vector called. 
	 */
	public double magnitude() {
		return Math.sqrt((this.coordinates[0]*this.coordinates[0]) + (this.coordinates[1]*this.coordinates[1]));
	}
	
	/** Make a method with the parameter of any vector, then calculate the dot product with the parameter and the called vector. 
	 * @param paramVector A vector parameter.
	 * @return the dot product of any parameter vector and the vector of what was called from.
	 */
	public double dotProduct(Vector2 paramVector) {
		return (this.coordinates[0] * paramVector.getX() + this.coordinates[1] * paramVector.getY());
	}
	
	/** Make a main method with short test print statements to work of throughout the coding process. 
	 * @param args
	 */
	public static void main(String[] args) {
		Vector2 vector1 = new Vector2(2.0, 3.0);
		Vector2 vector2 = new Vector2(5.0, 4.0);
		System.out.println(vector1.magnitude());
		System.out.println(vector1.dotProduct(vector2));		
	}
}