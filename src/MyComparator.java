package alg;

import java.util.Comparator;

class MyComparator implements Comparator<Point> {
  public int compare(Point a, Point b) {
    return Integer.compare((int)a.getX(), (int)b.getX());
  }
}
