package alg;

public class LinkedPQ<T> {
  private PQNode<T> head = null;
  
  private int size = 0;
  
  public int length() {
    return this.size;
  }
  
  public boolean full() {
    return false;
  }
  
  public void toArray(Object[] t) {
    PQNode<T> tmp = this.head;
    int i = 0;
    while (tmp != null) {
      t[i++] = tmp.data;
      tmp = tmp.next;
    } 
  }
  
  public void enqueue(T e, int pty) {
    PQNode<T> tmp = new PQNode<>(e, pty);
    if (this.size == 0 || pty < this.head.priority) {
      tmp.next = this.head;
      this.head = tmp;
    } else {
      PQNode<T> p = this.head;
      PQNode<T> q = null;
      while (p != null && pty >= p.priority) {
        q = p;
        p = p.next;
      } 
      tmp.next = p;
      q.next = tmp;
    } 
    this.size++;
  }
  
  public PQNode<T> serve() {
    PQNode<T> node = this.head;
    this.head = this.head.next;
    this.size--;
    return node;
  }
}
