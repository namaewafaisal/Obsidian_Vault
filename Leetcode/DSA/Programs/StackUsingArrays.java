public class StackUsingArrays<E> {
    private E arr[];
    private int top;
    private int capacity;

    StackUsingArrays(int capacity){
        this.top = -1;
        this.arr = new E[capacity];
        this.capacity = capacity;
    }
}
class StackUsingArraysMain{
    public static void main(String[] args) {
        StackUsingArrays<Integer> stack = new StackUsingArrays<>(10);
    }
}