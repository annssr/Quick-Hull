package alg;

public class Hull {
  private double slope;
  
  private Point p1;
  
  private Point p2;
  
  private double xStart;
  
  private double xEnd;
  
  public Hull(Point p1, Point p2) {
    this.p1 = p1;
    this.p2 = p2;
    this.slope = slope();
  }
  
  public double slope() {
    return (this.p2.getY() - this.p1.getY()) / (this.p2.getX() - this.p1.getX());
  }
  
  public double dist(Point p, char direction) {
    double res = 0.0D;
    if (direction == 'U')
      res = distUpper(p); 
    if (direction == 'L')
      res = distLower(p); 
    return res;
  }
  
  private double distUpper(Point p) {
    double dist1 = p.getX() - this.p1.getX();
    if (dist1 == 0.0D && 
      p.getX() > this.p1.getX())
      return p.getY() - this.p1.getY(); 
    double d_s = dist1 * this.slope + this.p1.getY();
    if (p.getY() <= d_s)
      return 0.0D; 
    return Point.distance(this.p1, p) + Point.distance(p, this.p2);
  }
  
  private double distLower(Point p) {
    double dist1 = p.getX() - this.p1.getX();
    if (dist1 == 0.0D && 
      p.getX() < this.p1.getX())
      return this.p1.getY() - p.getY(); 
    double d_s = dist1 * this.slope + this.p1.getY();
    if (p.getY() >= d_s)
      return 0.0D; 
    return Point.distance(this.p1, p) + Point.distance(p, this.p2);
  }
  
  public void display() {
    System.out.print(" P1 ");
    this.p1.display();
    System.out.print(" P2 ");
    this.p2.display();
    System.out.println();
  }
  
  public double getSlope() {
    return this.slope;
  }
  
  public void setSlope(double slope) {
    this.slope = slope;
  }
  
  public Point getP1() {
    return this.p1;
  }
  
  public void setP1(Point p1) {
    this.p1 = p1;
  }
  
  public Point getP2() {
    return this.p2;
  }
  
  public void setP2(Point p2) {
    this.p2 = p2;
  }
  
  public double getxStart() {
    return this.xStart;
  }
  
  public void setxStart(double xStart) {
    this.xStart = xStart;
  }
  
  public double getxEnd() {
    return this.xEnd;
  }
  
  public void setxEnd(double xEnd) {
    this.xEnd = xEnd;
  }
}
