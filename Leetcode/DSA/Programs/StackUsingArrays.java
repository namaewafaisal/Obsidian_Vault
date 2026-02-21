import java.util.EmptyStackException;

public class StackUsingArrays<E> {
    private E arr[];
    private int top;
    private int capacity;

    StackUsingArrays(int capacity){
        this.top = -1;
        this.arr = (E[]) new Object[capacity];
        this.capacity = capacity;
    }

    public boolean isEmpty(){
        return top <= -1;
    }
    public boolean isFull(){
        return top == capacity-1;
    }
    public void push(E data){
        if(isFull()) throw new StackOverflowError();
        top++;
        arr[top] = data;
    }
    public E pop(){
        if(isEmpty()) throw new EmptyStackException();
        return arr[top--];
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
        StackUsingArrays<Integer> stack = new StackUsingArrays<>(10);
        stack.
    }
}