import java.util.EmptyStackException;

public class StackUsingArrays<E> {
    private E arr[];
    private int top;
    private int capacity;

    public StackUsingArrays(int capacity){
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity must be positive");
        this.top = -1;
        this.arr = (E[]) new Object[capacity];
        this.capacity = capacity;
    }

    public boolean isEmpty(){
        return top == -1;
    }
    public boolean isFull(){
        return top == capacity-1;
    }
    public int size(){
        return top+1;
    }
    public int capacity(){
        return capacity;
    }
    public void push(E data){
        if(isFull()) throw new StackOverflowError();
        top++;
        arr[top] = data;
    }
    public E pop(){
        if(isEmpty()) throw new EmptyStackException();
        E value = arr[top];
        arr[top] = null;
        top--;
        return value;
    }
    public E peek(){
        if(isEmpty()) throw new EmptyStackException();
        return arr[top];
    }
    public void display(){
        if(isEmpty()) return;
        for(int i = top; i>=0; i--){
            System.out.print(arr[i] + " ");
        }
    }
}
class StackUsingArraysMain{
    public static void main(String[] args) {
        StackUsingArrays<Integer> stack = new StackUsingArrays<>(0);
        System.out.println(stack.size());
        System.out.println(stack.capacity());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.display();
        System.out.println(stack.isEmpty());
        System.out.println(stack.isFull());
    }
}