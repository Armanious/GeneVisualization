package org.armanious;

public final class PhysicsVector {
	
	public static final PhysicsVector ZERO_VECTOR = new PhysicsVector(0, 0, 0, 0);
	private final double x;
	private final double y;
	private final double magnitude;
	private final double direction;
		
	private PhysicsVector(double x, double y, double magnitude, double direction){
		this.x = x;
		this.y = y;
		this.magnitude = magnitude;
		this.direction = direction;
		if(x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY || x == Double.NaN ||
				y == Double.POSITIVE_INFINITY || y == Double.NEGATIVE_INFINITY || y == Double.NaN){
			System.err.println("Houston, we have a problem");
		}
		if(magnitude == Double.POSITIVE_INFINITY || magnitude == Double.NEGATIVE_INFINITY){
			System.err.println("Hi, I'm Paul!");
		}
	}	
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public double getMagnitude(){
		return magnitude;
	}
	
	public double getDirection(){
		return direction;
	}
	
	public PhysicsVector add(PhysicsVector rhs){
		return PhysicsVector.fromXY(x + rhs.x, y + rhs.y);
	}
	
	public PhysicsVector subtract(PhysicsVector rhs){
		return PhysicsVector.fromXY(x - rhs.x, y - rhs.y);
	}

	public PhysicsVector multiply(double m) {
		return PhysicsVector.fromMagnitudeDirection(magnitude * m, direction);
	}
	
	@Override
	public String toString() {
		return "<" + x + ", " + y + ">";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(!(obj instanceof PhysicsVector)) return false;
		final PhysicsVector other = (PhysicsVector) obj;
		return other.magnitude == magnitude && other.direction == direction;
	}
	
	@Override
	public int hashCode() {
		return (int) (magnitude * 23 + direction);
	}

	public static PhysicsVector fromXY(double x, double y) {
		return new PhysicsVector(x, y, Math.sqrt(x * x + y * y), Math.atan2(y, x));
	}
	
	public static PhysicsVector fromMagnitudeDirection(double magnitude, double direction){
		return new PhysicsVector(magnitude * Math.cos(direction), magnitude * Math.sin(direction), magnitude, direction);
	}

	public double distanceTo(PhysicsVector other) {
		final double deltaX = other.x - x;
		final double deltaY = other.y - y;
		return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	}

}
