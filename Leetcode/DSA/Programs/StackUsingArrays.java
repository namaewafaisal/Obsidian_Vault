import java.util.ArrayList;

public class StackUsingArrays<E> {

  ArrayList<E> list;
  int top;

  StackUsingArrays(){
    top = -1;
    list = new ArrayList<>();
  }

  public void push(E data){
    top++;
    list.add(data);
  }
  public E pop(){
    E value = list.removeLast();
    top--;
    return value;
  }
  public void display(){
    for(int i = top; i>=0; i--){
      System.out.print(list.get)
    }
  }
}
class StackArrayMain{
  public static void main(String[] args) {
    StackUsingArrays<Integer> stack = new StackUsingArrays<>();
    stack.push(0);
    stack.push(2);
    stack.push(3);
    System.out.print(stack.pop());
  }
}
