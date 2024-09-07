package alg;

public class PQNode<T> {
  public T data;
  
  public int priority;
  
  public PQNode<T> next;
  
  public PQNode() {
    this.next = null;
  }
  
  public PQNode(T e, int p) {
    this.data = e;
    this.priority = p;
  }
  
  public T getData() {
    return this.data;
  }
  
  public void setData(T data) {
    this.data = data;
  }
  
  public int getPriority() {
    return this.priority;
  }
  
  public void setPriority(int priority) {
    this.priority = priority;
  }
  
  public PQNode<T> getNext() {
    return this.next;
  }
  
  public void setNext(PQNode<T> next) {
    this.next = next;
  }
}
