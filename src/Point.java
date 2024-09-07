package alg;

public class Point {
  private double x;
  
  private double y;
  
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  public boolean equals(Point p) {
    return (this.x == p.x && this.y == p.y);
  }
  
  public double getX() {
    return this.x;
  }
  
  public void setX(double x) {
    this.x = x;
  }
  
  public double getY() {
    return this.y;
  }
  
  public void setY(double y) {
    this.y = y;
  }
  
  public void display() {
    System.out.print(" X: " + getX() + " Y: " + getY());
  }
  
  public static double distance(Point p1, Point p2) {
    double d = Math.pow(p2.getY() - p1.getY(), 2.0D) + Math.pow(p2.getX() - p1.getX(), 2.0D);
    return Math.sqrt(d);
  }
}
