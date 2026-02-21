import java.util.LinkedList;

public class StackUsingList<E> {
    private LinkedList<E> stack;
    

    public StackUsingList (){
        stack = new LinkedList<>();
    }
    public boolean isEmpty(){
        return stack.isEmpty();
    }
    public int size(){
        return stack.size();
    }
    public void push(E data){
        stack.add(data)
    }
    public E peek(){
        return stack.getFirst();
    }


}
class StackUsingListMain{
    public static void main(String[] args) {
        StackUsingList<Integer> stack = new StackUsingList<>();

    }
}