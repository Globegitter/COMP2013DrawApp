package drawapp;
public class Turtle {
	private double posX;
	private double posY;
	private double orientation;

	public Turtle() {
		this.posX = 0;
		this.posY = 0;
		this.orientation = 0;
	}

	public void turnLeft(double angle) {
		this.orientation -= angle;
	}

	public void turnRight(double angle) {
		this.orientation += angle;
	}

	public void moveForward(double distance) {
		this.posX += (distance * Math.cos(orientation));
		this.posY += (distance * Math.sin(orientation));
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public double getOrientation() {
		return orientation;
	}
}
