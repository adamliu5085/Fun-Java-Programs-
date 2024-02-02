// A4 Encapsulation Code written by ADAM LIU. CS1410
package a4;

public class Vector { // Instance Variables
	private double xVecVal;
	private double yVecVal;
	
	/** Make a public class object constructor called vector that holds an X and a Y coordinate.
	 * @param inputX a double data type holding an x coordinate for a vector.
	 * @param inputY a double data type holding a y coordinate for a vector. 
	 */
	public Vector(double inputX, double inputY) { // Object Caller
		xVecVal = inputX;
		yVecVal = inputY;
	}
	
	/** Make a setter for the X vector coordinate.
	 * @param xVal a double value for the X vector coordinate.
	 */
	public void setX(double xVal) { // X Setter
		xVecVal = xVal;
	}
	
	/** Make a setter for the Y vector coordinate.
	 * @param yVal a double value for the X vector coordinate.
	 */
	public void setY(double yVal) { // Y Setter
		yVecVal = yVal;
	}
	
	/** Make a getter for the X vector coordinate.
	 * @return the X value of the vector.
	 */
	public double getX() { // X Getter
		return xVecVal;
	}
	
	/** Make a getter for the Y vector coordinate.
	 * @return the Y value of the vector. 
	 */
	public double getY() { // Y Getter
		return yVecVal;
	}
	
	/** Make a magnitude method that will return the magnitude of a vector. 
	 * @return The magnitude of the vector called. 
	 */
	public double magnitude() {
		return Math.sqrt((this.xVecVal*this.xVecVal) + (this.yVecVal*this.yVecVal));
	}
	
	/** Make a method with the parameter of any vector, then calculate the dot product with the parameter and the called vector. 
	 * @param paramVector A vector parameter.
	 * @return the dot product of any parameter vector and the vector of what was called from.
	 */
	public double dotProduct(Vector paramVector) {
		return (this.xVecVal * paramVector.getX() + this.yVecVal * paramVector.getY());
	}
	
	/** Make a main method with short test print statements to work of throughout the coding process. 
	 * @param args
	 */
	public static void main(String[] args) {
		Vector vector1 = new Vector(56.3, 100.0);
		Vector vector2 = new Vector(5.0, 4.0);
		System.out.println(vector1.magnitude());
		System.out.println(vector1.dotProduct(vector2));		
	}
}