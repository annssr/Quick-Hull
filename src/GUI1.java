package alg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUI1 extends JFrame {
  public static final int HEIGHT = 500;
  
  public static boolean next;
  
  private static LinkedPQ<Point> p1 = null;
  
  private static LinkedList<Hull> d = null;
  
  private static LinkedList<NodeH> h = null;
  
  private JPanel contentPane;
  
  public boolean nexto = false;
  
  private static int i = 0, jind = 0;
  
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
          public void run() {
            try {
              GUI1 frame = new GUI1();
              frame.setVisible(true);
              Graph g = new Graph();
              String tmpPath = GUI1.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("%20", " ");
              double[] pFile = g.getPointsFile(tmpPath.substring(0, tmpPath.lastIndexOf("/")) + "\\inputForQuickhull.txt");
              GUI1.p1 = new LinkedPQ<Point>();
              for (int i = 0; i < pFile.length; i += 2)
                GUI1.p1.enqueue(new Point(pFile[i], pFile[i + 1]), (int)pFile[i]); 
              g.setPoints(GUI1.p1);
              GUI1.d = g.getHulls();
              GUI1.h = g.getHistory();
            } catch (Exception e) {
              e.printStackTrace();
            } 
          }
        });
  }
  
  public void paint(Graphics g) {
    super.paint(g);
    do {
    
    } while (p1 == null);
    Point[] parr = new Point[p1.length()];
    p1.toArray(parr);
    for (int i = 0; i < parr.length; i++) {
      g.fillOval((int)parr[i].getX() * 4 - 5 + 50, 500 - (int)parr[i].getY() * 4 - 5 - 50, 10, 10);
      g.drawString(String.valueOf(parr[i].getX()) + "," + parr[i].getY(), (int)parr[i].getX() * 4 + 50, 500 - (int)parr[i].getY() * 4 - 5 - 50);
    } 
    do {
    
    } while (h == null);
    System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + GUI1.i + "     " + h.size());
    for (int t = 0; t < GUI1.i && t < h.size(); t++) {
      System.out.println("DD");
      if (((NodeH)h.get(t)).flag) {
        System.out.println("BLAAAAACK" + t + "   " + h.size());
        g.setColor(Color.BLACK);
        g.drawLine((int)((NodeH)h.get(t)).h1.getP1().getX() * 4 + 50, 500 - (int)((NodeH)h.get(t)).h1.getP1().getY() * 4 - 50, (int)((NodeH)h.get(t)).h1.getP2().getX() * 4 + 50, 500 - (int)((NodeH)h.get(t)).h1.getP2().getY() * 4 - 50);
      } else {
        System.out.println("white");
        g.setColor(getBackground());
        g.drawLine((int)((NodeH)h.get(t)).h1.getP1().getX() * 4 + 50, 500 - (int)((NodeH)h.get(t)).h1.getP1().getY() * 4 - 50, (int)((NodeH)h.get(t)).h1.getP2().getX() * 4 + 50, 500 - (int)((NodeH)h.get(t)).h1.getP2().getY() * 4 - 50);
      } 
    } 
  }
  
  public GUI1() {
    setDefaultCloseOperation(3);
    setBounds(0, 0, 500, 500);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    this.contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(this.contentPane);
    addKeyListener(new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            System.out.println("FFFF");
            GUI1.i = GUI1.i + 1;
            GUI1.this.repaint();
          }
        });
  }
}
