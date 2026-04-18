import java.util.ArrayList;
import java.util.EmptyStackException;

public class StackUsingDArrays<E> {

  private ArrayList<E> list;

  StackUsingDArrays(){
    this.list = new ArrayList<>();
  }

  public void push(E data){
    list.add(data);
  }
  public E pop(){
    if(list.isEmpty()) throw new EmptyStackException();
    E value = list.remove(list.size() -1);
    return value;
  }

  public E peek(){
    if(list.isEmpty()) throw new EmptyStackException();
    return list.get(list.size()-1);
  }
  public boolean isEmpty(){
    return list.isEmpty();
  }
  public int size(){
    return list.size();
  }
  public void display(){
    for(int i = list.size()-1; i>=0; i--){
      System.out.print(list.get(i) + " ");
    }
  }
}
class StackArrayMain{
  public static void main(String[] args) {
    StackUsingDArrays<Integer> stack = new StackUsingDArrays<>();
    stack.push(0);
    stack.push(2);
    stack.push(3);
    System.out.print(stack.pop());
    System.out.println();
    stack.push(5);
    stack.push(6);
    stack.display();

  }
}
