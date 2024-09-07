package alg;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
  private LinkedPQ<Point> points = new LinkedPQ<>();
  
  private LinkedList<Hull> upper_hull = new LinkedList<>();
  
  private LinkedList<Hull> lower_hull = new LinkedList<>();
  
  private LinkedList<NodeH> history = new LinkedList<>();
  
  public LinkedList<Hull> getHulls() {
    searchHulls('U');
    searchHulls('L');
    LinkedList<Hull> res = this.upper_hull;
    res.addAll(this.lower_hull);
    for (int i = 0; i < res.size(); i++) {
      System.out.println("HULL " + i);
      ((Hull)res.get(i)).display();
    } 
    return res;
  }
  
  public ArrayList<Point> dividePoints(Hull h, char d, Point[] pp) {
    double slop = h.getSlope();
    ArrayList<Point> ar = new ArrayList<>();
    int z = 0;
    for (int i = 0; i < pp.length; i++) {
      if (((pp[i].equals(h.getP1()) ? 0 : 1) & (pp[i].equals(h.getP2()) ? 0 : 1) & ((h.dist(pp[i], d) != 0.0D) ? 1 : 0) & (ar.contains(pp[i]) ? 0 : 1)) != 0)
        ar.add(pp[i]); 
    } 
    return ar;
  }
  
  private void addULH(char dir, Hull h) {
    if (dir == 'U') {
      this.upper_hull.add(h);
    } else if (dir == 'L') {
      this.lower_hull.add(h);
    } 
  }
  
  private LinkedList<Hull> searchHulls(char dir) {
    if (dir == 'U') {
      System.out.println("----------UPPER");
    } else {
      System.out.println("----------LOWER");
    } 
    Point[] pp = new Point[this.points.length()];
    this.points.toArray(pp);
    Point pStart = pp[0];
    Point pEnd = pp[pp.length - 1];
    double max = 0.0D;
    for (int i = 0; i < this.points.length() && pp[i].getX() == pStart.getX(); i++) {
      for (int k = this.points.length() - 1; k > 0 && pp[k].getX() == pEnd.getX(); k--) {
        if (max < Point.distance(pp[i], pp[k])) {
          max = Point.distance(pp[i], pp[k]);
          pStart = pp[i];
          pEnd = pp[k];
        } 
      } 
    } 
    System.out.println(pp.length);
    pStart.display();
    System.out.println("\n***************");
    pEnd.display();
    System.out.println("\n***************");
    Hull h = new Hull(pStart, pEnd);
    addULH(dir, h);
    this.history.addLast(new NodeH(h, Boolean.valueOf(true)));
    ArrayList<Point> pf = dividePoints(h, dir, pp);
    int index = -1;
    boolean flag = true;
    int j = 0;
    while (flag) {
      Hull tmp = getH(dir).get(j);
      double max2 = 0.0D;
      Point p = null;
      for (int k = 0; k < pf.size(); k++) {
        if ((((((Point)pf.get(k)).getX() >= tmp.getP1().getX()) ? 1 : 0) & ((((Point)pf.get(k)).getX() <= tmp.getP2().getX()) ? 1 : 0)) != 0) {
          double dist = tmp.dist(pf.get(k), dir);
          if (dist > max2) {
            max2 = dist;
            p = pf.get(k);
            index = k;
          } 
        } 
      } 
      if (max2 != 0.0D) {
        pf.remove(index);
        addh(tmp, p, j, dir);
      } else {
        j++;
      } 
      if (j == getH(dir).size())
        flag = false; 
    } 
    return null;
  }
  
  public void addh(Hull h, Point p, int i, char diraction) {
    if (h.getP1().equals(p) || h.getP2().equals(p))
      return; 
    getH(diraction).remove(i);
    Hull h1 = new Hull(h.getP1(), p);
    Hull h2 = new Hull(p, h.getP2());
    this.history.addLast(new NodeH(h1, Boolean.valueOf(true)));
    this.history.addLast(new NodeH(h2, Boolean.valueOf(true)));
    this.history.addLast(new NodeH(h, Boolean.valueOf(false)));
    if (diraction == 'U') {
      this.upper_hull.add(i, h1);
      this.upper_hull.add(i + 1, h2);
    } else if (diraction == 'L') {
      this.lower_hull.add(i, h1);
      this.lower_hull.add(i + 1, h2);
    } 
  }
  
  public LinkedList<Hull> getH(char dir) {
    LinkedList<Hull> lh = null;
    if (dir == 'U') {
      lh = getUpper_hull();
    } else if (dir == 'L') {
      lh = getLower_hull();
    } 
    return lh;
  }
  
  public LinkedList<Hull> getLower_hull() {
    return this.lower_hull;
  }
  
  public LinkedList<Hull> getUpper_hull() {
    return this.upper_hull;
  }
  
  public LinkedPQ<Point> getPoints() {
    return this.points;
  }
  
  public void setPoints(LinkedPQ<Point> points) {
    this.points = points;
  }
  
  public void setDshU(LinkedList<Hull> dshU) {
    this.upper_hull = dshU;
  }
  
  public void setDshL(LinkedList<Hull> dshL) {
    this.lower_hull = dshL;
  }
  
  public static void main(String[] args) {}
  
  public double[] getPointsFile(String path) {
    Files.openRFile(path);
    double[] result = Files.readFileString(path);
    Files.closeRFile();
    return result;
  }
  
  public LinkedList<NodeH> getHistory() {
    return this.history;
  }
  
  public void setHistory(LinkedList<NodeH> history) {
    history = history;
  }
}
