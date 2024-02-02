// A4 Encapsulation Code written by ADAM LIU. CS1410
package a4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Vector2Test {
	@Test
	void testSetXthenGetX() {
		Vector2 setAndGetVector = new Vector2(0.0, 0.0);
		setAndGetVector.setX(1.0);
		assertEquals(1.0, setAndGetVector.getX(), "Setting X has failed");
	}
	
	@Test
	void testSetYthenGetY() {
		Vector2 setAndGetVector = new Vector2(0.0, 0.0);
		setAndGetVector.setY(1.0);
		assertEquals(1.0, setAndGetVector.getY(), "Setting Y has failed.");
	}
	
	@Test
	void testMagnitude() {
		Vector2 vector1 = new Vector2(2.0, 3.0);
		Vector2 vector2 = new Vector2(10.0, 6.0);
		Vector2 vector3 = new Vector2(56.3, 100.0);
		assertEquals(3.605551275463989, vector1.magnitude(), "Magnitude of the vector does not equal.");
		assertEquals(11.661903789690601, vector2.magnitude(), "Magnitude of the vector does not equal.");
		assertEquals(114.75926977808807, vector3.magnitude(), "Magnitude of the vector does not equal.");
	}
	
	@Test
	void testDotProduct() {
		Vector2 vector1 = new Vector2(2.0, 3.0);
		Vector2 vector2 = new Vector2(5.0, 4.0);
		Vector2 vector3 = new Vector2(56.3, 100.0);
		Vector2 vector4 = new Vector2(8.0, 32.0);
		Vector2 vector5 = new Vector2(12.3, 45.6);
		Vector2 vector6 = new Vector2(78.9, 120.0);
		assertEquals(22.0, vector1.dotProduct(vector2), "Dot product of Vector 1 and Vector 2 does not equal.");
		assertEquals(3650.4, vector3.dotProduct(vector4), "Dot product of Vector 3 and Vector 4 does not equal.");
		assertEquals(6442.47, vector5.dotProduct(vector6), "Dot product of Vector 5 and Vector 6 does not equal.");
	}
}